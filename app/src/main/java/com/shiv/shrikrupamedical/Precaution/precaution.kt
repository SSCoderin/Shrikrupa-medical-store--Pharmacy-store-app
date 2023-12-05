package com.shiv.shrikrupamedical.Precaution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.shiv.shrikrupamedical.R

class precaution : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_precaution)


        val button: Button = findViewById(R.id.Flu)

        button.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.influenza, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button1: Button = findViewById(R.id.covid)

        button1.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.covid19, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button2: Button = findViewById(R.id.cold)

        button2.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.cold, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button3: Button = findViewById(R.id.hyper)

        button3.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.hypertension, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button4: Button = findViewById(R.id.diadetes)

        button4.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.diabetes, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button5: Button = findViewById(R.id.asthma)

        button5.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.asthma, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button6: Button = findViewById(R.id.tb)

        button6.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.tb, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }

        val button7: Button = findViewById(R.id.malaria)
        button7.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.malaria, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button8: Button = findViewById(R.id.dengue)
        button8.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.dengue, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }

        val button9: Button = findViewById(R.id.depression)
        button9.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.depression, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button10: Button = findViewById(R.id.kidney)
        button10.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.kidney, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }

        val button11: Button = findViewById(R.id.hiv)
        button11.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.hiv, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }

        val button12: Button = findViewById(R.id.Migraine)
        button12.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.migrane, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button13: Button = findViewById(R.id.a)
        button13.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitamina, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button14: Button = findViewById(R.id.b1)
        button14.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb1, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button15: Button = findViewById(R.id.b2)
        button15.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb2, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button16: Button = findViewById(R.id.b3)
        button16.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb3, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button17: Button = findViewById(R.id.b4)
        button17.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb4, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button18: Button = findViewById(R.id.b5)
        button18.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb5, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }

        val button20: Button = findViewById(R.id.b7)
        button20.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb7, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button21: Button = findViewById(R.id.b8)
        button21.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb8, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button22: Button = findViewById(R.id.b9)
        button22.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb9, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button23: Button = findViewById(R.id.b12)
        button23.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminb12, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button24: Button = findViewById(R.id.c)
        button24.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitaminc, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button25: Button = findViewById(R.id.d)
        button25.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitamind, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
        val button26: Button = findViewById(R.id.e)
        button26.setOnClickListener {
            // Inflate the "flu.xml" layout
            val view = layoutInflater.inflate(R.layout.vitamine, null)

            // Create a dialog to display the layout
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
    }


}