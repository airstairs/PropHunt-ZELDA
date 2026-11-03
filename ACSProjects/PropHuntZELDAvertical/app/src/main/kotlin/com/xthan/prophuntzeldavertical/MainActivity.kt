package com.xthan.prophuntzeldavertical

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // Hide system bars and enable immersive sticky mode
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webV: WebView = findViewById(R.id.myWebView)
        
        // --- CRITICAL SETTINGS FOR WEBVIEW GAMES ---
        webV.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true     // Fixes localStorage crashes!
            allowFileAccess = true       // Allows loading local assets & scripts
            allowContentAccess = true
            databaseEnabled = true
        }
        
        webV.loadUrl("file:///android_asset/index.html")
    }
}
