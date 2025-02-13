package com.example.fintechdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechdemo.datamodels.Account
import com.example.fintechdemo.datamodels.TransactionEntity
import com.example.fintechdemo.db.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    // Mutable list of accounts
    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> get() = _accounts

    // List of mock accounts
    init {
        _accounts.value = listOf(
            Account(1, "Emma Wilson", 2500.0, "112233445"),
            Account(2, "Michael Brown", 8000.0, "998877665"),
            Account(3, "Sophia Martinez", 4500.0, "556677889"),
            Account(4, "James Anderson", 6000.0, "443322110"),
            Account(5, "Olivia Taylor", 3500.0, "221100334"),
            Account(6, "William Lee", 9000.0, "667788990"),
            Account(7, "Emily Davis", 1200.0, "889900112"),
            Account(8, "Alice Smith", 3000.0, "987654321"),
            Account(9, "Bob Johnson", 7000.0, "567890123")
        )
    }

    // Transactions from Room
    val transactions: LiveData<List<TransactionEntity>> = repository.getAllTransactions()

    // Perform money transfer
    fun transferMoney(source: Account, destination: Account, amount: Double) {
        val updatedList = _accounts.value?.map {
            when (it.accountNumber) {
                source.accountNumber -> it.copy(balance = it.balance - amount)
                destination.accountNumber -> it.copy(balance = it.balance + amount)
                else -> it
            }
        } ?: emptyList()

        _accounts.value = updatedList

        // Store transaction in Room
        viewModelScope.launch {
            repository.addTransaction(
                TransactionEntity(
                    sourceAccount = source.accountNumber,
                    destinationAccount = destination.accountNumber,
                    sourceAccountName = source.name,
                    destinationAccountName = destination.name,
                    amount = amount
                )
            )
        }
    }
}