package com.shiv.shrikrupamedical.Doctor

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.shiv.shrikrupamedical.R

class Doctor4: AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor4)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        // Enable JavaScript (optional)
        webView.settings.javaScriptEnabled = true

        // Set WebViewClient to handle page loading and progress
        webView.webViewClient = CustomWebViewClient()

        // Set WebChromeClient to handle progress bar
        webView.webChromeClient = CustomWebChromeClient()

        // Load a URL (Replace "https://www.example.com" with your desired URL)
        webView.loadUrl("https://sscoderin.github.io/healthcare/doctor4.html")
    }

    private inner class CustomWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }

    private inner class CustomWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            // You can handle progress updates if needed
        }
    }
}
