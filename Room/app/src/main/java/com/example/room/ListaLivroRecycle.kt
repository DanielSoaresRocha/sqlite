package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.room.Adapter.LivrosAdapterRecycle
import com.example.room.Entity.Livro
import com.example.room.RecycleClickLisneter.MyRecycleViewClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lista_livro_recycle.*

class ListaLivroRecycle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livro_recycle)

        var adapter = LivrosAdapterRecycle(this, MainActivity.livros as List<Livro>)
        recyclerview.adapter = adapter


        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //val layout = GridLayoutManager(this,4)
        //val layout = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

        recyclerview.layoutManager = layout

        recyclerview.addOnItemTouchListener(
            MyRecycleViewClickListener(
                this@ListaLivroRecycle,
                recyclerview,
                object : MyRecycleViewClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@ListaLivroRecycle,"Click simples interface",Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = MainActivity.livros[position]
                        MainActivity.livros.remove(removida)
                        recyclerview.adapter!!.notifyItemRemoved(position)
                        Toast.makeText(this@ListaLivroRecycle,"Clique longo interface", Toast.LENGTH_SHORT)
                            .show()
                        val snack = Snackbar.make(
                            recyclerview.parent as View,
                            "Removido com sucesso!!",
                            Snackbar.LENGTH_LONG)
                            .setAction("Cancelar?"){
                                MainActivity.livros.add(position,removida)
                                recyclerview.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()

                    }
                }
            )
        )


    }
}
