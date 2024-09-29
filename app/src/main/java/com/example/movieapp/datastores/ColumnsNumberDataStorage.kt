package com.example.movieapp.datastores

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ColumnsNumberDataStorage : LocalDataStorage<Int> {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val key = intPreferencesKey("columns_key")

    override suspend fun save(context: Context, value: Int) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override fun get(context: Context): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: 3
        }
    }

}