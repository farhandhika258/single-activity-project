package com.example.myapplication.services

import com.example.myapplication.libs.Const
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Display : RealmObject() {

    @SerializedName(Const.USD)
    var usd: USD? = null

}