package com.submission.valorantagentandroid.core.di

import androidx.room.Room
import com.submission.valorantagentandroid.BuildConfig
import com.submission.valorantagentandroid.core.data.AgentRepository
import com.submission.valorantagentandroid.core.data.source.local.LocalDataSource
import com.submission.valorantagentandroid.core.data.source.local.room.AgentDatabase
import com.submission.valorantagentandroid.core.data.source.remote.RemoteDataSource
import com.submission.valorantagentandroid.core.data.source.remote.network.ApiService
import com.submission.valorantagentandroid.core.domain.repository.IAgentRepository
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
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_VALORANT_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IAgentRepository> { AgentRepository(get(), get(), get()) }

}