package com.example.room.Interface

import androidx.room.*
import com.example.room.Entity.Livro

@Dao
interface LivroDao{

    @Insert
    fun insert(livro : Livro) : Long

    @Delete
    fun delete(livro : Livro) : Int

    @Update
    fun update(livro: Livro): Int

    @Query("SELECT * FROM tabela_livro")
    fun listAll(): Array<Livro>

    @Query("SELECT * FROM tabela_livro WHERE idLivro = :id")
    fun findById(id: Long): Livro

    @Query("SELECT * FROM tabela_livro WHERE nomeLivro = :nome")
    fun findByName (nome: String) : Livro
}