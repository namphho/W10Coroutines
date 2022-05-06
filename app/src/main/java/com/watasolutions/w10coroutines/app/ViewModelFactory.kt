package com.watasolutions.w10coroutines.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
//            val repo = appData.userRepository
//            return HomeVM(repo) as T
//        }
        throw IllegalArgumentException("unknown view model")
    }
}