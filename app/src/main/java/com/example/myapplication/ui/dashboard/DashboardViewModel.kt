package com.example.myapplication.ui.dashboard

import com.example.myapplication.di.SingleActivity
import com.example.myapplication.domain.Announcer
import com.example.myapplication.domain.Navigator
import com.example.myapplication.services.Crypto
import com.example.myapplication.services.CryptoService
import com.example.myapplication.ui.base.BaseViewModel
import com.example.myapplication.ui.base.BaseViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SingleActivity
class DashboardViewModel @Inject constructor(
        private val cryptoService: CryptoService,
        private val navigator: Navigator,
        private val announcer: Announcer
) : BaseViewModel<DashboardViewModel.ViewState>() {

    override var lastViewState: ViewState = ViewState()

    fun loadResults() {
        emit(lastViewState.copy(loadingIsVisible = true))
        cryptoService.fetchPrices(50, "USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it.type == 100) {
                                // Success
                                if (it.data.isNotEmpty()) {
                                    emit(lastViewState.copy(results = it.data, displayStatus = 1, loadingIsVisible = false))
                                } else {
                                    emit(lastViewState.copy(loadingIsVisible = false, displayStatus = 2))
                                }
                            } else {
                                // Counted as error
                                emit(lastViewState.copy(loadingIsVisible = false, displayStatus = 3))
                            }
                        },
                        {
                            emit(lastViewState.copy(loadingIsVisible = false, displayStatus = 3))
                        }
                ).addToComposite()
    }

    data class ViewState(
            val loadingIsVisible: Boolean = false,
            val results: MutableList<Crypto> = mutableListOf(),
            val displayStatus: Int = 1
    ) : BaseViewState
}