package com.example.room.PageAdapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.room.Adapter.PageAdapter
import com.example.room.MainActivity
import com.example.room.R
import kotlinx.android.synthetic.main.activity_page_adapter_controller.*

class PageAdapterController : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_adapter_controller)

        viewpager.adapter = PageAdapter(this, MainActivity.livros)
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

            override fun onPageScrollStateChanged(state: Int) {
                Log.i("AULA17", "onPageScrollStateChanged")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.i("AULA17", "onPageScrolled chamado, posição: "+position)
            }

            override fun onPageSelected(position: Int) {
                Log.i("AULA17", "onPageSelected chamado, posição: "+position)
            }

        })
    }
}
