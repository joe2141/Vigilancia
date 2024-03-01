import com.example.vigilancia.Data.LoginBody
import com.example.vigilancia.models.LoginResponse

import com.example.vigilancia.models.PreguntasResponse
import com.example.vigilancia.models.VigilanciasResponse
import com.example.vigilancia.models.VigilanteResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body loginBody: LoginBody): LoginResponse


    @GET("/api/v1/vigilancias/vigilante/{personaid}")
    fun getVigilanteidByPersonaId(@Path("personaid") personaid: Int): Call<VigilanteResponse>

    @GET("/api/v1/vigilancias/vigilanteVigilancias/{vigilanteid}")
    fun getVigilanciasByVigilanteId(@Path("vigilanteid") vigilanteid: Int): Call<VigilanciasResponse>
    @GET("/api/v1/vigilancias/preguntas")
    fun getPreguntas(
        @Query("vigilanciaCategoriaId") vigilanciaCategoriaId: Int?,
        @Query("apartado") apartado: Int?
    ): Call<PreguntasResponse>


}
