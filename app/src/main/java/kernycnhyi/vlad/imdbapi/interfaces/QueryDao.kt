package kernycnhyi.vlad.imdbapi.interfaces

import android.database.Cursor
import androidx.room.*
import io.reactivex.Single
import kernycnhyi.vlad.imdbapi.model.BaseMovieModel

@Dao
interface QueryDao {
    @Query("SELECT * FROM queryList")
    fun getAllQueries(): Single<List<BaseMovieModel>>

    @Query("SELECT * FROM queryList")
    fun getAllQueriesWithCursor(): Cursor

    @Query("SELECT * FROM queryList WHERE id = (:id)")
    fun getById(id: Int): Single<BaseMovieModel>

    @Query("SELECT * FROM queryList WHERE id = (:id)")
    fun getByIdWithCursor(id: Long): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuery(model: BaseMovieModel):Long

    @Query("DELETE FROM queryList WHERE id = (:id)")
    fun delete(id: Long):Int

    @Query("SELECT * FROM queryList")
    fun checkEmptyDb(): List<BaseMovieModel>

    @Update
    fun updateMovie(movie:BaseMovieModel): Int
}