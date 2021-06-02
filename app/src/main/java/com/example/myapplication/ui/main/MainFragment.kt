package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.base.LifecycleReceiver
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    @Inject lateinit var mainViewModel: MainViewModel

    override fun getLifecycleReceivers(): List<LifecycleReceiver> {
        return listOf(mainViewModel)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initListeners()
    }

    private fun initToolbar() {
        val toolbar: Toolbar? = activity?.findViewById(R.id.toolbar)
        toolbar?.title = getString(R.string.masuk)
        toolbar?.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_chevron_left_white)
        toolbar?.inflateMenu(R.menu.login_bar_menu)
        toolbar?.setOnMenuItemClickListener {
            // TODO : Navigate when right icon pressed
            return@setOnMenuItemClickListener true
        }
    }

    private fun initListeners() {
        login_button.setOnClickListener {
            mainViewModel.goToDashboard()
        }
    }
}
