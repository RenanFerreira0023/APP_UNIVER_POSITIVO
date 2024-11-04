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
import com.example.projeto_criar_personagem.CriarPersonagem.EditarPersonagemFragment
import com.example.projeto_criar_personagem.CriarPersonagem.PersonagemFragment
import com.example.projeto_criar_personagem.Database.DatabaseHelper
import com.example.projeto_criar_personagem.MainActivity
import com.example.projeto_criar_personagem.R
class ActvListarPersonagens : AppCompatActivity() , EditarPersonagemFragment.OnFragmentInteractionListener{
    private lateinit var RECYCLERVIEW: RecyclerView
    private lateinit var DB_HELPER: DatabaseHelper

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
        RECYCLERVIEW = findViewById(R.id.recyclerView)
        RECYCLERVIEW.layoutManager = LinearLayoutManager(this)

        DB_HELPER = DatabaseHelper(this)
        // Recuperar todos os personagens do banco de dados
        val personagens = DB_HELPER.getAllPersonagens()

        val recyclerView: RecyclerView = RECYCLERVIEW
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PersonagemAdapter(personagens,
            ON_DELETE_CLICK = { personagem ->
                Toast.makeText(
                    this,
                    "Você deletou o personagem ${personagem.nomePersonagem}",
                    Toast.LENGTH_SHORT
                ).show()
                DB_HELPER.deletePersonagem(personagem.id)
                recreate()
            },
            ON_EDIT_CLICK = { personagem ->


                val editarFragment = EditarPersonagemFragment()


                val bundle = Bundle()
                bundle.putInt("personagemId", personagem.id.toInt())
                editarFragment.arguments = bundle


                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, editarFragment)
                    .addToBackStack(null)
                    .commit()

            }
        )
        recyclerView.adapter = adapter

        // Configurar o botão de voltar
        val buttonVoltar: Button = findViewById(R.id.button_voltar)
        buttonVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onFragmentViewCreated(fragment: PersonagemFragment) {
        TODO("Not yet implemented")
    }
}