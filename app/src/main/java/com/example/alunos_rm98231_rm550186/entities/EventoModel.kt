package com.example.alunos_rm98231_rm550186.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class EventoModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val tipoEventoExtremo: String,
    val grauDeImpacto: String,
    val dataEvento: String,
    val nmrPessoasAfetadas: Int
)
