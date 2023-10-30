package com.ipirangad3v.rockpaperscissors.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ipirangad3v.rockpaperscissors.domain.models.Resource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataStore(private val context: Context) {

    private val userName = stringPreferencesKey("user_name")

    suspend fun storeUsername(name: String): Resource<String> {
        context.preferencesDataStore.edit { preferences ->
            preferences[userName] = name
        }
        return Resource.success(name)
    }

    suspend fun readUserName(): Resource<String> {
        return Resource.success(
            context.preferencesDataStore.data.map { preferences ->
                preferences[userName] ?: ""
            }.first()
        )
    }
}

private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore("preferences")
