package com.example.myapplication.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.services.Crypto
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.base.LifecycleReceiver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class DashboardFragment : BaseFragment() {

    @Inject lateinit var dashboardViewModel: DashboardViewModel

    private var mainAdapter: DashboardAdapter? = null

    override fun getLifecycleReceivers(): List<LifecycleReceiver> {
        return listOf(dashboardViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        dashboardViewModel.loadResults()
    }

    override fun onStart() {
        super.onStart()
        val disposable = dashboardViewModel.viewStateEmitter.subscribe {

            dashboard_refresh_layout?.isRefreshing = it.loadingIsVisible

            setRecyclerDisplay(it.displayStatus)

            populateData(it.results, true)

            it.results.forEach { member ->
                mainAdapter?.addItem(member)
            }
        }
        compositeDisposable.add(disposable)
    }

    private fun initRecyclerView() {

        dashboard_refresh_layout.setOnRefreshListener {
            dashboardViewModel.loadResults()
        }

        val adapter = DashboardAdapter(mutableListOf())
        adapter.itemClickListener { member ->
            // Onclick here
        }

        dashboard_recycler_view?.adapter = adapter
        dashboard_recycler_view?.layoutManager = LinearLayoutManager(activity)

        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.divider_item_vertical)
        drawable?.let{ divider.setDrawable(it) }
        dashboard_recycler_view?.addItemDecoration(divider)

        dashboard_recycler_view?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as LinearLayoutManager
                val rAdapter = recyclerView.adapter as DashboardAdapter
                val lastVisible = manager.findLastVisibleItemPosition()
                if (!rAdapter.getLoading() && manager.itemCount <= (lastVisible + 3)) {
                    recyclerView.post {
                        //dashboardViewModel?.prepareFetchNextData()
                    }
                }
            }
        })
    }

    private fun setRecyclerDisplay(type: Int) {
        when (type) {
            1 -> {
                // Data is available
                dashboard_recycler_view?.visibility = View.VISIBLE
                failed_connect_layout_activity_dashboard.visibility = View.GONE
                failed_connect_layout_activity_dashboard.visibility = View.GONE
            }

            2 -> {
                // Data is empty
                dashboard_recycler_view?.visibility = View.GONE
                failed_connect_layout_activity_dashboard.visibility = View.GONE
                failed_connect_layout_activity_dashboard.visibility = View.VISIBLE
            }

            3 -> {
                // Failed Connection or error occurred
                dashboard_recycler_view?.visibility = View.GONE
                failed_connect_layout_activity_dashboard.visibility = View.VISIBLE
                failed_connect_layout_activity_dashboard.visibility = View.GONE
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun populateData(members: MutableList<Crypto>, clear: Boolean) {
        if (dashboard_recycler_view.adapter is DashboardAdapter) {
            if (clear) {
                (dashboard_recycler_view.adapter as DashboardAdapter).clearList()
            }

            Observable.fromIterable(members)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ next ->
                        (dashboard_recycler_view.adapter as DashboardAdapter).addItem(next)
                    }, { error ->
                        error.printStackTrace()
                    })
        }
    }

}
