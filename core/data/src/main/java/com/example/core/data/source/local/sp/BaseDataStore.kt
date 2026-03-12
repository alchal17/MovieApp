package com.example.core.data.source.local.sp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private val Context.encryptedDataStore: DataStore<Preferences> by preferencesDataStore(name = "movie_app_prefs")

internal abstract class BaseDataStore(protected val context: Context) {
    protected fun getDataStore(): DataStore<Preferences> = context.encryptedDataStore
}