package com.example.room.DataBase

import androidx.room.*
import com.example.room.Entity.Livro
import com.example.room.Interface.LivroDao

@Database(entities = [Livro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun livroDao(): LivroDao
}