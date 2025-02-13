package com.example.fintechdemo.datamodels
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transactions")  // Ensure table name is correctly set
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sourceAccount: String,
    val destinationAccount: String,
    val sourceAccountName: String,
    val destinationAccountName: String,
    val amount: Double,
    val timestamp: Long = System.currentTimeMillis()
)
