package com.test.fetchrewards.Main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.fetchrewards.Item
import com.test.fetchrewards.databinding.ItemListBinding

class ListAdapter(): ListAdapter<Item, com.test.fetchrewards.Main.ListAdapter.ViewHolder>(
    Diffcallback
) {

    companion object Diffcallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)

    }

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {

            binding.listId.text = item.listId.toString()
            binding.listName.text = item.name

            binding.executePendingBindings()

        }
    }
}