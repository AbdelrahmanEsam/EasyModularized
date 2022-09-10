package com.apptikar.easy_write.common.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.apptikar.easy_write.data.dto.DataX
import com.google.gson.Gson

class CustomNavType : NavType<DataX>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): DataX? {
        return bundle.getParcelable(key,DataX::class.java)
    }

    override fun parseValue(value: String): DataX {
        return Gson().fromJson(value, DataX::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: DataX) {
        bundle.putParcelable(key, value)
    }
}