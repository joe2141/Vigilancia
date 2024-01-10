package com.example.vigilancia.utility

import android.content.Context

object Shared {
    private const val PREFERENCES_FILE = "Shared"

    fun saveToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }
    fun saveVigilanteId(context: Context, vigilanteId: Int) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("vigilanteId", vigilanteId)
        editor.apply()
    }

    fun getVigilanteId(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getInt("vigilanteId", -1)
    }
}
