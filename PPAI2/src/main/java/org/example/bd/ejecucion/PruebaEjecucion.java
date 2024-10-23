package org.example.bd.ejecucion;
import org.example.GestorGenerarRanking;
import org.example.PantallaGenerarRanking;
import org.example.bd.clasesMapeadas.*;
import jakarta.persistence.*;
import java.util.*;

public class PruebaEjecucion {
    public static void main(String[] args) {
        ManejoBd bd = new ManejoBd();
        bd.eliminarTodoDeBd();
        bd.poblarBd();
        GestorGenerarRankingP gestor = new GestorGenerarRankingP();
        PantallaGenerarRankingP pantalla = new PantallaGenerarRankingP();
        pantalla.opcGenerarRankingVinos(gestor, pantalla);
    }

}


