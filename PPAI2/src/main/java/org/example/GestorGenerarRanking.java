package org.example;

import java.util.*;

import java.util.Map.Entry;

import java.io.FileOutputStream;
import java.util.*;



public class GestorGenerarRanking {
    private Date fechaDesde;
    private Date fechaHasta;
    //private List<Map.Entry<String, Double>> vinosOrdenados;

    private HashMap<String, HashMap<String, Object>> vinosOrdenados;

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
        gestor.imprimirInformacionVinos();

        gestor.finCu(pantalla);
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
        ordenarVinosPorPromedio(pantalla, gestor);
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
                double puntajePromedio = 5;

                HashMap<String, Object> info = new HashMap<>();
                info.put("precio", precioVino);
                info.put("nombreBodega", nombreBodega);
                info.put("nombreRegion", nombreRegion);
                info.put("nombrePais", nombrePais);
                info.put("descripcionVarietal", descripcionVarietal);
                info.put("puntajePromedio", puntajePromedio);
                vinosInfo.put(nombreVino, info);
            }
        }
        gestor.buscarPuntajeSommelierEnPeriodo(pantalla, gestor, fechaDesde, fechaHasta);
    }

    //ESTE METODO NO VA, ES SOLO PARA MOSTRAR COMO SE OBTIENEN LOS DATOS DEL HASHMAP
    private void imprimirInformacionVinos() {
        for (Map.Entry<String, HashMap<String, Object>> entry : vinosOrdenados.entrySet()) {
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
    public void ordenarVinosPorPromedio(PantallaGenerarRanking pantalla, GestorGenerarRanking gestor) {
        List<Map.Entry<String, HashMap<String, Object>>> lista = new ArrayList<>(vinosInfo.entrySet());

        // Ordena la lista basada en el valor de 'puntajePromedio'
        Collections.sort(lista, new Comparator<Map.Entry<String, HashMap<String, Object>>>() {
            public int compare(Map.Entry<String, HashMap<String, Object>> o1, Map.Entry<String, HashMap<String, Object>> o2) {
                double puntaje1 = (double) o1.getValue().get("puntajePromedio");
                double puntaje2 = (double) o2.getValue().get("puntajePromedio");
                return Double.compare(puntaje2, puntaje1);
            }
        });

        // Inserta los datos de la lista al nuevo HashMap
        vinosOrdenados = new LinkedHashMap<>();
        for (Map.Entry<String, HashMap<String, Object>> entry : lista) {
            vinosOrdenados.put(entry.getKey(), entry.getValue());
        }

        InterfazExcel.exportarExcel(lista);

    }

    public void finCu(PantallaGenerarRanking pantalla) {
        pantalla.cerrarVentana();
    }


}
