package kernycnhyi.vlad.imdbapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "queryList")
open class BaseMovieModel @JvmOverloads constructor(

    @ColumnInfo(name = "title")
    @SerializedName("Title")
    @Expose
    val title: String = "",

    @ColumnInfo(name = "year")
    @SerializedName("Year")
    @Expose
    val year: String = "",

    @ColumnInfo(name = "poster")
    @SerializedName("Poster")
    @Expose
    val poster: String = "",

    @ColumnInfo(name = "type")
    @SerializedName("Type")
    @Expose
    val type: String = "",

    @ColumnInfo(name = "isYounger2000")
    var isYounger: Boolean = false,

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

) {
    fun baseToMovie(): Movie = Movie(title, year, poster, type, isYounger)
    fun baseToSeries(): Series = Series(title, year, poster, type, isYounger)

    override fun toString(): String {
        return "$id $title $year $poster $type"
    }
}