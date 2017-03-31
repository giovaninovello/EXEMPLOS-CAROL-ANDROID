package com.example.giovani.aula3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Carol on 22/03/2017.
 */

public class AlunoAdapter extends BaseAdapter{

    private Context context;
    private List<Aluno> list;

    public  AlunoAdapter (Context context, List<Aluno> list){
        this.context = context;
        this.list  = list;
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int auxPosition = position;//indica a posição do list

        //o método inflate do LayoutInflater permite que os componentes do layout de XML lista_alunos sejam manipulados
        // e permite trasnformar layout do xml em um item da ListActivity
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_lista_aluno,null);

        TextView txtNome = (TextView)layout.findViewById(R.id.textViewNomeAluno);//cria um objeto TextView e vincula com o TextView no activity_listar__aluno
        txtNome.setText(list.get(position).getNome());//seta o TextView com o nome do aluno contido no list

        Button btnEditar = (Button)layout.findViewById(R.id.buttonEditar);//cria um Botão e vincula do botão de editar do activity_listar__aluno
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//cria uma ação para o evento onClickListener do botão
                Intent intent = new Intent( context, AlunoActivity.class);//cria uma intent para chamar a tela de cadastro de alunos
                intent.putExtra("nome", list.get(auxPosition).getNome());//envia o nome do aluno para a AlunoActivity
                intent.putExtra("id", list.get(auxPosition).getId());//envia o id do aluno para a AlunoActivity
                intent.putExtra("cgu", list.get(auxPosition).getCgu());//envia o cgu do aluno para a AlunoActivity
                intent.putExtra("matricula", list.get(auxPosition).getMatricula());//envia o numero de matricula do aluno para a AlunoActivity
                context.startActivity(intent);//starta a activity
            }
        });

        return layout;//retorna o layout configurado
    }
}