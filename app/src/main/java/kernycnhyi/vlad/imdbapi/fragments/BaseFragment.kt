package kernycnhyi.vlad.imdbapi.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kernycnhyi.vlad.imdbapi.R
import moxy.MvpAppCompatFragment

open class BaseFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    fun showErrorDialog(){
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(getString(R.string.dialog_error_text))
            .setPositiveButton(R.string.dialog_error_text
            ) { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }


}
