import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VigilanciaApartado(
    val id: Int,
    val nombre: String
) : Parcelable
