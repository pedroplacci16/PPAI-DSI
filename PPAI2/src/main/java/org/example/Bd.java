package org.example;

import org.example.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Bd {
    // Crear los ArrayLists como atributos de la clase Bd
    public ArrayList<TipoUva> tiposUva = new ArrayList<>();
    public ArrayList<Varietal> varietales = new ArrayList<>();
    public ArrayList<Maridaje> maridajes = new ArrayList<>();
    public ArrayList<Bodega> bodegas = new ArrayList<>();
    public ArrayList<Vino> vinos = new ArrayList<>();
    public ArrayList<Resena> resenas = new ArrayList<>();
    public ArrayList<NovedadEvento> novedadesEvento = new ArrayList<>();

    public Bd() {
        // Inicializar los objetos en el constructor de Bd
        tiposUva.add(new TipoUva("Descripción TipoUva1", "Nombre TipoUva1"));
        tiposUva.add(new TipoUva("Descripción TipoUva2", "Nombre TipoUva2"));
        tiposUva.add(new TipoUva("Descripción TipoUva3", "Nombre TipoUva3"));

        varietales.add(new Varietal("Descripción Varietal1", 0.5, tiposUva.get(0)));
        varietales.add(new Varietal("Descripción Varietal2", 0.5, tiposUva.get(1)));
        varietales.add(new Varietal("Descripción Varietal3", 0.5, tiposUva.get(2)));

        maridajes.add(new Maridaje("Descripción Maridaje1", "Nombre Maridaje1"));
        maridajes.add(new Maridaje("Descripción Maridaje2", "Nombre Maridaje2"));
        maridajes.add(new Maridaje("Descripción Maridaje3", "Nombre Maridaje3"));

        // Crear objetos de NovedadEvento
        novedadesEvento.add(new NovedadEvento("Código Descuento1", "Descripción NovedadEvento1", true, new Date(), "Nombre Evento1"));
        Region region = new Region("Ciudad", "Capital");
        Region region2 = new Region("Ciudad2", "Capital1");
        Region region3 = new Region("Ciudad3", "Capital2");
        // Crear el ArrayList
        ArrayList<Region> regionList = new ArrayList<>();

        // Añadir los objetos Region al ArrayList
        regionList.add(region);
        regionList.add(region2);
        regionList.add(region3);
        Provincia provincia = new Provincia("Mendoza", regionList);

        // Crear más objetos Region con datos inventados
        Region region4 = new Region("Ciudad4", "Capital3");
        Region region5 = new Region("Ciudad5", "Capital4");
        Region region6 = new Region("Ciudad6", "Capital5");

        // Asegúrate de que regionList2 ha sido inicializado
        ArrayList<Region> regionList2 = new ArrayList<>();

        // Añadir los nuevos objetos Region al ArrayList regionList2
        regionList2.add(region4);
        regionList2.add(region5);
        regionList2.add(region6);

        Provincia provincia2 = new Provincia("Cordoba", regionList2);

        ArrayList<Provincia> provincias = new ArrayList<>();
        provincias.add(provincia);
        provincias.add(provincia2);

        Pais pais1 = new Pais("Argentina", provincias);

        //En esta parte modelo la relacion de dependencia de las clases, seteando a que provincia pertenece la region del pais
        //Para evitar complicaciones vamos a modelar solo con la primer region
        Provincia provDePais = pais1.getProvincias().get(0);
        pais1.getProvincias().get(0).getRegiones().get(0).setProvincia(provDePais);
        pais1.getProvincias().get(0).setPais(pais1);

        // Crear objetos de Bodega con el nuevo constructor
        bodegas.add(new Bodega("Coordenadas Bodega1", "Descripción Bodega1", "Historia Bodega1", "Nombre Bodega1", 1, region, List.of(novedadesEvento.get(0))));

        // Crear ArrayList de Resena
        ArrayList<Resena> resenasVino1 = new ArrayList<>();
        resenasVino1.add(new Resena("Comentario Resena1", true, new Date(), 4.5, null));
        resenas.add(resenasVino1.get(0));

        ArrayList<Resena> resenasVino2 = new ArrayList<>();
        resenasVino2.add(new Resena("Comentario Resena2", false, new Date(), 4.8, null));
        resenas.add(resenasVino2.get(0));

        ArrayList<Resena> resenasVino3 = new ArrayList<>();
        resenasVino3.add(new Resena("Comentario Resena3", true, new Date(), 4.6, null));
        resenas.add(resenasVino3.get(0));

        // Crear objetos de Vino con el nuevo constructor
        vinos.add(new Vino("Nombre Vino1", 100.0, 4.5, new Date(), URI.create("http://imagen1.com"), bodegas.get(0), List.of(maridajes.get(0)), varietales.get(0), resenasVino1));
        vinos.add(new Vino("Nombre Vino2", 200.0, 4.8, new Date(), URI.create("http://imagen2.com"), bodegas.get(0), List.of(maridajes.get(1)), varietales.get(1), resenasVino2));
        vinos.add(new Vino("Nombre Vino3", 300.0, 4.6, new Date(), URI.create("http://imagen3.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales.get(2), resenasVino3));

        // Asignar los vinos a las reseñas
        resenasVino1.get(0).setVino(vinos.get(0));
        resenasVino2.get(0).setVino(vinos.get(1));
        resenasVino3.get(0).setVino(vinos.get(2));
    }
}
