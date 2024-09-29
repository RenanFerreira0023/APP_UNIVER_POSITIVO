package com.example.projeto_criar_personagem.ListarPersonagens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_criar_personagem.Database.DatabaseHelper
import com.example.projeto_criar_personagem.Database.PersonagemAdapter
import com.example.projeto_criar_personagem.MainActivity
import com.example.projeto_criar_personagem.R
class ActvListarPersonagens : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonagemAdapter
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actv_listar_personagens)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar o RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar o DatabaseHelper
        dbHelper = DatabaseHelper(this)

        // Recuperar todos os personagens do banco de dados
        val personagens = dbHelper.getAllPersonagens()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PersonagemAdapter(personagens) { personagem ->
            Toast.makeText(this, "Você deletou o personagem  ${personagem.nomePersonagem}", Toast.LENGTH_SHORT).show()
        // deletar esse cara
            dbHelper.deletePersonagem(personagem.id)
        }

        recyclerView.adapter = adapter

        // Configurar o botão de voltar
        val buttonVoltar: Button = findViewById(R.id.button_voltar)
        buttonVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Opcional: fecha esta activity
        }
    }
}