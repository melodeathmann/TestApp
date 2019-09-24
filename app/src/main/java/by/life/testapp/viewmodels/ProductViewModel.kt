package by.life.testapp.viewmodels

import androidx.lifecycle.ViewModel
import by.life.testapp.api.ApiRequest
import by.life.testapp.db.AppDatabase
import by.life.testapp.models.Product
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class ProductViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun load(db: AppDatabase, listener: (List<Product>) -> Unit) {
        db.dataDao()
            .all()
            .flatMap {
                if (it.isEmpty())
                    ApiRequest.api
                        .data()
                        .map { list ->
                            val data = list.products ?: listOf()
                            db.dataDao().insert(data)
                            data
                        }
                else
                    Flowable.just(it)
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(listener, {
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }

    fun details(db: AppDatabase, id: String, listener: (Product) -> Unit) {
        ApiRequest.api
            .details(id)
            .map {
                db.dataDao().insert(it)
                it
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(listener, {
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}