package com.example.anes.pro_inter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anes.agender.ClickListener
import kotlinx.android.synthetic.main.cardview.view.*

class AdaptadorCustom(private val lista_notas : ArrayList<Nota>, c: Context) : RecyclerView.Adapter<HolderNota>() {
    var context = c

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderNota {
        return HolderNota(LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false))
    }

    override fun getItemCount(): Int {
        return this.lista_notas.size
    }

    override fun onBindViewHolder(holder: HolderNota, position: Int) {
        holder.itemView.tv_titulo_cardview.text = lista_notas.get(position).titulo
        holder.itemView.tv_cuerpo_cardview.text = lista_notas.get(position).cuerpo
        holder.itemView.tv_etiqueta_cardview.text = lista_notas.get(position).etiqueta

            holder.itemView.tv_titulo_cardview.visibility= View.VISIBLE
            holder.itemView.tv_cuerpo_cardview.visibility= View.VISIBLE
            holder.itemView.tv_etiqueta_cardview.visibility= View.VISIBLE

    }


}