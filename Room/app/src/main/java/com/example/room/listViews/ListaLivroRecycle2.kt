package com.example.room.listViews

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Adapter.LivrosAdapterRecycle
import com.example.room.Entity.Livro
import com.example.room.OpcoesListas
import com.example.room.R
import kotlinx.android.synthetic.main.activity_lista_livro_recycle2.*

class ListaLivroRecycle2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livro_recycle2)

        var adapter = LivrosAdapterRecycle(this, OpcoesListas.livros as ArrayList<Livro>)
        recyclerview2.adapter = adapter


        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview2.layoutManager = layout


       //-----------------------------------------SWAP-----------------------------------------------
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
           UP or DOWN, START or END)
       {
           override fun onMove(
               recyclerView: RecyclerView,
               viewHolder: RecyclerView.ViewHolder,
               target: RecyclerView.ViewHolder
           ): Boolean {
               val fromPosition = viewHolder.adapterPosition
               val toPosision = target.adapterPosition
               val adapter = recyclerView.adapter as LivrosAdapterRecycle
               adapter.mover(fromPosition,toPosision)
               return true
           }

           override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               var posicao = viewHolder.adapterPosition
               var adapter = recyclerview2.adapter as LivrosAdapterRecycle

               adapter.removerComTempo(posicao)
           }
           //-----------------------onChild-----------------------------------------------
           override fun onChildDraw(
               c: Canvas,
               recyclerView: RecyclerView,
               viewHolder: RecyclerView.ViewHolder,
               dX: Float,
               dY: Float,
               actionState: Int,
               isCurrentlyActive: Boolean
           ) {
               val itemView = viewHolder.itemView
               val background = ColorDrawable(resources.getColor(R.color.colorAccent))
               // not sure why, but this method get's called for viewholder that are already swiped away
               if (viewHolder.adapterPosition === -1) {
                   // not interested in those
                   return
               }
               Log.i("AULA17", "dx = $dX")
               // Here, if dX > 0 then swiping right.
               // If dX < 0 then swiping left.
               // If dX == 0 then at at start position.
               // draw red background
               if (dX < 0) {
                   Log.i("AULA17", "dX < 0")
                   background.setBounds(
                       (itemView.right + dX).toInt(),
                       itemView.top,
                       itemView.right,
                       itemView.bottom
                   )
               } else if (dX > 0) {
                   Log.i("AULA17", "dX > 0")
                   background.setBounds(
                       itemView.left,
                       itemView.top,
                       (dX).toInt(),
                       itemView.bottom
                   )
               }
               background.draw(c)

               super.onChildDraw(
                   c,
                   recyclerView,
                   viewHolder,
                   dX,
                   dY,
                   actionState,
                   isCurrentlyActive
               )

           }

           override fun isLongPressDragEnabled(): Boolean {
               //return false; se quiser, é possivel desabilitar o drag and drop
               return true
           }

           override fun isItemViewSwipeEnabled(): Boolean {
               //return false; se quiser, é possivel desabilitar o swipe
               return true
           }

       })
       itemTouchHelper.attachToRecyclerView(recyclerview2)

    }
}
