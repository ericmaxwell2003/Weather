package com.acme.weather.app.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.acme.weather.R
import com.acme.weather.common.navigation.NavigationResult
import com.acme.weather.common.navigation.isStartDestination
import com.acme.weather.security.viewmodel.AuthenticationViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController
    val authenticationViewModel by lazy { ViewModelProviders.of(this).get(AuthenticationViewModel::class.java) }

    var showMenu = true
        set(value) {
            if(value != field) {
                field = value
                invalidateOptionsMenu()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.weather_nav_fragment)

        // Prevent up on weather list and the login screens
        val appBarConfig =
                AppBarConfiguration(setOf(R.id.weather_list, R.id.enter_credentials))

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            showMenu = (destination.id != R.id.enter_credentials)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(showMenu) {
            menuInflater.inflate(R.menu.global_menu, menu)
            return true
        } else {
            return super.onCreateOptionsMenu(menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.logout) {
            authenticationViewModel.logout()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }

    }
//
//    fun navigateBackWithResult(result: Bundle) {
//        val childFragmentManager = supportFragmentManager.findFragmentById(R.id.weather_nav_fragment)?.childFragmentManager
//        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
//        backStackListener = FragmentManager.OnBackStackChangedListener {
//            (childFragmentManager?.fragments?.get(0) as NavigationResult).onNavigationResult(result)
//            childFragmentManager.removeOnBackStackChangedListener(backStackListener)
//        }
//        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
//        navController.popBackStack()
//    }

}

