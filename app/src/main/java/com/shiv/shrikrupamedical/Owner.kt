package com.shiv.shrikrupamedical

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import androidx.appcompat.app.AppCompatActivity
import com.shiv.shrikrupamedical.DataUploadRetrive.Medicine.PeopleMedicineActivity
import com.shiv.shrikrupamedical.DataUploadRetrive.NewProductUplload
import com.shiv.shrikrupamedical.GeneralStore.UploadGeneralStore

import com.shiv.shrikrupamedical.databinding.ActivityOwnerBinding

class Owner : AppCompatActivity() {
    private var binding: ActivityOwnerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.NewProduct?.setOnClickListener {
            startActivity(Intent(this, NewProductUplload::class.java))
            finish()
        }

        binding?.medicinelist?.setOnClickListener {
            startActivity(Intent(this, PeopleMedicineActivity::class.java))
            finish()
        }
        binding?.GeneralStore?.setOnClickListener {
            startActivity(Intent(this, UploadGeneralStore::class.java))
            finish()
        }


    }
    }


