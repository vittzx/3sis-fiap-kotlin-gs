package com.example.alunos_rm98231_rm550186.factory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.alunos_rm98231_rm550186.data.EventoCadastroDao
import com.example.alunos_rm98231_rm550186.data.EventoDatabase
import com.example.alunos_rm98231_rm550186.entities.EventoModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val eventoDao: EventoCadastroDao


    val itemsLiveData: LiveData<List<EventoModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            EventoDatabase::class.java,
            "eventos_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        eventoDao = database.eventoDao()
        itemsLiveData = eventoDao.getAll()
    }


    fun addEvento(item: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val newItem = EventoModel(name = item,
                tipoEventoExtremo = "evento",
                grauDeImpacto = "grau",
                dataEvento = Date.from(Instant.now()).toString()    ,
                nmrPessoasAfetadas = 10)
            eventoDao.insert(newItem)
        }
    }


    fun removeItem(item: EventoModel) {

        viewModelScope.launch(Dispatchers.IO) {
            eventoDao.delete(item)
        }
    }
}