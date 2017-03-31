package com.example.giovani.aula3;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListaAlunosActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_lista_alunos);

        //chama o Aluno adapter para preencher a ListActivity com os dados contidos na lista de alunos
        setListAdapter(new AlunoAdapter(this, AlunoActivity.listaAlunos));
    }
}