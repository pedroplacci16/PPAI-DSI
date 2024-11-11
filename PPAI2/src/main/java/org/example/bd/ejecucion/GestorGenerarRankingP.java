package org.example.bd.ejecucion;

import org.example.InterfazExcel;
import org.example.bd.clasesMapeadas.VinoBd;
import org.example.iterator.IAgregado;
import org.example.iterator.IteradorVinos;

import java.util.*;

public class GestorGenerarRankingP implements IAgregado {
    private Date fechaDesde;
    private Date fechaHasta;

    private List<Map.Entry<String, HashMap<String, Object>>> listaOrdenados;

    private HashMap<String, HashMap<String, Object>> vinosInfo = new HashMap<>();

    public void opcGenerarRankingVinos(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor){
        pantalla.solicitarSeleccionFechas(gestor, pantalla);

    }

    public void tomarSeleccionFechas(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor, Date fechaDesde, Date fechaHasta){
        System.out.println(fechaHasta.toString());
        System.out.println(fechaDesde.toString());
        if(gestor.validarPeriodo(fechaDesde, fechaHasta)){
            gestor.fechaDesde = fechaDesde;
            gestor.fechaHasta = fechaHasta;
            pantalla.solicitarSeleccionTipoResena(pantalla,gestor);
        } else {
            pantalla.error("Fechas incorrectas, Ingrese las fechas nuevamente", false);
        }

    }


    public boolean validarPeriodo(Date fechaDesde, Date fechaHasta){
        return fechaDesde.before(fechaHasta);
    }


    public void tomarSeleccionTipoResena(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor, String tipo){
        System.out.println(tipo);
        if(tipo == "Resenas sommelier") {
            pantalla.solicitarSeleccionFormaVisualizacion(pantalla, gestor);
        } if(tipo == "Resenas normales") {
            // Flujo alternativo
        } if(tipo == "Resenas amigos") {
            // Flujo alternativo
        }
    }


    public void tomarSeleccionFormaVisualizacion(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor, String forma) {
        System.out.println(forma);
        if(forma == "Excel") {
            pantalla.solicitarConfirmacionReporte(pantalla, gestor);
        } if(forma == "PDF") {
            //
        }if(forma == "Pantalla") {
            //
        }
    }


    public void tomarConfirmacionReporte(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
        System.out.println("Correcto");
        gestor.buscarVinosConResenasEnPeriodo(pantalla, gestor);
        //  Ordenar los vinos según la calificación de sommeliers.
        gestor.ordenarVinosPorPromedio();
        InterfazExcel.exportarExcel(gestor.listaOrdenados);
        pantalla.informarExportacionExitosa(pantalla, gestor);
        gestor.finCu(pantalla);
    }


    private void buscarPuntajeSommelierEnPeriodo(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
        System.out.println("Buscando....");
        //Tengo que modelar el acceso a BD
        ManejoBd md = new ManejoBd();
        List<VinoBd> vinos = md.obtenerTodosLosVinos();
        System.out.println(vinos.get(0).getPrecio());
        Map<String, Double> promedios = new HashMap<>();

        for (VinoBd vino : vinos) {
            // Calculamos el puntaje promedio del vino en el período especificado
            double promedio = vino.tenesPuntajeSommelierEnPeriodo(gestor.fechaDesde, gestor.fechaHasta);

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
private void buscarVinosConResenasEnPeriodo(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
    System.out.println("Buscando vinos con reseñas en el período especificado...");
    ManejoBd md = new ManejoBd();
    List<VinoBd> vinos = md.obtenerTodosLosVinos();
    VinoBd[] vinosArray = vinos.toArray(new VinoBd[0]);
    IteradorVinos iterador = this.crearIterador(vinosArray);
    iterador.primero();
    while(!iterador.haTerminado()) {
        VinoBd vinoAct = (VinoBd) iterador.elementoActual();
        if (vinoAct != null) {
            String nombreVino = vinoAct.getNombre();
            double precioVino = vinoAct.getPrecio();
            String[] infoBodega = vinoAct.buscarInfoBodega();
            String nombreBodega = infoBodega[0];
            String nombreRegion = infoBodega[1];
            String nombrePais = infoBodega[2];
            String[] descripcionVarietal = vinoAct.buscarVarietal();


            HashMap<String, Object> info = new HashMap<>();
            info.put("precio", precioVino);
            info.put("nombreBodega", nombreBodega);
            info.put("nombreRegion", nombreRegion);
            info.put("nombrePais", nombrePais);
            info.put("descripcionVarietal", descripcionVarietal);
            vinosInfo.put(nombreVino, info);

        }
        iterador.siguiente();
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


    public void finCu(PantallaGenerarRankingP pantalla) {
        // Finaliza el CU
    }

    @Override
    public IteradorVinos crearIterador(Object[] vinos) {
        Object[] fechasFiltro = {fechaDesde, fechaHasta};
        return new IteradorVinos(vinos, fechasFiltro);
    }
}
