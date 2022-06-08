package com.example.listadealunos.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.listadealunos.R
import com.example.listadealunos.dao.AlunosDao
import com.example.listadealunos.model.Aluno

const val NOVOALUNO_APPBAR: String = "Novo aluno"

class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEmail: EditText
    private val dao = AlunosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        this.title = NOVOALUNO_APPBAR

        inicializacaoCampos()
        configuraBotaoSalvar()
    }

    private fun inicializacaoCampos() {
        campoNome = findViewById(R.id.acitivity_formulario_aluno_nome)
        campoTelefone = findViewById(R.id.acitivity_formulario_aluno_telefone)
        campoEmail = findViewById(R.id.acitivity_formulario_aluno_email)
    }

    private fun configuraBotaoSalvar() {
        val btnSalvar: Button = findViewById(R.id.activity_formulario_aluno_salvar)
        btnSalvar.setOnClickListener {
            val novoAluno = criaAluno()
            salva(novoAluno)
        }
    }

    private fun criaAluno(): Aluno {
        val nome = campoNome.text.toString()
        val telefone = campoTelefone.text.toString()
        val email = campoEmail.text.toString()

        return Aluno(nome, telefone, email)
    }

    private fun salva(aluno: Aluno) {
        dao.salva(aluno)
        finish()
    }
}

