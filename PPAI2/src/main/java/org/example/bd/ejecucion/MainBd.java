package org.example.bd.ejecucion;

public class MainBd {
    public static void main(String[] args) {
        ManejoBd bd = new ManejoBd();
        bd.eliminarTodoDeBd();
        bd.poblarBd();
        GestorGenerarRankingP gestor = new GestorGenerarRankingP();
        PantallaGenerarRankingP pantalla = new PantallaGenerarRankingP();
        pantalla.opcGenerarRankingVinos(gestor, pantalla);
    }
}