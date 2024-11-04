package com.example.projeto_criar_personagem.CriarPersonagem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projeto_criar_personagem.R // Importar corretamente o R do seu pacote

class PersonagemFragment(private val tituloParametro: String) : Fragment() {

    private lateinit var EDT_VALOR_ATRIBUTO: EditText
    private lateinit var LB_MSG_RESTO: TextView
    private lateinit var LB_CONSTITUICAO: TextView
    private var LISTENER_FRAGMENT_INTERACTION: OnFragmentInteractionListener? = null

    // Essa interface é responsavel por transacionar os itens do fragmento pra activity
    interface OnFragmentInteractionListener {
        fun onFragmentViewCreated(fragment: PersonagemFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            LISTENER_FRAGMENT_INTERACTION = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_personagem, container, false)

        EDT_VALOR_ATRIBUTO = view.findViewById(R.id.edtValorAtributo)
        LB_MSG_RESTO = view.findViewById(R.id.lblRestoValor)

        LB_CONSTITUICAO = view.findViewById(R.id.lblNomeConstituicao)
        LB_CONSTITUICAO.text = tituloParametro


        // Listener do botão
        val btnProximo: Button = view.findViewById(R.id.btnProximo)
        btnProximo.setOnClickListener {
            val valorAtributo = EDT_VALOR_ATRIBUTO.text.toString()
            if (valorAtributo.isEmpty()) {
                Toast.makeText(requireContext(), "Informe um valor de atributo", Toast.LENGTH_SHORT).show()
            } else {
                (activity as ActvCriarPersonagem).navegarParaProximoFragmento(
                    "Atributos", nomeAtributo = tituloParametro, valorAtributo = getValorAtributo()
                )
            }
        }

        // Notifica a atividade que o fragmento foi criado
        LISTENER_FRAGMENT_INTERACTION?.onFragmentViewCreated(this)
        return view
    }

    fun atualizarTextoResto(novoTexto: String) {

        LB_MSG_RESTO.text = novoTexto
    }

    fun getValorAtributo(): String {
        return EDT_VALOR_ATRIBUTO.text.toString()
    }

}