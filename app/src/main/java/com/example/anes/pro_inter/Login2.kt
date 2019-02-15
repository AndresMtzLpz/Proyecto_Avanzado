package com.example.anes.pro_inter


import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login2.*

class Login2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth= FirebaseAuth.getInstance()



        btn_iniciar2.setOnClickListener{
            if(et_contrasenaL2.text.isEmpty()){
                et_contrasenaL2.error = "Introduce tu contraseña"
            }else{
                auth.signInWithEmailAndPassword(auth.currentUser!!.email.toString(),et_contrasenaL2.text.toString()).addOnCompleteListener(){
                        task ->
                    if (!task.isSuccessful){

                        Toast.makeText(context,"Datos erroneos", Toast.LENGTH_SHORT).show()
                    }else{
                        val trans = fragmentManager!!.beginTransaction()
                        trans.replace(R.id.contenedor_maestro, MensajesSecretos())
                        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        trans.addToBackStack(null)
                        trans.commit()
                    }
                }
            }
        }

    }



}
