package com.example.room.listViews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.room.Adapter.LivrosAdapter
import com.example.room.Entity.Livro
import com.example.room.MainActivity
import com.example.room.OpcoesListas
import com.example.room.R
import kotlinx.android.synthetic.main.activity_list_grid_view.*

class listGridView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_grid_view)

        gridViewList.adapter = LivrosAdapter(this, OpcoesListas.livros as List<Livro>)

        gridViewList.setOnItemClickListener{adapterView, view, i, l ->
            var livroSelecionado = MainActivity.livros?.get(i)
            Toast.makeText(this, "${livroSelecionado?.titulo} id=${livroSelecionado?.idLivro}", Toast.LENGTH_SHORT).show()
        }
    }
}
