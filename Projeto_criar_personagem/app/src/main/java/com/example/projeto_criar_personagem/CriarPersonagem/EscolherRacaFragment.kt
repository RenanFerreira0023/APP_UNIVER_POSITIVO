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

    private lateinit var spinnerRacas: Spinner
    private lateinit var btnProximo: Button
    private lateinit var editNomePersonagem: EditText // Declare a variável para EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_escolher_raca, container, false)

        // Inicializa o Spinner, o Button e o EditText usando os IDs corretos
        spinnerRacas = view.findViewById(R.id.spinnerRacas)
        btnProximo = view.findViewById(R.id.btnProximo)
        editNomePersonagem = view.findViewById<EditText>(R.id.editNomePersonagem) // Use o ID correto aqui

        // Preencher o Spinner com dados aleatórios
        val racas = GLOBAL_UTIL.mostrar_menu_personagem()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, racas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRacas.adapter = adapter

        // Configurar o clique do botão
        btnProximo.setOnClickListener {
            val indiceRacaEscolhida = spinnerRacas.selectedItemPosition // Obtém o índice do item selecionado
            val nomeRacaEscolhido = GLOBAL_UTIL.nome_raca_por_id(indiceRacaEscolhida + 1)

            // Obter o valor do EditText
            val nomePersonagem = editNomePersonagem.text.toString() // Converte o texto para String

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
