package com.submission.valorantagentandroid.core.di

import androidx.room.Room
import com.submission.valorantagentandroid.core.BuildConfig
import com.submission.valorantagentandroid.core.data.repository.AgentRepository
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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    val passphrase: ByteArray = SQLiteDatabase.getBytes("submission".toCharArray())
    val factory = SupportFactory(passphrase)

    factory { get<AgentDatabase>().agentDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AgentDatabase::class.java,
            "Agent.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    factory { get<NewsDatabase>().newsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            "News.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    single {
        val hostNameValorant = "valorant-api.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostNameValorant, "sha256/Bg/J5Mg1jarbiBnw/2Ds6dMJUN0y/CIYvJHsusU2Ozg=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val hostNameNews = "newsapi.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostNameNews, "sha256/UHYOXs5BxRVKGG7ykhBYGxgne9rRrDaUTXC1MpEtwZU=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    single<IAgentRepository> { AgentRepository(get(), get(), get()) }
    single<INewsRepository> { NewsRepository(get(), get()) }
    single<ISettingRepository> { SettingRepository(get()) }
}
