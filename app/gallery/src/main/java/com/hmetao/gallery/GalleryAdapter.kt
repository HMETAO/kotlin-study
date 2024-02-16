package com.hmetao.gallery

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hmetao.gallery.databinding.GalleryCellBinding
import com.hmetao.gallery.model.PhotoItem
import io.supercharge.shimmerlayout.ShimmerLayout

class GalleryAdapter : ListAdapter<PhotoItem, MyViewHolder>(DifferCallback) {


    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) 1 else 0
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder: MyViewHolder
        if (viewType == 0) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.gallery_cell, parent, false)
            holder = MyViewHolder(view)
            holder.itemView.setOnClickListener {
                Bundle().apply {
                    putParcelable("PHOTO", getItem(holder.adapterPosition))
                    holder.itemView.findNavController()
                        .navigate(R.id.action_galleryFragment_to_photoFragment, this)
                }
            }
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.gallery_footer, parent, false)
            (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
            holder = MyViewHolder(view)
        }

        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (getItemViewType(position) == 1) return
        val shimmerLayout = holder.itemView.findViewById<ShimmerLayout>(R.id.shimmerLayoutCell)
        shimmerLayout.apply {
            setShimmerColor(0x55ffffff)
            setShimmerAngle(0)
            startShimmerAnimation()
        }
        Glide.with(holder.itemView)
            .load(getItem(position).webformatURL)
            .placeholder(R.drawable.baseline_insert_photo_24)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false.also {
                        shimmerLayout?.stopShimmerAnimation()
                    }
                }
            })
            .into(holder.itemView.findViewById(R.id.imageViewCell))
    }

    object DifferCallback : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem == newItem
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}

