package io.github.tuguzd.dialogfragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class TestCheckboxDialog : DialogFragment() {
    private val options = arrayOf("Луна и звезды ночью", "Солнце днем")
    private val checked = booleanArrayOf(false, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = requireActivity().let {
        AlertDialog.Builder(it)
            .setMultiChoiceItems(options, checked) { _, which, isChecked ->
                checked[which] = isChecked
            }
            .setPositiveButton("Ok") { _, _ ->
                TestTimePickerDialog(checked.first(), checked.last())
                    .show(it.supportFragmentManager, TestTimePickerDialog::class.simpleName)
            }
            .create()
    }
}
