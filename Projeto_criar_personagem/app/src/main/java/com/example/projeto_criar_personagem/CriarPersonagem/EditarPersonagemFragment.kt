package com.example.projeto_criar_personagem.CriarPersonagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projeto_criar_personagem.Database.DatabaseHelper
import com.example.projeto_criar_personagem.Database.Model.Atributo
import com.example.projeto_criar_personagem.Database.Model.Personagem
import com.example.projeto_criar_personagem.R

class EditarPersonagemFragment : Fragment() {

    companion object {
        private const val ARG_PERSONAGEM_ID = "personagemId"
        private lateinit var DB_HELPER: DatabaseHelper
    }


    private var personagemId: Int ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personagemId = arguments?.getInt(ARG_PERSONAGEM_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_personagem, container, false)

        val btnSalvar = view.findViewById<View>(R.id.btn_salvar)
        btnSalvar.setOnClickListener {
            DB_HELPER = DatabaseHelper(requireContext())

            // Captura os valores dos campos de entrada
            val nomePersonagem = view.findViewById<TextView>(R.id.edtNomePersonagem).text.toString()
            val valorXP = view.findViewById<TextView>(R.id.edtValorXP).text.toString().toIntOrNull() ?: 0 // Converte para inteiro ou usa 0 se não for válido
            val nomeRaca = view.findViewById<TextView>(R.id.edtNomeRaca).text.toString()

            // Captura os atributos do personagem
            val forcaFixo = view.findViewById<TextView>(R.id.edtForcaValorFixo).text.toString().toIntOrNull() ?: 0
            val forcaPontosInputados = view.findViewById<TextView>(R.id.edtForcaPontosInputados).text.toString().toIntOrNull() ?: 0
            val forcaValorCusto = view.findViewById<TextView>(R.id.edtForcaValorCusto).text.toString().toIntOrNull() ?: 0
            val forcaValorBonus = view.findViewById<TextView>(R.id.edtForcaValorBonus).text.toString().toIntOrNull() ?: 0
            val forcaValorAtributoFinal = view.findViewById<TextView>(R.id.edtForcaValorAtributoFinal).text.toString().toIntOrNull() ?: 0
            val forcaValorModificador = view.findViewById<TextView>(R.id.edtForcaValorModificador).text.toString().toIntOrNull() ?: 0

            val destrezaFixo = view.findViewById<TextView>(R.id.edtDestrezaValorFixo).text.toString().toIntOrNull() ?: 0
            val destrezaPontosInputados = view.findViewById<TextView>(R.id.edtDestrezaPontosInputados).text.toString().toIntOrNull() ?: 0
            val destrezaValorCusto = view.findViewById<TextView>(R.id.edtDestrezaValorCusto).text.toString().toIntOrNull() ?: 0
            val destrezaValorBonus = view.findViewById<TextView>(R.id.edtDestrezaValorBonus).text.toString().toIntOrNull() ?: 0
            val destrezaValorAtributoFinal = view.findViewById<TextView>(R.id.edtDestrezaValorAtributoFinal).text.toString().toIntOrNull() ?: 0
            val destrezaValorModificador = view.findViewById<TextView>(R.id.edtDestrezaValorModificador).text.toString().toIntOrNull() ?: 0

            val inteligenciaFixo = view.findViewById<TextView>(R.id.edtInteligenciaValorFixo).text.toString().toIntOrNull() ?: 0
            val inteligenciaPontosInputados = view.findViewById<TextView>(R.id.edtInteligenciaPontosInputados).text.toString().toIntOrNull() ?: 0
            val inteligenciaValorCusto = view.findViewById<TextView>(R.id.edtInteligenciaValorCusto).text.toString().toIntOrNull() ?: 0
            val inteligenciaValorBonus = view.findViewById<TextView>(R.id.edtInteligenciaValorBonus).text.toString().toIntOrNull() ?: 0
            val inteligenciaValorAtributoFinal = view.findViewById<TextView>(R.id.edtInteligenciaValorAtributoFinal).text.toString().toIntOrNull() ?: 0
            val inteligenciaValorModificador = view.findViewById<TextView>(R.id.edtInteligenciaValorModificador).text.toString().toIntOrNull() ?: 0

            val carismaFixo = view.findViewById<TextView>(R.id.edtCarismaValorFixo).text.toString().toIntOrNull() ?: 0
            val carismaPontosInputados = view.findViewById<TextView>(R.id.edtCarismaPontosInputados).text.toString().toIntOrNull() ?: 0
            val carismaValorCusto = view.findViewById<TextView>(R.id.edtCarismaValorCusto).text.toString().toIntOrNull() ?: 0
            val carismaValorBonus = view.findViewById<TextView>(R.id.edtCarismaValorBonus).text.toString().toIntOrNull() ?: 0
            val carismaValorAtributoFinal = view.findViewById<TextView>(R.id.edtCarismaValorAtributoFinal).text.toString().toIntOrNull() ?: 0
            val carismaValorModificador = view.findViewById<TextView>(R.id.edtCarismaValorModificador).text.toString().toIntOrNull() ?: 0

            val constituicaoFixo = view.findViewById<TextView>(R.id.edtConstituicaoValorFixo).text.toString().toIntOrNull() ?: 0
            val constituicaoPontosInputados = view.findViewById<TextView>(R.id.edtConstituicaoPontosInputados).text.toString().toIntOrNull() ?: 0
            val constituicaoValorCusto = view.findViewById<TextView>(R.id.edtConstituicaoValorCusto).text.toString().toIntOrNull() ?: 0
            val constituicaoValorBonus = view.findViewById<TextView>(R.id.edtConstituicaoValorBonus).text.toString().toIntOrNull() ?: 0
            val constituicaoValorAtributoFinal = view.findViewById<TextView>(R.id.edtConstituicaoValorAtributoFinal).text.toString().toIntOrNull() ?: 0
            val constituicaoValorModificador = view.findViewById<TextView>(R.id.edtConstituicaoValorModificador).text.toString().toIntOrNull() ?: 0

            val sabedoriaFixo = view.findViewById<TextView>(R.id.edtSabedoriaValorFixo).text.toString().toIntOrNull() ?: 0
            val sabedoriaPontosInputados = view.findViewById<TextView>(R.id.edtSabedoriaPontosInputados).text.toString().toIntOrNull() ?: 0
            val sabedoriaValorCusto = view.findViewById<TextView>(R.id.edtSabedoriaValorCusto).text.toString().toIntOrNull() ?: 0
            val sabedoriaValorBonus = view.findViewById<TextView>(R.id.edtSabedoriaValorBonus).text.toString().toIntOrNull() ?: 0
            val sabedoriaValorAtributoFinal = view.findViewById<TextView>(R.id.edtSabedoriaValorAtributoFinal).text.toString().toIntOrNull() ?: 0
            val sabedoriaValorModificador = view.findViewById<TextView>(R.id.edtSabedoriaValorModificador).text.toString().toIntOrNull() ?: 0


            val personagem = Personagem(
                id = personagemId!!.toLong(),
                nomePersonagem = nomePersonagem,
                valorXP = valorXP,
                nomeRaca = nomeRaca,
                Atributo(forcaFixo, forcaPontosInputados, forcaValorCusto, forcaValorBonus, forcaValorAtributoFinal, forcaValorModificador), // Força
                Atributo(destrezaFixo, destrezaPontosInputados, destrezaValorCusto, destrezaValorBonus, destrezaValorAtributoFinal, destrezaValorModificador), // Destreza
                Atributo(inteligenciaFixo, inteligenciaPontosInputados, inteligenciaValorCusto, inteligenciaValorBonus, inteligenciaValorAtributoFinal, inteligenciaValorModificador), // Inteligência
                Atributo(carismaFixo, carismaPontosInputados, carismaValorCusto, carismaValorBonus, carismaValorAtributoFinal, carismaValorModificador), // Carisma
                Atributo(constituicaoFixo, constituicaoPontosInputados, constituicaoValorCusto, constituicaoValorBonus, constituicaoValorAtributoFinal, constituicaoValorModificador), // Constituição
                Atributo(sabedoriaFixo, sabedoriaPontosInputados, sabedoriaValorCusto, sabedoriaValorBonus, sabedoriaValorAtributoFinal, sabedoriaValorModificador) // Sabedoria
            )


            DB_HELPER.updatePersonagem(personagem)

            Toast.makeText(requireContext(), "Personagem atualizado com sucesso!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()


        }

        return view
    }

















    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Chama a função para carregar os dados do personagem se o ID não for nulo
        personagemId?.let {
            carregarPersonagem(it)
        }
    }

    private fun carregarPersonagem(id: Int ) {
        println("Carregando dados do personagem com ID: $id")

        DB_HELPER = DatabaseHelper(requireContext())

        // Obtém o personagem pelo ID
        val personagem = DB_HELPER.getPersonagemPorId(id.toLong())

        // Aqui você pode atualizar os elementos da UI com os dados do personagem
        if (personagem != null) {
            // Atualiza os campos da UI com os dados do personagem
            val textViewNome = view?.findViewById<TextView>(R.id.edtNomePersonagem)
            val textViewValorXP = view?.findViewById<TextView>(R.id.edtValorXP)
            val textViewRaca = view?.findViewById<TextView>(R.id.edtNomeRaca)

            // Campos de Força
            val edtForcaValorFixo = view?.findViewById<EditText>(R.id.edtForcaValorFixo)
            val edtForcaPontosInputados = view?.findViewById<EditText>(R.id.edtForcaPontosInputados)
            val edtForcaValorCusto = view?.findViewById<EditText>(R.id.edtForcaValorCusto)
            val edtForcaValorBonus = view?.findViewById<EditText>(R.id.edtForcaValorBonus)
            val edtForcaValorAtributoFinal = view?.findViewById<EditText>(R.id.edtForcaValorAtributoFinal)
            val edtForcaValorModificador = view?.findViewById<EditText>(R.id.edtForcaValorModificador)

            // Preenche os campos de Força
            edtForcaValorFixo?.setText(personagem.Forca.valorFixo.toString())
            edtForcaPontosInputados?.setText(personagem.Forca.pontosInputados.toString())
            edtForcaValorCusto?.setText(personagem.Forca.valorCusto.toString())
            edtForcaValorBonus?.setText(personagem.Forca.valorBonus.toString())
            edtForcaValorAtributoFinal?.setText(personagem.Forca.valorAtributoFinal.toString())
            edtForcaValorModificador?.setText(personagem.Forca.valormodificador.toString())

            // Campos de Destreza
            val edtDestrezaValorFixo = view?.findViewById<EditText>(R.id.edtDestrezaValorFixo)
            val edtDestrezaPontosInputados = view?.findViewById<EditText>(R.id.edtDestrezaPontosInputados)
            val edtDestrezaValorCusto = view?.findViewById<EditText>(R.id.edtDestrezaValorCusto)
            val edtDestrezaValorBonus = view?.findViewById<EditText>(R.id.edtDestrezaValorBonus)
            val edtDestrezaValorAtributoFinal = view?.findViewById<EditText>(R.id.edtDestrezaValorAtributoFinal)
            val edtDestrezaValorModificador = view?.findViewById<EditText>(R.id.edtDestrezaValorModificador)

            // Preenche os campos de Destreza
            edtDestrezaValorFixo?.setText(personagem.Destreza.valorFixo.toString())
            edtDestrezaPontosInputados?.setText(personagem.Destreza.pontosInputados.toString())
            edtDestrezaValorCusto?.setText(personagem.Destreza.valorCusto.toString())
            edtDestrezaValorBonus?.setText(personagem.Destreza.valorBonus.toString())
            edtDestrezaValorAtributoFinal?.setText(personagem.Destreza.valorAtributoFinal.toString())
            edtDestrezaValorModificador?.setText(personagem.Destreza.valormodificador.toString())

            // Campos de Inteligência
            val edtInteligenciaValorFixo = view?.findViewById<EditText>(R.id.edtInteligenciaValorFixo)
            val edtInteligenciaPontosInputados = view?.findViewById<EditText>(R.id.edtInteligenciaPontosInputados)
            val edtInteligenciaValorCusto = view?.findViewById<EditText>(R.id.edtInteligenciaValorCusto)
            val edtInteligenciaValorBonus = view?.findViewById<EditText>(R.id.edtInteligenciaValorBonus)
            val edtInteligenciaValorAtributoFinal = view?.findViewById<EditText>(R.id.edtInteligenciaValorAtributoFinal)
            val edtInteligenciaValorModificador = view?.findViewById<EditText>(R.id.edtInteligenciaValorModificador)

            // Preenche os campos de Inteligência
            edtInteligenciaValorFixo?.setText(personagem.Inteligencia.valorFixo.toString())
            edtInteligenciaPontosInputados?.setText(personagem.Inteligencia.pontosInputados.toString())
            edtInteligenciaValorCusto?.setText(personagem.Inteligencia.valorCusto.toString())
            edtInteligenciaValorBonus?.setText(personagem.Inteligencia.valorBonus.toString())
            edtInteligenciaValorAtributoFinal?.setText(personagem.Inteligencia.valorAtributoFinal.toString())
            edtInteligenciaValorModificador?.setText(personagem.Inteligencia.valormodificador.toString())

            // Campos de Carisma
            val edtCarismaValorFixo = view?.findViewById<EditText>(R.id.edtCarismaValorFixo)
            val edtCarismaPontosInputados = view?.findViewById<EditText>(R.id.edtCarismaPontosInputados)
            val edtCarismaValorCusto = view?.findViewById<EditText>(R.id.edtCarismaValorCusto)
            val edtCarismaValorBonus = view?.findViewById<EditText>(R.id.edtCarismaValorBonus)
            val edtCarismaValorAtributoFinal = view?.findViewById<EditText>(R.id.edtCarismaValorAtributoFinal)
            val edtCarismaValorModificador = view?.findViewById<EditText>(R.id.edtCarismaValorModificador)

            // Preenche os campos de Carisma
            edtCarismaValorFixo?.setText(personagem.Carisma.valorFixo.toString())
            edtCarismaPontosInputados?.setText(personagem.Carisma.pontosInputados.toString())
            edtCarismaValorCusto?.setText(personagem.Carisma.valorCusto.toString())
            edtCarismaValorBonus?.setText(personagem.Carisma.valorBonus.toString())
            edtCarismaValorAtributoFinal?.setText(personagem.Carisma.valorAtributoFinal.toString())
            edtCarismaValorModificador?.setText(personagem.Carisma.valormodificador.toString())

            // Campos de Constituição
            val edtConstituicaoValorFixo = view?.findViewById<EditText>(R.id.edtConstituicaoValorFixo)
            val edtConstituicaoPontosInputados = view?.findViewById<EditText>(R.id.edtConstituicaoPontosInputados)
            val edtConstituicaoValorCusto = view?.findViewById<EditText>(R.id.edtConstituicaoValorCusto)
            val edtConstituicaoValorBonus = view?.findViewById<EditText>(R.id.edtConstituicaoValorBonus)
            val edtConstituicaoValorAtributoFinal = view?.findViewById<EditText>(R.id.edtConstituicaoValorAtributoFinal)
            val edtConstituicaoValorModificador = view?.findViewById<EditText>(R.id.edtConstituicaoValorModificador)

            // Preenche os campos de Constituição
            edtConstituicaoValorFixo?.setText(personagem.Constituicao.valorFixo.toString())
            edtConstituicaoPontosInputados?.setText(personagem.Constituicao.pontosInputados.toString())
            edtConstituicaoValorCusto?.setText(personagem.Constituicao.valorCusto.toString())
            edtConstituicaoValorBonus?.setText(personagem.Constituicao.valorBonus.toString())
            edtConstituicaoValorAtributoFinal?.setText(personagem.Constituicao.valorAtributoFinal.toString())
            edtConstituicaoValorModificador?.setText(personagem.Constituicao.valormodificador.toString())

            // Campos de Sabedoria
            val edtSabedoriaValorFixo = view?.findViewById<EditText>(R.id.edtSabedoriaValorFixo)
            val edtSabedoriaPontosInputados = view?.findViewById<EditText>(R.id.edtSabedoriaPontosInputados)
            val edtSabedoriaValorCusto = view?.findViewById<EditText>(R.id.edtSabedoriaValorCusto)
            val edtSabedoriaValorBonus = view?.findViewById<EditText>(R.id.edtSabedoriaValorBonus)
            val edtSabedoriaValorAtributoFinal = view?.findViewById<EditText>(R.id.edtSabedoriaValorAtributoFinal)
            val edtSabedoriaValorModificador = view?.findViewById<EditText>(R.id.edtSabedoriaValorModificador)

            // Preenche os campos de Sabedoria
            edtSabedoriaValorFixo?.setText(personagem.Sabedoria.valorFixo.toString())
            edtSabedoriaPontosInputados?.setText(personagem.Sabedoria.pontosInputados.toString())
            edtSabedoriaValorCusto?.setText(personagem.Sabedoria.valorCusto.toString())
            edtSabedoriaValorBonus?.setText(personagem.Sabedoria.valorBonus.toString())
            edtSabedoriaValorAtributoFinal?.setText(personagem.Sabedoria.valorAtributoFinal.toString())
            edtSabedoriaValorModificador?.setText(personagem.Sabedoria.valormodificador.toString())

            // Atualiza nome, XP e raça
            textViewNome?.text = personagem.nomePersonagem
            textViewValorXP?.text = "${personagem.valorXP}"
            textViewRaca?.text = "${personagem.nomeRaca}"
        } else {
            println("Personagem não encontrado para o ID: $id")
        }
    }




    interface OnFragmentInteractionListener {
        fun onFragmentViewCreated(fragment: PersonagemFragment)
    }


}
