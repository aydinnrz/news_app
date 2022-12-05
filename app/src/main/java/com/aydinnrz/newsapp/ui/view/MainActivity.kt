package com.aydinnrz.newsapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aydinnrz.newsapp.R
import com.aydinnrz.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // hide actionbar
        supportActionBar?.hide()
    }
}