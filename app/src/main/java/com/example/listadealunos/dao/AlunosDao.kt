package com.example.listadealunos.dao

import com.example.listadealunos.model.Aluno

class AlunosDao {

    companion object {
        var alunos: MutableList<Aluno> = mutableListOf()
            private set
    }

    fun salva(aluno: Aluno) {
        alunos.add(aluno)
    }

    fun listaTodosAlunos(): List<Aluno> {
        return alunos.toList()
    }

}
