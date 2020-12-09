package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenfinal.Adapter.AdapterList;
import com.example.examenfinal.Entity.PokemonClass;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPokemonActivity extends AppCompatActivity {

    private static final String url = "https://upn.lumenes.tk/pokemons/N00026495";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pokemon);

        pokemonList();
    }

    private void pokemonList() {

        RequestQueue queue = Volley.newRequestQueue(MyPokemonActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {

                    Gson gson = new Gson();

                    List<PokemonClass> list = Arrays.asList(gson.fromJson(response, PokemonClass[].class));
                    SendAdapter(list);
                }

            }
        }, error -> {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        });
        queue.add(stringRequest);
    }

    private void SendAdapter(List<PokemonClass> classes) {

        RecyclerView recyclerView = findViewById(R.id.listPokemon);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        AdapterList adapterList = new AdapterList(classes, MyPokemonActivity.this);
        recyclerView.setAdapter(adapterList);

    }
}