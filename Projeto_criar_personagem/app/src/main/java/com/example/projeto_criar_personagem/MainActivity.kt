package com.example.projeto_criar_personagem

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.projeto_criar_personagem.CriarPersonagem.ActvCriarPersonagem
import com.example.projeto_criar_personagem.ListarPersonagens.ActvListarPersonagens
import com.example.projeto_criar_personagem.Servicos.MyForegroundService
import com.example.projeto_criar_personagem.Util.NotificationSimples
import com.example.projeto_criar_personagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var NOTIFICATION_SIMPLES:NotificationSimples

    private lateinit var BINDING: ActivityMainBinding
    var FOREGROUND_SERVICE_INTENT: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        /////////////////////////////////////////////
        NOTIFICATION_SIMPLES = NotificationSimples(this)


        /////////////////////////////////////////////
        FOREGROUND_SERVICE_INTENT = Intent(this, MyForegroundService::class.java)


        /////////////////////////////////////////////
        BINDING = ActivityMainBinding.inflate(layoutInflater)
        val view = BINDING.root
        setContentView( view)
        BINDING.btnCriarPersonagem.setOnClickListener(this)
        BINDING.btnListarPersonagem.setOnClickListener(this)
        BINDING.btnStart.setOnClickListener(this)
        BINDING.btnStop.setOnClickListener(this)
        BINDING.notificacaoSimples.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnCriarPersonagem -> {
                val intent = Intent(this, ActvCriarPersonagem::class.java)
                startActivity(intent)
            }
            R.id.btnListarPersonagem -> {
                val intent = Intent(this, ActvListarPersonagens::class.java)
                startActivity(intent)
            }
            R.id.notificacaoSimples -> {
                if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)
                {
                    val permision_code = 11
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),permision_code)
                    }
                }else{
                    NOTIFICATION_SIMPLES.initNotification()
                }
            }
            R.id.btnStart -> {
                startService(FOREGROUND_SERVICE_INTENT)
            }
            R.id.btnStop -> {
                stopService(FOREGROUND_SERVICE_INTENT)
            }
        }
    }
}