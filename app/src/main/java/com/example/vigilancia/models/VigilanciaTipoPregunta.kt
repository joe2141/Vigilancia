import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class VigilanciaTipoPregunta(
    val id: Int,
    val nombre: String
) : Parcelable