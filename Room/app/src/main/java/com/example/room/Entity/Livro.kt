package com.example.room.Entity

import androidx.room.*
import java.sql.Date

@Entity(tableName = "tabela_livro")
data class Livro(
    @PrimaryKey(autoGenerate = true)
    val idLivro: Int = 0,

    @ColumnInfo(name = "titulo")
    val titulo: String?,

    @ColumnInfo(name = "autor")
    val autor: String?,

    @ColumnInfo(name = "ano")
    val ano: Int?,

    @ColumnInfo(name = "nota")
    val nota: Float



)