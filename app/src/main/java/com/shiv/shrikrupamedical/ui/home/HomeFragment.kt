package com.shiv.shrikrupamedical.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.shiv.shrikrupamedical.ContactUs.contact
import com.shiv.shrikrupamedical.DataUploadRetrive.ImageDetails
import com.shiv.shrikrupamedical.DataUploadRetrive.ImageListAdapter
import com.shiv.shrikrupamedical.DataUploadRetrive.Medicine.UploadMedicineActivity
import com.shiv.shrikrupamedical.Doctor.doctor
import com.shiv.shrikrupamedical.GeneralStore.Ayurvedic
import com.shiv.shrikrupamedical.GeneralStore.BabyCare
import com.shiv.shrikrupamedical.GeneralStore.Bandage
import com.shiv.shrikrupamedical.GeneralStore.Condition
import com.shiv.shrikrupamedical.GeneralStore.Diabetic
import com.shiv.shrikrupamedical.GeneralStore.HealthDrinks
import com.shiv.shrikrupamedical.GeneralStore.HealthFood
import com.shiv.shrikrupamedical.GeneralStore.Personal
import com.shiv.shrikrupamedical.GeneralStore.SavedListActivity
import com.shiv.shrikrupamedical.GeneralStore.SkinCare
import com.shiv.shrikrupamedical.MainActivity
import com.shiv.shrikrupamedical.Precaution.precaution
import com.shiv.shrikrupamedical.R
import com.shiv.shrikrupamedical.databinding.FragmentHomeBinding
import com.shiv.shrikrupamedical.healthcare.EyeCare
import com.shiv.shrikrupamedical.healthcare.ankle
import com.shiv.shrikrupamedical.healthcare.back
import com.shiv.shrikrupamedical.healthcare.head
import com.shiv.shrikrupamedical.healthcare.heart
import com.shiv.shrikrupamedical.healthcare.kidney
import com.shiv.shrikrupamedical.healthcare.kneepain
import com.shiv.shrikrupamedical.healthcare.lungs
import com.shiv.shrikrupamedical.healthcare.neck
import com.shiv.shrikrupamedical.healthcare.stomach
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageListAdapter


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root







        val viewPager = root.findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = root.findViewById<TabLayout>(R.id.tabLayout)

        val images = listOf(R.drawable.orange, R.drawable.apple, R.drawable.banana, R.drawable.a, R.drawable.b, R.drawable.grapes, R.drawable.cucumber, R.drawable.potato, R.drawable.strawberry, R.drawable.tomato,R.drawable.q1,R.drawable.q2,R.drawable.q3,R.drawable.q4,R.drawable.q5,R.drawable.q6,R.drawable.q7,R.drawable.q8,R.drawable.q9,R.drawable.q10) // Add your image resources here

        val adapters = ImageSliderAdapter(requireContext(), images)
        viewPager.adapter = adapters
        tabLayout.setupWithViewPager(viewPager, true)

        val myImageView = root.findViewById<ImageView>(R.id.upload)
        myImageView?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, UploadMedicineActivity::class.java)
            startActivity(intent)
        }
        val myImageView1 = root.findViewById<ImageView>(R.id.contact)
        myImageView1?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, contact::class.java)
            startActivity(intent)
        }
        val myImageView2 = root.findViewById<ImageView>(R.id.doctor)
        myImageView2?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, doctor::class.java)
            startActivity(intent)
        }
        val myImageView3 = root.findViewById<ImageView>(R.id.precaution)
        myImageView3?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, precaution::class.java)
            startActivity(intent)
        }
        val myImageView4 = root.findViewById<ImageView>(R.id.eye)
        myImageView4?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, EyeCare::class.java)
            startActivity(intent)
        }
        val myImageView5 = root.findViewById<ImageView>(R.id.stomach)
        myImageView5?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, stomach::class.java)
            startActivity(intent)
        }
        val myImageView6 = root.findViewById<ImageView>(R.id.heart)
        myImageView6?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, heart::class.java)
            startActivity(intent)
        }
        val myImageView7 = root.findViewById<ImageView>(R.id.back)
        myImageView7?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, back::class.java)
            startActivity(intent)
        }
        val myImageView8 = root.findViewById<ImageView>(R.id.head)
        myImageView8?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, head::class.java)
            startActivity(intent)
        }
        val myImageView9 = root.findViewById<ImageView>(R.id.kidneys)
        myImageView9?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, kidney::class.java)
            startActivity(intent)
        }
        val myImageView10 = root.findViewById<ImageView>(R.id.ankle)
        myImageView10?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, ankle::class.java)
            startActivity(intent)
        }
        val myImageView11 = root.findViewById<ImageView>(R.id.kneepain)
        myImageView11?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, kneepain::class.java)
            startActivity(intent)
        }

        val myImageView12 = root.findViewById<ImageView>(R.id.neck)
        myImageView12?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, neck::class.java)
            startActivity(intent)
        }
        val myImageView13 = root.findViewById<ImageView>(R.id.lungs)
        myImageView13?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, lungs::class.java)
            startActivity(intent)
        }
        val myImageView14 = root.findViewById<ImageView>(R.id.skin)
        myImageView14?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, SkinCare::class.java)
            startActivity(intent)
        }
        val myImageView15 = root.findViewById<ImageView>(R.id.baby)
        myImageView15?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, BabyCare::class.java)
            startActivity(intent)
        }
        val myImageView16 = root.findViewById<ImageView>(R.id.health)
        myImageView16?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, HealthDrinks::class.java)
            startActivity(intent)
        }
        val myImageView17 = root.findViewById<ImageView>(R.id.food)
        myImageView17?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, HealthFood::class.java)
            startActivity(intent)
        }
        val myImageView18 = root.findViewById<ImageView>(R.id.Bandage)
        myImageView18?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, Bandage::class.java)
            startActivity(intent)
        }
        val myImageView19 = root.findViewById<ImageView>(R.id.ayurvedic)
        myImageView19?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, Ayurvedic::class.java)
            startActivity(intent)
        }
        val myImageView20 = root.findViewById<ImageView>(R.id.personal)
        myImageView20?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, Personal::class.java)
            startActivity(intent)
        }
        val myImageView21 = root.findViewById<ImageView>(R.id.condition)
        myImageView21?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, Condition::class.java)
            startActivity(intent)
        }
        val myImageView22 = root.findViewById<ImageView>(R.id.Diabetic)
        myImageView22?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, Diabetic::class.java)
            startActivity(intent)
        }
        val myImageView23 = root.findViewById<Button>(R.id.homepage)
        myImageView23?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        val myImageView24 = root.findViewById<Button>(R.id.doctorpage)
        myImageView24?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, doctor::class.java)
            startActivity(intent)
        }
        val myImageView25 = root.findViewById<Button>(R.id.precautionpage)
        myImageView25?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, precaution::class.java)
            startActivity(intent)
        }
        val myImageView26 = root.findViewById<Button>(R.id.uploadpage)
        myImageView26?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, UploadMedicineActivity::class.java)
            startActivity(intent)
        }

        val myImageView27 = root.findViewById<Button>(R.id.contactpage)
        myImageView27?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, contact::class.java)
            startActivity(intent)
        }

        val myImageView28 = root.findViewById<Button>(R.id.savedlist)
        myImageView28?.setOnClickListener {
            // Handle ImageView click event
            val intent = Intent(activity, SavedListActivity::class.java)
            startActivity(intent)
        }
        recyclerView = root.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ImageListAdapter(emptyList())
        recyclerView.adapter = adapter

        loadImagesFromFirestore()

        return root

    }



    private fun loadImagesFromFirestore() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("images")
                .document(currentUser.uid)
                .collection("uploaded_images")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        // Handle error
                        return@addSnapshotListener
                    }

                    if (snapshot != null && !snapshot.isEmpty) {
                        val images = snapshot.documents.mapNotNull { document ->
                            document.toObject(ImageDetails::class.java)
                        }
                        adapter.updateImages(images)
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}