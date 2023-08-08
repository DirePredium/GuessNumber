package dev.trafficsquad.guessnumber.exceptions

import android.util.Log
import android.widget.Toast

class GuessNumberExceptionHandler : Thread.UncaughtExceptionHandler {

    private var oldHandler: Thread.UncaughtExceptionHandler? = null

    init {
        oldHandler = Thread.getDefaultUncaughtExceptionHandler()
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        if (oldHandler != null)
            oldHandler!!.uncaughtException(thread, throwable)
    }

}