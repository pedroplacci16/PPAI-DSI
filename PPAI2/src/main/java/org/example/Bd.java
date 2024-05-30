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
        resenasVino2.add(new Resena("Comentario Resena2", false, new Date(), 3.5, null));
        resenas.add(resenasVino2.get(0));

        ArrayList<Resena> resenasVino3 = new ArrayList<>();
        resenasVino3.add(new Resena("Comentario Resena3", true, new Date(), 4.6, null));
        resenas.add(resenasVino3.get(0));

        ArrayList<Resena> resenasVino4 = new ArrayList<>();
        resenasVino4.add(new Resena("Comentario Resena4", true, new Date(), 4.7, null));
        resenas.add(resenasVino4.get(0));

        ArrayList<Resena> resenasVino5 = new ArrayList<>();
        resenasVino5.add(new Resena("Comentario Resena5", true, new Date(), 4.9, null));
        resenas.add(resenasVino5.get(0));

        ArrayList<Resena> resenasVino6 = new ArrayList<>();
        resenasVino6.add(new Resena("Comentario Resena6", true, new Date(), 4.0, null));
        resenas.add(resenasVino6.get(0));

        ArrayList<Resena> resenasVino7 = new ArrayList<>();
        resenasVino7.add(new Resena("Comentario Resena7", true, new Date(), 3.9, null));
        resenas.add(resenasVino7.get(0));

        ArrayList<Resena> resenasVino8 = new ArrayList<>();
        resenasVino8.add(new Resena("Comentario Resena8", true, new Date(), 5.0, null));
        resenas.add(resenasVino8.get(0));

        ArrayList<Resena> resenasVino9 = new ArrayList<>();
        resenasVino9.add(new Resena("Comentario Resena9", true, new Date(), 4.4, null));
        resenas.add(resenasVino3.get(0));

        ArrayList<Resena> resenasVino10 = new ArrayList<>();
        resenasVino10.add(new Resena("Comentario Resena10", true, new Date(), 3.7, null));
        resenas.add(resenasVino10.get(0));

        ArrayList<Resena> resenasVino11 = new ArrayList<>();
        resenasVino11.add(new Resena("Comentario Resena11", true, new Date(), 5.4, null));
        resenas.add(resenasVino11.get(0));

        ArrayList<Resena> resenasVino12 = new ArrayList<>();
        resenasVino12.add(new Resena("Comentario Resena12", false, new Date(), 3.4, null));
        resenas.add(resenasVino12.get(0));

        ArrayList<Resena> resenasVino13 = new ArrayList<>();
        resenasVino13.add(new Resena("Comentario Resena13", true, new Date(), 5.2, null));
        resenas.add(resenasVino13.get(0));

        ArrayList<Resena> resenasVino14 = new ArrayList<>();
        resenasVino14.add(new Resena("Comentario Resena14", true, new Date(), 5.8, null));
        resenas.add(resenasVino14.get(0));


        ArrayList<Resena> resenasVino15 = new ArrayList<>();
        resenasVino15.add(new Resena("Comentario Resena15", true, new Date(), 5.7, null));
        resenas.add(resenasVino15.get(0));

        // Crear objetos de Vino con el nuevo constructor
        vinos.add(new Vino("Nombre Vino1", 200.0, 4.5, new Date(), URI.create("http://imagen1.com"), bodegas.get(0), List.of(maridajes.get(0)), varietales, resenasVino1));
        vinos.add(new Vino("Nombre Vino2", 100.0, 3.5, new Date(), URI.create("http://imagen2.com"), bodegas.get(0), List.of(maridajes.get(1)), varietales, resenasVino2));
        vinos.add(new Vino("Nombre Vino3", 300.0, 4.6, new Date(), URI.create("http://imagen3.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino3));
        vinos.add(new Vino("Nombre Vino4", 400.0, 4.7, new Date(), URI.create("http://imagen4.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino4));
        vinos.add(new Vino("Nombre Vino5", 500.0, 4.9, new Date(), URI.create("http://imagen5.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino5));
        vinos.add(new Vino("Nombre Vino6", 600.0, 4.0, new Date(), URI.create("http://imagen6.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino6));
        vinos.add(new Vino("Nombre Vino7", 700.0, 3.9, new Date(), URI.create("http://imagen7.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino7));
        vinos.add(new Vino("Nombre Vino8", 800.0, 5.0, new Date(), URI.create("http://imagen8.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino8));
        vinos.add(new Vino("Nombre Vino9", 900.0, 4.4, new Date(), URI.create("http://imagen9.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino9));
        vinos.add(new Vino("Nombre Vino10", 300.0, 3.7, new Date(), URI.create("http://imagen10.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino10));
        vinos.add(new Vino("Nombre Vino11", 450.0, 5.4, new Date(), URI.create("http://imagen11.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino11));
        vinos.add(new Vino("Nombre Vino12", 550.0, 3.4, new Date(), URI.create("http://imagen12.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino12));
        vinos.add(new Vino("Nombre Vino13", 650.0, 5.2, new Date(), URI.create("http://imagen13.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino13));
        vinos.add(new Vino("Nombre Vino14", 750.0, 5.8, new Date(), URI.create("http://imagen14.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino14));
        vinos.add(new Vino("Nombre Vino15", 950.0, 5.7, new Date(), URI.create("http://imagen15.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino15));




        // Asignar los vinos a las reseñas
        resenasVino1.get(0).setVino(vinos.get(0));
        resenasVino2.get(0).setVino(vinos.get(1));
        resenasVino3.get(0).setVino(vinos.get(2));
        resenasVino4.get(0).setVino(vinos.get(3));
        resenasVino5.get(0).setVino(vinos.get(4));
        resenasVino6.get(0).setVino(vinos.get(5));
        resenasVino7.get(0).setVino(vinos.get(6));
        resenasVino8.get(0).setVino(vinos.get(7));
        resenasVino9.get(0).setVino(vinos.get(8));
        resenasVino10.get(0).setVino(vinos.get(9));
        resenasVino11.get(0).setVino(vinos.get(10));
        resenasVino12.get(0).setVino(vinos.get(11));
    }
}
