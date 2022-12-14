package com.apptikar.common.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")


 suspend fun Context.readToken(key: String) :String?{
    val dataStoreKey = stringPreferencesKey(key)
    val token = dataStore.data.map {
        it[dataStoreKey]
    }
    return token.first()
}

 suspend fun Context.saveToken(key:String,value:String){
    val dataStoreKey = stringPreferencesKey(key)
    dataStore.edit {
        it[dataStoreKey] = value
    }

}