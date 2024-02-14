package com.example.kotlin_study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_study.adapter.MyAdapter
import com.example.kotlin_study.databinding.ActivityViewPager2Binding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val myAdapter = MyAdapter(this)
        binding.vp.adapter = myAdapter
        TabLayoutMediator(binding.tab, binding.vp) { tab, position ->
            tab.text = "Tab $position"
        }.attach()
    }
}


