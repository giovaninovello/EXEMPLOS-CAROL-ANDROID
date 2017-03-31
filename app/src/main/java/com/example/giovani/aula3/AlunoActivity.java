package com.example.giovani.aula3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class AlunoActivity extends AppCompatActivity {

    //criando os objetos referentes aos campos de texto que existem na activity_aluno
    public TextView txtId;
    public EditText edtNome;
    public EditText edtCGU;
    public EditText edtMatricula;

    public Aluno aluno;//objeto da classe aluno
    public static int id = 1;//variável para incrementar um valor para o id do aluno
    public boolean ehNovo = true;

    public static List<Aluno> listaAlunos = new ArrayList<>();//lista de alunos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        //vincula os objetos criados aos campos de texto presentes na activity_aluno
        edtNome = (EditText)findViewById(R.id.editTextNome);
        edtCGU = (EditText)findViewById(R.id.editTextCGU);
        edtMatricula = (EditText) findViewById(R.id.editTextMatricula);
        txtId = (TextView) findViewById(R.id.textViewValorID);

        txtId.setText(String.valueOf(id));
        //cria um objeto do tipo intent para receber os dados que possam ter vindo na chamada da activity
        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle    = intent.getExtras();//cria um objeto bundle para receber os dados extras que possam ter sido enviados na  chamada da activity
            if (bundle != null){//se houverem extras entra no if (em caso de edição são enviados os dados do aluno )
                aluno = new Aluno();//instancia o objeto aluno
                //seta os dados do alunos com os dados enviados
                aluno.setId(bundle.getInt("id"));
                aluno.setNome(bundle.getString("nome"));
                aluno.setCgu(bundle.getString("cgu"));
                aluno.setMatricula(bundle.getString("matricula"));

                //preenche os campos da tela da activity_alunos com os dados do aluno
                txtId.setText(String.valueOf(aluno.getId()));
                edtNome.setText(aluno.getNome());
                edtCGU.setText(aluno.getCgu());;
                edtMatricula.setText(aluno.getMatricula());

                ehNovo = false;//seta a operação como edição
            }

        }

    }

    //função que salva o registro do aluno na lista de alunos
    //esta função deve ser indicada no evento onClick do botão Salvar da activity_aluno
    public void Salvar(View v){
        if (ehNovo) {//se for novo
            aluno = new Aluno();//instancia novo objeto aluno
            //seta os dados do aluno
            aluno.setId(Integer.parseInt(txtId.getText().toString()));
            aluno.setNome(edtNome.getText().toString());
            aluno.setCgu(edtCGU.getText().toString());
            aluno.setMatricula(edtMatricula.getText().toString());
            listaAlunos.add(aluno);//adiciona o objeto aluno na lista

        }else{//se não é novo está em modo de edição
            for(int i =0; i< listaAlunos.size(); i++){//laço para percorrer a lista de alunos
                if(listaAlunos.get(i).getId() ==aluno.getId()){//verifica se o aluno da lista tem o mesmo id do aluno editado
                    //seta os novos valores no objeto aluno
                    aluno.setNome(edtNome.getText().toString());
                    aluno.setCgu(edtCGU.getText().toString());
                    aluno.setMatricula(edtMatricula.getText().toString());

                    listaAlunos.set(i, aluno);//seta o posição indicada na lista com o novo objeto aluno
                    break;//sai do laço
                }
            }

        }


        Toast.makeText(this, "Aluno salvo com sucesso", Toast.LENGTH_SHORT).show();//mensagem curta
    }

    //função que limpa os campos e prepara a tela para que um novo aluno seja inserido
    //esta função deve ser indicada no evento onClick do botão Novo da activity_aluno
    public void Novo(View V){
        id++;//incrementa o id
        //limpa os campos de texto
        txtId.setText(String.valueOf(id));
        edtNome.setText("");
        edtMatricula.setText("");
        edtCGU.setText("");
        ehNovo= true;
    }

    //função que chama a listagem de alunos
    //esta função deve ser indicada no evento onClick do botão Listar da activity_aluno
    public void  Lista(View view){
        Intent intent = new Intent(this, ListaAlunosActivity.class);//cria uma nova intent para chamar a tela de listagem de alunos
        startActivity(intent);//chama a tela de listagem de alunos
    }

    //função para excluir o registro de um aluno da lista
    //esta função deve ser indicada no evento onClick do botão Excluir da activity_aluno
    public void Excluir (View view){

        //criando uma mensagem do tipo AlertDialog com dois botões "Sim" e "Cancelar" para confirmar a exclusão
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Exclusão de Aluno");
        alertDialog.setMessage("Tem certeza que deseja excluir o aluno: " + aluno.getNome()+ "?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //se o usuário clicar em sim deve percorrer a lista até encontrar o aluno com o id correspondente
                        for(int i =0; i< listaAlunos.size(); i++){
                            if(listaAlunos.get(i).getId() ==aluno.getId()){
                                listaAlunos.remove(i);//remove o registro do aluno
                                Toast.makeText(AlunoActivity.this, "Aluno Excluido com Sucesso!", Toast.LENGTH_SHORT).show();//mensagem curta
                                break;
                            }
                        }
                    }
                }
        );
        alertDialog.setNeutralButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
        alertDialog.show();//comando para exibir a mensagem
    }
}
