package com.example.alunos_rm98231_rm550186.factory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alunos_rm98231_rm550186.R
import com.example.alunos_rm98231_rm550186.entities.EventoModel


class EventoAdapter(private val onItemRemoved: (EventoModel) -> Unit) :
    RecyclerView.Adapter<EventoAdapter.ItemViewHolder>() {

    private var eventos = listOf<EventoModel>()


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameView = view.findViewById<TextView>(R.id.textViewName)
        val grauImapatoView = view.findViewById<TextView>(R.id.textViewGrauImpacto)
        val tipoEventoView = view.findViewById<TextView>(R.id.textViewTipoEvento)
        val nmrImpactoView = view.findViewById<TextView>(R.id.textViewNmrImpacto)

        val button = view.findViewById<ImageButton>(R.id.imageButton)


        fun bind(evento: EventoModel) {
            nameView.text = evento.name
            grauImapatoView.text = evento.grauDeImpacto
            tipoEventoView.text = evento.tipoEventoExtremo
            nmrImpactoView.text  =  evento.nmrPessoasAfetadas.toString()

            button.setOnClickListener {
                onItemRemoved(evento)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cadastro_evento, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = eventos.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = eventos[position]
        holder.bind(item)
    }


    fun updateEvents(newItems: List<EventoModel>) {
        eventos = newItems
        notifyDataSetChanged()
    }
}