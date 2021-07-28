package com.example.databindingdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.databindingdemo.databinding.RecyclerviewRowBinding

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()

    fun setDatalist(data: ArrayList<RecyclerData>) {
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


    class MyViewHolder(val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: RecyclerData) {
            binding.recyclerData = data
            binding.executePendingBindings()
        }
    }
}