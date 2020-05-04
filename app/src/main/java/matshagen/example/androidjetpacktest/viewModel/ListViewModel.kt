package matshagen.example.androidjetpacktest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import matshagen.example.androidjetpacktest.model.PlacesApiService
import matshagen.example.androidjetpacktest.model.PlacesResponse

class ListViewModel: ViewModel() {

    private val placesService = PlacesApiService()
    private val disposable = CompositeDisposable()

    val places = MutableLiveData<List<PlacesResponse.Feature.Properties>>()
    val placesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            placesService.getPlaces()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<PlacesResponse.Feature.Properties>>() {

                    override fun onSuccess(placeList: List<PlacesResponse.Feature.Properties>) {
                        places.value = placeList
                        placesLoadError.value = false
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        placesLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}