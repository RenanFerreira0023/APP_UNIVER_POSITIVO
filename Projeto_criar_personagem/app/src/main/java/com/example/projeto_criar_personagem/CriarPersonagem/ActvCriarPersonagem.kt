package com.example.projeto_criar_personagem.CriarPersonagem

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projeto_criar_personagem.Database.DatabaseHelper
import com.example.projeto_criar_personagem.R
import org.appsskilldeveloper.GLOBAL_UTIL
import org.appsskilldeveloper.personagem.NovoPersonagem
import org.appsskilldeveloper.personagem.Racas.*
import org.example.atributos.*

class ActvCriarPersonagem : AppCompatActivity(), PersonagemFragment.OnFragmentInteractionListener {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
    private val REQUEST_CODE_NOTIFICATION_PERMISSION = 100


    lateinit var personagem_X: NovoPersonagem
    private var fragmentIndex = 0
    private var fragmentos = listOf(
        EscolherRacaFragment(),
        PersonagemFragment("Força"),
        PersonagemFragment("Destreza"),
        PersonagemFragment("Inteligência"),
        PersonagemFragment("Carisma"),
        PersonagemFragment("Constituição"),
        PersonagemFragment("Sabedoria"),
        FinalizarFragment()
    )

    private val dadosPersonagem = mutableListOf<String>()


