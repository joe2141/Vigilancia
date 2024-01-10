import com.example.vigilancia.Data.LoginBody
import com.example.vigilancia.models.LoginResponse
import com.example.vigilancia.models.VigilanciaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/v1/auth/login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>

    @GET("/api/v1/vigilancias/vigilante/{personaid}")
    fun getVigilanteidByPersonaId(@Path("personaid") personaid: Int): Call<VigilanciaResponse>

    @GET("/api/v1/vigilancias/vigilanteVigilancias/{vigilanteid}")
    fun getVigilanciaByVigilanteid(@Path("vigilanteid") vigilanteid: Int): Call<VigilanciaResponse>
}
