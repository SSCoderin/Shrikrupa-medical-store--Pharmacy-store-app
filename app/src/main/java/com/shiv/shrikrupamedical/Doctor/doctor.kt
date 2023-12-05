package com.shiv.shrikrupamedical.Doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shiv.shrikrupamedical.GeneralStore.UploadGeneralStore
import com.shiv.shrikrupamedical.R
import com.shiv.shrikrupamedical.databinding.ActivityDoctorBinding
import com.shiv.shrikrupamedical.databinding.ActivityOwnerBinding

class doctor : AppCompatActivity() {
    private var binding: ActivityDoctorBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.d4?.setOnClickListener {
            startActivity(Intent(this, Doctor4::class.java))
            finish()
        }

        binding?.d3
            ?.setOnClickListener {
            startActivity(Intent(this, Doctor3::class.java))
            finish()
        }

        binding?.d2?.setOnClickListener {
            startActivity(Intent(this, Doctor2::class.java))
            finish()
        }


        binding?.d1?.setOnClickListener {
            startActivity(Intent(this,doctor1::class.java))
            finish()
        }
    }


}