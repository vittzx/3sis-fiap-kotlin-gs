package com.example.alunos_rm98231_rm550186.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.alunos_rm98231_rm550186.entities.EventoModel


@Dao
interface EventoCadastroDao {

    @Query("SELECT * FROM EventoModel")
    fun getAll(): LiveData<List<EventoModel>>

    @Insert
    fun insert(item: EventoModel)

    @Delete
    fun delete(item: EventoModel)
}