package com.example.anes.pro_inter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_crear_mensaje_normal.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.ArrayList

class CrearMensajeNormal : AppCompatActivity() {

    companion object {
        val IMAGE_CODE = 123
    }
    lateinit var storageDb: FirebaseStorage
    lateinit var nomUser: String
    lateinit var baseChat: DatabaseReference
    lateinit var adapter : AdaptadorCustom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_mensaje_normal)
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
        btnGuardar.setOnClickListener() {
            if(et_titulo.text.isEmpty()){
                et_titulo.error = "Introduce un titulo"
            }else if(et_cuerpo.text.isEmpty()){
                et_cuerpo.error = "Introduce algo en la nota"
            }else{
                val new_msj = Nota(et_titulo.text.toString(),et_cuerpo.text.toString(),et_etiqueta.selectedItem.toString())
                baseChat.child("Users").child(auth.currentUser!!.uid).child("NotasNormales").push().setValue(new_msj)

                et_titulo.setText("")
                et_cuerpo.setText("")

            }


        }

    }
}
