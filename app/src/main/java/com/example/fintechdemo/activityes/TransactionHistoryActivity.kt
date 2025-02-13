package com.example.fintechdemo.activityes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fintechdemo.R
import com.example.fintechdemo.adapters.TransactionAdapter
import com.example.fintechdemo.databinding.ActivityTransactionHistoryBinding
import com.example.fintechdemo.db.Repository
import com.example.fintechdemo.factories.MainViewModelFactory
import com.example.fintechdemo.viewmodels.MainViewModel

class TransactionHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionHistoryBinding
    private lateinit var adapter: TransactionAdapter
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(Repository.getInstance(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        adapter = TransactionAdapter()
        binding.transactionHistoryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.transactionHistoryRecyclerView.adapter = adapter

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        // Observe Transaction List
        viewModel.transactions.observe(this) { transactions ->
            if (transactions.isEmpty()) {
                binding.tvNoHistory.text = getString(R.string.no_transaction_history_found)
            }
            adapter.submitList(transactions)
        }
    }
}
