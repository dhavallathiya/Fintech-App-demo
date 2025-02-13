package com.example.fintechdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintechdemo.databinding.ItemTransactionBinding
import com.example.fintechdemo.datamodels.TransactionEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionAdapter :
    ListAdapter<TransactionEntity, TransactionAdapter.TransactionViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TransactionViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: TransactionEntity) {


            binding.sourceAccountName.text =
                "${transaction.sourceAccountName} (${transaction.sourceAccount}) "
            binding.destinationAccountName.text =
                "${transaction.destinationAccountName} (${transaction.destinationAccount})"
            binding.amount.text = "₹${transaction.amount}"
            binding.transactionTime.text =
                SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                    .format(Date(transaction.timestamp))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TransactionEntity>() {
        override fun areItemsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity) =
            oldItem == newItem
    }
}
