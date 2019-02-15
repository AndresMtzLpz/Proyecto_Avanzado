package com.example.anes.pro_inter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_crear_mensaje_normal.*
import kotlinx.android.synthetic.main.activity_crear_mensaje_secreto.*
import java.util.ArrayList

class CrearMensajeSecreto : AppCompatActivity() {

    lateinit var nomUser: String
    lateinit var baseChat: DatabaseReference
    lateinit var adapter : AdaptadorCustom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_mensaje_secreto)

        val auth = FirebaseAuth.getInstance()
        val dbReference = FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        val llm = LinearLayoutManager(this)

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("Something went wrong when retrieving data!")
            }

            override fun onDataChange(p0: DataSnapshot) {
                nomUser = p0!!.value.toString()
            }
        })

        val notas = ArrayList<Nota>()
        adapter = AdaptadorCustom(notas,this)
        adapter.notifyItemRangeChanged(0, adapter.getItemCount())
//

//

//

        llm.setStackFromEnd(true)
        //rvMensajes.layoutManager

        baseChat = FirebaseDatabase.getInstance().reference
        btnGuardarS.setOnClickListener() {
            if(et_tituloS.text.isEmpty()){
                et_tituloS.error = "Introduce un titulo"
            }else if(et_cuerpoS.text.isEmpty()){
                et_cuerpoS.error = "Introduce algo en la nota"
            }else{
                val new_msj = Nota(et_tituloS.text.toString(),et_cuerpoS.text.toString(),et_etiquetaS.selectedItem.toString())
                baseChat.child("Users").child(auth.currentUser!!.uid).child("NotasSecretas").push().setValue(new_msj)

                et_tituloS.setText("")
                et_cuerpoS.setText("")
            }



        }

    }
}
