package kernycnhyi.vlad.imdbapi

import android.os.Bundle
import kernycnhyi.vlad.imdbapi.interfaces.NavigationInterface

class MainActivity : BasicActivity(), NavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFirstFragment()
    }

    override fun openFirstFragment() {
        router.openFirstFrag()
    }
}
