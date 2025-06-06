package com.example.alunos_rm98231_rm550186.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alunos_rm98231_rm550186.entities.EventoModel

@Database(entities = [EventoModel::class], version = 2)
abstract class EventoDatabase : RoomDatabase() {
   abstract fun eventoDao(): EventoCadastroDao
}