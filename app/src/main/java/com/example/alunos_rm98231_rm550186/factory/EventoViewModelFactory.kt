package com.example.alunos_rm98231_rm550186.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class EventoViewModelFactory(private val application: Application) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventosViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}