package com.example.projeto_criar_personagem.CriarPersonagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projeto_criar_personagem.R

class FinalizarFragment : Fragment() {

    private lateinit var textoFinal: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finalizar, container, false)

        textoFinal = view.findViewById(R.id.textoFinal)
        val btnFinalizar = view.findViewById<Button>(R.id.btnFinalizar)

        btnFinalizar.setOnClickListener {
            // Finalize a atividade ou faça outra ação
            (activity as ActvCriarPersonagem).navegarParaProximoFragmento("Finalizar")
            activity?.finish()
        }

        return view
    }

    fun setDados(jsonDados : String) {
        // Verifique se os dados não estão vazios
            textoFinal.text = jsonDados
    }
}
