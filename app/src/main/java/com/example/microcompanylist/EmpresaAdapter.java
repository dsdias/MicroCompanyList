package com.example.microcompanylist;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EmpresaAdapter extends BaseAdapter {

    private List<Empresa> empresas;
    private Activity activity;

    public EmpresaAdapter(Activity activity, List<Empresa> empresas){
        this.activity = activity;
        this.empresas = empresas;
    }


    @Override
    public int getCount() {
        return empresas.size();
    }

    @Override
    public Object getItem(int position) {
        return empresas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return empresas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        TextView nomeEmpresa = v.findViewById(R.id.textNomeEmpresa);
        TextView tipoServico = v.findViewById(R.id.textTipoServico);
        TextView telefone = v.findViewById(R.id.textTelefone);
        TextView endereco = v.findViewById(R.id.textBairro);
        TextView proprietario = v.findViewById(R.id.textProprietario);
        Empresa a = empresas.get(position);
        nomeEmpresa.setText(a.getNomeEmpresa());
        tipoServico.setText(a.getTipoServico());
        telefone.setText(a.getTelefone());
        endereco.setText(a.getEndereco());
        proprietario.setText(a.getProprietario());

        return v;
    }
}
