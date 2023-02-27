package com.sriraksha.artsgallery.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sriraksha.artsgallery.databinding.ItemArtBinding

class ArtGalleryListAdapter(private val onArtItemClick: OnArtItemClick) : ListAdapter<Int, ArtGalleryListAdapter.ViewHolder>(DIFF_CALLBACK()) {

    class DIFF_CALLBACK : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemArtBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bindTo(getItem(position))
    }

    inner class ViewHolder(private val itemArtBinding: ItemArtBinding) :
        RecyclerView.ViewHolder(itemArtBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bindTo(artName: Int) {
            itemArtBinding.artName.text = "Art name : $artName"
            itemArtBinding.artName.setOnClickListener {
                onArtItemClick.onArtItemClick(artName.toString())
            }
        }
    }

    interface OnArtItemClick {
        fun onArtItemClick(objectId: String)
    }
}
