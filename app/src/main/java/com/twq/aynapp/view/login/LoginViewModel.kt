package com.twq.aynapp.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twq.aynapp.repository.UserRepository

class LoginViewModel : ViewModel(){

    fun login(email:String , password:String): LiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        UserRepository().login(email,password).observeForever{
            if(it.email.isNotEmpty() && it.password.isNotEmpty()){
                mLiveData.postValue(true)
            }else{
                mLiveData.postValue(false)
            }

        }
        return mLiveData
    }

}