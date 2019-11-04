package com.example.room.ViewHolders

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R

class LivroViewHolder(v: View){

    val textViewTitulo: TextView
    val textViewAutor: TextView
    val img: ImageView

    init {
        Log.i("HOLDER", "Fazendo buscas por id...")
        textViewTitulo = v.findViewById(R.id.textViewTitulo)
        textViewAutor = v.findViewById(R.id.textViewAutor)
        img = v.findViewById(R.id.imagemView)
    }
}