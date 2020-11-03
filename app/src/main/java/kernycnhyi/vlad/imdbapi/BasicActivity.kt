package kernycnhyi.vlad.imdbapi

import androidx.appcompat.app.AppCompatActivity
import kernycnhyi.vlad.imdbapi.utils.Router

abstract class BasicActivity : AppCompatActivity() {
    val router:Router by lazy {
        Router(
            supportFragmentManager,
            R.id.fragmentContainer
        )
    }
}
