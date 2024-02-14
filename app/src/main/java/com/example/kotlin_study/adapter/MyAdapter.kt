package com.example.kotlin_study.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_study.fragment.RotateFragment
import com.example.kotlin_study.fragment.ScaleFragment
import com.example.kotlin_study.fragment.TranslateFragment

class MyAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RotateFragment.newInstance("1", "2")
            1 -> ScaleFragment.newInstance("1", "2")
            else -> TranslateFragment.newInstance("1", "2")
        }
    }

}