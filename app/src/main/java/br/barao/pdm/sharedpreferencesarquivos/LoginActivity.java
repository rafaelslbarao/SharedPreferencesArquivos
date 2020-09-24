package br.barao.pdm.sharedpreferencesarquivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{
    private static final String NOME_SHAREDPREFERENCES = "INFORMACOES_LOGIN";
    private static final String EMAIL_KEY_SHAREDPREFERENCES = "EMAIL";
    private static final String SENHA_KEY_SHAREDPREFERENCES = "SENHA";
    private static final String MANTERLOGADO_KEY_SHAREDPREFERENCES = "MANTERLOGADO";
    //
    private Context context = LoginActivity.this;
    private EditText etEmail;
    private EditText etSenha;
    private CheckBox ckManterLogado;
    private Button btAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaComponentes();
        inicializaEventos();
        verificaUsuarioLogado();
    }

    private void inicializaComponentes()
    {
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        ckManterLogado = findViewById(R.id.ckManterLogado);
        btAcessar = findViewById(R.id.btAcessar);
    }

    private void inicializaEventos()
    {
        btAcessar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                realizaAcesso();
            }
        });
    }

    private void verificaUsuarioLogado()
    {
        String email;
        String senha;
        boolean manterLogado;
        //
        //Realiza a abertura do arquivo que armazena em formato XML os valores (chave, valor)
        SharedPreferences sharedPreferences = context.getSharedPreferences(NOME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(EMAIL_KEY_SHAREDPREFERENCES, null);
        senha = sharedPreferences.getString(SENHA_KEY_SHAREDPREFERENCES, null);
        manterLogado = sharedPreferences.getBoolean(MANTERLOGADO_KEY_SHAREDPREFERENCES, false);
        if(manterLogado)
        {
            abreSegundaTela();
        }
    }

    private void realizaAcesso()
    {
        if(!validaEmail())
            return;
        if(!validaSenha())
            return;

        salvaInformacoesLogin();
        abreSegundaTela();
    }

    private boolean validaEmail()
    {
        if(etEmail.getText().toString().trim().isEmpty())
        {
            etEmail.setError(getString(R.string.InformeEmail));
            return false;
        }
        else
            return true;
    }

    private boolean validaSenha()
    {
        if(etSenha.getText().toString().trim().isEmpty())
        {
            etSenha.setError(getString(R.string.InformeSenha));
            return false;
        }
        else
            return true;
    }

    private void salvaInformacoesLogin()
    {
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();

        SharedPreferences sharedPreferences = context.getSharedPreferences(NOME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorSharedPreferences = sharedPreferences.edit();
        if(ckManterLogado.isChecked())
        {
            editorSharedPreferences.putString(EMAIL_KEY_SHAREDPREFERENCES, email);
            editorSharedPreferences.putString(SENHA_KEY_SHAREDPREFERENCES, senha);
        }
        else
        {
            editorSharedPreferences.remove(EMAIL_KEY_SHAREDPREFERENCES);
            editorSharedPreferences.remove(SENHA_KEY_SHAREDPREFERENCES);
            //Limpa e apaga todas as chaves do arquivo de sharedpreferences
            //editorSharedPreferences.clear();
        }
        editorSharedPreferences.putBoolean(MANTERLOGADO_KEY_SHAREDPREFERENCES, ckManterLogado.isChecked());
        editorSharedPreferences.commit();
    }

    private void abreSegundaTela()
    {
        finish();
    }

}