package com.example.projeto_criar_personagem.Util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationSimplesRecevier:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("msg")
        Toast.makeText(context, message ?: "Ação sem mensagem", Toast.LENGTH_SHORT).show()
    }
}