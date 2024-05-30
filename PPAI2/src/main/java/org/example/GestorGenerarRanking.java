package org.example;

import java.util.*;


public class GestorGenerarRanking {
    private Date fechaDesde;
    private Date fechaHasta;

    private List<Map.Entry<String, HashMap<String, Object>>> listaOrdenados;

    private HashMap<String, HashMap<String, Object>> vinosInfo = new HashMap<>();

    public void opcGenerarRankingVinos(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor){
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
            pantalla.error("Estan mal las fechas", false);
        }

    }
    public boolean validarPeriodo(Date fechaDesde, Date fechaHasta){
        return fechaDesde.before(fechaHasta);
    }
    public void tomarSeleccionTipoResena(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, String tipo){
        System.out.println(tipo);
        if(tipo == "Resenas sommelier") {
            pantalla.solicitarSeleccionFormaVisualizacion(pantalla, gestor);
        } if(tipo == "Resenas normales") {
            // Flujo alternativo
        } if(tipo == "Resenas amigos") {
            // Flujo alternativo
        }
    }

    public void tomarSeleccionFormaVisualizacion(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, String forma) {
        System.out.println(forma);
        if(forma == "Excel") {
            pantalla.solicitarConfirmacionReporte(pantalla, gestor);
        } if(forma == "PDF") {
            //
        }if(forma == "Pantalla") {
            //
        }
    }

    public void tomarConfirmacionReporte(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor) {
        System.out.println("Correcto");
        gestor.buscarVinosConResenasEnPeriodo(pantalla, gestor);
        //  Ordenar los vinos según la calificación de sommeliers.
        gestor.ordenarVinosPorPromedio();
        InterfazExcel.exportarExcel(gestor.listaOrdenados);
        pantalla.informarExportacionExitosa(pantalla, gestor);
        gestor.finCu(pantalla);
    }


    private void buscarPuntajeSommelierEnPeriodo(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor) {
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
                //Esto hay que rehacerlo
                promedios.put(vino.getNombre(), promedio);
                HashMap<String, Object> info = vinosInfo.get(vino.getNombre());
                if (info != null) {
                    info.put("puntajePromedio", promedio);  // agregamos el puntaje promedio a la información del vino
                }
            }
        }


    }
    private void buscarVinosConResenasEnPeriodo(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor) {
        System.out.println("Buscando vinos con reseñas en el período especificado...");
        Bd bd = new Bd();
        ArrayList<Vino> vinos = bd.vinos;

        for (Vino vino : vinos) {
            if (vino.tenesResenaEnPeriodo(gestor.fechaDesde, gestor.fechaHasta)) {
                String nombreVino = vino.getNombre();
                double precioVino = vino.getPrecio();
                String[] infoBodega = vino.buscarInfoBodega();
                String nombreBodega = infoBodega[0];
                String nombreRegion = infoBodega[1];
                String nombrePais = infoBodega[2];
                String[] descripcionVarietal = vino.buscarVarietal();


                HashMap<String, Object> info = new HashMap<>();
                info.put("precio", precioVino);
                info.put("nombreBodega", nombreBodega);
                info.put("nombreRegion", nombreRegion);
                info.put("nombrePais", nombrePais);
                info.put("descripcionVarietal", descripcionVarietal);
                vinosInfo.put(nombreVino, info);
            }
        }
        // Flujo alternativo
        if (vinosInfo.isEmpty()){
            pantalla.error("No existen resenas para ese periodo seleccionado", true);
        }
        gestor.buscarPuntajeSommelierEnPeriodo(pantalla, gestor);
    }

    public void ordenarVinosPorPromedio() {
        listaOrdenados = new ArrayList<>(vinosInfo.entrySet());

        // Ordena la lista basada en el valor de 'puntajePromedio'
        Collections.sort(listaOrdenados, new Comparator<Map.Entry<String, HashMap<String, Object>>>() {
            public int compare(Map.Entry<String, HashMap<String, Object>> o1, Map.Entry<String, HashMap<String, Object>> o2) {
                double puntaje1 = (double) o1.getValue().get("puntajePromedio");
                double puntaje2 = (double) o2.getValue().get("puntajePromedio");
                return Double.compare(puntaje2, puntaje1);
            }
        });




    }

    public void finCu(PantallaGenerarRanking pantalla) {
        pantalla.cerrarVentana();
    }


}
