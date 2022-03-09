package com.fueled.list.di

import com.fueled.list.domain.PokemonRepository
import com.fueled.list.domain.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PokemonRepositoryBinding {

    @Binds
    internal abstract fun bindPokemonRepository(
        pokemonRepository: PokemonRepositoryImpl
    ): PokemonRepository
}
