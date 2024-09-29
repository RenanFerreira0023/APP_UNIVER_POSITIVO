package com.example.projeto_criar_personagem.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.projeto_criar_personagem.Database.Model.Atributo
import com.example.projeto_criar_personagem.Database.Model.Personagem
import com.google.gson.Gson


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "rpg_2.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PERSONAGEM = "Personagem"

        // Colunas da tabela
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME_PERSONAGEM = "nomePersonagem"
        private const val COLUMN_VALOR_XP = "valorXP"
        private const val COLUMN_NOME_RACA = "nomeRaca"

        // Atributos

        private const val COLUMN_FORCA_FIXO = "forcaValorFixo"
        private const val COLUMN_FORCA_PONTOS = "forcaPontosInputados"
        private const val COLUMN_FORCA_CUSTO = "forcaValorCusto"
        private const val COLUMN_FORCA_BONUS = "forcaValorBonus"
        private const val COLUMN_FORCA_FINAL = "forcaValorAtributoFinal"
        private const val COLUMN_FORCA_MOD = "forcaValormodificador"

        // Destreza
        private const val COLUMN_DESTREZA_FIXO = "destrezaValorFixo"
        private const val COLUMN_DESTREZA_PONTOS = "destrezaPontosInputados"
        private const val COLUMN_DESTREZA_CUSTO = "destrezaValorCusto"
        private const val COLUMN_DESTREZA_BONUS = "destrezaValorBonus"
        private const val COLUMN_DESTREZA_FINAL = "destrezaValorAtributoFinal"
        private const val COLUMN_DESTREZA_MOD = "destrezaValormodificador"

        // Inteligência
        private const val COLUMN_INTELIGENCIA_FIXO = "inteligenciaValorFixo"
        private const val COLUMN_INTELIGENCIA_PONTOS = "inteligenciaPontosInputados"
        private const val COLUMN_INTELIGENCIA_CUSTO = "inteligenciaValorCusto"
        private const val COLUMN_INTELIGENCIA_BONUS = "inteligenciaValorBonus"
        private const val COLUMN_INTELIGENCIA_FINAL = "inteligenciaValorAtributoFinal"
        private const val COLUMN_INTELIGENCIA_MOD = "inteligenciaValormodificador"

        // Carisma
        private const val COLUMN_CARISMA_FIXO = "carismaValorFixo"
        private const val COLUMN_CARISMA_PONTOS = "carismaPontosInputados"
        private const val COLUMN_CARISMA_CUSTO = "carismaValorCusto"
        private const val COLUMN_CARISMA_BONUS = "carismaValorBonus"
        private const val COLUMN_CARISMA_FINAL = "carismaValorAtributoFinal"
        private const val COLUMN_CARISMA_MOD = "carismaValormodificador"

        // Constituição
        private const val COLUMN_CONSTITUICAO_FIXO = "constitucaoValorFixo"
        private const val COLUMN_CONSTITUICAO_PONTOS = "constitucaoPontosInputados"
        private const val COLUMN_CONSTITUICAO_CUSTO = "constitucaoValorCusto"
        private const val COLUMN_CONSTITUICAO_BONUS = "constitucaoValorBonus"
        private const val COLUMN_CONSTITUICAO_FINAL = "constitucaoValorAtributoFinal"
        private const val COLUMN_CONSTITUICAO_MOD = "constitucaoValormodificador"

        // Sabedoria
        private const val COLUMN_SABEDORIA_FIXO = "sabedoriaValorFixo"
        private const val COLUMN_SABEDORIA_PONTOS = "sabedoriaPontosInputados"
        private const val COLUMN_SABEDORIA_CUSTO = "sabedoriaValorCusto"
        private const val COLUMN_SABEDORIA_BONUS = "sabedoriaValorBonus"
        private const val COLUMN_SABEDORIA_FINAL = "sabedoriaValorAtributoFinal"
        private const val COLUMN_SABEDORIA_MOD = "sabedoriaValormodificador"

        // Faça o mesmo para Inteligência, Carisma, Constituição e Sabedoria
        // ...
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
        CREATE TABLE $TABLE_PERSONAGEM (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NOME_PERSONAGEM TEXT,
            $COLUMN_VALOR_XP INTEGER,
            $COLUMN_NOME_RACA TEXT,
            
            $COLUMN_FORCA_FIXO INTEGER,
            $COLUMN_FORCA_PONTOS INTEGER,
            $COLUMN_FORCA_CUSTO INTEGER,
            $COLUMN_FORCA_BONUS INTEGER,
            $COLUMN_FORCA_FINAL INTEGER,
            $COLUMN_FORCA_MOD INTEGER,
            
            $COLUMN_DESTREZA_FIXO INTEGER,
            $COLUMN_DESTREZA_PONTOS INTEGER,
            $COLUMN_DESTREZA_CUSTO INTEGER,
            $COLUMN_DESTREZA_BONUS INTEGER,
            $COLUMN_DESTREZA_FINAL INTEGER,
            $COLUMN_DESTREZA_MOD INTEGER,

            $COLUMN_INTELIGENCIA_FIXO INTEGER,
            $COLUMN_INTELIGENCIA_PONTOS INTEGER,
            $COLUMN_INTELIGENCIA_CUSTO INTEGER,
            $COLUMN_INTELIGENCIA_BONUS INTEGER,
            $COLUMN_INTELIGENCIA_FINAL INTEGER,
            $COLUMN_INTELIGENCIA_MOD INTEGER,

            $COLUMN_CARISMA_FIXO INTEGER,
            $COLUMN_CARISMA_PONTOS INTEGER,
            $COLUMN_CARISMA_CUSTO INTEGER,
            $COLUMN_CARISMA_BONUS INTEGER,
            $COLUMN_CARISMA_FINAL INTEGER,
            $COLUMN_CARISMA_MOD INTEGER,

            $COLUMN_CONSTITUICAO_FIXO INTEGER,
            $COLUMN_CONSTITUICAO_PONTOS INTEGER,
            $COLUMN_CONSTITUICAO_CUSTO INTEGER,
            $COLUMN_CONSTITUICAO_BONUS INTEGER,
            $COLUMN_CONSTITUICAO_FINAL INTEGER,
            $COLUMN_CONSTITUICAO_MOD INTEGER,

            $COLUMN_SABEDORIA_FIXO INTEGER,
            $COLUMN_SABEDORIA_PONTOS INTEGER,
            $COLUMN_SABEDORIA_CUSTO INTEGER,
            $COLUMN_SABEDORIA_BONUS INTEGER,
            $COLUMN_SABEDORIA_FINAL INTEGER,
            $COLUMN_SABEDORIA_MOD INTEGER
        )
    """
        db?.execSQL(createTable)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PERSONAGEM")
        onCreate(db)
    }

    fun deletePersonagem(id: Long): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_PERSONAGEM, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }


    fun insertPersonagem(personagemJson: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        // Converter o JSON para um objeto Personagem
        val personagem = Gson().fromJson(personagemJson, Personagem::class.java)

        // Armazenar os dados do personagem no ContentValues
        contentValues.put(COLUMN_NOME_PERSONAGEM, personagem.nomePersonagem)
        contentValues.put(COLUMN_VALOR_XP, personagem.valorXP)
        contentValues.put(COLUMN_NOME_RACA, personagem.nomeRaca.toString())

        // Atributos de Força
        contentValues.put(COLUMN_FORCA_FIXO, personagem.Forca.valorFixo)
        contentValues.put(COLUMN_FORCA_PONTOS, personagem.Forca.pontosInputados)
        contentValues.put(COLUMN_FORCA_CUSTO, personagem.Forca.valorCusto)
        contentValues.put(COLUMN_FORCA_BONUS, personagem.Forca.valorBonus)
        contentValues.put(COLUMN_FORCA_FINAL, personagem.Forca.valorAtributoFinal)
        contentValues.put(COLUMN_FORCA_MOD, personagem.Forca.valormodificador)

        // Atributos de Destreza
        contentValues.put(COLUMN_DESTREZA_FIXO, personagem.Destreza.valorFixo)
        contentValues.put(COLUMN_DESTREZA_PONTOS, personagem.Destreza.pontosInputados)
        contentValues.put(COLUMN_DESTREZA_CUSTO, personagem.Destreza.valorCusto)
        contentValues.put(COLUMN_DESTREZA_BONUS, personagem.Destreza.valorBonus)
        contentValues.put(COLUMN_DESTREZA_FINAL, personagem.Destreza.valorAtributoFinal)
        contentValues.put(COLUMN_DESTREZA_MOD, personagem.Destreza.valormodificador)

        // Atributos de Inteligência
        contentValues.put(COLUMN_INTELIGENCIA_FIXO, personagem.Inteligencia.valorFixo)
        contentValues.put(COLUMN_INTELIGENCIA_PONTOS, personagem.Inteligencia.pontosInputados)
        contentValues.put(COLUMN_INTELIGENCIA_CUSTO, personagem.Inteligencia.valorCusto)
        contentValues.put(COLUMN_INTELIGENCIA_BONUS, personagem.Inteligencia.valorBonus)
        contentValues.put(COLUMN_INTELIGENCIA_FINAL, personagem.Inteligencia.valorAtributoFinal)
        contentValues.put(COLUMN_INTELIGENCIA_MOD, personagem.Inteligencia.valormodificador)

        // Atributos de Carisma
        contentValues.put(COLUMN_CARISMA_FIXO, personagem.Carisma.valorFixo)
        contentValues.put(COLUMN_CARISMA_PONTOS, personagem.Carisma.pontosInputados)
        contentValues.put(COLUMN_CARISMA_CUSTO, personagem.Carisma.valorCusto)
        contentValues.put(COLUMN_CARISMA_BONUS, personagem.Carisma.valorBonus)
        contentValues.put(COLUMN_CARISMA_FINAL, personagem.Carisma.valorAtributoFinal)
        contentValues.put(COLUMN_CARISMA_MOD, personagem.Carisma.valormodificador)

        // Atributos de Constituição
        contentValues.put(COLUMN_CONSTITUICAO_FIXO, personagem.Constituicao.valorFixo)
        contentValues.put(COLUMN_CONSTITUICAO_PONTOS, personagem.Constituicao.pontosInputados)
        contentValues.put(COLUMN_CONSTITUICAO_CUSTO, personagem.Constituicao.valorCusto)
        contentValues.put(COLUMN_CONSTITUICAO_BONUS, personagem.Constituicao.valorBonus)
        contentValues.put(COLUMN_CONSTITUICAO_FINAL, personagem.Constituicao.valorAtributoFinal)
        contentValues.put(COLUMN_CONSTITUICAO_MOD, personagem.Constituicao.valormodificador)

        // Atributos de Sabedoria
        contentValues.put(COLUMN_SABEDORIA_FIXO, personagem.Sabedoria.valorFixo)
        contentValues.put(COLUMN_SABEDORIA_PONTOS, personagem.Sabedoria.pontosInputados)
        contentValues.put(COLUMN_SABEDORIA_CUSTO, personagem.Sabedoria.valorCusto)
        contentValues.put(COLUMN_SABEDORIA_BONUS, personagem.Sabedoria.valorBonus)
        contentValues.put(COLUMN_SABEDORIA_FINAL, personagem.Sabedoria.valorAtributoFinal)
        contentValues.put(COLUMN_SABEDORIA_MOD, personagem.Sabedoria.valormodificador)

        val result = db.insert(TABLE_PERSONAGEM, null, contentValues)
        db.close()
        return result != -1L
    }

    // Função para recuperar todos os personagens
    fun getAllPersonagens(): List<Personagem> {
        val personagemList = ArrayList<Personagem>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_PERSONAGEM", null)

        if (cursor.moveToFirst()) {
            do {
                val personagem = Personagem(
                    cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_PERSONAGEM)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_VALOR_XP)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_RACA)),

                    Atributo(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA_FIXO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA_PONTOS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA_CUSTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA_BONUS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA_FINAL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA_MOD))

                    ),
                    Atributo(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA_FIXO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA_PONTOS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA_CUSTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA_BONUS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA_FINAL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA_MOD))

                    ),
                    Atributo(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA_FIXO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA_PONTOS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA_CUSTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA_BONUS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA_FINAL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA_MOD))

                    ),
                    Atributo(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA_FIXO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA_PONTOS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA_CUSTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA_BONUS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA_FINAL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA_MOD))

                    ),
                    Atributo(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO_FIXO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO_PONTOS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO_CUSTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO_BONUS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO_FINAL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO_MOD))
                    ),
                    Atributo(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA_FIXO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA_PONTOS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA_CUSTO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA_BONUS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA_FINAL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA_MOD))
                    )


                )
                personagemList.add(personagem)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return personagemList
    }

}
