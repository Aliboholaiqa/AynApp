package com.twq.aynapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
//https://618ebc2e50e24d0017ce141f.mockapi.io/
    companion object {
        private val retrofit : Retrofit
        init{
            retrofit = Retrofit.Builder().
            baseUrl("https://61adebe3a7c7f3001786f41c.mockapi.io/api/ayn/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun getInstance():Retrofit{
            return retrofit
        }
    }

}