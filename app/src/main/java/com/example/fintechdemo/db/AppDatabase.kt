package com.example.fintechdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fintechdemo.datamodels.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

}