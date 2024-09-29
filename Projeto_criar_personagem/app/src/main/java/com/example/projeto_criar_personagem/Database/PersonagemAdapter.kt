package com.example.projeto_criar_personagem.Database


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_criar_personagem.Database.Model.Personagem
import com.example.projeto_criar_personagem.R

class PersonagemAdapter(
    private val personagens: List<Personagem>,
    private val onPersonagemButtonClicked: (Personagem) -> Unit
) : RecyclerView.Adapter<PersonagemAdapter.PersonagemViewHolder>() {

    class PersonagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomePersonagem: TextView = itemView.findViewById(R.id.textNomePersonagem)
        val valorXP: TextView = itemView.findViewById(R.id.textValorXP)
        val nomeRaca: TextView = itemView.findViewById(R.id.textNomeRaca)
        val buttonAcao: ImageView = itemView.findViewById(R.id.btnDeletarPersonagem)


        // Força
        val forcaValorFixo: TextView = itemView.findViewById(R.id.textForcaValorFixo)
        val forcaPontosInputados: TextView = itemView.findViewById(R.id.textForcaPontosInputados)
        val forcaValorCusto: TextView = itemView.findViewById(R.id.textForcaValorCusto)
        val forcaValorBonus: TextView = itemView.findViewById(R.id.textForcaValorBonus)
        val forcaValorAtributoFinal: TextView = itemView.findViewById(R.id.textForcaValorAtributoFinal)
        val forcaValorModificador: TextView = itemView.findViewById(R.id.textForcaValorModificador)

        // Destreza
        val destrezaValorFixo: TextView = itemView.findViewById(R.id.textDestrezaValorFixo)
        val destrezaPontosInputados: TextView = itemView.findViewById(R.id.textDestrezaPontosInputados)
        val destrezaValorCusto: TextView = itemView.findViewById(R.id.textDestrezaValorCusto)
        val destrezaValorBonus: TextView = itemView.findViewById(R.id.textDestrezaValorBonus)
        val destrezaValorAtributoFinal: TextView = itemView.findViewById(R.id.textDestrezaValorAtributoFinal)
        val destrezaValorModificador: TextView = itemView.findViewById(R.id.textDestrezaValorModificador)

        // Inteligência
        val inteligenciaValorFixo: TextView = itemView.findViewById(R.id.textInteligenciaValorFixo)
        val inteligenciaPontosInputados: TextView = itemView.findViewById(R.id.textInteligenciaPontosInputados)
        val inteligenciaValorCusto: TextView = itemView.findViewById(R.id.textInteligenciaValorCusto)
        val inteligenciaValorBonus: TextView = itemView.findViewById(R.id.textInteligenciaValorBonus)
        val inteligenciaValorAtributoFinal: TextView = itemView.findViewById(R.id.textInteligenciaValorAtributoFinal)
        val inteligenciaValorModificador: TextView = itemView.findViewById(R.id.textInteligenciaValorModificador)

        // Carisma
        val carismaValorFixo: TextView = itemView.findViewById(R.id.textCarismaValorFixo)
        val carismaPontosInputados: TextView = itemView.findViewById(R.id.textCarismaPontosInputados)
        val carismaValorCusto: TextView = itemView.findViewById(R.id.textCarismaValorCusto)
        val carismaValorBonus: TextView = itemView.findViewById(R.id.textCarismaValorBonus)
        val carismaValorAtributoFinal: TextView = itemView.findViewById(R.id.textCarismaValorAtributoFinal)
        val carismaValorModificador: TextView = itemView.findViewById(R.id.textCarismaValorModificador)

        // Constituição
        val constituicaoValorFixo: TextView = itemView.findViewById(R.id.textConstituicaoValorFixo)
        val constituicaoPontosInputados: TextView = itemView.findViewById(R.id.textConstituicaoPontosInputados)
        val constituicaoValorCusto: TextView = itemView.findViewById(R.id.textConstituicaoValorCusto)
        val constituicaoValorBonus: TextView = itemView.findViewById(R.id.textConstituicaoValorBonus)
        val constituicaoValorAtributoFinal: TextView = itemView.findViewById(R.id.textConstituicaoValorAtributoFinal)
        val constituicaoValorModificador: TextView = itemView.findViewById(R.id.textConstituicaoValorModificador)

        // Sabedoria
        val sabedoriaValorFixo: TextView = itemView.findViewById(R.id.textSabedoriaValorFixo)
        val sabedoriaPontosInputados: TextView = itemView.findViewById(R.id.textSabedoriaPontosInputados)
        val sabedoriaValorCusto: TextView = itemView.findViewById(R.id.textSabedoriaValorCusto)
        val sabedoriaValorBonus: TextView = itemView.findViewById(R.id.textSabedoriaValorBonus)
        val sabedoriaValorAtributoFinal: TextView = itemView.findViewById(R.id.textSabedoriaValorAtributoFinal)
        val sabedoriaValorModificador: TextView = itemView.findViewById(R.id.textSabedoriaValorModificador)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personagem, parent, false)
        return PersonagemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        val personagem = personagens[position]

        holder.nomePersonagem.text = personagem.nomePersonagem
        holder.valorXP.text = personagem.valorXP.toString()
        holder.nomeRaca.text = personagem.nomeRaca
        holder.buttonAcao.setOnClickListener {
            onPersonagemButtonClicked(personagem)
        }


        // Força
        holder.forcaValorFixo.text = personagem.Forca.valorFixo.toString()
        holder.forcaPontosInputados.text = personagem.Forca.pontosInputados.toString()
        holder.forcaValorCusto.text = personagem.Forca.valorCusto.toString()
        holder.forcaValorBonus.text = personagem.Forca.valorBonus.toString()
        holder.forcaValorAtributoFinal.text = personagem.Forca.valorAtributoFinal.toString()
        holder.forcaValorModificador.text = personagem.Forca.valormodificador.toString()

        // Destreza
        holder.destrezaValorFixo.text = personagem.Destreza.valorFixo.toString()
        holder.destrezaPontosInputados.text = personagem.Destreza.pontosInputados.toString()
        holder.destrezaValorCusto.text = personagem.Destreza.valorCusto.toString()
        holder.destrezaValorBonus.text = personagem.Destreza.valorBonus.toString()
        holder.destrezaValorAtributoFinal.text = personagem.Destreza.valorAtributoFinal.toString()
        holder.destrezaValorModificador.text = personagem.Destreza.valormodificador.toString()

        // Inteligência
        holder.inteligenciaValorFixo.text = personagem.Inteligencia.valorFixo.toString()
        holder.inteligenciaPontosInputados.text = personagem.Inteligencia.pontosInputados.toString()
        holder.inteligenciaValorCusto.text = personagem.Inteligencia.valorCusto.toString()
        holder.inteligenciaValorBonus.text = personagem.Inteligencia.valorBonus.toString()
        holder.inteligenciaValorAtributoFinal.text = personagem.Inteligencia.valorAtributoFinal.toString()
        holder.inteligenciaValorModificador.text = personagem.Inteligencia.valormodificador.toString()

        // Carisma
        holder.carismaValorFixo.text = personagem.Carisma.valorFixo.toString()
        holder.carismaPontosInputados.text = personagem.Carisma.pontosInputados.toString()
        holder.carismaValorCusto.text = personagem.Carisma.valorCusto.toString()
        holder.carismaValorBonus.text = personagem.Carisma.valorBonus.toString()
        holder.carismaValorAtributoFinal.text = personagem.Carisma.valorAtributoFinal.toString()
        holder.carismaValorModificador.text = personagem.Carisma.valormodificador.toString()

        // Constituição
        holder.constituicaoValorFixo.text = personagem.Constituicao.valorFixo.toString()
        holder.constituicaoPontosInputados.text = personagem.Constituicao.pontosInputados.toString()
        holder.constituicaoValorCusto.text = personagem.Constituicao.valorCusto.toString()
        holder.constituicaoValorBonus.text = personagem.Constituicao.valorBonus.toString()
        holder.constituicaoValorAtributoFinal.text = personagem.Constituicao.valorAtributoFinal.toString()
        holder.constituicaoValorModificador.text = personagem.Constituicao.valormodificador.toString()

        // Sabedoria
        holder.sabedoriaValorFixo.text = personagem.Sabedoria.valorFixo.toString()
        holder.sabedoriaPontosInputados.text = personagem.Sabedoria.pontosInputados.toString()
        holder.sabedoriaValorCusto.text = personagem.Sabedoria.valorCusto.toString()
        holder.sabedoriaValorBonus.text = personagem.Sabedoria.valorBonus.toString()
        holder.sabedoriaValorAtributoFinal.text = personagem.Sabedoria.valorAtributoFinal.toString()
        holder.sabedoriaValorModificador.text = personagem.Sabedoria.valormodificador.toString()
    }

    override fun getItemCount(): Int = personagens.size
}
