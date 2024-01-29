package com.example.vigilancia
import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.vigilancia.Activity.IncoActivity


class AreasActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areas)
        setupActionBar()

        val buttonIncorporacion: Button = findViewById(R.id.bt_Incorporacion)
        buttonIncorporacion.setOnClickListener {

            val intent = Intent(this, IncoActivity::class.java)
            startActivity(intent)
        }
    }
}