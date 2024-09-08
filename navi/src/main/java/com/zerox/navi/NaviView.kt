package com.zerox.navi

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.GridLayoutManager
import com.zerox.navi.databinding.NavLayoutBinding

class NaviView : FrameLayout {
    constructor(context: Context, viewMode: Int) : super(context) {
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr,
    ) {
        initViews()
    }

    private lateinit var viewBinding: NavLayoutBinding
    private var orientation: Int = Configuration.ORIENTATION_PORTRAIT

    private fun getLayoutOrientation(): Int =
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager.HORIZONTAL
        } else {
            GridLayoutManager.VERTICAL
        }

    private fun initViews() {
        val rootView = LayoutInflater.from(context).inflate(R.layout.nav_layout, this)
        orientation = resources.configuration.orientation
        viewBinding = NavLayoutBinding.bind(rootView)
    }

    fun setupWithNavController(
        navController: NavController,
        items: List<Navi>,
        selectedColor: Int = R.color.purple_500,
        nonSelectedColor: Int = R.color.black,
    ) {
        val startDestination = navController.graph.startDestinationId
        val navOptions = NavOptions.Builder().setPopUpTo(startDestination, false).build()
        val adapter =
            NaviAdapter(
                selectedDestination = startDestination,
                items = items,
                itemClickListener = {
                    navController.navigate(it, null, navOptions)
                },
                nonSelectedColor = ContextCompat.getColor(context, nonSelectedColor),
                selectedColor = ContextCompat.getColor(context, selectedColor),
            )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            adapter.updateSelectedDestination(destination.id)
        }

        viewBinding.naviView.adapter = adapter
        viewBinding.naviView.layoutManager =
            GridLayoutManager(context, items.size, getLayoutOrientation(), false)
    }
}
