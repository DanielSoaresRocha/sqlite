package com.example.room.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Entity.Livro
import com.example.room.R

class LivrosAdapterRecycle(var c: Context, var livros:List<Livro>) : RecyclerView.Adapter<LivroViewHolderRecycle>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolderRecycle {
        //Exitem 2 exemplos de layout para ser inflado nessse projeto. Teste os 3.

        //val view = LayoutInflater.from(c).inflate(R.layout.fruta_inflater, parent, false)
        val view = LayoutInflater.from(c).inflate(R.layout.livro_layout, parent, false)

        return LivroViewHolderRecycle(view)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(holder: LivroViewHolderRecycle, position: Int) {

        val livroEscolhido = livros[position]
        holder.textViewTitulo.text = livroEscolhido.titulo
        holder.img.setImageResource(R.drawable.livro)

        /*
        if (livroEscolhido.bitten) {
            holder.img.setImageResource(R.drawable.bitten)
        } else {
            holder.img.setImageResource(R.drawable.fruit)
        }
        holder.img.setOnClickListener{
            frutaescolhida.bitten = true
            notifyItemChanged(position)
        }*/
    }
}