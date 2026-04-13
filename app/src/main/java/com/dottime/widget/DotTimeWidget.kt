package com.dottime.widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import kotlin.math.roundToInt

class DotTimeWidget : GlanceAppWidget() {

    companion object {
        private const val DOT_COUNT = 12
        private val BG_COLOR = Color(0xFF000000)
        private val FILLED_COLOR = Color(0xFFFFFFFF)
        private val CURRENT_COLOR = Color(0xFFFF0000)
        private val EMPTY_COLOR = Color(0xFF333333)
        private val TEXT_COLOR = Color(0xFFFFFFFF)
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val rows = calculateAllProgress()

            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(BG_COLOR)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                rows.forEachIndexed { index, row ->
                    DotRow(row)
                    if (index < rows.lastIndex) {
                        Spacer(modifier = GlanceModifier.height(6.dp))
                    }
                }
            }
        }
    }

    @androidx.compose.runtime.Composable
    private fun DotRow(progress: TimeProgress) {
        val currentIndex = (progress.progress * DOT_COUNT).roundToInt().coerceIn(0, DOT_COUNT)

        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Label
            Text(
                text = progress.label,
                style = TextStyle(
                    color = ColorProvider(TEXT_COLOR),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                ),
                modifier = GlanceModifier.width(42.dp)
            )

            Spacer(modifier = GlanceModifier.width(4.dp))

            // Dots
            Row(
                modifier = GlanceModifier.defaultWeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until DOT_COUNT) {
                    val dotColor = when {
                        i < currentIndex -> FILLED_COLOR
                        i == currentIndex -> CURRENT_COLOR
                        else -> EMPTY_COLOR
                    }
                    Box(
                        modifier = GlanceModifier
                            .size(8.dp)
                            .background(dotColor)
                    ) {}
                    if (i < DOT_COUNT - 1) {
                        Spacer(modifier = GlanceModifier.width(3.dp))
                    }
                }
            }

            Spacer(modifier = GlanceModifier.width(4.dp))

            // Percentage
            Text(
                text = progress.percentText,
                style = TextStyle(
                    color = ColorProvider(TEXT_COLOR),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Monospace
                ),
                modifier = GlanceModifier.width(36.dp)
            )
        }
    }
}
