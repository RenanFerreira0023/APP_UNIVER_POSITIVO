package com.example.projeto_criar_personagem.CriarPersonagem

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projeto_criar_personagem.R
import org.appsskilldeveloper.GLOBAL_UTIL

class EscolherRacaFragment : Fragment() {

    private lateinit var SPN_RACAS: Spinner
    private lateinit var BTN_PROXIMO: Button
    private lateinit var EDT_NOME_PERSONAGEM: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_escolher_raca, container, false)

        /////////////////////////////////////////////////
        SPN_RACAS = view.findViewById(R.id.spinnerRacas)
        BTN_PROXIMO = view.findViewById(R.id.btnProximo)
        EDT_NOME_PERSONAGEM = view.findViewById<EditText>(R.id.editNomePersonagem)

        // Preencher o Spinner com dados de raça
        val racas = GLOBAL_UTIL.mostrar_menu_personagem()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, racas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SPN_RACAS.adapter = adapter

        // Configurar o clique do botão
        BTN_PROXIMO.setOnClickListener {
            val indiceRacaEscolhida = SPN_RACAS.selectedItemPosition
            val nomeRacaEscolhido = GLOBAL_UTIL.nome_raca_por_id(indiceRacaEscolhida + 1)


            val nomePersonagem = EDT_NOME_PERSONAGEM.text.toString()

            if (nomePersonagem.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Informe o nome de um personagem",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                (activity as? ActvCriarPersonagem)?.navegarParaProximoFragmento(
                    "EscolherRaca",
                    nomeRaca = nomeRacaEscolhido,
                    nomePersonagem = nomePersonagem
                )
            }
        }

        return view
    }
}
