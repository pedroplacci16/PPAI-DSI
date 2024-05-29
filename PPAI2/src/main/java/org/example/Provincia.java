package org.example;

import java.util.ArrayList;

public class Provincia {

    private String nombre;
    private Pais pais;

    private ArrayList<Region> regiones;

    public Provincia(String nombre, ArrayList<Region> regiones) {
        this.nombre = nombre;
        this.regiones = regiones;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public ArrayList<Region> getRegiones() {
        return regiones;
    }

    public void setRegiones(ArrayList<Region> regiones) {
        this.regiones = regiones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String buscarPais() {
        return pais.getNombre();
    }
}