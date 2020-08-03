package com.example.recitewords.views.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.recitewords.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)
        //Show progress
        activityDetailBinding.uiState = 2
        val webView = activityDetailBinding.webView
        val webSettings = webView.settings
        //设置支持JavaScript
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true

        val word = intent.getStringExtra("word")
        webView.loadUrl("https://fanyi.baidu.com/#en/zh/${word}")
        webView.webViewClient = MyWebViewClient()
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            //Hidden progress
            activityDetailBinding.uiState = 1
        }
    }
}
