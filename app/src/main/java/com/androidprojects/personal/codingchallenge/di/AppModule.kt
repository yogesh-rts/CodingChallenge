package com.androidprojects.personal.codingchallenge.di

import android.app.Application
import androidx.room.Room
import com.androidprojects.personal.codingchallenge.api.ApiRequest
import com.androidprojects.personal.codingchallenge.data.StoryDatabase
import com.androidprojects.personal.codingchallenge.data.UserTypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/*
* @AppModule, defines the instantiation of BB and Retrofit instance for API request and response callbacks
* And maintained singleton pattern
*/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp = OkHttpClient.Builder()
        .connectTimeout(10,TimeUnit.SECONDS)
        .readTimeout(2,TimeUnit.SECONDS)
        .writeTimeout(2, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .addInterceptor(logger).build()

//    private val typeConverterInstance = UserTypeConverters()

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiRequest.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()

    @Provides
    @Singleton
    fun provideStoryApi(retrofit: Retrofit): ApiRequest =
        retrofit.create(ApiRequest::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): StoryDatabase =
        Room.databaseBuilder(app,StoryDatabase::class.java,"story_database")
            .build()
}