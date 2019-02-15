package com.example.anes.pro_inter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            val i = Intent(this,Login::class.java)
            startActivity(i)
        }

        btn_registrar.setOnClickListener {
            val i = Intent(this,Registro::class.java)
            startActivity(i)
        }
    }
}
