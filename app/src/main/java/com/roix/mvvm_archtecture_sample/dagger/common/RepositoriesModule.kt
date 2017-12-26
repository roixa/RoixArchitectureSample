package com.roix.mvvm_archtecture_sample.dagger.common

import android.content.Context
import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import com.roix.mvvm_archtecture_sample.data.repositories.server.ServerApi
import com.roix.mvvm_archtecture_sample.data.repositories.server.ServerRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by roix on 28.11.2017.
 */
@Module
@Singleton
class RepositoriesModule {


    @Provides fun provideOkHttpClient(context: Context): OkHttpClient {
        return ServerApi.ServerFactory.createOkHttpClient(context)
    }

    @Provides fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return ServerApi.ServerFactory.createInstance(okHttpClient)
    }

    @Provides fun provideServerApi(retrofit: Retrofit): ServerApi {
        return retrofit.create(ServerApi::class.java)
    }

    @Provides fun provideServerRepository(serverApi: ServerApi): IServerRepository = ServerRepository(serverApi)

}