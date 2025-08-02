package com.example.sliding_puzzle.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "settings")

class BestTimeRepository(private val context: Context) {

    companion object {
        private val BEST_TIME_KEY = intPreferencesKey("best_time")
    }

    private val bestTimeFlow = context.dataStore.data.map { prefs ->
        prefs[BEST_TIME_KEY] ?: 0
    }

    suspend fun saveBestTime(time: Int) {
        context.dataStore.edit { prefs ->
            prefs[BEST_TIME_KEY] = time
        }
    }

    suspend fun getBestTime(): Int {
        return bestTimeFlow.first()
    }
}