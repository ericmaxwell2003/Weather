package com.acme.weather.common.navigation

import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign

/**
 * A [NavHostFragment] who supports navigation to [DialogFragment].
 */
class DialogNavHostFragment : NavHostFragment() {

    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        navController.navigatorProvider += DialogNavigator(requireContext(), childFragmentManager)
        return super.createFragmentNavigator()
    }
}