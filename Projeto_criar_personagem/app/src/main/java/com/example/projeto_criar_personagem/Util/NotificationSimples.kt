package com.example.projeto_criar_personagem.Util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.projeto_criar_personagem.R

class NotificationSimples(val context: Context) {
    private val NOTIFICATION_CHANNEL = "Notification"
    private val NOTIFICATI = 1000
    private val NOTIFICATION_MANAGER = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private var LOOP_REQUEST = 0

    fun initNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL,
                "Notificações de Botão",
                NotificationManager.IMPORTANCE_HIGH
            )
            NOTIFICATION_MANAGER.createNotificationChannel(channel)
        }

        // Layout personalizado da notificação
        val customView = RemoteViews(context.packageName, R.layout.notilay)
        customView.setOnClickPendingIntent(R.id.btn1, getBroadcast("Oi !!"))
        customView.setTextViewText(R.id.text1, "Título da Notificação")
        customView.setTextViewText(R.id.text2, "Mensagem de teste da notificação")

        // Configuração do NotificationCompat.Builder
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle()) // Estilo decorado para layout personalizado
            .setCustomContentView(customView) // Define o layout personalizado
            .setAutoCancel(true) // Fecha a notificação ao clicar

        // Exibe a notificação
        NOTIFICATION_MANAGER.notify(NOTIFICATI, builder.build())
    }

    private fun getBroadcast(msg: String): PendingIntent {
        val intent = Intent(context, NotificationSimplesRecevier::class.java).apply {
            action = "BTN_ACTION"
            putExtra("msg", msg)
        }
        LOOP_REQUEST++
        return PendingIntent.getBroadcast(context, LOOP_REQUEST, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}