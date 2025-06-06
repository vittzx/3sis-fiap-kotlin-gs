package com.example.alunos_rm98231_rm550186

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.alunos_rm98231_rm550186.entities.EventoModel
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
        val nomeEvento = findViewById<EditText>(R.id.editText)
        val grauImpacto = findViewById<EditText>(R.id.editText2)
        val tipoImpacto = findViewById<EditText>(R.id.editText3)
        val numeroPessoasImpactadas = findViewById<EditText>(R.id.editText4)




        button.setOnClickListener {
            if (nomeEvento.text.isEmpty()) {
                nomeEvento.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addEvento(EventoModel(name = nomeEvento.text.toString(), grauDeImpacto = grauImpacto, tipoEventoExtremo = tipoImpacto, nmrPessoasAfetadas = numeroPessoasImpactadas ))
            nomeEvento.text.clear()
        }

        val viewModelFactory = ItemsViewModelFactory    (application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            eventAdapter.updateEvents(items)
        }
    }
}