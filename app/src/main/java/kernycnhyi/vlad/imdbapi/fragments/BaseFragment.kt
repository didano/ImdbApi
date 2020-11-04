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
    ): View? = inflater.inflate(R.layout.fragment_blank, container, false)

    fun showErrorDialog(error: String?) {
        AlertDialog.Builder(activity).apply {
            error ?: getString(R.string.dialog_error_text)
            this.setMessage(error)
                .setPositiveButton(getString(R.string.ok_button_text)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }
}
