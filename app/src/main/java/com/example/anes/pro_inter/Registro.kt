package com.example.anes.pro_inter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    lateinit var storageDb: FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val auth= FirebaseAuth.getInstance()
        val database= FirebaseDatabase.getInstance()
        val dbReference= database.reference
//        storageDb = FirebaseStorage.getInstance()
        //--------------------------------------------------------------------------------------------------------------------
        //img_perfil.setOnClickListener(){
        //val intent = Intent()
        //intent.action = Intent.ACTION_GET_CONTENT
        //intent.type = "image/*"
        //  intent.putExtra( Intent.EXTRA_LOCAL_ONLY,true)
        //    startActivityForResult(intent, IMAGE_PERFIL_CODE)

        //--------------------------------------------------------------------------------------------------------------------
        btn_registrarR.setOnClickListener(){
            if(et_correoR.text.isEmpty()){
                et_correoR.error = "Introduce tu correo"
            }else if(et_contrasenaR.text.isEmpty()){
                et_contrasenaR.error = "Introduce tu contraseña"
            }else if(et_nombreR.text.isEmpty()){
                et_nombreR.error = "Introduce tu nombre"
            }else{
                auth.createUserWithEmailAndPassword(et_correoR.text.toString(),et_contrasenaR.text.toString()).addOnCompleteListener(this){
                        task ->
                    if(!task.isSuccessful){
                        Toast.makeText(this,"Fallo el registro de usuario :(", Toast.LENGTH_SHORT).show()
                        Log.w("CrateAccountActivity", "createUserWithEmail:failure", task.exception)
                    }else{
                        Toast.makeText(this,"Cuenta creada con éxito (:", Toast.LENGTH_SHORT).show()

                        val userActual = auth.currentUser
                        val userUID= userActual?.uid
                        val userName = userActual?.displayName
                        //Toast.makeText(this,userActual?.uid,Toast.LENGTH_SHORT).show()
                        //Si solo mandamos el nombre
                        val parent= dbReference.child("Users").child(userUID.toString()).setValue(et_nombreR.text)
                        userActual?.sendEmailVerification()?.addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Se ha enviado un correo de validacion (:", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Fallo el envio de correo de validacion :(", Toast.LENGTH_SHORT).show()
                                Log.w("CrateAccountActivity", "sendEmai:failure", task.exception)
                            }
                        }

                    }
                }
            }


        }
        //----------------------------------------------------------------------------------------------------------------------
    }
}
