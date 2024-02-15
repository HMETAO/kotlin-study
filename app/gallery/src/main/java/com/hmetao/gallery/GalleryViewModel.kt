package com.hmetao.gallery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.hmetao.gallery.model.PhotoItem
import com.hmetao.gallery.model.Pixabay

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive: LiveData<List<PhotoItem>> = _photoListLive
    fun fetchData() {
        val arr = arrayOf("cat", "dog", "bird", "fish", "cow")
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://pixabay.com/api/?key=42380841-bee5546480b1fc56187e0a4e7&q=${arr.random()}&per_page=100",
            {
                _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
            },
            {
                Log.d("HMETAO", "fetchData: ${it}.")
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }
}