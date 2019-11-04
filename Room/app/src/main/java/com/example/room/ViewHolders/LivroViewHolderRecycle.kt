package com.example.room.ViewHolders

import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.LinearLayout


class LivroViewHolderRecycle(var v: View) : RecyclerView.ViewHolder(v){

    val textViewTitulo: TextView
    val textViewAutor: TextView
    val img: ImageView
    val layoutNormal:LinearLayout
    val layoutGone : LinearLayout
    val undoButton : Button

    init {
        Log.i("HOLDER", "Fazendo buscas por id...")
        textViewTitulo = v.findViewById(R.id.textViewTitulo)
        textViewAutor = v.findViewById(R.id.textViewAutor)
        img = v.findViewById(R.id.imagemView)
        layoutNormal = v.findViewById(R.id.layout_normal)
        layoutGone = v.findViewById(R.id.layout_gone)
        undoButton = v.findViewById(R.id.undo_button)
    }

    fun addAnimation(){
        val animation = ScaleAnimation(
            0f, 1f,
            0f, 1f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f
        )
        animation.fillAfter = true
        animation.duration = 1500
        v.setAnimation(animation)
    }

    /*
    val translateAnimation = TranslateAnimation(300f, 0f, 0f, 0f)
        val alphaAnimation = AlphaAnimation(0f, 1f)
        translateAnimation.duration = 500
        alphaAnimation.duration = 1300
        val animation = AnimationSet(true)
        animation.addAnimation(translateAnimation)
        animation.addAnimation(alphaAnimation)
        v!!.setAnimation(animation)
    */

    /*
            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(1500);
            convertView.setAnimation(animation);

     */
}