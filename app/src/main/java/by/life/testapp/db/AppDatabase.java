package by.life.testapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import by.life.testapp.models.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
   public abstract ProductDao dataDao();
}