package com.fueled.list.presentation.detail.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.fueled.core_ui.theme.Dimens
import com.fueled.list.domain.model.PokemonStat

@Composable
fun StatisticBar(statistic: PokemonStat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = Dimens.PaddingFourth)
    ) {

        Text(text = statistic.name, modifier = Modifier.padding(start = Dimens.PaddingDefault))

        Canvas(modifier = Modifier.fillMaxWidth()) {
            val progressWidth = size.width * statistic.strength
            drawLine(
                color = Color.White,
                cap = StrokeCap.Round,
                start = Offset(100F, 40F),
                end = Offset(size.width - 100F, 40F),
                strokeWidth = 60.0F
            )
            drawLine(
                color = statistic.color,
                cap = StrokeCap.Round,
                start = Offset(100F, 40F),
                end = Offset(progressWidth, 40F),
                strokeWidth = 60.0F
            )
            drawIntoCanvas { canvas ->
                val isInlined = progressWidth < 190
                val textX = if (!isInlined) {
                    progressWidth - 50F
                } else {
                    progressWidth + 100F
                }
                val paint = Paint().apply {
                    textAlign = Paint.Align.CENTER
                    textSize = 36F
                    isFakeBoldText = true
                    color = if (!isInlined) 0xFFFFFFFF.toInt() else 0xFF000000.toInt()
                }
                canvas.nativeCanvas.drawText(
                    "${statistic.rawValue}/200",
                    textX,
                    center.y + 53F,
                    paint
                )
            }
        }
    }
}