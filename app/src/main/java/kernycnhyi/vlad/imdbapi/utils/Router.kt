package kernycnhyi.vlad.imdbapi.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kernycnhyi.vlad.imdbapi.fragments.ApiDataFragment

class Router(private val fragmentManager: FragmentManager, private val containerId: Int) {

    fun openFirstFrag(addToBackStack: Boolean = true) {
        openFragment(ApiDataFragment(), addToBackStack)
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean) {
        if (addToBackStack) {
            fragmentManager.beginTransaction().replace(containerId, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            fragmentManager.beginTransaction().replace(containerId, fragment)
                .commit()
        }
    }
}