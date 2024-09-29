package com.example.projeto_criar_personagem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto_criar_personagem.CriarPersonagem.ActvCriarPersonagem
import com.example.projeto_criar_personagem.Database.DatabaseHelper
import com.example.projeto_criar_personagem.ListarPersonagens.ActvListarPersonagens


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnCriarPersonagem: Button = findViewById(R.id.btnCriarPersonagem)
        val btnListarPersonagem: Button = findViewById(R.id.btnListarPersonagem)

        btnCriarPersonagem.setOnClickListener(this)
        btnListarPersonagem.setOnClickListener(this)
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