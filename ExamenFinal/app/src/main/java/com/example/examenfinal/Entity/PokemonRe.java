package com.example.examenfinal.Entity;

public class PokemonRe {
    String nombre, tipo, imagen;
    Double latitude, longitude;

    public PokemonRe() {

    }

    public PokemonRe(String nombre, String tipo, String imagen, Double latitude, Double longitude) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl_imagen() {
        return imagen;
    }

    public void setUrl_imagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
