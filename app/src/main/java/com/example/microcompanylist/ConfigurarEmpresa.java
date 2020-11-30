package com.example.microcompanylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ConfigurarEmpresa extends AppCompatActivity {

    private ListView listView;
    private EmpresaDAO dao;
    private List<Empresa> empresas;
    private List<Empresa> empresasFiltraados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_empresa);

        listView = findViewById(R.id.lista_empresas);
        dao = new EmpresaDAO(this);
        empresas = dao.obterTodos();
        empresasFiltraados.addAll(empresas);

        ArrayAdapter<Empresa> adaptador = new ArrayAdapter<Empresa>(this, android.R.layout.simple_list_item_1, empresasFiltraados);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Empresa empresaExcluir = empresasFiltraados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir a Empresa ?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          empresasFiltraados.remove(empresaExcluir);
                          empresas.remove(empresaExcluir);
                          dao.excluir(empresaExcluir);
                          listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Empresa empresaAtualizar = empresasFiltraados.get(menuInfo.position);

        Intent it = new Intent(this, CadastrarEmpresa.class);
        it.putExtra("empresa", empresaAtualizar);
        startActivity(it);
    }

    public void onResume() {
        super.onResume();
        empresas = dao.obterTodos();
        empresasFiltraados.clear();
        empresasFiltraados.addAll(empresas);
        listView.invalidateViews();
    }
}