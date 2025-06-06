package com.example.alunos_rm98231_rm550186

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.alunos_rm98231_rm550186.factory.EventoAdapter
import com.example.alunos_rm98231_rm550186.factory.ItemsViewModel
import com.example.alunos_rm98231_rm550186.factory.ItemsViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Lista de Compras"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val eventAdapter = EventoAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = eventAdapter

        val button = findViewById<Button>(R.id.button)
        val nomeProduto = findViewById<EditText>(R.id.editText)

        button.setOnClickListener {
            if (nomeProduto.text.isEmpty()) {
                nomeProduto.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addEvento(nomeProduto.text.toString())
            nomeProduto.text.clear()
        }

        val viewModelFactory = ItemsViewModelFactory    (application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            eventAdapter.updateEvents(items)
        }
    }
}