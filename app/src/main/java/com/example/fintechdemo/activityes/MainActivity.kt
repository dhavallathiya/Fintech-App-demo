package com.example.fintechdemo.activityes

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fintechdemo.adapters.AccountAdapter
import com.example.fintechdemo.databinding.ActivityMainBinding
import com.example.fintechdemo.datamodels.Account
import com.example.fintechdemo.db.Repository
import com.example.fintechdemo.dialog.TransferDialog
import com.example.fintechdemo.factories.MainViewModelFactory
import com.example.fintechdemo.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AccountAdapter
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(Repository.getInstance(this))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        adapter = AccountAdapter { selectedAccount ->
            showTransferDialog(selectedAccount)
        }
        binding.accountRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.accountRecyclerView.adapter = adapter

        // Observe Account List
        viewModel.accounts.observe(this) { accounts ->
            adapter.submitList(accounts)
        }

        binding.btnTransfer.setOnClickListener {
            showTransferDialog()
        }
        binding.btnHistory.setOnClickListener {
            showTransactionHistory()
        }
    }

    private fun showTransferDialog(selectedAccount: Account? = null) {
        TransferDialog(
            this,
            viewModel.accounts.value ?: emptyList()
        ) { source, destination, amount ->
            viewModel.transferMoney(source, destination, amount)
        }.show()
    }


    private fun showTransactionHistory() {
        startActivity(Intent(this, TransactionHistoryActivity::class.java))
    }
}