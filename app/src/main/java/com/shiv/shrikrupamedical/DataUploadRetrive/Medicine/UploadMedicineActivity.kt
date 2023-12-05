package com.shiv.shrikrupamedical.DataUploadRetrive.Medicine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shiv.shrikrupamedical.R
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class UploadMedicineActivity : AppCompatActivity() {

    private lateinit var medicineImageView: ImageView
    private lateinit var medicineNameEditText: EditText
    private lateinit var personNameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var selectImageButton: Button
    private lateinit var uploadButton: Button

    private var imageUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    imageUri = it
                    medicineImageView.setImageURI(it)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_medicine)

        medicineImageView = findViewById(R.id.medicineImageView)
        medicineNameEditText = findViewById(R.id.medicineNameEditText)
        personNameEditText = findViewById(R.id.personNameEditText)
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        selectImageButton = findViewById(R.id.selectImageButton)
        uploadButton = findViewById(R.id.uploadButton)

        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            getContent.launch(intent)
        }

        uploadButton.setOnClickListener {
            uploadMedicineDetails()
        }
    }

    private fun uploadMedicineDetails() {
        val medicineName = medicineNameEditText.text.toString()
        val personName = personNameEditText.text.toString()
        val phoneNumber = phoneNumberEditText.text.toString()

        if (imageUri != null && medicineName.isNotEmpty() && personName.isNotEmpty() && phoneNumber.isNotEmpty()) {
            // Upload the image to Firebase Storage
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("medicine_images/${UUID.randomUUID()}.jpg")
            val uploadTask = imageRef.putFile(imageUri!!)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val imageUrl = downloadUri.toString()

                    // Now, upload the details to Firestore
                    val medicineDetails = hashMapOf(
                        "name" to medicineName,
                        "personName" to personName,
                        "phoneNumber" to phoneNumber,
                        "imageUrl" to imageUrl,
                        "timestamp" to System.currentTimeMillis() // Added timestamp
                    )

                    val currentUser = FirebaseAuth.getInstance().currentUser
                    if (currentUser != null) {
                        val db = FirebaseFirestore.getInstance()
                        db.collection("medicines")
                            .document(currentUser.uid)
                            .collection("uploaded_medicines")
                            .add(medicineDetails)
                            .addOnSuccessListener {
                                // Successfully uploaded to Firestore
                                clearFields()
                            }
                            .addOnFailureListener {
                                // Handle failure
                            }
                    }
                }
            }
        } else {
            // Display error message, some fields are empty
        }
    }

    private fun clearFields() {
        medicineNameEditText.text.clear()
        personNameEditText.text.clear()
        phoneNumberEditText.text.clear()
        medicineImageView.setImageResource(R.drawable.ic_launcher_background)
    }
}
