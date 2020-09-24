package br.barao.pdm.sharedpreferencesarquivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.barao.pdm.sharedpreferencesarquivos.controladores.LoginControlador;

public class LoginActivity extends AppCompatActivity
{
    private Context context = LoginActivity.this;
    private LoginControlador loginControlador;
    private EditText etEmail;
    private EditText etSenha;
    private CheckBox ckManterLogado;
    private Button btAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaControladores();
        inicializaComponentes();
        inicializaEventos();
        verificaUsuarioLogado();
    }

    private void inicializaControladores()
    {
        loginControlador = new LoginControlador(context);
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
        if(loginControlador.existeUsuarioLogado())
            abreSegundaTela();
    }

    private void realizaAcesso()
    {
        if(!validaEmail())
            return;
        if(!validaSenha())
            return;

        loginControlador.salvaInformacoesLogin(etEmail.getText().toString().trim(), etSenha.getText().toString(), ckManterLogado.isChecked());
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



    private void abreSegundaTela()
    {
        startActivity(new Intent(context, AnotacoesActivity.class));
        finish();
    }

}