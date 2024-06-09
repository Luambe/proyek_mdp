package com.example.code.Dashboard.Company

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.constraintlayout.widget.Constraints
import androidx.databinding.library.baseAdapters.R
import androidx.recyclerview.widget.DiffUtil
import com.example.code.data.source.model.Company
import androidx.recyclerview.widget.ListAdapter

class CompanyDiffUtil: DiffUtil.ItemCallback<Company>(){
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.companyId == newItem.companyId
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.companyName == newItem.companyName
    }
}
class CompanyAdapter (
    var onClickListener: ((Company)->Unit)
) : ListAdapter<Company, CompanyAdapter.ViewHolder>(CompanyDiffUtil()){
    class ViewHolder(val row:View) : RecyclerView.ViewHolder(row){
        val tvName:TextView = row.findViewById(com.example.code.R.id.tvName)
        val constraint: Constraints = row.findViewById(com.example.code.R.id.constraint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            com.example.code.R.layout.company_item, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = getItem(position)
        holder.tvName.text = company.companyName

        holder.constraint.setOnClickListener{
            onClickListener?.invoke(company)
        }
    }

}