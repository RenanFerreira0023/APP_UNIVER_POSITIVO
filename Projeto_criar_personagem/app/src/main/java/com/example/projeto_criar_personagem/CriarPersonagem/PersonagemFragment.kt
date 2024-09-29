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
import org.appsskilldeveloper.personagem.NovoPersonagem

class PersonagemFragment(private val titulo: String) : Fragment() {

    private lateinit var edtValorAtributo: EditText
    private lateinit var lbMsgResto: TextView
    private lateinit var lbConstituicao: TextView

    interface OnFragmentInteractionListener {
        fun onFragmentViewCreated(fragment: PersonagemFragment)
    }

    private var listener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_personagem, container, false)

        // Inicializa as views
        lbConstituicao = view.findViewById(R.id.lblNomeConstituicao)
        edtValorAtributo = view.findViewById(R.id.edtValorAtributo)
        lbMsgResto = view.findViewById(R.id.lblRestoValor)
        val btnProximo: Button = view.findViewById(R.id.btnProximo)

        // Configura o título do fragmento
        lbConstituicao.text = titulo


        // Listener do botão
        btnProximo.setOnClickListener {
            val valorAtributo = edtValorAtributo.text.toString()
            if (valorAtributo.isEmpty()) {
                Toast.makeText(requireContext(), "Informe um valor de atributo", Toast.LENGTH_SHORT).show()
            } else {
                (activity as ActvCriarPersonagem).navegarParaProximoFragmento(
                    "Atributos", nomeAtributo = titulo, valorAtributo = getValorAtributo()
                )
            }
        }

        // Notifica a atividade que o fragmento foi criado
        listener?.onFragmentViewCreated(this)

        return view
    }

    fun atualizarTextoResto(novoTexto: String) {

        lbMsgResto.text = novoTexto
    }



    fun getValorAtributo(): String {
        return edtValorAtributo.text.toString()
    }



}