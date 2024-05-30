package org.example;

public class Main {
    public static void main(String[] args) {
        GestorGenerarRanking gestor = new GestorGenerarRanking();
        PantallaGenerarRanking pantalla = new PantallaGenerarRanking();
        pantalla.opcGenerarRankingVinos(gestor, pantalla);
    }
}