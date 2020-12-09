package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonPoke).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MyPokemonActivity.class)));

        findViewById(R.id.buttonPokeCap).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegisterPokemonActivity.class)));
    }
}