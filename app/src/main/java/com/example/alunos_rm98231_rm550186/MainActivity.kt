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
import com.example.alunos_rm98231_rm550186.factory.EventoViewModelFactory
import com.example.alunos_rm98231_rm550186.factory.EventosViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EventosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Cadastros de Eventos Extremos"

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
        val dataEvento = findViewById<EditText>(R.id.editText7)





        button.setOnClickListener {
            if (nomeEvento.text.isEmpty()) {
                nomeEvento.error = "Preencha um valor"
                return@setOnClickListener
            }

            if (grauImpacto.text.isEmpty()) {
                grauImpacto.error = "Preencha um valor"
                return@setOnClickListener
            }

            if (tipoImpacto.text.isEmpty()) {
                tipoImpacto.error = "Preencha um valor"
                return@setOnClickListener
            }

            if (numeroPessoasImpactadas.text.isEmpty() || numeroPessoasImpactadas.text.toString().toInt() < 0) {
                numeroPessoasImpactadas.error = "Preencha um valor e acima de 0"
                return@setOnClickListener
            }
            if (dataEvento.text.isEmpty()) {
                dataEvento.error = "Preencha um valor"
                return@setOnClickListener
            }

            nomeEvento.text.clear()
            nomeEvento.text.clear()
            nomeEvento.text.clear()
            nomeEvento.text.clear()
            nomeEvento.text.clear()



            viewModel.addEvento(EventoModel(
                name = nomeEvento.text.toString(),
                grauDeImpacto = grauImpacto.text.toString(),
                tipoEventoExtremo = tipoImpacto.text.toString(),
                nmrPessoasAfetadas = numeroPessoasImpactadas.text.toString().toInt(),
                dataEvento = dataEvento.text.toString()))
        }

        val viewModelFactory = EventoViewModelFactory    (application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EventosViewModel::class.java)

        viewModel.eventosLiveData.observe(this) { items ->
            eventAdapter.updateEvents(items)
        }
    }
}