package com.example.microcompanylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarEmpresa extends AppCompatActivity {

    private ListView listView;
    private EmpresaDAO dao;
    private List<Empresa> empresas;
    private List<Empresa> empresasFiltraados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empresa);

        listView = findViewById(R.id.lista_empresas);
        dao = new EmpresaDAO(this);
        empresas = dao.obterTodos();
        empresasFiltraados.addAll(empresas);

//        ArrayAdapter<Empresa> adaptador = new ArrayAdapter<Empresa>(this, android.R.layout.simple_list_item_1, empresasFiltraados);
        EmpresaAdapter adaptador = new EmpresaAdapter(this,empresasFiltraados);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                                      @Override
                                      public boolean onQueryTextSubmit(String query) {
                                          return false;
                                      }

                                      @Override
                                      public boolean onQueryTextChange(String s) {
                                          procuraEmpresa(s);
                                          return false;
                                      }
                                  }
        );

        return true;
    }

    public void cadastrar(MenuItem menu){
        Intent intent = new Intent(this, CadastrarEmpresa.class);
        startActivity(intent);
    }
    public void configurar(MenuItem menu){
        Intent intent = new Intent(this, ConfigurarEmpresa.class);
        startActivity(intent);
    }

    public void procuraEmpresa(String pesquisa) {
        empresasFiltraados.clear();
        String pesquisas = pesquisa.toLowerCase();
        for (Empresa a : empresas) {
            if (a.getTipoServico().toLowerCase().contains(pesquisas)
                    || a.getNomeEmpresa().toLowerCase().contains(pesquisas)
                    || a.getEndereco().toLowerCase().contains(pesquisas)
                    || a.getProprietario().toLowerCase().contains(pesquisas)
                    || a.getTelefone().toLowerCase().contains(pesquisas)) {
                empresasFiltraados.add(a);
            }
        }
        listView.invalidateViews();
    }

    public void onResume() {
        super.onResume();
        empresas = dao.obterTodos();
        empresasFiltraados.clear();
        empresasFiltraados.addAll(empresas);
        listView.invalidateViews();
    }

}