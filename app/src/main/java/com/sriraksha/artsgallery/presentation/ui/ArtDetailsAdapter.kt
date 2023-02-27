package com.sriraksha.artsgallery.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sriraksha.artsgallery.databinding.ItemArtDetailsBinding
import com.sriraksha.artsgallery.util.loadProfilePhoto

class ArtDetailsAdapter : ListAdapter<String, ArtDetailsAdapter.ViewHolder>(DIFF_CALLBACK) {

    object DIFF_CALLBACK : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemArtDetailsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bindTo(getItem(position))
    }

    inner class ViewHolder(private val itemArtDetailsBinding: ItemArtDetailsBinding) :
        RecyclerView.ViewHolder(itemArtDetailsBinding.root) {
        fun bindTo(artImgUrl: String) {
            itemArtDetailsBinding.artImg.loadProfilePhoto(artImgUrl, itemArtDetailsBinding.artImg.context)
        }
    }
}
