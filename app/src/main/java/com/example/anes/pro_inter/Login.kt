package com.example.anes.pro_inter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth= FirebaseAuth.getInstance()

        btn_iniciar.setOnClickListener{
            if(et_correoL.text.isEmpty()){
                et_correoL.error = "Introduce tu correo"
            }else if(et_contrasenaL.text.isEmpty()){
                et_contrasenaL.error = "Introduce tu contraseña"
            }else{
                auth.signInWithEmailAndPassword(et_correoL.text.toString(),et_contrasenaL.text.toString()).addOnCompleteListener(this){
                        task ->
                    if (!task.isSuccessful){

                        Toast.makeText(this,"Datos erroneos", Toast.LENGTH_SHORT).show()
                    }else{
                        val i = Intent(this,Menu::class.java)
                        startActivity(i)

                    }
                }
            }
        }

        tv_olvido.setOnClickListener{
            val i1 = Intent(this,Contrasena::class.java)
            startActivity(i1)
        }
    }
}
