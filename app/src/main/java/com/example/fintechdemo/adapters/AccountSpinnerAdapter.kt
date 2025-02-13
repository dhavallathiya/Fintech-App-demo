package com.example.fintechdemo.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fintechdemo.datamodels.Account

class AccountSpinnerAdapter(
    context: Context,
    private val accounts: List<Account>
) : ArrayAdapter<Account>(context, android.R.layout.simple_spinner_item, accounts) {

    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = accounts[position].name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        view.text = accounts[position].name
        return view
    }
}
