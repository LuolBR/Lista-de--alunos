package com.example.listadealunos.model

import java.io.Serializable

class Aluno(var nome: String, var telefone: String, var email: String) : Serializable {

    private var id: Int = 0

    fun setID(id: Int) {
        this.id = id
    }

    fun getID(): Int {
        return id
    }

    override fun toString(): String {
        return nome
    }
}
