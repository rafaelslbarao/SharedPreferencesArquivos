package br.barao.pdm.sharedpreferencesarquivos.controladores;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginControlador
{
    private static final String NOME_SHAREDPREFERENCES = "INFORMACOES_LOGIN";
    private static final String EMAIL_KEY_SHAREDPREFERENCES = "EMAIL";
    private static final String SENHA_KEY_SHAREDPREFERENCES = "SENHA";
    private static final String MANTERLOGADO_KEY_SHAREDPREFERENCES = "MANTERLOGADO";

    private Context context;

    public LoginControlador(Context context)
    {
        this.context = context;
    }

    public void salvaInformacoesLogin(String email, String senha, boolean manterLogado)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NOME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.putString(EMAIL_KEY_SHAREDPREFERENCES, email);
        if (manterLogado)
            editorSharedPreferences.putString(SENHA_KEY_SHAREDPREFERENCES, senha);
        else
            editorSharedPreferences.remove(SENHA_KEY_SHAREDPREFERENCES);
        editorSharedPreferences.putBoolean(MANTERLOGADO_KEY_SHAREDPREFERENCES, manterLogado);
        editorSharedPreferences.commit();
    }

    public void limpaInformacoesLogin()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NOME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        editorSharedPreferences.clear();
        editorSharedPreferences.commit();
    }

    public boolean existeUsuarioLogado()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NOME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        boolean manterLogado = sharedPreferences.getBoolean(MANTERLOGADO_KEY_SHAREDPREFERENCES, false);
        return manterLogado;
    }

    public String getEmail()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NOME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL_KEY_SHAREDPREFERENCES,"");
    }
}
