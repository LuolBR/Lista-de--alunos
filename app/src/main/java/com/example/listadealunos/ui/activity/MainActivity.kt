package com.example.listadealunos.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listadealunos.R
import com.example.listadealunos.dao.AlunosDao
import com.example.listadealunos.model.Aluno
import com.example.listadealunos.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAIN_APPBAR: String = "Lista de alunos"
    }

    private val dao = AlunosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = MAIN_APPBAR

        configuraFabNovoAluno()

        val pessoa1 = Aluno(nome = "Teste1", telefone = "1234", email = "teste1@dev.com")
        val pessoa2 = Aluno(nome = "Teste2", telefone = "1234", email = "teste2@dev.com")
        dao.salva(pessoa1)
        dao.salva(pessoa2)
    }

    private fun configuraFabNovoAluno() {
        val botaoNovoAluno: FloatingActionButton = findViewById(R.id.activity_main_fab_novo_aluno)
        botaoNovoAluno.setOnClickListener {
            abreFormularioModoInsereAluno()
        }
    }

    private fun abreFormularioModoInsereAluno() {
        startActivity(
            Intent(this, FormularioAlunoActivity::class.java)
        )
    }

    override fun onResume() {
        super.onResume()
        configuraLista()
    }

    private fun configuraLista() {

        val listaDeAlunos = findViewById<ListView>(R.id.activity_main_lista_de_alunos)
        val alunos: List<Aluno> = dao.listaTodosAlunos()

        configuraAdapter(alunos, listaDeAlunos)
        configuraListenerCliquePorItem(listaDeAlunos)
    }

    private fun configuraAdapter(
        alunos: List<Aluno>,
        listaDeAlunos: ListView
    ) {
        ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            alunos
        ).apply { listaDeAlunos.adapter = this }
    }

    private fun configuraListenerCliquePorItem(
        listaDeAlunos: ListView
    ) {
        // Adapter ListView -> ArrayAdapter
        listaDeAlunos.setOnItemClickListener { adapterView, _, posicao, _ ->
            val alunoEscolhido: Aluno = adapterView.getItemAtPosition(posicao) as Aluno
            abreFormularioModoEditaAluno(alunoEscolhido)
        }
    }

    private fun abreFormularioModoEditaAluno(aluno: Aluno) {
        startActivity(
            Intent(this, FormularioAlunoActivity::class.java).apply {
                putExtra(CHAVE_ALUNO, aluno)
            }
        )
    }
}