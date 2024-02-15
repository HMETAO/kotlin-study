package com.hmetao.gallery.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hmetao.gallery.GalleryAdapter
import com.hmetao.gallery.GalleryViewModel
import com.hmetao.gallery.R
import com.hmetao.gallery.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ad = GalleryAdapter()
        binding.re.apply {
            adapter = ad
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        viewModel.photoListLive.observe(viewLifecycleOwner) {
            ad.submitList(it)
            binding.swipeContainer.isRefreshing = false
        }
        viewModel.photoListLive.value ?: viewModel.fetchData()

        binding.swipeContainer.setOnRefreshListener {
            viewModel.fetchData()
        }
    }
}