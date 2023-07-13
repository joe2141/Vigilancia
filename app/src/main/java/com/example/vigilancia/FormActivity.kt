package com.example.vigilancia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val spinnerSpecialties = findViewById<Spinner>(R.id.multi)
        val optionsSpinner = arrayOf("Si", "No")
        spinnerSpecialties.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsSpinner)
    }

}