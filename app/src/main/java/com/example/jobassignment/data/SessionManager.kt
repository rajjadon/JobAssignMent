package com.example.jobassignment.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.jobassignment.common.AppConstant.IS_LOGGED_IN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Session manager to save and fetch data from SharedPreferences
 */


private const val DATA_STORE_NAME = "jobAssignment"
private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

class SessionManager(context: Context) {

    private val dataStore = context.dataStore

    val isLoggedIn: Flow<Boolean>
        get() = dataStore.data.map {
            it[booleanPreferencesKey(IS_LOGGED_IN)] ?: false
        }

    suspend fun saveSession(isLogin: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(IS_LOGGED_IN)] = isLogin
        }
    }

    suspend fun deleteSession() {
        dataStore.edit { it.clear() }
    }
}