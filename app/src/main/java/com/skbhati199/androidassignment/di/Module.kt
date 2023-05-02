package com.skbhati199.androidassignment.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.skbhati199.androidassignment.api.PhotoService
import com.skbhati199.androidassignment.respository.PhotoRepository
import com.skbhati199.androidassignment.viewmodels.PhotoViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    viewModel { PhotoViewModel(get()) }
}


val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): PhotoService {
        return retrofit.create(PhotoService::class.java)
    }

    single { provideUserApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 20 * 1024 * 1024 // Testing for 20 MB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideChecker(application: Application, cache: Cache): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(ChuckerInterceptor(application.applicationContext))
            .cache(cache)
    }

    fun provideHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpClientBuilder.build()
    }


    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideChecker(androidApplication(), get()) }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get()) }

}

val repositoryModule = module {
    fun provideUserRepository(api: PhotoService): PhotoRepository {
        return PhotoRepository(api)
    }

    single { provideUserRepository(get()) }
}
