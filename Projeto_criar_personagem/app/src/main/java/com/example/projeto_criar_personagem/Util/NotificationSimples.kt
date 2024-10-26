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
    private val notificationChannel = "Notification"
    private val notificationId = 1000
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private var request = 0

    fun initNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                notificationChannel,
                "Notificações de Botão",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Layout personalizado da notificação
        val customView = RemoteViews(context.packageName, R.layout.notilay)
        customView.setOnClickPendingIntent(R.id.btn1, getBroadcast("Oi !!"))
        customView.setTextViewText(R.id.text1, "Título da Notificação")
        customView.setTextViewText(R.id.text2, "Mensagem de teste da notificação")

        // Configuração do NotificationCompat.Builder
        val builder = NotificationCompat.Builder(context, notificationChannel)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle()) // Estilo decorado para layout personalizado
            .setCustomContentView(customView) // Define o layout personalizado
            .setAutoCancel(true) // Fecha a notificação ao clicar

        // Exibe a notificação
        notificationManager.notify(notificationId, builder.build())
    }

    private fun getBroadcast(msg: String): PendingIntent {
        val intent = Intent(context, NotificationSimplesRecevier::class.java).apply {
            action = "BTN_ACTION"
            putExtra("msg", msg)
        }
        request++
        return PendingIntent.getBroadcast(context, request, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}