package com.example.anes.pro_inter


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_mensajes_normales.*
import kotlinx.android.synthetic.main.fragment_mensajes_secretos.*
import java.util.ArrayList


class MensajesSecretos : Fragment() {


    lateinit var nomUser: String
    lateinit var baseChat: DatabaseReference
    lateinit var adapter : AdaptadorCustom

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mensajes_secretos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        //--------------------------------------------------Recupera el nombre de la base y lo pone en el TextView
        val auth = FirebaseAuth.getInstance()
        val dbReference = FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        val llm = LinearLayoutManager(context)
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("Something went wrong when retrieving data!")
            }

            override fun onDataChange(p0: DataSnapshot) {
                nomUser = p0!!.value.toString()
            }
        })
        //
        //-----------------------------------------------------------------------------------------------------------

        val notas = ArrayList<Nota>()
        adapter = AdaptadorCustom(notas, context!!)
        adapter.notifyItemRangeChanged(0, adapter.getItemCount())

        //rvMensajes.layoutManager
        rv_MensajesSecretos.layoutManager = llm
        rv_MensajesSecretos.adapter = adapter

        //--------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------

        baseChat = FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        val childEvent = object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val mensaje = p0?.getValue(Nota::class.java)
                notas.add(mensaje!!)
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        }
        baseChat.child("NotasSecretas").addChildEventListener(childEvent)

        fabS.setOnClickListener {
            val i = Intent(context,CrearMensajeSecreto::class.java)
            startActivity(i)
        }
    }

}
