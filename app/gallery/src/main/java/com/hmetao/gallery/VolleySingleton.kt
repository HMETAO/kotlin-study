package com.hmetao.gallery

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context: Context) {
    companion object {

        private var instance: VolleySingleton? = null

        @JvmStatic
        fun getInstance(context: Context): VolleySingleton {
            if (instance == null) {
                synchronized(VolleySingleton::class) {
                    if (instance == null)
                        instance = VolleySingleton(context)
                }
            }
            return instance!!
        }

    }


    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
}