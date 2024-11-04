package com.example.projeto_criar_personagem.Database.Model

data class Personagem(
    val id: Long,
    var nomePersonagem: String,
    val valorXP: Int,
    val nomeRaca: String,
    val Forca: Atributo,
    val Destreza: Atributo,
    val Inteligencia: Atributo,
    val Carisma: Atributo,
    val Constituicao: Atributo,
    val Sabedoria: Atributo,
)

data class Atributo(
    val valorFixo: Int,
    val pontosInputados: Int,
    val valorCusto: Int,
    val valorBonus: Int,
    val valorAtributoFinal: Int,
    val valormodificador: Int
)
