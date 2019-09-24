package by.life.testapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.life.testapp.models.Product
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ProductDao {

    @Insert
    fun insert(data: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Product)

    @Query("SELECT * from product")
    fun all(): Flowable<List<Product>>

}