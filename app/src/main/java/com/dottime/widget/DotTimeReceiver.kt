package com.dottime.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class DotTimeReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = DotTimeWidget()

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        scheduleUpdates(context)
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
    }

    companion object {
        private const val WORK_NAME = "dottime_update"

        fun scheduleUpdates(context: Context) {
            val request = PeriodicWorkRequestBuilder<DotTimeUpdateWorker>(
                15, TimeUnit.MINUTES
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
}
