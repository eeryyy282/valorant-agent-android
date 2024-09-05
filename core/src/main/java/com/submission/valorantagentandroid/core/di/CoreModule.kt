package com.submission.valorantagentandroid.core.di

import androidx.room.Room
import com.submission.valorantagentandroid.core.BuildConfig
import com.submission.valorantagentandroid.core.data.repository.NewsRepository
import com.submission.valorantagentandroid.core.data.repository.SettingRepository
import com.submission.valorantagentandroid.core.data.source.local.LocalDataSource
import com.submission.valorantagentandroid.core.data.source.local.pref.SettingPreference
import com.submission.valorantagentandroid.core.data.source.local.pref.dataStore
import com.submission.valorantagentandroid.core.data.source.local.room.agent.AgentDatabase
import com.submission.valorantagentandroid.core.data.source.local.room.news.NewsDatabase
import com.submission.valorantagentandroid.core.data.source.remote.AgentRemoteDataSource
import com.submission.valorantagentandroid.core.data.source.remote.NewsRemoteDataSource
import com.submission.valorantagentandroid.core.data.source.remote.network.AgentApiService
import com.submission.valorantagentandroid.core.data.source.remote.network.NewsApiService
import com.submission.valorantagentandroid.core.domain.repository.IAgentRepository
import com.submission.valorantagentandroid.core.domain.repository.INewsRepository
import com.submission.valorantagentandroid.core.domain.repository.ISettingRepository
import com.submission.valorantagentandroid.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AgentDatabase>().agentDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AgentDatabase::class.java, "Agent.db"
        ).fallbackToDestructiveMigration().build()
    }

    factory { get<NewsDatabase>().newsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "News.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_VALORANT_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(AgentApiService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_NEWS_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(NewsApiService::class.java)
    }
}


val repositoryModule = module {
    single { LocalDataSource(get(), get(), get()) }
    single { AgentRemoteDataSource(get()) }
    single { NewsRemoteDataSource(get()) }
    single { androidContext().dataStore }
    single { SettingPreference(get()) }
    factory { AppExecutors() }
    single<IAgentRepository> {
        com.submission.valorantagentandroid.core.data.repository.AgentRepository(
            get(),
            get(),
            get()
        )
    }
    single<INewsRepository> { NewsRepository(get(), get()) }
    single<ISettingRepository> { SettingRepository(get()) }

}