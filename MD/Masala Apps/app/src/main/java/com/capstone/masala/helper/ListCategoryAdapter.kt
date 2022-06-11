package com.capstone.masala.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.masala.data.ListCategory
import com.capstone.masala.databinding.ItemListCategoryBinding
import com.capstone.masala.databinding.ItemListLokasiBinding

class ListCategoryAdapter : RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>(){

    private val list = ArrayList<ListCategory>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(data: ArrayList<ListCategory>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemListCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ListCategory){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
            binding.apply {
                tvCategory.text = data.category
                tvJumlah.text = list.size.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: ListCategory)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListCategory>() {
            override fun areItemsTheSame(oldItem: ListCategory, newItem: ListCategory): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListCategory, newItem: ListCategory): Boolean {
                return oldItem.category == newItem.category
            }
        }
    }
}