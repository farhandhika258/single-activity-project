package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.di.SingleActivity
import com.example.myapplication.domain.Navigator
import com.example.myapplication.extensions.startActivity
import com.example.myapplication.ui.main.MainFragmentDirections
import javax.inject.Inject

@SingleActivity
class NavigatorImpl @Inject constructor(
        private val mainActivity: MainActivity
): Navigator {

    override fun goToDashboard() {
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment()
        mainActivity.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun goToUrl(url: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).startActivity(mainActivity)
    }
}