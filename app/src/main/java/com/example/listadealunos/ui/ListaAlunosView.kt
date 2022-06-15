package com.example.listadealunos.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import com.example.listadealunos.dao.AlunosDao
import com.example.listadealunos.model.Aluno
import com.example.listadealunos.ui.adapter.ListaAlunoAdapter

class ListaAlunosView(private val context: Context) {

    private val adapter = ListaAlunoAdapter(context)
    private val dao: AlunosDao = AlunosDao()

    fun confirmaRemocao(item: MenuItem) {
        AlertDialog
            .Builder(context)
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

    fun atualizaAlunos() {
        adapter.atualiza(dao.listaTodosAlunos())
    }

    private fun remove(aluno: Aluno) {
        dao.remove(aluno)
        adapter.remove(aluno)
    }

    fun configuraAdapter(listaDeAlunos: ListView) {
        listaDeAlunos.adapter = adapter
    }
}