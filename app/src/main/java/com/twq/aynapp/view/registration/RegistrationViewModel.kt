package com.twq.aynapp.view.registration

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twq.aynapp.repository.UserRepository
import com.twq.aynapp.utility.SharedPreferenceHelper

class RegistrationViewModel : ViewModel(){
    fun register(username:String ,email: String, password:String): LiveData<Boolean> {
        val mLiveData = MutableLiveData<Boolean>()
        UserRepository().register(username,email, password).observeForever{
            if(it.username.isNotEmpty()&&it.email.isNotEmpty()&&it.password.isNotEmpty()){
                mLiveData.postValue(true)
            }else{
                mLiveData.postValue(false)
            }
        }
        return mLiveData
    }
}