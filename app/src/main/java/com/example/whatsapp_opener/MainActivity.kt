package com.example.whatsapp_opener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.whatsapp_opener.R
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG="i am here"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number: String = ""
        val openbtn=findViewById<Button>(R.id.open)
        openbtn.setOnClickListener {
            val ph=findViewById<EditText>(R.id.phonenumber)
            number=ph.text.toString()
            Log.d(TAG, "onclick: "+number)
            if (number.isDigitsOnly()) {
                Log.d(TAG, "onCreate: i am here")
                startwhatsapp(number)

            } else {
                Toast.makeText(this, "please check number", Toast.LENGTH_SHORT).show()
            }

        }



        if (intent.action == Intent.ACTION_PROCESS_TEXT) {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

            if (number.isDigitsOnly()) {
                Log.d(TAG, "onCreate: i am here")
                startwhatsapp(number)

            } else {
                Toast.makeText(this, "please check number", Toast.LENGTH_SHORT).show()
            }
        }

    }



    fun startwhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        Log.d(TAG, "startwhatsapp: "+number)
        val data:String = if(number[0] == '+'){
            number.substring(1)
        }else if(number.length == 10){
            "91"+number
        }else{
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        Log.d(TAG, "startwhatsapp: "+intent.dataString)
        startActivity(intent)

        if (packageManager.resolveActivity(intent, 0) != null) {
            Log.d(TAG, "startwhatsapp: ")

            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show()
        }
    }
}
