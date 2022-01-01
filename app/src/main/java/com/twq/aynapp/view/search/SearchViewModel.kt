package com.twq.aynapp.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twq.aynapp.model.User
import com.twq.aynapp.repository.UserRepository

class SearchViewModel : ViewModel(){

    var user = UserRepository()

    fun getAllUsers(): LiveData<List<User>> {
        return user.getAllUsers()
    }
}