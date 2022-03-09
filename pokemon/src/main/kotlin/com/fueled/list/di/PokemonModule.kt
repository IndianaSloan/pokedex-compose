package com.fueled.list.di

import com.fueled.list.data.PokemonService
import com.fueled.list.domain.PokemonRepository
import com.fueled.list.domain.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {

    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService = retrofit.create()
}
