package com.example.fintechdemo.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    var balance: Double,
    val accountNumber: String // Unique identifier for each account
)

