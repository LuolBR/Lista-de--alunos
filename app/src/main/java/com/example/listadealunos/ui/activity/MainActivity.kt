package com.example.listadealunos.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listadealunos.R
import com.example.listadealunos.dao.AlunosDao
import com.example.listadealunos.model.Aluno
import com.example.listadealunos.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import com.example.listadealunos.ui.adapter.ListaAlunoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAIN_APPBAR: String = "Lista de alunos"
    }

    private lateinit var adapter: ListaAlunoAdapter
    private val dao = AlunosDao()

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
            confirmaRemocao(item)
        }
        return super.onContextItemSelected(item)
    }

    private fun confirmaRemocao(item: MenuItem) {
        AlertDialog
            .Builder(this)
            .setTitle("Removendo Aluno")
            .setMessage("Tem certeza que deseja excluir o aluno?")
            .setPositiveButton("Sim") { _: DialogInterface, _: Int ->
                val menuInfo: AdapterView.AdapterContextMenuInfo = item
                    .menuInfo as AdapterView.AdapterContextMenuInfo
                val alunoEscolhido: Aluno = adapter.getItem(menuInfo.position) as Aluno
                remove(alunoEscolhido)
            }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun remove(aluno: Aluno) {
        dao.remove(aluno)
        adapter.remove(aluno)
    }

    override fun onResume() {
        super.onResume()
        atualizaAlunos()
    }

    private fun atualizaAlunos() {
        adapter.atualiza(dao.listaTodosAlunos())
    }

    private fun configuraLista() {
        val listaDeAlunos = findViewById<ListView>(R.id.activity_main_lista_de_alunos)

        configuraAdapter(listaDeAlunos)
        configuraListenerCliquePorItem(listaDeAlunos)
        registerForContextMenu(listaDeAlunos)
    }


    private fun configuraAdapter(listaDeAlunos: ListView) {
        adapter = ListaAlunoAdapter(this)
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






