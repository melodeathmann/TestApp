package by.life.testapp.api

import by.life.testapp.models.Product
import by.life.testapp.models.Products
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("developer-application-test/cart/list")
    fun data(): Flowable<Products>

    @GET("developer-application-test/cart/{productId}/detail")
    fun details(@Path("productId") productId: String): Flowable<Product>

}