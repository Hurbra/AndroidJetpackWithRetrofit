package matshagen.example.androidjetpacktest.model

data class PlacesResponse(
    val features: List<Feature>,
    val type: String
) {
    data class Feature(
        val geometry: Geometry,
        val properties: Properties,
        val type: String
    ) {
        data class Geometry(
            val coordinates: List<Double>,
            val type: String
        )

        data class Properties(
            val icon: String,
            val id: Long,
            val name: String
        )
    }
}