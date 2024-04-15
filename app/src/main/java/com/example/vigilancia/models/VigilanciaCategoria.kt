import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class VigilanciaCategoria(
    val id: Int,
    val nombre: String,
    val instruccion: String
) : Parcelable