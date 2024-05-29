package org.example;

import java.util.*;


public class GestorGenerarRanking {
    private Date fechaDesde;
    private Date fechaHasta;
    private List<Map.Entry<String, Double>> vinosOrdenados;

    private HashMap<String, HashMap<String, Object>> vinosInfo = new HashMap<>();

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
        buscarVinosConResenasEnPeriodo(pantalla, gestor, gestor.fechaDesde, gestor.fechaHasta);
    }

    public void tomarConfirmacionReporte(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor) {
        System.out.println("Correcto");
        //Aca tengo que cambiar este metodo para que quede como en el diagrama
        //gestor.buscarPuntajeSommelierEnPeriodo(pantalla, gestor, gestor.fechaDesde, gestor.fechaHasta);
        gestor.buscarVinosConResenasEnPeriodo(pantalla, gestor, fechaDesde, fechaHasta);
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
                //Esto hay que rehacerlo
                promedios.put(vino.getNombre(), promedio);
                HashMap<String, Object> info = vinosInfo.get(vino.getNombre());
                if (info != null) {
                    info.put("puntajePromedio", promedio);  // agregamos el puntaje promedio a la información del vino
                }
            }
        }

        //  Ordenar los vinos según la calificación de sommeliers.
        ordenarVinosPorPromedio(pantalla, gestor,promedios);


    }

    private void ordenarVinosPorPromedio(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, Map<String, Double> promedios) {
        //convertimos el mapa a una lista
        vinosOrdenados = new ArrayList<>(promedios.entrySet());

        // Ordenamos la lista de vinos por puntaje promedio
        Collections.sort(vinosOrdenados, Map.Entry.<String, Double>comparingByValue().reversed());

        for (Map.Entry<String, Double> entry : vinosOrdenados) {
            String nombreVino = entry.getKey();
            double promedioSommelier = entry.getValue();
            System.out.println("Nombre del vino: " + nombreVino + ", Puntaje promedio del sommelier: " + promedioSommelier);
        }

        //BORRAR ESTO DESPUES
        gestor.imprimirInformacionVinos();
    }

    private void buscarVinosConResenasEnPeriodo(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor, Date fechaDesde, Date fechaHasta) {
        System.out.println("Buscando vinos con reseñas en el período especificado...");
        Bd bd = new Bd();
        ArrayList<Vino> vinos = bd.vinos;

        for (Vino vino : vinos) {
            if (vino.tenesResenaEnPeriodo(fechaDesde, fechaHasta)) {
                String nombreVino = vino.getNombre();
                double precioVino = vino.getPrecio();
                String[] infoBodega = vino.buscarInfoBodega();
                String nombreBodega = infoBodega[0];
                String nombreRegion = infoBodega[1];
                String nombrePais = infoBodega[2];
                String descripcionVarietal = vino.buscarVarietal();

                HashMap<String, Object> info = new HashMap<>();
                info.put("precio", precioVino);
                info.put("nombreBodega", nombreBodega);
                info.put("nombreRegion", nombreRegion);
                info.put("nombrePais", nombrePais);
                info.put("descripcionVarietal", descripcionVarietal);

                vinosInfo.put(nombreVino, info);
            }
        }
        gestor.buscarPuntajeSommelierEnPeriodo(pantalla, gestor, fechaDesde, fechaHasta);
    }

    //ESTE METODO NO VA, ES SOLO PARA MOSTRAR COMO SE OBTIENEN LOS DATOS DEL HASHMAP
    private void imprimirInformacionVinos() {
        for (Map.Entry<String, HashMap<String, Object>> entry : vinosInfo.entrySet()) {
            String nombreVino = entry.getKey();
            HashMap<String, Object> info = entry.getValue();

            double precioVino = (double) info.get("precio");
            String nombreBodega = (String) info.get("nombreBodega");
            String nombreRegion = (String) info.get("nombreRegion");
            String nombrePais = (String) info.get("nombrePais");
            String descripcionVarietal = (String) info.get("descripcionVarietal");
            double puntaje = (double) info.get("puntajePromedio");

            System.out.println("------------------------------------------------------------");
            System.out.println("Vino: " + nombreVino);
            System.out.println("Precio: " + precioVino);
            System.out.println("Bodega: " + nombreBodega);
            System.out.println("Región: " + nombreRegion);
            System.out.println("País: " + nombrePais);
            System.out.println("Descripcion Varietal: " + descripcionVarietal);
            System.out.println("Puntaje promedio: " + puntaje);
            System.out.println("------------------------------------------------------------");
        }
    }



}
