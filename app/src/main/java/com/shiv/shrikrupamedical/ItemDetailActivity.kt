package com.shiv.shrikrupamedical

// ProductDetailActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.shiv.shrikrupamedical.R

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val imageView: ImageView = findViewById(R.id.product_detail_image)
        val nameTextView: TextView = findViewById(R.id.product_detail_name)
        val priceTextView: TextView = findViewById(R.id.product_detail_price)
        val tagTextView: TextView = findViewById(R.id.product_detail_tag)

        // Get the product details from the Intent
        val name = intent.getStringExtra("name")
        val price = intent.getDoubleExtra("price", 0.0)
        val tag = intent.getStringExtra("tag")
        val imageUrl = intent.getStringExtra("imageUrl")

        // Set the details in the views
        nameTextView.text = name
        priceTextView.text = "Price: $$price"
        tagTextView.text = "Tag: $tag"

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}
