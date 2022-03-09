package com.fueled.list.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.fueled.core.data.ApiConst
import com.fueled.core_ui.extension.center
import com.fueled.core_ui.extension.centerBottom
import com.fueled.core_ui.extension.clickable
import com.fueled.core_ui.theme.Dimens
import com.fueled.list.domain.model.PokemonPreview

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun PokemonCard(
    pokemonPreview: PokemonPreview,
    modifier: Modifier = Modifier,
    onPokemonSelected: (String) -> Unit = { _ -> },
) {
    Card(
        modifier = modifier
            .padding(Dimens.PaddingTwoThirds)
            .fillMaxWidth(0.5F)
            .aspectRatio(1F, false)
            .clickable { onPokemonSelected(pokemonPreview.id) },
        shape = RoundedCornerShape(12.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
                .background(Color.White.copy(alpha= 0.1F)),
        ) {
            val (imageRef, textRef) = createRefs()
            Image(
                modifier = Modifier
                    .size(Dimens.PokemonImageSize)
                    .constrainAs(imageRef) { center() },
                painter = rememberImagePainter(pokemonPreview.imageUrl),
                contentDescription = "Pokemon Image",
            )
            Text(
                style = MaterialTheme.typography.h1,
                modifier = Modifier.constrainAs(textRef) { centerBottom() }
                    .padding(bottom = Dimens.PaddingHalf),
                text = pokemonPreview.name,
            )
        }
    }
}

@Preview(showSystemUi = true, apiLevel = 26)
@Composable
private fun PokemonCardPreview() {
    val previewPokemon = PokemonPreview("1", "Bulbasaur", ApiConst.BASE_IMAGE_URL.format("1"))
    PokemonCard(pokemonPreview = previewPokemon)
}
