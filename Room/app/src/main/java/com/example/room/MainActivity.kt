package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.*
import com.example.room.DataBase.AppDatabase
import com.example.room.Entity.Livro
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.SQLOutput

class MainActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database-name")
            .allowMainThreadQueries()
            .build()
    }

    var livros = ArrayList<Livro>()
    var livroDaVez = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listener()

        db.livroDao().listAll().forEach {
            livros.add(it)
            Log.i("dados", it.toString())
        }

        verifica() //esta função verifica se há livros para serem mostrados
    }

    fun verifica(){
        if(livros.size >0){
            direitaBtn.visibility = View.VISIBLE
            mudarLivro()
        }else{
            direitaBtn.visibility = View.INVISIBLE
            esquerdaBtn.visibility = View.INVISIBLE
        }
    }

    fun mudarLivro(){
        idLivro.text = livros.get(livroDaVez).idLivro.toString()
        nomeLivro.text = livros.get(livroDaVez).nomeLivro.toString()
        tipoLivro.text = livros.get(livroDaVez).tipoLivro.toString()

        testarTamanho()
    }

    fun listener(){
        direitaBtn.setOnClickListener {
            livroDaVez++
            mudarLivro()
            Log.i("LIVRO", "Livro da vez = " + livroDaVez)
        }

        esquerdaBtn.setOnClickListener {
            livroDaVez--
            mudarLivro()
            Log.i("LIVRO", "Livro da vez = " + livroDaVez)
        }

        cadastrarBtn.setOnClickListener {
            var telaCadastro = Intent(this,Cadastro::class.java)
            startActivity(telaCadastro)
        }
    }

    fun testarTamanho(){
        if(livroDaVez+1 >= livros.size) {
            direitaBtn.visibility = View.INVISIBLE
        }else{
            direitaBtn.visibility = View.VISIBLE
        }

        if(livroDaVez-1 < 0){
            esquerdaBtn.visibility = View.INVISIBLE
        }else{
            esquerdaBtn.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        livros.clear()//limpar lista

        db.livroDao().listAll().forEach { // atualizar lista
            livros.add(it)
            Log.i("dados", it.toString())
        }

        livroDaVez = 0
        verifica()
    }
}