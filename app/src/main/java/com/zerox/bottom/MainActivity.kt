package com.zerox.bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.zerox.bottom.databinding.ActivityMainBinding
import com.zerox.navi.Navi
import com.zerox.navi.NaviView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val items by lazy {
        listOf(
            Navi.Item(
                title = "Dashboard",
                R.drawable.ic_dashboard_black_24dp,
                destination = R.id.navigation_dashboard,
            ),
            Navi.Item(
                title = "Dashboard",
                R.drawable.ic_dashboard_black_24dp,
                destination = R.id.navigation_dashboard,
            ),
            Navi.ItemNoTitle(R.drawable.ic_home_black_24dp, destination = R.id.navigation_home),
            Navi.Item(
                title = "Notifications",
                R.drawable.ic_notifications_black_24dp,
                destination = R.id.navigation_notifications,
            ),
            Navi.Item(
                title = "Notifications",
                R.drawable.ic_notifications_black_24dp,
                destination = R.id.navigation_notifications,
            ),
        )
    }

    private val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navi: NaviView by lazy { binding.navView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navi.setupWithNavController(
            navController = navController,
            items = items,
        )
        lifecycleScope.launch {
            delay(5000)
            navi.updateCount(R.id.navigation_dashboard, 10)
        }
    }
}
