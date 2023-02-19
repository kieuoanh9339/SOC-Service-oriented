package com.sudo248.hantest

import android.R.attr.thumbnail
import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val source: TextView = findViewById<TextView>(R.id.source)
        val target: TextView = findViewById<TextView>(R.id.target)

        source.setOnDragListener { v, event ->
            Log.d("sudoo8", "onCreate: ")
            false
        }

        source.setOnLongClickListener {
            val clip = ClipData.newPlainText("key", "value")
            val shadow = View.DragShadowBuilder(it)
            it.startDragAndDrop(clip, shadow, null, View.DRAG_FLAG_GLOBAL)
            it.visibility = View.INVISIBLE
            true
        }
    }
}