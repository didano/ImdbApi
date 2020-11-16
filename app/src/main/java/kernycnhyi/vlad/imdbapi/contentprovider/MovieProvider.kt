package kernycnhyi.vlad.imdbapi.contentprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import kernycnhyi.vlad.imdbapi.db.ApiQueryDatabase
import kernycnhyi.vlad.imdbapi.model.BaseMovieModel
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException

class MovieProvider : ContentProvider() {

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val resultUri:Uri? = null
        when(URI_MATCHER.match(uri)){
            ALL_MOVIES_CODE -> if(context!=null){
                val id = ApiQueryDatabase.db!!.queryDao()
                    .insertQuery(BaseMovieModel.movieFromContentValues(values))
                if(id!=0.toLong()){
                    context!!.contentResolver.notifyChange(uri,null)
                    return ContentUris.withAppendedId(uri,id.toLong())
                }
            }
            ONE_MOVIE_CODE -> throw IllegalArgumentException("Invalid URI: Insert failed $uri")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        return resultUri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor:Cursor? = null
        when(URI_MATCHER.match(uri)){
            ONE_MOVIE_CODE -> cursor = ApiQueryDatabase.db!!.queryDao().getByIdWithCursor(ContentUris.parseId(uri))
            ALL_MOVIES_CODE -> cursor = ApiQueryDatabase.db!!.queryDao().getAllQueriesWithCursor()
        }
        return cursor
    }

    override fun onCreate(): Boolean {
        if (context != null) {
            ApiQueryDatabase.db = Room.databaseBuilder(
                context!!, ApiQueryDatabase::class.java,
                ApiQueryDatabase.DB_NAME
            ).build()
            return true
        }
        return false
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var count = 0
        when(URI_MATCHER.match(uri)){
            ALL_MOVIES_CODE -> if(context!=null){
                count = ApiQueryDatabase.db!!.queryDao().updateMovie(BaseMovieModel.movieFromContentValues(values))
                if(count!=0){
                    context!!.contentResolver.notifyChange(uri,null)
                    return count
                }
            }
            ONE_MOVIE_CODE -> throw IllegalArgumentException("Invalid URI: cannot update")
            else -> throw IllegalArgumentException("Unknown URI")
        }
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var count = 0
        when(URI_MATCHER.match(uri)){
            ALL_MOVIES_CODE -> throw IllegalArgumentException("Can't delete")
            ONE_MOVIE_CODE -> if(context!=null){
                count = ApiQueryDatabase.db!!.queryDao().delete(ContentUris.parseId(uri))
                context!!.contentResolver.notifyChange(uri,null)
                return count
            }
            else -> throw IllegalArgumentException("Unknow URI: $uri")
        }
        return count
    }

    override fun getType(uri: Uri): String? = when (URI_MATCHER.match(uri)) {
        ALL_MOVIES_CODE -> "vnd.android.cursor.dir/$AUTHORITY.$MOVIES_TABLE"
        ONE_MOVIE_CODE -> "vnd.android.cursor.item/$AUTHORITY.$MOVIES_TABLE"
        else -> throw UnsupportedOperationException("Not implemented")
    }


    companion object {
        val TAG = MovieProvider::class.simpleName
        const val AUTHORITY = "kernycnhyi.vlad.imdbapi.moviesdb"
        const val MOVIES_TABLE = "queryList"
        const val ALL_MOVIES_CODE = 100
        const val ONE_MOVIE_CODE = 101

        val URI_MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            URI_MATCHER.addURI(AUTHORITY, MOVIES_TABLE, ALL_MOVIES_CODE)
            URI_MATCHER.addURI(AUTHORITY, "$MOVIES_TABLE/*", ONE_MOVIE_CODE)
        }
    }
}