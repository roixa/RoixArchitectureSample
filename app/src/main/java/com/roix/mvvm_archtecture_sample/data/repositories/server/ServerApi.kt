package com.roix.mvvm_archtecture_sample.data.repositories.server

import android.content.Context
import com.roix.mvvm_archtecture_sample.BuildConfig
import com.roix.mvvm_archtecture_sample.data.repositories.server.models.ServerThreadListResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

/**
 * Created by belyalov on 26.12.2017.
 */


interface ServerApi {

    @GET("{board}/{page}" + BuildConfig.SERVER_URL_END)
    fun getThreads(@Path("board") board: String, @Path("page") page: Int): Single<ServerThreadListResponse>

    class ServerFactory {

        companion object {

            fun createOkHttpClient(context: Context): OkHttpClient {
                val okHttpClientBuilder = OkHttpClient.Builder()
                okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS)
                okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
                okHttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
                return okHttpClientBuilder.build()
            }

            fun createInstance(okHttpClient: OkHttpClient): Retrofit {
                return Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BuildConfig.SERVER_ENDPOINT)
                        .client(okHttpClient)
                        .build()
            }
        }
    }
}