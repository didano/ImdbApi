package kernycnhyi.vlad.imdbapi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kernycnhyi.vlad.imdbapi.interfaces.QueryDao
import kernycnhyi.vlad.imdbapi.model.BaseMovieModel

@Database(entities = [BaseMovieModel::class], version = 1, exportSchema = false)
abstract class ApiQueryDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "query_db"
        var db: ApiQueryDatabase? = null
        private val LOCK = Any()

        fun createInstance(context: Context): ApiQueryDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(context, ApiQueryDatabase::class.java, DB_NAME).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun queryDao(): QueryDao
}