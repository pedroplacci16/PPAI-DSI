package org.example;

import java.util.*;


public class GestorGenerarRanking {
    private Date fechaDesde;
    private Date fechaHasta;
    private List<Vino> vinosOrdenados;

    public void opcGenerarRankingVinos(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor){
        //Aca tenemos que generar toda la parte del CU que hace el ranking de los vinos
        pantalla.solicitarSeleccionFechas(gestor, pantalla);

    }
    public void tomarSeleccionFechas(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, Date fechaDesde, Date fechaHasta){
        System.out.println(fechaHasta.toString());
        System.out.println(fechaDesde.toString());
        if(gestor.validarPeriodo(fechaDesde, fechaHasta)){
            gestor.fechaDesde = fechaDesde;
            gestor.fechaHasta = fechaHasta;
            pantalla.solicitarSeleccionTipoResena(pantalla,gestor);
        } else {
            //Falta programar alternativa (no es parte del CU)
        }

    }
    public boolean validarPeriodo(Date fechaDesde, Date fechaHasta){
        return fechaDesde.before(fechaHasta);
    }
    public void tomarSeleccionTipoResena(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, String tipo){
        System.out.println(tipo);
        pantalla.solicitarSeleccionFormaVisualizacion(pantalla, gestor);
    }

    public void tomarSeleccionFormaVisualizacion(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, String forma) {
        System.out.println(forma);
        pantalla.solicitarConfirmacionReporte(pantalla, gestor);
    }

    public void tomarConfirmacionReporte(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor) {
        System.out.println("Correcto");
        gestor.buscarPuntajeSommelierEnPeriodo(pantalla, gestor, gestor.fechaDesde, gestor.fechaHasta);
    }

    private void buscarPuntajeSommelierEnPeriodo(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, Date fechaDesde, Date fechaHasta) {
        System.out.println("Buscando....");
        //Tengo que modelar el acceso a BD
        Bd bd = new Bd();
        ArrayList<Vino> vinos = bd.vinos;
        System.out.println(bd.vinos.get(0).getPrecio());
        Map<String, Double> promedios = new HashMap<>();

        for (Vino vino : vinos) {
            // Calculamos el puntaje promedio del vino en el período especificado
            double promedio = vino.buscarPuntajeSommelierEnPeriodo(gestor.fechaDesde, gestor.fechaHasta);
            // Guardamos el puntaje promedio en el mapa con el nombre del vino como clave
            if(promedio!=0) {
                promedios.put(vino.getNombre(), promedio);
            }
        }

        // Ahora puedes ordenar el mapa 'promedios' por valor (puntaje promedio) y mostrarlo
                promedios.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).forEach(entry -> System.out.println("Vino: " + entry.getKey() + ", Puntaje promedio: " + entry.getValue()));



    }

}
