package com.fueled.list.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.fueled.core_ui.theme.Dimens
import com.fueled.core_ui.theme.PokedexTypography
import com.fueled.list.domain.model.PokemonType

@Composable
fun PokemonTypesRow(
    modifier: Modifier = Modifier,
    types: List<PokemonType>,
) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        types.map { type ->
            PokemonTypePill(type = type)
        }
    }
}

@Composable
internal fun PokemonTypePill(
    type: PokemonType
) {
    Card(
        modifier = Modifier
            .padding(Dimens.PaddingEighth)
            .wrapContentWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(Dimens.PaddingDefault)
    ) {
        Text(
            modifier = Modifier
                .background(type.color)
                .padding(horizontal = Dimens.PaddingDefault, vertical = Dimens.PaddingFourth),
            text = type.name,
            color = Color.White,
            style = PokedexTypography.h1
        )
    }
}

@Preview(showSystemUi = true, showBackground = true, apiLevel = 26)
@Composable
private fun PokemonTypePillPreview() {
    val types = listOf(
        PokemonType("Dragon", Color(0xFF378A94)),
        PokemonType("Fire", Color(0xFFB22328))
    )
    PokemonTypesRow(types = types)
}
