package br.barao.pdm.sharedpreferencesarquivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.barao.pdm.sharedpreferencesarquivos.controladores.LoginControlador;

public class AnotacoesActivity extends AppCompatActivity
{
    private Context context = AnotacoesActivity.this;
    private LoginControlador loginControlador;
    private EditText etAnotacoes;
    private Button btSair;
    private TextView tvUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes);
        inicializaControladores();
        inicializaComponentes();
        inicializaEventos();
        carregaInformacoesUsuario();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        salvaAnotacoes();
    }

    private void inicializaControladores()
    {
        loginControlador = new LoginControlador(context);
    }

    private void inicializaComponentes()
    {
        tvUsuarioLogado = findViewById(R.id.tvUsuarioLogado);
        etAnotacoes = findViewById(R.id.etAnotacoes);
        btSair = findViewById(R.id.btSair);
    }

    private void inicializaEventos()
    {
        btSair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                deslogarUsuario();
            }
        });
    }

    private void deslogarUsuario()
    {
        loginControlador.limpaInformacoesLogin();
        finish();
        startActivity(new Intent(context, LoginActivity.class));
    }

    private void carregaInformacoesUsuario()
    {
        tvUsuarioLogado.setText(getString(R.string.UsuarioLogadoParametro, loginControlador.getEmail()));
    }
}