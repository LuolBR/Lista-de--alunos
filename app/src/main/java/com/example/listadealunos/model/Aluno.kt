package com.example.listadealunos.model

data class Aluno(
    val nome: String,
    val telefone: String,
    val email: String
) {
    override fun toString(): String {
        return "$nome"
    }
}
