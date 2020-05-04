package matshagen.example.androidjetpacktest.model

import io.reactivex.Single
import retrofit2.http.GET

interface PlacesApi {
    @GET("home/api/v1/places/")
    fun getPlaces(): Single<List<PlacesResponse.Feature.Properties>>
}