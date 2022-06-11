package com.capstone.masala.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.capstone.masala.data.ListLocation
import com.capstone.masala.databinding.ItemListLokasiBinding

class ListLokasiAdapter : RecyclerView.Adapter<ListLokasiAdapter.ListViewHolder>(){

    private val list = ArrayList<ListLocation>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(data: ArrayList<ListLocation>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemListLokasiBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ListLocation){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
            binding.apply {
                tvCategory
                tvJumlah.text = list.size.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemListLokasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: ListLocation)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListLocation>() {
            override fun areItemsTheSame(oldItem: ListLocation, newItem: ListLocation): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListLocation, newItem: ListLocation): Boolean {
                return oldItem.location == newItem.location
            }
        }
    }
}