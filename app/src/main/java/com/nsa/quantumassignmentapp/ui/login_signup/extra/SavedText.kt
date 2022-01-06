package com.nsa.quantumassignmentapp.ui.login_signup.extra

import android.content.Context
import android.content.SharedPreferences
import com.nsa.quantumassignmentapp.R

class SavedText(private var context: Context) {
    private lateinit var preferences: SharedPreferences
    init {
        preferences=context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
    }
    public fun setText(key:String,text: String){
        preferences.edit().putString(key,text).apply()
    }
    public fun getText(key:String):String{
        return preferences.getString(key,"").toString()
    }
    fun remove(key: String?) {
        preferences.edit().remove(key).commit()
    }
}