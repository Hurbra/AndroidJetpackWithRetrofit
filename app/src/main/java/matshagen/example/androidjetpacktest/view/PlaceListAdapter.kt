package matshagen.example.androidjetpacktest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_place.view.*
import matshagen.example.androidjetpacktest.R
import matshagen.example.androidjetpacktest.model.PlacesResponse

class PlaceListAdapter(val placeList: ArrayList<PlacesResponse.Feature.Properties>): RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder>() {

    fun updatePlaceList(newPlaceList: List<PlacesResponse.Feature.Properties>) {
        placeList.clear()
        placeList.addAll(newPlaceList)
        notifyDataSetChanged()
    }

    class PlaceViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.view.name.text = placeList[position].name
        holder.view.placeId.text = placeList[position].id.toString()
    }

}