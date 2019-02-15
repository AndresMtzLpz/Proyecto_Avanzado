package com.example.anes.pro_inter

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.nav_header_menu.*

class Cabezal : AppCompatActivity() {

    lateinit var nomUser: String

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.nav_header_menu)

        val auth = FirebaseAuth.getInstance()
        val dbReference = FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        val llm = LinearLayoutManager(this)

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("Something went wrong when retrieving data!")
            }

            override fun onDataChange(p0: DataSnapshot) {
                nombreCabezal.text = p0!!.value.toString()


            }
        })

               emailCabezal.text = auth.currentUser!!.email.toString()
    }
}