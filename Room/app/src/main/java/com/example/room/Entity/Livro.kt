package com.example.room.Entity

import androidx.room.*

@Entity(tableName = "tabela_livro")
data class Livro(
    @PrimaryKey(autoGenerate = true)
    val idLivro: Int = 0,

    @ColumnInfo(name = "nomeLivro")
    val nomeLivro: String?,

    @ColumnInfo(name = "tipoLivro")
    val tipoLivro: String?


)