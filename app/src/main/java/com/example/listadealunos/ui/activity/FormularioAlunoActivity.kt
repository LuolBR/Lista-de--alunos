package com.example.listadealunos.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.listadealunos.R
import com.example.listadealunos.dao.AlunosDao
import com.example.listadealunos.model.Aluno
import com.example.listadealunos.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO

class FormularioAlunoActivity : AppCompatActivity() {

    companion object {
        private const val TITULO_NOVO_ALUNO_APPBAR: String = "Novo aluno"
        private const val TITULO_EDITA_ALUNO_APPBAR: String = "Edita aluno"
    }

    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEmail: EditText
    private lateinit var aluno: Aluno
    private val dao = AlunosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)

        inicializacaoCampos()
        carregaAluno()
    }

    private fun carregaAluno() {
        if (intent.hasExtra(CHAVE_ALUNO)) {
            aluno = intent.extras?.getSerializable(CHAVE_ALUNO) as Aluno
            this.title = TITULO_EDITA_ALUNO_APPBAR
            preencheCampos()
        } else {
            this.title = TITULO_NOVO_ALUNO_APPBAR
            aluno = Aluno(nome = "", telefone = "", email = "")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater
            .inflate(R.menu.activity_formulario_aluno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activity_formulario_aluno_menu_salvar) {
            preencheAluno()
            finalizaFormulario()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun preencheCampos() {
        campoNome.setText(aluno.nome)
        campoTelefone.setText(aluno.telefone)
        campoEmail.setText(aluno.email)
    }

    private fun inicializacaoCampos() {
        campoNome = findViewById(R.id.acitivity_formulario_aluno_nome)
        campoTelefone = findViewById(R.id.acitivity_formulario_aluno_telefone)
        campoEmail = findViewById(R.id.acitivity_formulario_aluno_email)
    }

    private fun preencheAluno() {
        val nome = campoNome.text.toString()
        val telefone = campoTelefone.text.toString()
        val email = campoEmail.text.toString()

        aluno.nome = nome
        aluno.telefone = telefone
        aluno.email = email
    }

    private fun finalizaFormulario() {
        if (aluno.getID() > 0) {
            dao.edita(aluno)
        } else {
            dao.salva(aluno)
        }
        finish()
    }
}

