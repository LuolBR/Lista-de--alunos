package com.example.listadealunos.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
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

    private lateinit var adapter: ArrayAdapter<Aluno>
    private val dao = AlunosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = MAIN_APPBAR

        configuraFabNovoAluno()
        configuraLista()

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
            val menuInfo: AdapterView.AdapterContextMenuInfo = item
                .menuInfo as AdapterView.AdapterContextMenuInfo
            val alunoEscolhido: Aluno? = adapter.getItem(menuInfo.position)
            remove(alunoEscolhido)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(aluno: Aluno?) {
        dao.remove(aluno)
        adapter.remove(aluno)
    }

    override fun onResume() {
        super.onResume()
        atualizaAlunos()
    }

    private fun atualizaAlunos() {
        adapter.clear()
        adapter.addAll(dao.listaTodosAlunos())
    }

    private fun configuraLista() {
        val listaDeAlunos = findViewById<ListView>(R.id.activity_main_lista_de_alunos)

        configuraAdapter(listaDeAlunos)
        configuraListenerCliquePorItem(listaDeAlunos)
        registerForContextMenu(listaDeAlunos)
    }


    private fun configuraAdapter(listaDeAlunos: ListView) {
        adapter = ArrayAdapter(this,
            android.R.layout.simple_expandable_list_item_1
        )
        listaDeAlunos.adapter = adapter
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