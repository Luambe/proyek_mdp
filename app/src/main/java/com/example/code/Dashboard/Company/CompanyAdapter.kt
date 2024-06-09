package com.example.code.Dashboard.Company

import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.code.data.source.model.Company
import androidx.recyclerview.widget.ListAdapter
import com.example.code.databinding.CompanyItemBinding

class CompanyDiffUtil: DiffUtil.ItemCallback<Company>(){
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.companyId == newItem.companyId
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.companyName == newItem.companyName && oldItem.ownerId == newItem.ownerId
    }
}
class CompanyAdapter (
    var onClickListener: ((Company)->Unit)? = null
) : ListAdapter<Company, CompanyAdapter.ViewHolder>(CompanyDiffUtil()){
    class ViewHolder(val binding:CompanyItemBinding) : RecyclerView.ViewHolder(binding.root){

        val tvName:TextView = binding.tvName
        val constraint: ConstraintLayout = binding.constraint
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:CompanyItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            com.example.code.R.layout.company_item,
            parent,
            false
        )
        Log.d("hahahaha", "hahaha")
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = getItem(position)
        println("masuk gasih")
        Log.d("cek Data", "$company")
        holder.tvName.text = company.companyName

        holder.constraint.setOnClickListener{
            onClickListener?.invoke(company)
        }
    }

}