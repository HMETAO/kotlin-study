package com.example.kotlin_study.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_study.R
import com.example.kotlin_study.databinding.FragmentScaleBinding

class ScaleFragment : Fragment() {

    private var bind: FragmentScaleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentScaleBinding.inflate(inflater, container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind!!.imageViewScale.setOnClickListener {
            ObjectAnimator.ofFloat(it, "scaleX",it.scaleX + 0.3f).start()
            ObjectAnimator.ofFloat(it, "scaleY", it.scaleY + 0.3f).start()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = ScaleFragment()
    }
}