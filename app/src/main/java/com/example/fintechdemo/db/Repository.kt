package com.example.fintechdemo.db

import android.content.Context
import androidx.room.Room
import com.example.fintechdemo.datamodels.TransactionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val transactionDao: TransactionDao) {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(context: Context): Repository {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fintech-db"
                ).build()
                instance = Repository(database.transactionDao())
                instance!!
            }
        }
    }

    fun getAllTransactions() = transactionDao.getAllTransactions()

    suspend fun addTransaction(transaction: TransactionEntity) {
        withContext(Dispatchers.IO) {
            transactionDao.insertTransaction(transaction)
        }
    }
}
