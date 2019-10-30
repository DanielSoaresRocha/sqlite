package com.example.room.RecycleClickLisneter

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class MyRecycleViewClickListener(val context: Context, val view:RecyclerView,val listener:OnItemClickListener) : RecyclerView.OnItemTouchListener{

    var myGestureDetector : GestureDetector


    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    init {
        myGestureDetector = GestureDetector(context,object :GestureDetector.SimpleOnGestureListener(){
            override fun onSingleTapUp(motionEvent: MotionEvent?): Boolean {
                return super.onSingleTapUp(motionEvent)
                val childView = view.findChildViewUnder(motionEvent!!.x, motionEvent.y)
                if(childView != null){
                    listener.onItemClick(childView,view.getChildAdapterPosition(childView))
                    Log.i("Teste","onSingleTapUp")
                }else{
                    Log.i("Teste","é nulo")
                }
                return true
            }

            override fun onLongPress(motionEvent: MotionEvent?) {
                super.onLongPress(motionEvent)
                val childView = view.findChildViewUnder(motionEvent!!.x,motionEvent.y)
                if (childView != null){
                    listener.onItemLongClick(
                        childView,
                        view.getChildAdapterPosition(childView)
                    )
                    Log.i("Teste", "onLongPress")
                }else{
                    Log.i("Teste","é nulo")
                }
            }
        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        myGestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}