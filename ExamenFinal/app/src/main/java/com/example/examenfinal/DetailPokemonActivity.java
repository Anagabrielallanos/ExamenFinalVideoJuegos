package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examenfinal.Entity.PokemonClass;
import com.squareup.picasso.Picasso;

public class DetailPokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pokemon);


        ImageView pokemonImage = findViewById(R.id.pokemonImage);
        TextView pokemonName = findViewById(R.id.pokemonName);
        TextView pokemonType = findViewById(R.id.pokemonType);

        PokemonClass aClass = (PokemonClass) getIntent().getSerializableExtra("Pokemon");

        assert aClass != null;

        Picasso.get()
                .load("https://upn.lumenes.tk" + aClass.getUrl_imagen())
                .into(pokemonImage);

        pokemonName.setText(aClass.getNombre());
        pokemonType.setText(aClass.getTipo());

        findViewById(R.id.pokemon).setOnClickListener(v -> {

            Intent intent = new Intent(DetailPokemonActivity.this, MapsActivity.class);
            intent.putExtra("Pokemon", aClass);
            startActivity(intent);
        });
    }
}