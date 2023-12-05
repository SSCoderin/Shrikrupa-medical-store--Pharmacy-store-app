package com.shiv.shrikrupamedical.DataUploadRetrive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shiv.shrikrupamedical.R

class ImageListAdapter(private var images: List<ImageDetails>) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.doctor)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = images[position]
        Glide.with(holder.itemView.context)
            .load(currentImage.url)
            .into(holder.imageView)
        holder.nameTextView.text = currentImage.name
        holder.priceTextView.text = String.format("Rs%.2f", currentImage.price)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun updateImages(newImages: List<ImageDetails>) {
        images = newImages
        notifyDataSetChanged()
    }
}
