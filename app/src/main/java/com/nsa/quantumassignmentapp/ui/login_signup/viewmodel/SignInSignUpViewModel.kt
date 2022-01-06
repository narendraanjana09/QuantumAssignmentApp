package com.nsa.quantumassignmentapp.ui.login_signup.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nsa.quantumassignmentapp.ui.login_signup.db.User
import com.nsa.quantumassignmentapp.ui.login_signup.db.UserDatabase
import com.nsa.quantumassignmentapp.ui.login_signup.db.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInSignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    val add_query=MutableLiveData<Long>()

    val user_query=MutableLiveData<User>()

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            val ans=repository.addUser(user)
            add_query.postValue(ans)
        }
    }
    fun checkUser(email: String){
        viewModelScope.launch(Dispatchers.IO) {
            val ans=repository.checkUser(email)
            user_query.postValue(ans)

        }
    }
}