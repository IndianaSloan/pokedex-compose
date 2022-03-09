package com.fueled.composetemplate.di

import android.app.Application
import android.content.Context
import com.fueled.composetemplate.base.ResourceProviderImpl
import com.fueled.core.base.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    fun providesResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider =
        resourceProvider
}
