package com.app.simuladordejuros.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.simuladordejuros.R
import com.app.simuladordejuros.model.Aplication
import kotlinx.android.synthetic.main.adapter_result.view.*

class AdapterAplication(
    private val aplications: MutableList<Aplication>,
    private val context: Context
) : RecyclerView.Adapter<AdapterAplication.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.adapter_result,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return aplications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var aplication = aplications[position]

       // Log.d("APLICATIONS","Mês${aplication.month}: ${aplication.previousBalance} x ${aplication.tax} = ${aplication.balance} ")

        holder?.let {
            it.textAplication.text = "Mês ${aplication.month}: ${String.format("%.2f", aplication.previousBalance)} x ${String.format("%.3f", aplication.tax)} + ${String.format("%.2f", aplication.aplicationValue)} = ${String.format("%.2f", aplication.balance)}"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textAplication = itemView.id_text_adapter
    }
}