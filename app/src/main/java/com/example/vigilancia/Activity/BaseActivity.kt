import androidx.appcompat.app.AppCompatActivity
import com.example.vigilancia.R

open class BaseActivity : AppCompatActivity() {

    protected fun setupActionBar() {
        // Cambiar el título de la ActionBar
        supportActionBar?.title = "SIIGES"

        // Agregar una imagen a la ActionBar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.icono_barra)  // Reemplaza 'tu_imagen' con el nombre de tu archivo de imagen.
        supportActionBar?.setDisplayUseLogoEnabled(true)
    }
}