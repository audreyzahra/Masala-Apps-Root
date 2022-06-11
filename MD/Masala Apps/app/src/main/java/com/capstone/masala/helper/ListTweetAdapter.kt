package com.capstone.masala.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.masala.data.ListTweet
import com.capstone.masala.databinding.ItemListLokasiBinding
import com.capstone.masala.databinding.ItemTweetBinding

class ListTweetAdapter : RecyclerView.Adapter<ListTweetAdapter.ListViewHolder>(){

    private val list = ArrayList<ListTweet>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: ArrayList<ListTweet>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ListTweet){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
            binding.apply {
                tvUsername.text = data.username
                tvTweet.text = data.caption
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemTweetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: ListTweet)
    }

}