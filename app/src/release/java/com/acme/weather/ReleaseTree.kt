package com.acme.weather

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return when(priority) {
            Log.VERBOSE, Log.DEBUG, Log.INFO -> false
            else -> true
        }
    }

    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {

        if(isLoggable(tag, priority) && message != null) {

            message.asSequence()
                    .chunked(MAX_LOG_LENGTH)
                    .onEach {
                        if(priority == Log.ASSERT) {
                            Log.wtf(tag, message)
                        } else {
                            Log.println(priority, tag, message)
                        }
                    }
        }
    }

    companion object {
        val MAX_LOG_LENGTH = 4000
    }
}
