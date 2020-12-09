package com.example.examenfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.examenfinal.Entity.PokemonClass;
import com.example.examenfinal.Entity.PokemonRe;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class RegisterPokemonActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    EditText namePokemon, typePokemon, Latitude, Longitude;
    ImageView imagePokemon;
    String imageConvert;
    private static final String URL = "https://upn.lumenes.tk/pokemons/N00026495/crear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pokemon);

        namePokemon = findViewById(R.id.namePokemon);
        typePokemon = findViewById(R.id.typePokemon);
        Latitude = findViewById(R.id.Latitude);
        Longitude = findViewById(R.id.Longitude);
        imagePokemon = findViewById(R.id.imagePokemon);

        UploadPokemon();
        imagePokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });
    }

    @SuppressLint("IntentReset")
    private void UploadImage() {

        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    RegisterPokemonActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        }

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.Select_application)),
                REQUEST_CODE_LOCATION_PERMISSION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_LOCATION_PERMISSION:

                    Uri uri = data.getData();
                    RequestOptions requestOptions = new RequestOptions()
                            .placeholder(R.drawable.ic_baseline_photo_camera_24);

                    Glide.with(RegisterPokemonActivity.this)
                            .load(uri)
                            .apply(requestOptions)
                            .into(imagePokemon);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                        byte[] image = stream2.toByteArray();
                        imageConvert = Base64.encodeToString(image, Base64.DEFAULT);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + requestCode);
            }
        }
    }

    private void UploadPokemon() {

        findViewById(R.id.savePokemon).setOnClickListener(v -> {

            PokemonRe pokemonClass = new PokemonRe();

            pokemonClass.setNombre(Objects.requireNonNull(namePokemon.getText().toString()));
            pokemonClass.setTipo(Objects.requireNonNull(typePokemon.getText().toString()));

            Double lat = Double.valueOf(Objects.requireNonNull(Latitude.getText().toString()));
            Double log = Double.valueOf(Objects.requireNonNull(Longitude.getText().toString()));

            pokemonClass.setLatitude(lat);
            pokemonClass.setLongitude(log);

            pokemonClass.setUrl_imagen(Objects.requireNonNull(imageConvert));

            String jsonString = new Gson().toJson(pokemonClass);

            RequestQueue queue = Volley.newRequestQueue(RegisterPokemonActivity.this);

            try {
                JSONObject objJSon = new JSONObject(jsonString);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, objJSon, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, (Response.ErrorListener) error -> {
                    Toast.makeText(getApplicationContext(), "Ocurrio un Error", Toast.LENGTH_SHORT).show();
                });
                queue.add(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(RegisterPokemonActivity.this,MainActivity.class));
        });
    }
}