package com.example.room.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.room.Entity.Livro
import com.example.room.R

class PageAdapter(var context: Context, var livros:List<Livro>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.page_adapter, container, false)
        val img: ImageView = view.findViewById(R.id.imagemLivro)
        img.setImageResource(livros[position].imagem)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return livros.size
    }

    override fun getPageTitle(position: Int): String? {
        return livros[position].titulo
    }

}