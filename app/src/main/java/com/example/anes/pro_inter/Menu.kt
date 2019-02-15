package com.example.anes.pro_inter

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.app_bar_menu.*
import kotlinx.android.synthetic.main.content_menu.*
import kotlinx.android.synthetic.main.nav_header_menu.*
import java.util.ArrayList

class Menu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

//    override fun responder(notas: Nota) {
//        val contenidoFrag = supportFragmentManager.findFragmentById(R.id.fr_descr_heroes) as Descripcion_Heroes_Frag
//
//        contenidoFrag.cargarDescripcion(heroe)
//    }

    lateinit var nomUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(toolbar)

        val auth = FirebaseAuth.getInstance()
        val dbReference = FirebaseDatabase.getInstance().getReference("Users").child(auth.currentUser!!.uid)
        val llm = LinearLayoutManager(this)


        val parent= dbReference.child("Users").child(auth.currentUser!!.displayName.toString())
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("Something went wrong when retrieving data!")
            }

            override fun onDataChange(p0: DataSnapshot) {
                nomUser = p0!!.value.toString()
                nombreCabezal.text = nomUser
                emailCabezal.text=auth.currentUser!!.email.toString()


            }
        })



        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

//        btn_guardar.setOnClickListener {
//            val new_note = Nota(et_titulo.text.toString(),et_cuerpo.text.toString(),"Hola","1")
//        }


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val frag: Fragment? = null
        val fragSelect: Boolean = false
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_Normales -> {
                reemplazarFragmento(
                    fragment = MensajesNormales(),
                    allowStateLoss = true,
                    contenedor = R.id.contenedor_maestro
                )
            }
            R.id.nav_Secretos-> {
                reemplazarFragmento(
                    fragment = Login2(),
                    allowStateLoss = true,
                    contenedor = R.id.contenedor_maestro
                )
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
