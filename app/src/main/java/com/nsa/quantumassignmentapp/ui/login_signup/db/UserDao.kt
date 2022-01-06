package com.nsa.quantumassignmentapp.ui.login_signup.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addUser(user: User):Long

    @Query("SELECT * FROM user_table WHERE email = :email")
     fun checkUser(email:String):User

    @Query("SELECT * FROM user_table ")
    fun readAllData(): LiveData<List<User>>

}