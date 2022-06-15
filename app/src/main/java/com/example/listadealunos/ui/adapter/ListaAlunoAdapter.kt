package com.example.listadealunos.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.listadealunos.R
import com.example.listadealunos.model.Aluno

class ListaAlunoAdapter(private val context: Context) : BaseAdapter() {
    private val alunos: MutableList<Aluno> = mutableListOf()

    override fun getCount(): Int {
        return alunos.size
    }

    override fun getItem(p0: Int): Any {
        return alunos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return alunos[p0].getID().toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val viewCriada: View = LayoutInflater
            .from(context)
            .inflate(R.layout.item_aluno, p2, false)
        val alunoDevolvido: Aluno = alunos[p0]

        vincula(viewCriada, alunoDevolvido)
        return viewCriada
    }

    private fun vincula(view: View, aluno: Aluno) {
        val campoNome: TextView = view.findViewById(R.id.item_aluno_nome)
        campoNome.text = aluno.nome

        val campoTelefone: TextView = view.findViewById(R.id.item_aluno_telefone)
        campoTelefone.text = aluno.telefone
    }

    fun atualiza(alunos: List<Aluno>) {
        this.alunos.clear()
        this.alunos.addAll(alunos)
        notifyDataSetChanged()
    }

    fun remove(aluno: Aluno) {
        alunos.remove(aluno)
        notifyDataSetChanged()
    }
}