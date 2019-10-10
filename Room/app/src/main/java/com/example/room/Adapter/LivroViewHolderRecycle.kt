package com.example.room.Adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R

class LivroViewHolderRecycle(v: View) : RecyclerView.ViewHolder(v){

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