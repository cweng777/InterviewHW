package com.weng.interviewhw.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Note: This is at the top level of the file, outside of any classes.
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "myDataStore")

class PreferencesManager(
    context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val KEY_TOKEN = stringPreferencesKey("key_token")
    }

    /**
     * 取 token
     */
    val token: Flow<String?>
    get() = dataStore.data.map { preferences->
        preferences[KEY_TOKEN]
    }

    /**
     * 存 token
     */
    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_TOKEN] = token
        }
    }

    /**
     * 清除 token
     */
    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_TOKEN)
        }
    }
}
