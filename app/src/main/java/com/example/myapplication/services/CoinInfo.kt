package com.example.myapplication.services

import com.example.myapplication.libs.Const
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class CoinInfo : RealmObject() {

    @SerializedName(Const.NAME)
    var name: String? = null

    @SerializedName(Const.FULLNAME)
    var fullName: String? = null

}