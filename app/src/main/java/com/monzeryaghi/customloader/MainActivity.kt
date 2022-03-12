package com.monzeryaghi.customloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monzeryaghi.loader.CustomLoader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customLoader = CustomLoader(this)
        customLoader.setCancelable(true)
        customLoader.showLoader();
    }
}