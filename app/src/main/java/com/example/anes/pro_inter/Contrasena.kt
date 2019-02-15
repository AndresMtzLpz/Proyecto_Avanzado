package com.example.anes.pro_inter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contrasena.*

class Contrasena : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)
        val auth= FirebaseAuth.getInstance()
        btn_C.setOnClickListener(){
            if(!et_correoC.text.toString().isEmpty()){
                auth.sendPasswordResetEmail(et_correoC.text.toString()).addOnCompleteListener(){
                        task ->
                    if(!task.isSuccessful){
                        Toast.makeText(this,"No hay un usuario registrado con ese correo :(", Toast.LENGTH_SHORT).show()
                        Log.w("CrateAccountActivity", "recuperaPassword", task.exception)
                    }else{
                        Toast.makeText(this,"Se ha enviado la contraseña a tu correo", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Introduce el correo >:(", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
