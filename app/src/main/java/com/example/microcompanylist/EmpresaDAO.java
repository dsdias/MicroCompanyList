package com.example.microcompanylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public EmpresaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Empresa empresa){
        ContentValues values = new ContentValues();
        values.put("nomeEmpresa", empresa.getNomeEmpresa());
        values.put("tipoServico", empresa.getTipoServico());
        values.put("endereco", empresa.getEndereco());
        values.put("telefone", empresa.getTelefone());
        values.put("proprietario", empresa.getProprietario());
        return banco.insert("empresa", null, values);
    }

    public long excluir(Empresa empresa){
        return banco.delete("empresa", "id = ?", new String[]{empresa.getId().toString()});
    }

    public long atualizar(Empresa empresa){
        ContentValues values = new ContentValues();
        values.put("nomeEmpresa", empresa.getNomeEmpresa());
        values.put("tipoServico", empresa.getTipoServico());
        values.put("endereco", empresa.getEndereco());
        values.put("telefone", empresa.getTelefone());
        values.put("proprietario", empresa.getProprietario());
        return banco.update("empresa", values,
                "id = ?", new String[]{empresa.getId().toString()});
    }

    public List<Empresa> obterTodos(){
        List<Empresa> empresas = new ArrayList<>();
        Cursor cursor = banco.query("empresa", new String[]{"id","nomeEmpresa","tipoServico", "endereco","telefone","proprietario"},null, null,null,null,null);
        while (cursor.moveToNext()){
            Empresa a = new Empresa();
            a.setId(cursor.getInt(0));
            a.setNomeEmpresa(cursor.getString(1));
            a.setTipoServico(cursor.getString(2));
            a.setEndereco(cursor.getString(3));
            a.setTelefone(cursor.getString(4));
            a.setProprietario(cursor.getString(5));
            empresas.add(a);
        }
        return empresas;
    }

}
