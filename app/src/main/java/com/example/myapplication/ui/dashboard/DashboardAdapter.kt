package com.example.myapplication.ui.dashboard

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.services.Crypto

class DashboardAdapter(val members: MutableList<Crypto>) : RecyclerView.Adapter<DashboardViewHolder>() {

    private var clickListener: ((Crypto) -> Unit)? = null
    private var loading: Boolean = false

    fun clearList() {
        members.clear()
        notifyDataSetChanged()
    }

    fun addItem(item: Crypto) {
        members.add(item)
        notifyItemInserted(members.indexOf(item))
    }

    fun addFirstItem(item: Crypto) {
        members.add(0, item)
        notifyItemInserted(0)
    }

    fun appendItems(items: List<Crypto>) {
        members.addAll(items)
        notifyDataSetChanged()
    }

    fun replaceAllItems(items: List<Crypto>) {
        members.clear()
        members.addAll(items)
        notifyDataSetChanged()
    }

    fun itemClickListener(listener: (Crypto) -> Unit) {
        this.clickListener = listener
    }

    fun setLoading(loading: Boolean) {
        this.loading = loading
        if (members.size > 0) {
            if (loading) {
                val complain = Crypto()
                members.add(complain)
                notifyItemInserted(members.indexOf(complain))
            } else {
                val lastCrypto = members.last()
                if (lastCrypto.coinInfo?.name == null) {
                    val index = members.indexOf(lastCrypto)
                    members.remove(lastCrypto)
                    notifyItemRemoved(index)
                }
            }
        }
    }

    fun getLoading() : Boolean {
        return loading
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return when (viewType) {
            1 -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.loading_view_cell, parent, false)
                DashboardViewHolder(parent.context, view, viewType)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cryptos, parent, false)
                return DashboardViewHolder(parent.context, view, viewType)
            }
        }
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (members[position].coinInfo?.name != null) 0 else 1
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        if (members[position].coinInfo != null) {
            val coinInfo = members[position].coinInfo

            coinInfo?.name?.let { label ->
                holder.title?.text = label
            }

            coinInfo?.fullName?.let { name ->
                holder.subtitle?.text = name
            }
        }

        if (members[position].display != null) {
            val displayInfo = members[position].display?.usd


            displayInfo?.let { display ->

                display.price?.let { id ->
                    holder.status?.text = id
                }

                display.changeDay?.let { status ->

                    when (display.changePctDay!![0].toString()) {
                        "-" -> {
                            holder.thirdtitle?.setTextColor(Color.parseColor("#E27F7D"))
                        }
                        "0" -> {
                            holder.thirdtitle?.setTextColor(Color.parseColor("#949494"))
                        }
                        else -> {
                            holder.thirdtitle?.setTextColor(Color.parseColor("#01AB6C"))
                        }
                    }

                    val text = "$status (${display.changePctDay}%)"
                    holder.thirdtitle?.text = text
                }

                clickListener?.let {
                    holder.itemView.setOnClickListener { _ ->
                        it(members[position])
                    }
                }
            }
        }
    }
}