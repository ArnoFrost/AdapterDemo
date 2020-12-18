package com.arno.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arno.adapter.ui.AdvanceActivity
import com.arno.adapter.ui.BasicActivity
import com.arno.adapter.ui.PreloadActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    fun goBasic(view: View) {
        startActivity(Intent(this, BasicActivity::class.java))
    }

    fun goAdvance(view: View) {
        startActivity(Intent(this, AdvanceActivity::class.java))
    }

    fun goPreload(view: View) {
        startActivity(Intent(this, PreloadActivity::class.java))
    }
}