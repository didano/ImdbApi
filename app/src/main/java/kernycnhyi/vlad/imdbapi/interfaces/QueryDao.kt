package kernycnhyi.vlad.imdbapi.interfaces

import androidx.room.*
import io.reactivex.Single
import kernycnhyi.vlad.imdbapi.model.BaseMovieModel

@Dao
interface QueryDao {
    @Query("SELECT * FROM queryList")
    fun getAllQueries(): Single<List<BaseMovieModel>>

    @Query("SELECT * FROM queryList WHERE id = (:id)")
    fun getById(id: Int): Single<BaseMovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuery(model: BaseMovieModel)

    @Delete
    fun delete(model: BaseMovieModel)

    @Query("SELECT * FROM queryList")
    fun checkEmptyDb(): List<BaseMovieModel>
}