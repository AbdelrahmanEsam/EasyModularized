package com.apptikar.easy_write.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataX(
    val id: Int,
    val name: String,
    val type: Int,
    val code: String,
    val token: String
) : Parcelable