package com.example.room.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.room.Entity.Livro
import android.view.animation.AnimationSet
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import com.example.room.ViewHolders.LivroViewHolder


class LivrosAdapter(var context: Context, var f: List<Livro>) : BaseAdapter() {

   override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
       Log.i("HOLDER", "GetView invocado...")
       var holder: LivroViewHolder
       var view:View

       if (convertView == null){
           Log.i("HOLDER", "Inflando View...")
           view = LayoutInflater.from(context).inflate(com.example.room.R.layout.livro_layout, viewGroup, false)
           holder = LivroViewHolder(view)
           view.tag = holder
       }else{
           view = convertView
           holder  = convertView.tag as LivroViewHolder
           animacao(convertView)
       }
       val livroescolhido = f[position]
       holder.textViewTitulo.text = livroescolhido.titulo
       holder.textViewAutor.text = livroescolhido.autor
       holder.img.setImageResource(com.example.room.R.drawable.livro)

       /*
       holder.textViewQuantidade.text = "${livroescolhido.quantidade}"
       if (livroescolhido.lido) {
           holder.img.setImageResource(R.drawable.open)
       } else {
           holder.img.setImageResource(R.drawable.flat)
       }*/
       return view
   }


    override fun getItem(position: Int): Any {
        return f.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return f.size
    }

    fun animacao(convertView: View?){
        val translateAnimation = TranslateAnimation(300f, 0f, 0f, 0f)
        val alphaAnimation = AlphaAnimation(0f, 1f)
        translateAnimation.duration = 500
        alphaAnimation.duration = 1300
        val animation = AnimationSet(true)
        animation.addAnimation(translateAnimation)
        animation.addAnimation(alphaAnimation)
        convertView!!.setAnimation(animation)
    }
}