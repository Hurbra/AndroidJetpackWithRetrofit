package matshagen.example.androidjetpacktest.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PlacesApiService {

    private val BASE_URL = "https://www.noforeignland.com"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PlacesApi::class.java)

    fun getPlaces(): Single<List<PlacesResponse.Feature.Properties>> {
        return api.getPlaces()
    }
}