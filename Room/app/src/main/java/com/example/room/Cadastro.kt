package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.room.DataBase.AppDatabase
import com.example.room.Entity.Livro
import kotlinx.android.synthetic.main.activity_cadastro.*
import java.io.IOException

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
        try {
            db.livroDao().insert(Livro(0,tituloLivroEditText.text.toString()
                ,autorLivroEditText.text.toString(),Integer.parseInt(anoLivroEditText.text.toString()),
                ratingBar.rating))
        }catch ( e: java.lang.NumberFormatException){ // se o usuario digiar um numero invalido o ano vai para 0
            db.livroDao().insert(Livro(0,tituloLivroEditText.text.toString()
                ,autorLivroEditText.text.toString(),0,
                ratingBar.rating))
        }

        Toast.makeText(this,"Livro adicionado",Toast.LENGTH_SHORT).show()

        tituloLivroEditText.setText("")
        autorLivroEditText.setText("")
        anoLivroEditText.setText("")
        ratingBar.numStars = 0
    }

}
