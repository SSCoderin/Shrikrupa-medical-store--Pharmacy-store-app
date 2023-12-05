package com.shiv.shrikrupamedical

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.shiv.shrikrupamedical.Owner
import com.shiv.shrikrupamedical.R
import com.shiv.shrikrupamedical.databinding.ActivityAboutShopBinding

class AboutShop : AppCompatActivity() {
    private var binding: ActivityAboutShopBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutShopBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }


    fun showPasswordDialog(view: android.view.View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Password")

        val input = EditText(this)
        input.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setView(input)


        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val password = input.text.toString()

            // Check if password is correct (You can replace "your_password" with your actual password)
            if (password == "12321") {
                val intent = Intent(this, Owner::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}

