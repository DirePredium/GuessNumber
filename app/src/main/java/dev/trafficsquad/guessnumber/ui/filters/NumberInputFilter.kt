package dev.trafficsquad.guessnumber.ui.filters

import android.text.InputFilter
import android.text.Spanned

class NumberInputFilter(private val min: Int, private val max: Int) : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.toString() + source.toString()).toInt()
            if (isInRange(input)) {
                return null
            }
        } catch (e: NumberFormatException) {
            // Ignored
        }
        return ""
    }

    private fun isInRange(value: Int): Boolean {
        return value in min..max
    }
}