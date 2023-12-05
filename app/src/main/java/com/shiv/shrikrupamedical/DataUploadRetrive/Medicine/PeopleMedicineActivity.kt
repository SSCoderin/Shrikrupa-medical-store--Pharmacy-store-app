package com.shiv.shrikrupamedical.DataUploadRetrive.Medicine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shiv.shrikrupamedical.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

import android.util.Log
import com.shiv.shrikrupamedical.Owner
import com.shiv.shrikrupamedical.databinding.ActivityNewProductUplloadBinding
import com.shiv.shrikrupamedical.databinding.ActivityPeopleMedicineBinding

import com.google.firebase.firestore.QuerySnapshot

class PeopleMedicineActivity : AppCompatActivity() {
    private var binding: ActivityPeopleMedicineBinding? = null


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MedicineListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeopleMedicineBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.backpage?.setOnClickListener {
            startActivity(Intent(this, Owner::class.java))
            finish()

        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MedicineListAdapter(emptyList())
        recyclerView.adapter = adapter

        loadMedicineDetailsFromFirestore()
    }


    private fun loadMedicineDetailsFromFirestore() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val db = FirebaseFirestore.getInstance()
            val collectionRef = db.collection("medicines")
                .document("jpAnUyRETzeKQOPhKuKNsFdppph2")
                .collection("uploaded_medicines")

            collectionRef
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documents = task.result?.documents
                        val medicines = documents?.mapNotNull { document ->
                            document.toObject(MedicineDetails::class.java)
                        }
                        if (medicines != null) {
                            adapter.updateMedicineList(medicines)
                        } else {
                            Log.d("Firestore", "No medicines found")
                        }
                    } else {
                        Log.e("Firestore", "Error getting documents: ", task.exception)
                    }
                }
        }
    }



}
