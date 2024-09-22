package com.example.conversordemoedas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class tela1_activity extends MyAppCompatActivity {

	private TextView valorDolar;
	private TextView valorLibra;
	private  TextView valorEuro;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela1);

		valorDolar = (TextView) findViewById(R.id.valorDolar);
		valorLibra = (TextView) findViewById(R.id.valorLibra);
		valorEuro = (TextView) findViewById(R.id.valorEuro);
		Context ctx = getBaseContext();

		// Realizando a conversão
		final double conversaoUSD =	ConversorDeMoedas.converterParaReal(ctx, 1,"USD", getApplicationContext());
		final double conversaoEUR =	ConversorDeMoedas.converterParaReal(ctx, 1,"EUR", getApplicationContext());
		final double conversaoGBP =	ConversorDeMoedas.converterParaReal(ctx, 1,"GBP", getApplicationContext());

		DecimalFormat df = new DecimalFormat("#.##");

		String valorConvertidoUSD =	df.format(conversaoUSD);
		Log.d("conversão","Valor USD convertido: "+ valorConvertidoUSD);

		String valorConvertidoEUR =	df.format(conversaoEUR);
		Log.d("conversão","Valor EUR convertido: "+ valorConvertidoEUR);

		String valorConvertidoGBP =	df.format(conversaoGBP);
		Log.d("conversão","Valor GBP convertido: "+ valorConvertidoGBP);

		valorDolar.setText(valorConvertidoUSD);
		valorEuro.setText(valorConvertidoEUR);
		valorLibra.setText(valorConvertidoGBP);
	}

	public void TelaConfiguracao(View v) {
		Intent telaConfiguracao = new Intent(this, tela3_activity.class);
		startActivity(telaConfiguracao);
	}

	String tipoMoedaSelecionada;

	public void idDaMoeda(View view) {
		int idDoBotao = view.getId();

		switch (idDoBotao) {
			case R.id.btnUSD:
				tipoMoedaSelecionada = "USD";
				break;
			case R.id.btnEURO:
				tipoMoedaSelecionada = "EUR";
				break;
			case R.id.btnLIBRA:
				tipoMoedaSelecionada = "GBP";
				break;
			case R.id.btnPESO:
				tipoMoedaSelecionada = "ARS";
				break;
			case R.id.btnYENE:
				tipoMoedaSelecionada = "JPY";
				break;
			case R.id.btnAUSTRALIANO:
				tipoMoedaSelecionada = "AUD";
				break;
			case R.id.btnCANADENSE:
				tipoMoedaSelecionada = "CAD";
				break;
			case R.id.btnYUAN:
				tipoMoedaSelecionada = "CNY";
				break;
			default:
				tipoMoedaSelecionada = "";
		}
	}

	public void TelaConversao(View view) {
		idDaMoeda(view);
		Intent telaConversao = new Intent(this, tela2_activity.class);
		telaConversao.putExtra("tipoMoedaSelecionada", tipoMoedaSelecionada);
		startActivity(telaConversao);
	}

}