package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.room.Room
import com.example.room.DataBase.AppDatabase
import com.example.room.Entity.Livro
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pesquisa_livro.*

class PesquisaLivro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_livro)

        var nomeLivros = arrayOfNulls<String>(MainActivity.livros.size)

        for(i in 0 until MainActivity.livros.size){
            nomeLivros[i] = MainActivity.livros.get(i).titulo
            Log.i("array de livros= ", ""+nomeLivros[i])
        }

        var livrosToListAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_expandable_list_item_1, nomeLivros)

        autoCompleteTextView.setAdapter(livrosToListAdapter)
    }
}
