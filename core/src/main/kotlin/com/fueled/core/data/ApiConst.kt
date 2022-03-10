package com.fueled.core.data

object ApiConst {

    const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val POKEMON_IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/%s.png"
}

object ApiParams {
    const val LIMIT = "limit"
    const val OFFSET = "offset"
}

object ApiFields {
    const val NEXT = "next"
    const val PREVIOUS = "previous"
    const val BASE_STAT = "base_stat"
}

object ApiPaths {
    const val POKEMON_ID = "pokemonId"
}

object Endpoints {
    const val POKEMON = "pokemon"
}