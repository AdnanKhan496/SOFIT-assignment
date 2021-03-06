package com.adnankhan.assignment.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.adnankhan.assignment.ui.fragments.home.EnumSearchType
import com.adnankhan.assignment.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreProvider(val context: Context) {

    private val Context.dataStore:
            DataStore<Preferences> by preferencesDataStore(name = Constants.DATA_STORE_NAME)
    val KEY = stringPreferencesKey("key")

    suspend fun saveState(type: String) {
        context.dataStore.edit {
            it[KEY] = type
        }
    }

    val type: Flow<String> = context.dataStore.data.map {
        it[KEY] ?: EnumSearchType.NAME.name
    }

}