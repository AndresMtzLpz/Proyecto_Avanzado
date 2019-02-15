package com.example.anes.pro_inter

class Nota() {

    lateinit var titulo: String
    lateinit var cuerpo: String
    lateinit var etiqueta: String

    constructor(titulo : String,cuerpo : String, etiqueta: String) : this() {

        this.titulo=titulo
        this.cuerpo=cuerpo
        this.etiqueta=etiqueta

    }
}