package com.shiv.shrikrupamedical.GeneralStore




import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shiv.shrikrupamedical.ItemDetailActivity
import com.shiv.shrikrupamedical.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class Personal: AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val auth = FirebaseAuth.getInstance()


    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal)

        recyclerView = findViewById(R.id.personal_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(showDeleteButton = false)
        recyclerView.adapter = adapter


        adapter.setOnSaveClickListener { product ->
            saveProductToFirestore(product)
        }

        adapter.setOnDeleteClickListener { product ->
            deleteProductFromSavedList(product)
        }


        loadData()
        adapter.setOnItemClickListener { product ->
            val intent = Intent(this, ItemDetailActivity::class.java).apply {
                putExtra("name", product.name)
                putExtra("price", product.price)
                putExtra("tag", product.tag)
                putExtra("imageUrl", product.imageUrl)
            }
            startActivity(intent)
        }
    }


    private fun saveProductToFirestore(product: Product) {
        val user = auth.currentUser
        user?.let {
            db.collection("users")
                .document(user.uid)
                .collection("savedProducts")
                .add(product)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Product saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding product to saved list", e)
                }
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
                    Toast.makeText(this, "Product deleted from saved list", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error deleting product from saved list", e)
                }
        }
    }

    private fun loadData() {
        db.collection("Personal Care")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val productList = mutableListOf<Product>()

                for (document in querySnapshot.documents) {
                    val name = document.getString("name") ?: ""
                    val price = document.getDouble("price") ?: 0.0
                    val tag = document.getString("tag") ?: ""
                    val imageUrl = document.getString("image_url") ?: ""

                    val product = Product(name, price, tag, imageUrl)
                    productList.add(product)
                }

                adapter.setProducts(productList)
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }
}
