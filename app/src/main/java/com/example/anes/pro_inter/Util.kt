package com.example.anes.pro_inter

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.reemplazarFragmento(
    fragment:Fragment,
    allowStateLoss: Boolean=false,
    @IdRes contenedor: Int) {
    val ft = supportFragmentManager.beginTransaction()
    val reemplazar = ft.replace(contenedor,fragment)
    if(!supportFragmentManager.isStateSaved){
        reemplazar.addToBackStack(null)
        reemplazar.commit()
    }else{
        reemplazar.commitAllowingStateLoss()
    }
}
