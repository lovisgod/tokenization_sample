package com.lovisgod.tokenization

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var startButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        startButton = findViewById(R.id.fab)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        handleViewClicks()
    }

    private fun handleViewClicks() {
        startButton.setOnClickListener {
            Snackbar.make(it, "Please  tap your device on a device", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .setAnchorView(R.id.fab).show()

                startCardHceService()
        }
    }

    private fun startCardHceService() {
        LovisgodHceService.isHceEnabled = true
    }

    private fun stopCardHceService () {
        LovisgodHceService.isHceEnabled = false

    }

    override fun onStop() {
        super.onStop()
        stopCardHceService()
    }
}
