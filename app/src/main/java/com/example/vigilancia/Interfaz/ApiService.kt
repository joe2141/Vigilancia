import com.example.vigilancia.Data.LoginBody
import com.example.vigilancia.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/auth/login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>
}
