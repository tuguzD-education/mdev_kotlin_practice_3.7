package io.github.tuguzd.dialogfragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TestTimePickerDialog(
    private val sun: Boolean,
    private val moonStars: Boolean,
): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        val listener: (TimePicker, Int, Int) -> Unit = { _, hour, minute ->
            val fragment = parentFragmentManager.findFragmentById(R.id.fragment_container)
            (fragment as TestFragment).setState(hour, minute, sun, moonStars)
        }
        return TimePickerDialog(activity, listener, currentHour, currentMinute, false)
    }
}
