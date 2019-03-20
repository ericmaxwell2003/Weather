package com.acme.weather.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination

fun NavController.isStartDestination(destination: NavDestination?) = destination?.id == graph.startDestination

val NavController.parentDestination get() = currentDestination?.parent