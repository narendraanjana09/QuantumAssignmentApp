package com.nsa.quantumassignmentapp.ui.login_signup.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user_table")
data class User(


    @NotNull
    val name: String,

    @PrimaryKey
    @NotNull
    val email: String,
    @NotNull
    val contact: String,
    @NotNull
    val password:String
)