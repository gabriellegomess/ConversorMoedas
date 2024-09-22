package com.example.conversordemoedas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import java.text.DecimalFormat;

public class tela2_activity extends MyAppCompatActivity {

	private EditText valorConverter;
	private TextView valorConvertido;

	@SuppressLint("SetTextI18n")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela2);

		valorConverter = findViewById(R.id.valorConverter);
		valorConvertido = findViewById(R.id.valorConvertido);
		TextView nomeMoeda = findViewById ( R.id.nome_da_moeda );
		//Titulo
		final Intent intent = getIntent();
		final String tipoDeMoedaSelecionada = intent.getStringExtra("tipoMoedaSelecionada");
		nomeMoeda.setText ( tipoDeMoedaSelecionada + " para BRL" );
	}

	public void TelaConfiguracao(View v) {
		Intent telaConfiguracao = new Intent(this, tela3_activity.class);
		startActivity(telaConfiguracao);
	}

	public void IrParaHome(View v) {
		Intent telaHome = new Intent(this, tela1_activity.class);
		startActivity(telaHome);
	}

	public void converter( View v) {
		final Intent intent = getIntent();
		final String tipoDeMoedaSelecionada = intent.getStringExtra("tipoMoedaSelecionada");
		final String txtValor = valorConverter.getText().toString();

		new Thread(() -> {
			try {
				// Convertendo o valor para um número de ponto flutuante
				double valorEmDouble = Double.parseDouble(txtValor);

				Context ctx = getBaseContext();

				// Realizando a conversão
				final double conversao =
						ConversorDeMoedas.converterParaReal(ctx, valorEmDouble, tipoDeMoedaSelecionada, getApplicationContext());

				runOnUiThread( () -> {
					if (conversao >= 0.0) {
						DecimalFormat df = new DecimalFormat("#.##");
						String valorConvertidoStr = df.format(conversao);
						valorConvertido.setText(valorConvertidoStr + " BRL");
						valorConverter.setText("");
						valorConverter.findFocus();
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						// Isso ocultará o teclado virtual
						imm.hideSoftInputFromWindow(valorConverter.getWindowToken(), 0);

					} else {
						valorConvertido.setText("erro");
					}
				} );
			} catch (NumberFormatException e) {
				runOnUiThread( () -> valorConvertido.setText("Valor inválido") );
			}
		}).start();
	}
}
