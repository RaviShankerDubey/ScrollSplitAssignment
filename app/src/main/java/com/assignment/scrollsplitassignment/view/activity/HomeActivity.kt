package com.assignment.scrollsplitassignment.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.assignment.scrollsplitassignment.R
import com.assignment.scrollsplitassignment.databinding.ActivityHomeBinding
import com.assignment.scrollsplitassignment.viewmodel.HomeViewModel
import com.assignment.scrollsplitassignment.viewmodel.requestResponse.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private var viewModel: HomeViewModel? = null
    private lateinit var binding: ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.titleToolbarLayout)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}