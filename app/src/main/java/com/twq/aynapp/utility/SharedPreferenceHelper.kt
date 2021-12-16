package com.twq.aynapp.utility

import android.content.Context

class SharedPreferenceHelper {

    companion object{
        fun saveToken (context: Context, uid:String):Unit{
            val pref = context.getSharedPreferences("Myref",Context.MODE_PRIVATE)
            pref.edit().putString("id",uid).commit()
        }
        fun getUserID(context: Context):String{
            val pref = context.getSharedPreferences("Myref",Context.MODE_PRIVATE)
            var id =pref.getString("id","null")
            return id!!
        }
    }


}