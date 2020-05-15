package com.ulusofona.aula_5.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

abstract class Utilities private constructor(){
    companion object{
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return if(activeNetwork?.isConnected!=null){
                activeNetwork.isConnected
            } else{
                false
            }
        }
    }
}