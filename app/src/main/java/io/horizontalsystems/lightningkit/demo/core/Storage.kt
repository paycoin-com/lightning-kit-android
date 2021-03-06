package io.horizontalsystems.lightningkit.demo.core

import android.content.SharedPreferences
import io.horizontalsystems.lightningkit.demo.remoteconnection.ConnectionParams

class Storage(private val sharedPreferences: SharedPreferences) {
    companion object {
        private const val KEY_CONNECTION_PARAMS = "CONNECTION_PARAMS"
    }

    fun saveConnectionParams(connectionParams: ConnectionParams) {
        val serialized = with(connectionParams) {
            listOf(host, port.toString(), certificate, macaroon).joinToString("|")
        }

        sharedPreferences.edit().putString(KEY_CONNECTION_PARAMS, serialized).apply()
    }

    fun getConnectionParams() : ConnectionParams? {
        return sharedPreferences.getString(KEY_CONNECTION_PARAMS, null)?.let {
            val chunks = it.split("|")
            ConnectionParams(chunks[0], chunks[1].toInt(), chunks[2], chunks[3])
        }
    }

}
