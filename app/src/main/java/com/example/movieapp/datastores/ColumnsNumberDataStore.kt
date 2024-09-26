package com.example.movieapp.datastores

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


object ColumnsNumberDataStore {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val COLUMNS_KEY = intPreferencesKey("columns_key")

    suspend fun saveSelectedColumns(context: Context, columns: Int) {
        context.dataStore.edit { preferences ->
            preferences[COLUMNS_KEY] = columns
        }
    }

    fun getSelectedColumns(context: Context): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[COLUMNS_KEY] ?: 3
        }
    }
}
