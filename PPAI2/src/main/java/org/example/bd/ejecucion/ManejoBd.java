package org.example.bd.ejecucion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.*;
import org.example.bd.clasesMapeadas.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManejoBd {
    // Crear los ArrayLists como atributos de la clase Bd
    public ArrayList<TipoUvaBd> tiposUva = new ArrayList<>();
    public ArrayList<VarietalBd> varietales = new ArrayList<>();
    public ArrayList<MaridajeBd> maridajes = new ArrayList<>();
    public ArrayList<BodegaBd> bodegas = new ArrayList<>();
    public ArrayList<VinoBd> vinos = new ArrayList<>();
    public ArrayList<ResenaBd> resenas = new ArrayList<>();
    public ArrayList<NovedadEventoBd> novedadesEvento = new ArrayList<>();

    public void poblarBd() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bd_ppai");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Inicializar los objetos en el constructor de Bd
            tiposUva.add(new TipoUvaBd("Descripción TipoUva1", "Nombre TipoUva1"));
            tiposUva.add(new TipoUvaBd("Descripción TipoUva2", "Nombre TipoUva2"));
            tiposUva.add(new TipoUvaBd("Descripción TipoUva3", "Nombre TipoUva3"));
            for(TipoUvaBd tipoUva: tiposUva){
                em.persist(tipoUva);
            }

            varietales.add(new VarietalBd("Descripción Varietal1", 0.5, tiposUva.get(0)));
            varietales.add(new VarietalBd("Descripción Varietal2", 0.5, tiposUva.get(1)));
            varietales.add(new VarietalBd("Descripción Varietal3", 0.5, tiposUva.get(2)));
            for(VarietalBd varietal: varietales){
                em.persist(varietal);
            }

            maridajes.add(new MaridajeBd("Descripción Maridaje1", "Nombre Maridaje1"));
            maridajes.add(new MaridajeBd("Descripción Maridaje2", "Nombre Maridaje2"));
            maridajes.add(new MaridajeBd("Descripción Maridaje3", "Nombre Maridaje3"));
            for (MaridajeBd maridaje : maridajes) {
                em.persist(maridaje);
            }

            // Crear objetos de NovedadEvento
            novedadesEvento.add(new NovedadEventoBd("Código Descuento1", "Descripción NovedadEvento1", true, new Date(), "Nombre Evento1"));
            for (NovedadEventoBd novedadEvento : novedadesEvento) {
                em.persist(novedadEvento);
            }
            RegionBd region = new RegionBd("Ciudad", "Capital");

            RegionBd region2 = new RegionBd("Ciudad2", "Capital1");

            RegionBd region3 = new RegionBd("Ciudad3", "Capital2");

            // Crear el ArrayList
            ArrayList<RegionBd> regionList = new ArrayList<>();

            // Añadir los objetos Region al ArrayList
            regionList.add(region);
            regionList.add(region2);
            regionList.add(region3);
            for(RegionBd regionBd : regionList) {
                em.persist(regionBd);
            }
            em.flush();
            ProvinciaBd provincia = new ProvinciaBd("Mendoza", regionList);


            // Crear más objetos Region
            RegionBd region4 = new RegionBd("Ciudad4", "Capital3");
            RegionBd region5 = new RegionBd("Ciudad5", "Capital4");
            RegionBd region6 = new RegionBd("Ciudad6", "Capital5");


            ArrayList<RegionBd> regionList2 = new ArrayList<>();

            // Añadir los nuevos objetos Region al ArrayList regionList2
            regionList2.add(region4);
            regionList2.add(region5);
            regionList2.add(region6);
            for (RegionBd regionBd : regionList2) {
                em.persist(regionBd);
            }

            ProvinciaBd provincia2 = new ProvinciaBd("Cordoba", regionList2);


            ArrayList<ProvinciaBd> provincias = new ArrayList<>();
            provincias.add(provincia);
            provincias.add(provincia2);

            PaisBd pais1 = new PaisBd("Argentina", provincias);

            //En esta parte modelo la relacion de dependencia de las clases, seteando a que provincia pertenece la region del pais
            //Para evitar complicaciones vamos a modelar solo con la primer region
            ProvinciaBd provDePais = pais1.getProvincias().get(0);
            provDePais.getRegiones().get(0).setProvincia(provDePais);
            provDePais.setPais(pais1);
            em.persist(pais1);
            em.persist(provDePais);

            // Crear objetos de Bodega con el nuevo constructor
            bodegas.add(new BodegaBd("Coordenadas Bodega1", "Descripción Bodega1", "Historia Bodega1", "Nombre Bodega1", 1, region, List.of(novedadesEvento.get(0))));
            for (BodegaBd bodega : bodegas) {
                em.persist(bodega);
            }
            // Crear ArrayList de Resena
            ArrayList<ResenaBd> resenasVino1 = new ArrayList<>();
            resenasVino1.add(new ResenaBd("Comentario Resena1", true, new Date(), 4.5, null));
            resenas.add(resenasVino1.get(0));

            ArrayList<ResenaBd> resenasVino2 = new ArrayList<>();
            resenasVino2.add(new ResenaBd("Comentario Resena2", false, new Date(), 3.5, null));
            resenas.add(resenasVino2.get(0));

            ArrayList<ResenaBd> resenasVino3 = new ArrayList<>();
            resenasVino3.add(new ResenaBd("Comentario Resena3", true, new Date(), 4.6, null));
            resenas.add(resenasVino3.get(0));

            ArrayList<ResenaBd> resenasVino4 = new ArrayList<>();
            resenasVino4.add(new ResenaBd("Comentario Resena4", true, new Date(), 4.7, null));
            resenas.add(resenasVino4.get(0));

            ArrayList<ResenaBd> resenasVino5 = new ArrayList<>();
            resenasVino5.add(new ResenaBd("Comentario Resena5", true, new Date(), 4.9, null));
            resenas.add(resenasVino5.get(0));

            ArrayList<ResenaBd> resenasVino6 = new ArrayList<>();
            resenasVino6.add(new ResenaBd("Comentario Resena6", false, new Date(), 4.0, null));
            resenas.add(resenasVino6.get(0));

            ArrayList<ResenaBd> resenasVino7 = new ArrayList<>();
            resenasVino7.add(new ResenaBd("Comentario Resena7", true, new Date(), 3.9, null));
            resenas.add(resenasVino7.get(0));

            ArrayList<ResenaBd> resenasVino8 = new ArrayList<>();
            resenasVino8.add(new ResenaBd("Comentario Resena8", true, new Date(), 5.0, null));
            resenas.add(resenasVino8.get(0));

            ArrayList<ResenaBd> resenasVino9 = new ArrayList<>();
            resenasVino9.add(new ResenaBd("Comentario Resena9", true, new Date(), 4.4, null));
            resenas.add(resenasVino3.get(0));

            ArrayList<ResenaBd> resenasVino10 = new ArrayList<>();
            resenasVino10.add(new ResenaBd("Comentario Resena10", true, new Date(), 3.7, null));
            resenas.add(resenasVino10.get(0));

            ArrayList<ResenaBd> resenasVino11 = new ArrayList<>();
            resenasVino11.add(new ResenaBd("Comentario Resena11", true, new Date(), 5.4, null));
            resenas.add(resenasVino11.get(0));

            ArrayList<ResenaBd> resenasVino12 = new ArrayList<>();
            resenasVino12.add(new ResenaBd("Comentario Resena12", false, new Date(), 3.4, null));
            resenas.add(resenasVino12.get(0));

            ArrayList<ResenaBd> resenasVino13 = new ArrayList<>();
            resenasVino13.add(new ResenaBd("Comentario Resena13", true, new Date(), 5.2, null));
            resenas.add(resenasVino13.get(0));

            ArrayList<ResenaBd> resenasVino14 = new ArrayList<>();
            resenasVino14.add(new ResenaBd("Comentario Resena14", true, new Date(), 5.8, null));
            resenas.add(resenasVino14.get(0));


            ArrayList<ResenaBd> resenasVino15 = new ArrayList<>();
            resenasVino15.add(new ResenaBd("Comentario Resena15", true, new Date(), 5.7, null));
            resenas.add(resenasVino15.get(0));


            // Crear objetos de Vino con el nuevo constructor
            vinos.add(new VinoBd("Nombre Vino1", 200.0, 4.5, new Date(), URI.create("http://imagen1.com"), bodegas.get(0), List.of(maridajes.get(0)), varietales, resenasVino1));
            vinos.add(new VinoBd("Nombre Vino2", 100.0, 3.5, new Date(), URI.create("http://imagen2.com"), bodegas.get(0), List.of(maridajes.get(1)), varietales, resenasVino2));
            vinos.add(new VinoBd("Nombre Vino3", 300.0, 4.6, new Date(), URI.create("http://imagen3.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino3));
            vinos.add(new VinoBd("Nombre Vino4", 400.0, 4.7, new Date(), URI.create("http://imagen4.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino4));
            vinos.add(new VinoBd("Nombre Vino5", 500.0, 4.9, new Date(), URI.create("http://imagen5.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino5));
            vinos.add(new VinoBd("Nombre Vino6", 600.0, 4.0, new Date(), URI.create("http://imagen6.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino6));
            vinos.add(new VinoBd("Nombre Vino7", 700.0, 3.9, new Date(), URI.create("http://imagen7.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino7));
            vinos.add(new VinoBd("Nombre Vino8", 800.0, 5.0, new Date(), URI.create("http://imagen8.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino8));
            vinos.add(new VinoBd("Nombre Vino9", 900.0, 4.4, new Date(), URI.create("http://imagen9.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino9));
            vinos.add(new VinoBd("Nombre Vino10", 300.0, 3.7, new Date(), URI.create("http://imagen10.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino10));
            vinos.add(new VinoBd("Nombre Vino11", 450.0, 5.4, new Date(), URI.create("http://imagen11.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino11));
            vinos.add(new VinoBd("Nombre Vino12", 550.0, 3.4, new Date(), URI.create("http://imagen12.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino12));
            vinos.add(new VinoBd("Nombre Vino13", 650.0, 5.2, new Date(), URI.create("http://imagen13.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino13));
            vinos.add(new VinoBd("Nombre Vino14", 750.0, 5.8, new Date(), URI.create("http://imagen14.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino14));
            vinos.add(new VinoBd("Nombre Vino15", 950.0, 5.7, new Date(), URI.create("http://imagen15.com"), bodegas.get(0), List.of(maridajes.get(2)), varietales, resenasVino15));
            for(VinoBd vino: vinos) {
                em.persist(vino);
            }

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
            resenasVino13.get(0).setVino(vinos.get(12));
            resenasVino14.get(0).setVino(vinos.get(13));
            resenasVino15.get(0).setVino(vinos.get(14));
            for(ResenaBd resena : resenas) {
                em.persist(resena);
            }


            tx.commit();
        }catch (Exception e){
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<VinoBd> obtenerTodosLosVinos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bd_ppai");
        EntityManager em = emf.createEntityManager();
        List<VinoBd> vinos = em.createQuery("SELECT v FROM VinoBd v", VinoBd.class).getResultList();
        em.close();
        return vinos;
    }
    public void eliminarTodoDeBd(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bd_ppai");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.createQuery("DELETE FROM ResenaBd").executeUpdate();
            em.createQuery("DELETE FROM VinoBd").executeUpdate();
            em.createQuery("DELETE FROM NovedadEventoBd").executeUpdate();
            em.createQuery("DELETE FROM BodegaBd").executeUpdate();
            em.createQuery("DELETE FROM MaridajeBd").executeUpdate();
            em.createQuery("DELETE FROM VarietalBd").executeUpdate();
            em.createQuery("DELETE FROM TipoUvaBd").executeUpdate();
            em.createQuery("DELETE FROM RegionBd").executeUpdate();
            em.createQuery("DELETE FROM ProvinciaBd").executeUpdate();
            em.createQuery("DELETE FROM PaisBd").executeUpdate();

            tx.commit();
            System.out.println("All created objects have been deleted from the database.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
