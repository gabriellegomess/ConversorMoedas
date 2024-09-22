package com.example.conversordemoedas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends MyAppCompatActivity {

    private static final int tempoLayout = 2000; // 2 segundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Usando um Handler para atrasar a transição
        new Handler().postDelayed((Runnable) () -> {

            // Este código será executado após o tempo especificado (2 segundos)
            Intent intent = new Intent(this, tela1_activity.class);
            startActivity(intent);

            // Encerrar esta Activity para que o usuário não possa voltar à tela Main
            finish();
        }, tempoLayout);
    }
}

