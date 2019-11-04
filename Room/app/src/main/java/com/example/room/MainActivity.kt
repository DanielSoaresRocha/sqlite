package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.room.*
import com.example.room.DataBase.AppDatabase
import com.example.room.Entity.Livro
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        autoComplete()
    }

    fun verifica(){
        if(livros.size >0){
            direitaBtn.visibility = View.VISIBLE
            excluirBtn.visibility = View.VISIBLE
            mudarLivro()
        }else{
            direitaBtn.visibility = View.INVISIBLE
            esquerdaBtn.visibility = View.INVISIBLE
            excluirBtn.visibility = View.INVISIBLE
            limparCampos()
        }
    }

    fun limparCampos(){
        idLivro.text = ""
        nomeLivro.text = ""
        tipoLivro.text = ""
        anoLivro.text = ""
        notaLivro.text = ""
    }

    fun mudarLivro(){
        idLivro.text = livros.get(livroDaVez).idLivro.toString()
        nomeLivro.text = livros.get(livroDaVez).titulo.toString() //titulo
        tipoLivro.text = livros.get(livroDaVez).autor.toString()  //autor
        anoLivro.text = livros.get(livroDaVez).ano.toString()
        notaLivro.text = livros.get(livroDaVez).nota.toString()

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

        excluirBtn.setOnClickListener {
            db.livroDao().delete(livros.get(livroDaVez))
            onResume()
        }

        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            var selected = adapterView.getItemAtPosition(i).toString()

            var livro = db.livroDao().findByName(selected)

            idLivro.setText(livro.idLivro.toString())
            nomeLivro.setText(livro.titulo.toString())
            tipoLivro.setText(livro.autor.toString())
            anoLivro.setText(livro.ano.toString())
            notaLivro.setText(livro.nota.toString())

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

    fun autoComplete(){
        var nomeLivros = arrayOfNulls<String>(livros.size)

        for(i in 0 until livros.size){
            nomeLivros[i] = livros.get(i).titulo
            Log.i("array de livros= ", ""+nomeLivros[i])
        }

        var livrosToListAdapter = ArrayAdapter<String>(this,
            R.layout.activity_main2, nomeLivros)

        autoCompleteTextView.setAdapter(livrosToListAdapter)

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
        autoComplete()
    }
}