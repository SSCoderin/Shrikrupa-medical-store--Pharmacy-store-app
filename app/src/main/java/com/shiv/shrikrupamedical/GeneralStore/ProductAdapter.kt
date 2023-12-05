package com.shiv.shrikrupamedical.GeneralStore
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.shiv.shrikrupamedical.R

class ProductAdapter(private val showDeleteButton: Boolean) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {



    private var products = mutableListOf<Product>()
    private var onItemClickListener: ((Product) -> Unit)? = null
    private var onSaveClickListener: ((Product) -> Unit)? = null
    private var onDeleteClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnSaveClickListener(listener: (Product) -> Unit) {
        onSaveClickListener = listener
    }

    fun setOnDeleteClickListener(listener: (Product) -> Unit) {
        onDeleteClickListener = listener
    }










    fun setProducts(products: List<Product>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        // Set click listener for item
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(product)
        }

        // Set click listener for Save button
        holder.saveButton.setOnClickListener {
            onSaveClickListener?.invoke(product)
        }

        // Set click listener for Delete button
        holder.deleteButton.setOnClickListener {
            onDeleteClickListener?.invoke(product)
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.product_image)
        private val nameTextView: TextView = itemView.findViewById(R.id.product_name)
        private val priceTextView: TextView = itemView.findViewById(R.id.product_price)
        private val tagTextView: TextView = itemView.findViewById(R.id.product_tag)
        val saveButton: Button = itemView.findViewById(R.id.save_button)
        val deleteButton: Button = itemView.findViewById(R.id.delete_button)

        init {
            // Show or hide the delete button based on the context
            deleteButton.visibility = if (showDeleteButton) View.VISIBLE else View.GONE
        }
        fun bind(product: Product) {
            Glide.with(itemView.context)
                .load(product.imageUrl)
                .into(imageView)

            nameTextView.text = product.name
            priceTextView.text = "Rs ${product.price}"
            tagTextView.text = product.tag

        }


        }
    }

