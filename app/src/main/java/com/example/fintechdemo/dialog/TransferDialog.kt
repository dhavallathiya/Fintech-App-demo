package com.example.fintechdemo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.fintechdemo.R
import com.example.fintechdemo.adapters.AccountSpinnerAdapter
import com.example.fintechdemo.databinding.DialogTransferBinding
import com.example.fintechdemo.datamodels.Account

class TransferDialog(
    context: Context,
    private val accountList: List<Account>,
    private val onTransfer: (source: Account, destination: Account, amount: Double) -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogTransferBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        // Set full width
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        setupUI()
    }

    private fun setupUI() {
        binding.spinnerSourceAccount.adapter = AccountSpinnerAdapter(context, accountList)
        binding.spinnerDestinationAccount.adapter = AccountSpinnerAdapter(context, accountList)

        binding.btnConfirmTransfer.setOnClickListener {
            val sourceAccount = binding.spinnerSourceAccount.selectedItem as Account
            val destinationAccount = binding.spinnerDestinationAccount.selectedItem as Account
            val amountText = binding.etAmount.text.toString()

            if (amountText.isEmpty()) {
                Toast.makeText(context,
                    context.getString(R.string.enter_transfer_amount), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null || amount <= 0) {
                Toast.makeText(context,
                    context.getString(R.string.enter_valid_transfer_amount), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (sourceAccount == destinationAccount) {
                Toast.makeText(
                    context,
                    context.getString(R.string.source_and_destination_account_cannot_be_the_same),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (amount > sourceAccount.balance) {
                Toast.makeText(context,
                    context.getString(R.string.insufficient_balance), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show confirmation summary
            binding.tvSummary.text =
                "Transaction Alert:\n ₹${amount} has been debited from ${sourceAccount.name} and credited to ${destinationAccount.name}."


            binding.tvSummary.visibility = View.VISIBLE

            binding.spinnerSourceAccount.visibility = View.GONE
            binding.spinnerDestinationAccount.visibility = View.GONE
            binding.etAmount.visibility = View.GONE

            binding.btnConfirmTransfer.setOnClickListener {
                onTransfer(sourceAccount, destinationAccount, amount)
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            binding.spinnerSourceAccount.visibility = View.VISIBLE
            binding.spinnerDestinationAccount.visibility = View.VISIBLE
            binding.etAmount.visibility = View.VISIBLE

            dismiss()
        }
    }
}
