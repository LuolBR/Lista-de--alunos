package com.example.listadealunos.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listadealunos.R
import com.example.listadealunos.model.Aluno
import com.example.listadealunos.ui.ListaAlunosView
import com.example.listadealunos.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAIN_APPBAR: String = "Lista de alunos"
    }

    private val listaAlunosView = ListaAlunosView(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = MAIN_APPBAR

        configuraFabNovoAluno()
        configuraLista()
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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_main_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activity_main_menu_remover) {
            listaAlunosView.confirmaRemocao(item)
        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        listaAlunosView.atualizaAlunos()
    }

    private fun configuraLista() {
        val listaDeAlunos = findViewById<ListView>(R.id.activity_main_lista_de_alunos)

        listaAlunosView.configuraAdapter(listaDeAlunos)
        configuraListenerCliquePorItem(listaDeAlunos)
        registerForContextMenu(listaDeAlunos)
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






