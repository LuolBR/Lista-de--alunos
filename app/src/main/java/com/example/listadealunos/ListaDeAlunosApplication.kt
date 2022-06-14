package com.example.listadealunos

import android.app.Application
import com.example.listadealunos.dao.AlunosDao
import com.example.listadealunos.model.Aluno

class ListaDeAlunosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val dao = AlunosDao()
        criaAlunosDeExemplo(dao)
    }

    private fun criaAlunosDeExemplo(dao: AlunosDao) {
        val pessoa1 = Aluno(nome = "Teste1", telefone = "1234", email = "teste1@dev.com")
        val pessoa2 = Aluno(nome = "Teste2", telefone = "1234", email = "teste2@dev.com")
        dao.salva(pessoa1)
        dao.salva(pessoa2)
    }
}