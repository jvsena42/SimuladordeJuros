package com.app.simuladordejuros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.AdapterResultBinding
import com.app.simuladordejuros.main.CapitalInvestment
import com.app.simuladordejuros.util.threeDecimal
import com.app.simuladordejuros.util.twoDecimal
import kotlinx.android.synthetic.main.adapter_result.view.*

class AdapterResult: RecyclerView.Adapter<AdapterResult.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<CapitalInvestment>(){

        override fun areItemsTheSame(oldItem: CapitalInvestment, newItem: CapitalInvestment): Boolean {
            return oldItem.month == newItem.month
        }

        override fun areContentsTheSame(oldItem: CapitalInvestment, newItem: CapitalInvestment): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterResultBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: AdapterResultBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CapitalInvestment) = binding.run {
            itemResultTX.text = "${root.context.getString(R.string.month)} ${item.month}: ${item.previousBalance.twoDecimal()} x ${item.tax.threeDecimal()} + ${item.applicationValue.twoDecimal()} = ${item.balance.twoDecimal()}"
        }
    }
}