package com.dottime.widget

import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

data class TimeProgress(
    val label: String,
    val progress: Float,
    val percentText: String
)

fun calculateAllProgress(): List<TimeProgress> {
    val now = LocalDateTime.now()

    val yearProgress = (now.dayOfYear - 1).toFloat() / now.toLocalDate().lengthOfYear()
    val monthProgress = (now.dayOfMonth - 1).toFloat() / now.toLocalDate().lengthOfMonth()
    val dayProgress = now.hour.toFloat() / 24f
    val hourProgress = now.minute.toFloat() / 60f

    return listOf(
        TimeProgress(
            label = now.year.toString(),
            progress = yearProgress,
            percentText = "${(yearProgress * 100).toInt()}%"
        ),
        TimeProgress(
            label = now.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase(),
            progress = monthProgress,
            percentText = "${(monthProgress * 100).toInt()}%"
        ),
        TimeProgress(
            label = now.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase(),
            progress = dayProgress,
            percentText = "${(dayProgress * 100).toInt()}%"
        ),
        TimeProgress(
            label = "${now.hour}h",
            progress = hourProgress,
            percentText = "${(hourProgress * 100).toInt()}%"
        )
    )
}
