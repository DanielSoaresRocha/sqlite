package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.room.DataBase.AppDatabase
import com.example.room.Entity.Livro
import kotlinx.android.synthetic.main.activity_cadastro.*

class Cadastro : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database-name")
            .allowMainThreadQueries()
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        listener()

    }

    fun listener(){
        adicionarBtn.setOnClickListener {
            adicionarLivro()
        }
        concluirBtn.setOnClickListener {
            finish()
        }
        cancelBtn.setOnClickListener {
            finish()
        }
    }

    fun adicionarLivro(){
        db.livroDao().insert(Livro(0,nomeLivroEditText.text.toString()
            ,tipoLivroEditText.text.toString()))
        Toast.makeText(this,"Livro adicionado",Toast.LENGTH_SHORT).show()

        nomeLivroEditText.setText("")
        tipoLivroEditText.setText("")
    }

}
