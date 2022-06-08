package com.example.listadealunos.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listadealunos.R
import com.example.listadealunos.dao.AlunosDao
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val MAIN_APPBAR: String = "Lista de alunos"

class MainActivity : AppCompatActivity() {

    private val dao = AlunosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = MAIN_APPBAR

        configuraFabNovoAluno()
    }

    private fun configuraFabNovoAluno() {
        val botaoNovoAluno: FloatingActionButton = findViewById(R.id.activity_main_fab_novo_aluno)
        botaoNovoAluno.setOnClickListener {
            abreFormularioAlunoActivity()
        }
    }

    private fun abreFormularioAlunoActivity() {
        startActivity(Intent(this, FormularioAlunoActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        configuraLista()
    }

    private fun configuraLista() {
        // Adapter ListView -> ArrayAdapter
        val listaDeAlunos = findViewById<ListView>(R.id.activity_main_lista_de_alunos)
        ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            dao.listaTodosAlunos()
        ).apply { listaDeAlunos.adapter = this }
    }
}