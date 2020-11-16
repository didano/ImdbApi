package kernycnhyi.vlad.imdbapi.model

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.POST
import java.lang.IllegalArgumentException

@Entity(tableName = "queryList")
open class BaseMovieModel @JvmOverloads constructor(

    @ColumnInfo(name = "title")
    @SerializedName("Title")
    @Expose
    var title: String = "",

    @ColumnInfo(name = "year")
    @SerializedName("Year")
    @Expose
    var year: String = "",

    @ColumnInfo(name = "poster")
    @SerializedName("Poster")
    @Expose
    var poster: String = "",

    @ColumnInfo(name = "type")
    @SerializedName("Type")
    @Expose
    var type: String = "",

    @ColumnInfo(name = "isYounger2000")
    var isYounger: Boolean = false,

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) {
    fun baseToMovie(): Movie = Movie(title, year, poster, type, isYounger)
    fun baseToSeries(): Series = Series(title, year, poster, type, isYounger)

    override fun toString(): String {
        return "$id $title $year $poster $type"
    }



    companion object {
        const val TITLE_KEY = "title"
        const val YEAR_KEY = "year"
        const val POSTER_KEY = "poster"
        const val TYPE_KEY = "type"
        const val ISYOUNGER_KEY = "isYounger2000"
        const val ID_KEY = "id"

        fun movieFromContentValues(values: ContentValues?): BaseMovieModel {
            val model = BaseMovieModel()
            values?.let {
                if (values.containsKey(TITLE_KEY)) {
                    model.title = values.getAsString(TITLE_KEY)
                }
                if (values.containsKey(YEAR_KEY)) {
                    model.year = values.getAsString(YEAR_KEY)
                }
                if (values.containsKey(POSTER_KEY)) {
                    model.poster = values.getAsString(POSTER_KEY)
                }
                if (values.containsKey(TYPE_KEY)) {
                    model.type = values.getAsString(TYPE_KEY)
                }
                if (values.containsKey(ISYOUNGER_KEY)) {
                    model.isYounger = values.getAsBoolean(ISYOUNGER_KEY)
                }
                if (values.containsKey(ID_KEY)) {
                    model.id = values.getAsInteger(ID_KEY)
                }
            }
            return model
        }
    }
}