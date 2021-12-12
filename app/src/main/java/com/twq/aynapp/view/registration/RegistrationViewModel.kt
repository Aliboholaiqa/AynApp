package com.twq.aynapp.view.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twq.aynapp.repository.UserRepository

class RegistrationViewModel : ViewModel(){
    fun register(username:String ,email: String, password:String): LiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        UserRepository().register(username,email, password).observeForever{
            if(it.username.isNotEmpty()&&it.password.isNotEmpty()){
                mLiveData.postValue(true)
            }else{
                mLiveData.postValue(false)
            }
        }
        return mLiveData
    }
}