    var GLOBAL_NOME_PERSONAGEM = ""
    // Função para criar e exibir a notificação







    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actv_criar_personagem)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Iniciar com o primeiro fragmento
        navegarParaFragmento(fragmentIndex)






    }


    var VALOR_INICIAL = 27
    var restante = VALOR_INICIAL
    override fun onFragmentViewCreated(fragment: PersonagemFragment) {
        fragment.atualizarTextoResto("Restam $restante pontos")
    }


    private fun navegarParaFragmento(index: Int) {
        if (index >= 0 && index < fragmentos.size) {
            val fragment = fragmentos[index]

            supportFragmentManager.beginTransaction()
                .replace(R.id.main, fragment)
                .commit()
        }
    }


    fun navegarParaProximoFragmento(
        step: String,
        valorAtributo: String = "",
        nomeAtributo: String = "",
        nomeRaca: String = "",
        nomePersonagem: String = ""
    ) {
        if (fragmentIndex < fragmentos.size - 1) {
            // Armazenar o texto do fragmento atual
            val fragment = supportFragmentManager.findFragmentById(R.id.main) as? PersonagemFragment
            fragment?.let {
                dadosPersonagem.add(it.getValorAtributo()) // Adiciona o texto do fragmento atual aos dados
            }

            if (step == "EscolherRaca") {
                GLOBAL_NOME_PERSONAGEM = nomePersonagem
                personagem_X = when (nomeRaca.lowercase()) { // Corrigido aqui
                    "anão" -> NovoPersonagem(RacaAnao())
                    "anão da montanha" -> NovoPersonagem(RacaAnaoDaMontanha())
                    "anão da colina" -> NovoPersonagem(RacaAnaoDaColina())
                    "humano" -> NovoPersonagem(RacaHumano())
                    "draconato" -> NovoPersonagem(RacaDraconato())
                    "meio-orc" -> NovoPersonagem(RacaMeioOrc())
                    "elfo" -> NovoPersonagem(RacaElfo())
                    "alto-elfo" -> NovoPersonagem(RacaAltoElfo())
                    "meio-elfo" -> NovoPersonagem(RacaMeioElfo())
                    "elfo da floresta" -> NovoPersonagem(RacaElfoDaFloresta())
                    "gnomo" -> NovoPersonagem(RacaGnomo())
                    "gnomo das rochas" -> NovoPersonagem(RacaGnomoDasRochas())
                    "gnomo da floresta" -> NovoPersonagem(RacaGnomoDaFloresta())
                    "halfling" -> NovoPersonagem(RacaHalfling())
                    "halfling robusto" -> NovoPersonagem(RacaHalflingRobusto())
                    "halfling pés-leves" -> NovoPersonagem(RacaHalflingPesLeves())
                    "tiefling" -> NovoPersonagem(RacaTiefling())
                    "drow" -> NovoPersonagem(RacaDrow())
                    else -> NovoPersonagem(RacaAnao())
                }
            }

            if (step == "Atributos" && valorAtributo != "") {

                //////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////
                if (nomeAtributo == "Força") {

                    var forcaInput: Int
                    forcaInput = GLOBAL_UTIL.filtro_input_custos(valorAtributo.toInt())
                    val restAux = restante
                    var custoHabilidade = GLOBAL_UTIL.descobrir_custo_de_habilidade(forcaInput)
                    restante = VALOR_INICIAL - custoHabilidade
                    if (restante <= -1) {
                        restante = restAux
                        custoHabilidade = -1
                        var messageToast =
                            ("Ops! -  você não pode distribuir valores tão alto | $restante")
                        Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                        return
                    }
                    personagem_X.aplicar_forca(Forca(forcaInput))


                }
                //////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////
                if (nomeAtributo == "Destreza") {

                    var destrezaInput: Int
                    destrezaInput = GLOBAL_UTIL.filtro_input_custos(valorAtributo.toInt())

                    val restAux = restante
                    var custoHabilidade =
                        GLOBAL_UTIL.descobrir_custo_de_habilidade(destrezaInput)
                    restante = restante - custoHabilidade
                    if (restante <= -1) {
                        restante = restAux
                        custoHabilidade = -1
                        var messageToast =
                            ("Ops! -  você não pode distribuir valores tão alto | $restante")
                        Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                        return
                    }
                    personagem_X.aplicar_destreza(Destreza(destrezaInput))

                }
                //////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////
                if (nomeAtributo == "Inteligência") {

                    var inteligenciaInput: Int
                    inteligenciaInput = GLOBAL_UTIL.filtro_input_custos(valorAtributo.toInt())
                    val restAux = restante
                    var custoHabilidade =
                        GLOBAL_UTIL.descobrir_custo_de_habilidade(inteligenciaInput)
                    restante = restante - custoHabilidade
                    if (restante <= -1) {
                        restante = restAux
                        custoHabilidade = -1
                        var messageToast =
                            ("Ops! -  você não pode distribuir valores tão alto | $restante")
                        Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                        return
                    }
                    personagem_X.aplicar_inteligencia(Inteliencia(inteligenciaInput))

                }


                //////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////
                if (nomeAtributo == "Carisma") {

                    var carismaInput: Int
                    carismaInput = GLOBAL_UTIL.filtro_input_custos(valorAtributo.toInt())
                    val restAux = restante
                    var custoHabilidade =
                        GLOBAL_UTIL.descobrir_custo_de_habilidade(carismaInput)
                    restante = restante - custoHabilidade
                    if (restante <= -1) {
                        restante = restAux
                        custoHabilidade = -1
                        var messageToast =
                            ("Ops! -  você não pode distribuir valores tão alto | $restante")
                        Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                        return
                    }
                    personagem_X.aplicar_carisma(Carisma(carismaInput))
                }
                //////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////
                if (nomeAtributo == "Constituição") {

                    var constituicaoInput: Int
                    constituicaoInput = GLOBAL_UTIL.filtro_input_custos(valorAtributo.toInt())
                    val restAux = restante
                    var custoHabilidade =
                        GLOBAL_UTIL.descobrir_custo_de_habilidade(constituicaoInput)
                    restante = restante - custoHabilidade
                    if (restante <= -1) {
                        restante = restAux
                        custoHabilidade = -1
                        var messageToast =
                            ("Ops! -  você não pode distribuir valores tão alto | $restante")
                        Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                        return
                    }
                    personagem_X.aplicar_constituicao(Constituicao(constituicaoInput))

                }

                //////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////

                if (nomeAtributo == "Sabedoria") {
                    var sabedoriaInput: Int
                    sabedoriaInput = GLOBAL_UTIL.filtro_input_custos(valorAtributo.toInt())
                    val restAux = restante
                    var custoHabilidade = GLOBAL_UTIL.descobrir_custo_de_habilidade(sabedoriaInput)
                    restante = restante - custoHabilidade
                    if (restante <= -1) {
                        restante = restAux
                        custoHabilidade = -1
                        var messageToast =
                            ("Ops! -  você não pode distribuir valores tão alto | $restante")
                        Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                        return
                    }
                    personagem_X.aplicar_sabedoria(Sabedoria(sabedoriaInput))
                }
            }


            fragmentIndex++
            navegarParaFragmento(fragmentIndex)

        } else {

            if (step == "Finalizar") {
                personagem_X.criar_personagem(GLOBAL_NOME_PERSONAGEM)
            }

            val finalizarFragment = fragmentos.last() as FinalizarFragment
            finalizarFragment.setDados("Salvando dados, aguarde ..")

            var databaseHelper = DatabaseHelper(this)
            databaseHelper.insertPersonagem(personagem_X.recuperar_json_dados().toString())


            navegarParaFragmento(fragmentIndex)

        }
    }


}
