package com.shiv.shrikrupamedical.GeneralStore

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.shiv.shrikrupamedical.Owner
import com.shiv.shrikrupamedical.R
import com.shiv.shrikrupamedical.databinding.ActivityOwnerBinding
import com.shiv.shrikrupamedical.databinding.ActivityUploadGeneralStoreBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UploadGeneralStore : AppCompatActivity() {
    private var binding: ActivityUploadGeneralStoreBinding? = null

    private lateinit var categorySpinner: Spinner
    private lateinit var imageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var tagEditText: EditText

    private lateinit var selectedCategory: String
    private lateinit var imageUri: Uri

    private val db = FirebaseFirestore.getInstance()
    private val storageRef: StorageReference = FirebaseStorage.getInstance().reference

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.let {
                    imageUri = it.data!!
                    imageView.setImageURI(imageUri)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadGeneralStoreBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        categorySpinner = findViewById(R.id.category_spinner)
        imageView = findViewById(R.id.image_view)
        nameEditText = findViewById(R.id.name_edit_text)
        priceEditText = findViewById(R.id.price_edit_text)
        tagEditText = findViewById(R.id.tag_edit_text)
        val uploadButton: Button = findViewById(R.id.upload_button)
        val submitButton: Button = findViewById(R.id.submit_button)

        val categories = listOf(
            "Skin Care",
            "Baby Care",
            "Health Drinks",
            "Health Food",
            "Bandage",
            "Ayurvedic Care",
            "Personal Care",
            "Health Condition",
            "Diabetic Care"
        )
        binding?.backpage?.setOnClickListener {
            startActivity(Intent(this, Owner::class.java))
            finish()
        }


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            getContent.launch(intent)
        }

        submitButton.setOnClickListener {
            selectedCategory = categorySpinner.selectedItem.toString()
            val name = nameEditText.text.toString()
            val price = priceEditText.text.toString().toDouble()
            val tag = tagEditText.text.toString()

            uploadImageAndData(selectedCategory, name, price, tag)
        }
    }

    private fun uploadImageAndData(category: String, name: String, price: Double, tag: String) {
        val imageRef = storageRef.child("$category/$name.jpg")

        imageRef.putFile(imageUri)
            .addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { imageUrl ->
                    val product = hashMapOf(
                        "name" to name,
                        "price" to price,
                        "tag" to tag,
                        "image_url" to imageUrl.toString()
                    )

                    db.collection(category)
                        .add(product)
                        .addOnSuccessListener {
                            // Data and image uploaded successfully

                            // Refresh the activity
                            val intent = Intent(this, UploadGeneralStore::class.java)
                            finish()
                            startActivity(intent)

                        }
                        .addOnFailureListener { e ->
                            // Handle failure
                        }
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }
}
