package com.silvio.jogo21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private TextView textView, soma;
    private Button btnAdd, btnStop;
    private Intent i;
    SharedPreferences pref;
    private int pontuacao = 0, contador = 0, carta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.carta);
        soma = findViewById(R.id.soma);
        btnAdd = findViewById(R.id.btnAdd);
        btnStop = findViewById(R.id.btnStop);

        pref = getSharedPreferences("player_details", MODE_PRIVATE);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador < 5 && pontuacao < 21){
                    carta = (int)(Math.random()*13)+1;
                    pontuacao += carta;
                    textView.setText(carta+"");
                    soma.setText("S: "+pontuacao);
                    contador ++;
                } else {
                    if(contador>=5)
                        Toast.makeText(getApplicationContext(), "Voce ja pegou todas as cartas! ", Toast.LENGTH_SHORT).show();
                    if(pontuacao >= 21)
                        Toast.makeText(getApplicationContext(), "Voce ja possui 21 ou mais pontos! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(getIntent().getExtras().getString("player"), pontuacao+"");
                editor.commit();
                i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
