package com.silvio.jogo21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btn1, btn2, btn3;
    SharedPreferences pref;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.vencedor);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        i = new Intent(MainActivity.this, Main2Activity.class);
        pref = getSharedPreferences("player_details",MODE_PRIVATE);

        textView.setText("");

        if(pref.contains("1") && pref.contains("2")){
            System.out.println("key 1 "+pref.getString("1",null));
            System.out.println("key 2 "+pref.getString("2",null));
            if(Integer.parseInt(pref.getString("1",null)) < 22){
                if(Integer.parseInt(pref.getString("2",null)) < 22){
                    if (Integer.parseInt(pref.getString("1",null)) > Integer.parseInt(pref.getString("2",null))) {
                        textView.setText("Player 1 venceu!");
                    } else if (Integer.parseInt(pref.getString("1",null)) < Integer.parseInt(pref.getString("2",null))){
                        textView.setText("Player 2 venceu!");
                    } else {
                        textView.setText("Empate!");
                    }
                } else {
                    textView.setText("Player 1 venceu!");
                }
            } else if(Integer.parseInt(pref.getString("2",null)) < 22) {
                textView.setText("Player 2 venceu!");
            } else {
                textView.setText("Empate!");
            }
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("player", "1");
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("player", "2");
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                textView.setText("");
            }
        });

    }
}
