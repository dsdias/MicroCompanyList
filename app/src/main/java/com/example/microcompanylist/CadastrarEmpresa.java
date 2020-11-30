package com.example.microcompanylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastrarEmpresa extends AppCompatActivity {

    private EditText nomeEmpresa;
    private EditText endereco;
    private EditText telefone;
    private EditText proprietario;
    private String servico;
    private EmpresaDAO dao;
    private Empresa empresa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_empresa);

        nomeEmpresa = findViewById(R.id.editNomeEmpresa);
        endereco = findViewById(R.id.editEndereco);
        telefone = findViewById(R.id.editTelefone);
        proprietario = findViewById(R.id.editProprietario);
        dao = new EmpresaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("empresa")){
            empresa = (Empresa) it.getSerializableExtra("empresa");
            nomeEmpresa.setText(empresa.getNomeEmpresa());
            endereco.setText(empresa.getEndereco());
            telefone.setText(empresa.getTelefone());
            proprietario.setText(empresa.getProprietario());
        }

        Spinner spinner = (Spinner) findViewById(R.id.spTipoServico);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipoServico, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                servico = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void salvar(View view){

        if(empresa == null) {
            Empresa empresa = new Empresa();
            empresa.setNomeEmpresa(nomeEmpresa.getText().toString());
            empresa.setTipoServico(servico);
            empresa.setEndereco(endereco.getText().toString());
            empresa.setTelefone(telefone.getText().toString());
            empresa.setProprietario(proprietario.getText().toString());
            long id = dao.inserir(empresa);
            Toast.makeText(this,"Empresa inserida com id:" + id,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ListarEmpresa.class);
            startActivity(intent);
        } else{
            empresa.setNomeEmpresa(nomeEmpresa.getText().toString());
            empresa.setTipoServico(servico);
            empresa.setEndereco(endereco.getText().toString());
            empresa.setTelefone(telefone.getText().toString());
            empresa.setProprietario(proprietario.getText().toString());
            dao.atualizar(empresa);
            Toast.makeText(this,"Empresa atualizada com sucesso:",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ListarEmpresa.class);
            startActivity(intent);
        }
    }


}