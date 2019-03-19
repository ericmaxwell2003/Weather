package com.acme.weather.app.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.acme.weather.R
import kotlinx.android.synthetic.main.location_add_dialog.view.*

class LocationDialogFragment : DialogFragment() {

    lateinit var zipCodeEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = LayoutInflater.from(activity).inflate(R.layout.location_add_dialog, null)
        zipCodeEditText = v.zip_code

        return AlertDialog.Builder(context!!)
                .setView(v)
                .setPositiveButton(R.string.location_add_button_label) { _, _ ->
                    sendResult(Activity.RESULT_OK, zipCodeEditText.text?.toString() ?: "")
                }
                .setNegativeButton(R.string.location_cancel_button_label) { _, _ ->
                    sendResult(Activity.RESULT_CANCELED, "")
                }
                .create()

    }

    fun sendResult(resultCode: Int, zipCode: String) {
        val intent = Intent()
        intent.putExtra(EXTRA_LOCATION, zipCode)
        targetFragment?.onActivityResult(targetRequestCode, resultCode, intent)
//        (requireActivity() as MainActivity).navigateBackWithResult(Bundle().apply { putString(EXTRA_LOCATION, zipCode) })
    }

    companion object {
        val EXTRA_LOCATION = "com.acme.weather.app.view.EXTRA_LOCATION"
    }
}

