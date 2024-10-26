package com.example.projeto_criar_personagem.Servicos

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.util.concurrent.Executors

class MyForegroundService : Service(){

    private var context: Context? = null
    private val NOTIFICATION_ID = 1
    private val CHANNEL_ID = "100"
    private var isDestroyed = false


    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startForeground(NOTIFICATION_ID,showNotification("esse é o content"))
    }


    fun showNotification(content: kotlin.String):Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,"Forefground notification",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }

        return NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Foreground service")
            .setContentText(content)
            .setOnlyAlertOnce(true)
            .setSmallIcon(R.drawable.star_on)
            .build()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(context,"starting service...",Toast.LENGTH_SHORT).show()
        doTask()
        return super.onStartCommand(intent, flags, startId)
    }


    fun doTask() {
        val data = IntArray(1)
        val executorService = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executorService.execute{
        for (i in 0.. 99){
                if(isDestroyed){
                    break
                }
                data[0] = i
                try{
                    handler.post{
                        updateNotification(data[0].toString())
                    }
                    Thread.sleep(5000)
                }catch (e: InterruptedException){
                        e.printStackTrace()
                }
            }
        }
    }


    fun updateNotification(data: String) {
        val notification: Notification = showNotification(data)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as
            NotificationManager
        notificationManager.notify(NOTIFICATION_ID,notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        isDestroyed = true
        Toast.makeText(context,"stopping service...",Toast.LENGTH_SHORT).show()

    }

}