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
    lateinit var notificationSimples:NotificationSimples

    private lateinit var binding: ActivityMainBinding
    var foregroundServiceIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        val btnCriarPersonagem: Button = findViewById(R.id.btnCriarPersonagem)
        val btnListarPersonagem: Button = findViewById(R.id.btnListarPersonagem)

        btnCriarPersonagem.setOnClickListener(this)
        btnListarPersonagem.setOnClickListener(this)

        /////////////////////////////////////////////
        /////////////////////////////////////////////

        val btnNotificacaoSimples: Button =findViewById(R.id.notificacaoSimples)
        notificationSimples= NotificationSimples(this)
        btnNotificacaoSimples.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)
            {
                val permision_code=11
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),permision_code)
                }
            }else{
                notificationSimples.initNotification()
            }
        }


        /////////////////////////////////////////////
        /////////////////////////////////////////////

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView( view)
        foregroundServiceIntent = Intent(this, MyForegroundService::class.java)

        val btnStart: Button = findViewById(R.id.btnStart)
        btnStart.setOnClickListener {
            startService(foregroundServiceIntent)
        }

        val btnStop: Button = findViewById(R.id.btnStop)
        btnStop.setOnClickListener {
            stopService(foregroundServiceIntent)

        }



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
        }
    }
}