package com.shiv.shrikrupamedical.DataUploadRetrive
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.shiv.shrikrupamedical.Owner
import com.shiv.shrikrupamedical.R
import com.shiv.shrikrupamedical.databinding.ActivityNewProductUplloadBinding
import com.shiv.shrikrupamedical.databinding.ActivityUploadGeneralStoreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class NewProductUplload : AppCompatActivity() {
    private var binding: ActivityNewProductUplloadBinding? = null

    private lateinit var imageView: ImageView
    private lateinit var imageNameEditText: EditText
    private lateinit var imagePriceEditText: EditText
    private lateinit var selectImageButton: Button
    private lateinit var uploadButton: Button

    private var imageUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri
            imageView.setImageURI(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewProductUplloadBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.backpage?.setOnClickListener {
            startActivity(Intent(this, Owner::class.java))
            finish()
        }

        imageView = findViewById(R.id.doctor)
        imageNameEditText = findViewById(R.id.imageNameEditText)
        imagePriceEditText = findViewById(R.id.imagePriceEditText)
        selectImageButton = findViewById(R.id.selectImageButton)
        uploadButton = findViewById(R.id.uploadButton)

        selectImageButton.setOnClickListener {
            getContent.launch("image/*")
        }

        uploadButton.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        val imageName = imageNameEditText.text.toString()
        val imagePrice = imagePriceEditText.text.toString().toDouble()

        if (imageUri != null && imageName.isNotEmpty()) {
            val storageRef =
                FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}.jpg")
            storageRef.putFile(imageUri!!)
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val imageURL = uri.toString()

                        val imageDetails = hashMapOf(
                            "name" to imageName,
                            "price" to imagePrice,
                            "url" to imageURL,
                            "timestamp" to System.currentTimeMillis() // Add a timestamp
                        )

                        val currentUser = FirebaseAuth.getInstance().currentUser
                        if (currentUser != null) {
                            val db = FirebaseFirestore.getInstance()
                            db.collection("images")
                                .document(currentUser.uid)
                                .collection("uploaded_images")
                                .add(imageDetails)
                                .addOnSuccessListener {
                                    // Successfully uploaded to Firestore
                                    clearFields() // Clear input fields and image view
                                }
                                .addOnFailureListener {
                                    // Handle failure
                                }
                        }

                    }
                }


        }
    }

    private fun clearFields() {
        imageNameEditText.text.clear()
        imagePriceEditText.text.clear()
        imageView.setImageResource(R.drawable.ic_launcher_foreground) // Set default image
    }
}

