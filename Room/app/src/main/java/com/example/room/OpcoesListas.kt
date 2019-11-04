package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.room.Room
import com.example.room.DataBase.AppDatabase
import com.example.room.Entity.Livro
import com.example.room.PageAdapter.PageAdapterController
import kotlinx.android.synthetic.main.activity_opcoes_listas.*

class OpcoesListas : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database-name")
            .allowMainThreadQueries()
            .build()
    }
    companion object {  //variaveis estáticas
        var livros = ArrayList<Livro>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opcoes_listas)

        listener()

        db.livroDao().listAll().forEach {
            MainActivity.livros.add(it)
            Log.i("dados", it.toString())
        }
    }

    fun listener() {
        cadastroBtn.setOnClickListener {
            var telaCadastro = Intent(this,Cadastro::class.java)
            startActivity(telaCadastro)
        }

        avancar_voltar.setOnClickListener {
            var avancarVoltar = Intent(this,MainActivity::class.java)
            startActivity(avancarVoltar)
        }

        listViewBtn.setOnClickListener {
            var listView = Intent(this,ListaLivros::class.java)
            startActivity(listView)
        }

        gridViewBtn.setOnClickListener {
            var gridView = Intent(this,listGridView::class.java)
            startActivity(gridView)
        }

        listaRecycleBtn.setOnClickListener {
            var listaRecycle = Intent(this,ListaLivroRecycle::class.java)
            startActivity(listaRecycle)
        }

        listaRecycleBtn2.setOnClickListener {
            var listaRecycle2 = Intent(this,ListaLivroRecycle2::class.java)
            startActivity(listaRecycle2)
        }

        pageViewBtn.setOnClickListener {
            var pageAdapter = Intent(this, PageAdapterController::class.java)
            startActivity(pageAdapter)
        }

    }

    override fun onResume() {
        super.onResume()
        livros.clear()//limpar lista

        db.livroDao().listAll().forEach { // atualizar lista
            livros.add(it)
            Log.i("dados", it.toString())
        }
    }

}
