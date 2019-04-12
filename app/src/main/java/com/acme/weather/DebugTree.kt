package com.acme.weather

import timber.log.Timber

class DebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        return super.createStackElementTag(element) + "at ${element.lineNumber}"
    }
}