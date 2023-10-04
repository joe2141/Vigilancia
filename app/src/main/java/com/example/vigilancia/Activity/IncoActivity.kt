package com.example.vigilancia.Activity
import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import com.example.vigilancia.R

class IncoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inco)
        setupActionBar()

        val radioGroup: RadioGroup = findViewById(R.id.radio_grup) // Asegúrate de darle un ID a tu RadioGroup en el XML

        // Si necesitas establecer un listener para saber cuándo se selecciona un RadioButton, puedes hacerlo así:
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_si -> {
                    // Aquí el código para cuando se selecciona "SÍ"
                }
                R.id.radio_no -> {
                    // Aquí el código para cuando se selecciona "NO"
                }
                R.id.radio_na -> {
                    // Aquí el código para cuando se selecciona "N/A"
                }
            }
        }
    }
}