package com.example.myapplication.ui.main

import com.example.myapplication.di.SingleActivity
import com.example.myapplication.domain.Navigator
import com.example.myapplication.ui.base.BaseViewModel
import com.example.myapplication.ui.base.BaseViewState
import javax.inject.Inject

@SingleActivity
class MainViewModel @Inject constructor(
        private val navigator: Navigator,
) : BaseViewModel<MainViewModel.ViewState>() {

    override var lastViewState = ViewState()

    fun goToDashboard() {
        navigator.goToDashboard()
    }

    data class ViewState(
            val loadingIsVisible: Boolean = false,
    ) : BaseViewState
}
