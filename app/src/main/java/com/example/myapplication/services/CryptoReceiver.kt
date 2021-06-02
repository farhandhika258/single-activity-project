package com.example.myapplication.services

import com.example.myapplication.libs.Const
import com.google.gson.annotations.SerializedName

open class CryptoReceiver {

    @SerializedName(Const.DATA)
    var data: MutableList<Crypto> = mutableListOf()

    @SerializedName(Const.MESSAGE)
    var message: String? = null

    @SerializedName(Const.TYPE)
    var type: Int? = null

}