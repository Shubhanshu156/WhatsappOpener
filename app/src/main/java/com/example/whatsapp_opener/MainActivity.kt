package com.example.whatsapp_opener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.whatsapp_opener.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number: String = ""
        if (intent.action == Intent.ACTION_PROCESS_TEXT) {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if (number.isDigitsOnly()) {
            startwhatsapp(number)

        } else {
            Toast.makeText(this, "please check number", Toast.LENGTH_SHORT).show()
        }
    }

    fun startwhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_PROCESS_TEXT)
        intent.setPackage("com.whatsapp")
        val data = if (number[0] == '+') {
            number.substring(1)
        } else if (number.length == 10) {
            "91" + number
        } else {
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")

        if (packageManager.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show()
        }
    }
}
