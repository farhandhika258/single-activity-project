package com.example.myapplication.services

import com.example.myapplication.libs.Const
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class USD : RealmObject() {

    @SerializedName(Const.PRICE)
    var price: String? = null

    @SerializedName(Const.CHANGEDAY)
    var changeDay: String? = null

    @SerializedName(Const.CHANGEPCTDAY)
    var changePctDay: String? = null

}