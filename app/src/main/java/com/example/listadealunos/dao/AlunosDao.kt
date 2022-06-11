package com.example.listadealunos.dao

import com.example.listadealunos.model.Aluno

class AlunosDao {

    companion object {
        var alunos: MutableList<Aluno> = mutableListOf()
            private set
        var contadorIDs: Int = 1
    }

    fun salva(aluno: Aluno) {
        aluno.setID(contadorIDs)
        alunos.add(aluno)
        atualizaIDs()
    }

    private fun atualizaIDs() {
        contadorIDs++
    }

    fun edita(aluno: Aluno?) {
        val alunoEncontrado: Aluno? = buscaAlunoPeloId(aluno)

        if (alunoEncontrado != null) {
            val posicaoAluno = alunos.indexOf(alunoEncontrado)

            if (aluno != null) {
                alunos[posicaoAluno] = aluno
            }
        }
    }

    fun remove(aluno: Aluno?) {
        val alunoExcluido = buscaAlunoPeloId(aluno)

        if (alunoExcluido != null) {
            alunos.remove(alunoExcluido)
        }
    }

    private fun buscaAlunoPeloId(aluno: Aluno?): Aluno? {
        for (a in alunos) {
            if (a.getID() == aluno?.getID()) {
                return a
            }
        }
        return null
    }

    fun listaTodosAlunos(): List<Aluno> {
        return alunos.toList()
    }
}
