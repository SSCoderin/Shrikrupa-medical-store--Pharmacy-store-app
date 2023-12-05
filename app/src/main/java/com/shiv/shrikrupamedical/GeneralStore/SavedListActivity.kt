package com.shiv.shrikrupamedical.GeneralStore
// SavedListActivity.kt
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shiv.shrikrupamedical.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class SavedListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_list)

        recyclerView = findViewById(R.id.saved_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(showDeleteButton = true)
        recyclerView.adapter = adapter
        loadSavedProducts()
    }

    private fun loadSavedProducts() {
        val user = auth.currentUser
        user?.let {
            db.collection("users")
                .document(user.uid)
                .collection("savedProducts")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val productList = mutableListOf<Product>()

                    for (document in querySnapshot.documents) {
                        val name = document.getString("name") ?: ""
                        val price = document.getDouble("price") ?: 0.0
                        val tag = document.getString("tag") ?: ""
                        val imageUrl = document.getString("imageUrl") ?: ""

                        val product = Product(name, price, tag, imageUrl)
                        productList.add(product)
                    }

                    adapter.setProducts(productList)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error getting saved products", e)
                }
        }

        // Set click listener for delete button in adapter
        adapter.setOnDeleteClickListener { product ->
            deleteProductFromSavedList(product)
        }
    }

    private fun deleteProductFromSavedList(product: Product) {
        val user = auth.currentUser
        user?.let {
            db.collection("users")
                .document(user.uid)
                .collection("savedProducts")
                .whereEqualTo("name", product.name) // Assuming "name" is the unique identifier
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        document.reference.delete()
                    }
                    loadSavedProducts() // Refresh the list after deletion
                    Toast.makeText(this, "Product deleted from saved list", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error deleting product from saved list", e)
                }
        }
    }
}
