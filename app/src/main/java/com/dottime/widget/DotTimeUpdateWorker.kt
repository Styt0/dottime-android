package com.dottime.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class DotTimeUpdateWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        DotTimeWidget().updateAll(applicationContext)
        return Result.success()
    }
}
