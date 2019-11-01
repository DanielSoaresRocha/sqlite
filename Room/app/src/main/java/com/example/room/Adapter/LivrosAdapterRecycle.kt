package com.example.room.Adapter

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Entity.Livro
import com.example.room.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class LivrosAdapterRecycle(var c: Context, var livros:ArrayList<Livro>) : RecyclerView.Adapter<LivroViewHolderRecycle>() {
    private val PENDING_REMOVAL_TIMEOUT:Long = 4000 // 3sec
    var itemsPendingRemoval = ArrayList<Livro>()

    private val handler = Handler() // hanlder que vai guardar os runnables que devem ser executados
    var pendingRunnables: HashMap<Livro, Runnable> =
        HashMap() // map de frutas com runnables pendentes, para que seja possível cancelar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolderRecycle {
        //Exitem 2 exemplos de layout para ser inflado nessse projeto. Teste os 3.

        //val view = LayoutInflater.from(c).inflate(R.layout.fruta_inflater, parent, false)
        val view = LayoutInflater.from(c).inflate(R.layout.livro_layout_novo, parent, false)
        Log.i("animacao","chamou onCreate")
        return LivroViewHolderRecycle(view)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(holder: LivroViewHolderRecycle, position: Int) {

        holder.addAnimation()
        val livroEscolhido = livros[position]
        holder.textViewTitulo.text = livroEscolhido.titulo
        holder.textViewAutor.text = livroEscolhido.autor
        holder.img.setImageResource(R.drawable.livro)
        Log.i("animacao","chamou onBind")

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

        if(itemsPendingRemoval.contains(livroEscolhido)){//se o livro esta pendente de remoção
            holder.layoutNormal.setVisibility(View.GONE)
            holder.layoutGone.setVisibility(View.VISIBLE)
            holder.undoButton.setVisibility(View.VISIBLE)
            holder.undoButton.setOnClickListener {
                val pendingRemovalRunnable = pendingRunnables[livroEscolhido]
                Log.i("Botao","CLICOU")
                if(pendingRemovalRunnable != null){
                    handler.removeCallbacks(pendingRemovalRunnable)
                }
                itemsPendingRemoval.remove(livroEscolhido)
                notifyItemChanged(position)//////////////////

            }
        }else{
            //mostra padrão
            holder.textViewTitulo.setText(livroEscolhido.titulo)
            holder.layoutNormal.setVisibility(View.VISIBLE)
            holder.layoutGone.setVisibility(View.GONE)
            holder.undoButton.setVisibility(View.GONE)
            holder.undoButton.setOnClickListener (null)
        }
    }

    fun remover(position: Int){
        var livro = livros[position]

        if(livros.contains(livro)){
            livros.remove(livro)
            notifyItemRemoved(position)
        }
    }

    fun removerComTempo(position: Int){
        val livro = livros[position]
        if(!itemsPendingRemoval.contains(livro)){
            itemsPendingRemoval.add(livro)
            notifyItemChanged(position)
            var pendingRemovalRunnable = Runnable {
                remover(position)
            }
            handler.postDelayed(pendingRemovalRunnable,PENDING_REMOVAL_TIMEOUT)
            pendingRunnables[livro] = pendingRemovalRunnable
        }
    }

    fun mover(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(livros, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(livros, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)
        notifyItemChanged(fromPosition)
    }
}