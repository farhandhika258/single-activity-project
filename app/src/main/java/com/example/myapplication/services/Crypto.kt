package com.example.myapplication.services

import android.os.Parcel
import android.os.Parcelable
import com.example.myapplication.libs.Const
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Crypto : RealmObject() {

    @SerializedName(Const.COIN_INFO)
    var coinInfo: CoinInfo? = null

    @SerializedName(Const.DISPLAY)
    var display: Display? = null

}