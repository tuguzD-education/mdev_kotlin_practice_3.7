package io.github.tuguzd.dialogfragments

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.math.abs

class TestFragment : Fragment() {
    private lateinit var mView: TestView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mView = TestView(activity)
        return mView
    }

    fun setState(hour:Int, minute: Int, sun: Boolean, moonStars: Boolean) {
        mView.setState(hour, minute, sun, moonStars)
    }

    private class TestView(context: Context?) : View(context) {
        private var hour: Int = 12
        private var minute: Int = 0
        private var moonstars = true
        private var sun = true
        private var skyColor = Color.BLUE
        private var groundColor = Color.GREEN
        private var isDay = true

        init {
            val c = Calendar.getInstance()
            hour = c.get(Calendar.HOUR_OF_DAY)
            minute = c.get(Calendar.MINUTE)
            setState(hour, minute, sun, moonstars)
        }

        val p = Paint()
        val rnd = Random()

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawColor(skyColor)
            p.color = Color.YELLOW
            if (sun && isDay) {
                canvas.drawCircle(width / 2f, 200f, 40f, p)
            }
            if (moonstars && !isDay) {
                for (i in 0..100) {
                    canvas.drawCircle(
                        rnd.nextInt(width).toFloat(),
                        rnd.nextInt(height).toFloat(),
                        2f,
                        p
                    )
                }
                p.color = Color.rgb(244, 241, 201)
                canvas.drawCircle(width / 4f * 3, 300f, 40f, p)

            }
            p.color = groundColor
            canvas.drawRect(0f, height / 10f * 9,
                width.toFloat(), height.toFloat(), p)
        }

        fun setState(hour: Int, minute: Int, sun: Boolean, moonStars: Boolean) {
            this.sun = sun
            this.moonstars = moonStars
            this.hour = hour
            this.minute = minute
            val dayTimeHour = hour + minute / 60f
            val dark = if (dayTimeHour < 3 || dayTimeHour > 21) {
                1f
            } else {
                abs(dayTimeHour - 12) / 9
            }
            val light = 1 - dark
            val red = (0xff * light + 0x00 * dark).toInt()
            val green = (0xff * light + 0x18 * dark).toInt()
            val blue = (0xff * light + 0x3e * dark).toInt()
            isDay = dayTimeHour > 6 && dayTimeHour <= 18
            skyColor = Color.rgb(red, green, blue)
            groundColor = Color.HSVToColor(floatArrayOf(120f, 1f, dark))
            invalidate()
        }
    }
}
