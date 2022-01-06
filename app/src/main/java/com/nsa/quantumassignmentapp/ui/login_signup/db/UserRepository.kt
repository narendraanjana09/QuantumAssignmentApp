package com.nsa.quantumassignmentapp.ui.login_signup.db

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

     fun addUser(user: User)=userDao.addUser(user)
    fun checkUser(email: String)=userDao.checkUser(email)


}