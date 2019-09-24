package by.life.testapp.db

import android.content.Context
import androidx.room.Room

object DataBase {

    private var db: AppDatabase? = null

    fun get(context: Context): AppDatabase {
        if (db == null)
            db = Room.databaseBuilder(context, AppDatabase::class.java, "database").fallbackToDestructiveMigration().build()

        return db!!
    }

}