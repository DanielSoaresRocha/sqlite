package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.Adapter.LivrosAdapterRecycle
import com.example.room.Entity.Livro
import kotlinx.android.synthetic.main.activity_lista_livro_recycle.*

class ListaLivroRecycle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livro_recycle)

        var adapter = LivrosAdapterRecycle(this, MainActivity.livros as List<Livro>)
        recyclerview.adapter = adapter


        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout
    }
}
