package com.example.myapplication.ui.dashboard

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class DashboardViewHolder(val context: Context?, itemView: View, var viewType: Int) : RecyclerView.ViewHolder(itemView) {

    var title: TextView? = null
    var subtitle: TextView? = null
    var thirdtitle: TextView? = null
    var status: TextView? = null

    init {
        if (viewType != 1){
            title = itemView.findViewById(R.id.dashboard_cell_label)
            subtitle = itemView.findViewById(R.id.dashboard_cell_subtitle)
            thirdtitle = itemView.findViewById(R.id.dashboard_cell_thirdtitle)
            status = itemView.findViewById(R.id.dashboard_cell_status)
        }
    }
}