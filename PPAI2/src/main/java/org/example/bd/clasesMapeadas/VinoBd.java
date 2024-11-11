package org.example.bd.clasesMapeadas;
import jakarta.persistence.*;
import org.example.iterator.IAgregado;
import org.example.iterator.IIterador;
import org.example.iterator.IteradorResena;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "vino")
public class VinoBd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private double precio;
    @Column(name = "calificacion")
    private double notaCata;
    @Column(name = "fecha_cosecha")
    private Date anada;
    @Column(name = "imagen_url")
    private URI imagen;
    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private BodegaBd bodega;
    @ManyToMany
    @JoinTable(name = "vino_maridaje",
    joinColumns = @JoinColumn(name = "vino_id"),
    inverseJoinColumns = @JoinColumn(name = "maridaje_id"))
    private List<MaridajeBd> maridajes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vino_varietal",
    joinColumns = @JoinColumn(name = "vino_id"),
    inverseJoinColumns = @JoinColumn(name = "varietal_id"))
    private List<VarietalBd> varietales;
    @OneToMany(mappedBy = "vino", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ResenaBd> resena;



    public VinoBd(String nombre, double precio, double notaCata, Date anada, URI imagen, BodegaBd bodega, List<MaridajeBd> maridajes, List<VarietalBd> varietales, List<ResenaBd> resena) {
        this.nombre = nombre;
        this.precio = precio;
        this.notaCata = notaCata;
        this.anada = anada;
        this.imagen = imagen;
        this.bodega = bodega;
        this.maridajes = maridajes;
        this.varietales = varietales;
        this.resena = resena;
    }

    public VinoBd() {

    }

    public List<ResenaBd> getResena() {
        return resena;
    }

    public void setResena(List<ResenaBd> resena) {
        this.resena = resena;
    }

    public BodegaBd getBodega() {
        return bodega;
    }

    public List<MaridajeBd> getMaridajes() {
        return maridajes;
    }

    public List<VarietalBd> getVarietales() {
        return varietales;
    }

    public void setVarietales(List<VarietalBd> varietales) {
        this.varietales = varietales;
    }

    public void setBodega(BodegaBd bodega) {
        this.bodega = bodega;
    }

    public void setMaridajes(List<MaridajeBd> maridajes) {
        this.maridajes = maridajes;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setNotaCata(double notaCata) {
        this.notaCata = notaCata;
    }

    public void setAnada(Date anada) {
        this.anada = anada;
    }

    public void setImagen(URI imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getNotaCata() {
        return notaCata;
    }

    public Date getAnada() {
        return anada;
    }

    public URI getImagen() {
        return imagen;
    }


    public double tenesPuntajeSommelierEnPeriodo(Date fechaDesde, Date fechaHasta) {
        List<Double> puntajes = new ArrayList<Double>();
        for (ResenaBd resena : resena){
            if(resena.sosDePeriodo(fechaDesde, fechaHasta)){
                if(resena.sosSommelierVerificado()){
                    puntajes.add(resena.getPuntaje());
                } else { puntajes.add((double) 0);}
            }
        }
        return this.calcularPuntajePromedio(puntajes);
    }

    public double calcularPuntajePromedio(List<Double> puntajes){
        // Calculamos el promedio
        double sum = 0;
        for (Double puntaje : puntajes) {
            sum += puntaje;
        }
        return sum / puntajes.size();
    }

    public boolean tenesResenaEnPeriodo(Date fechaDesde, Date fechaHasta) {
        if (resena == null || resena.isEmpty()) {
            return false; //no hay resenas
        }

        for (ResenaBd resena : resena) {
            if (resena.sosDePeriodo(fechaDesde, fechaHasta)) {
                if(resena.sosSommelierVerificado()) {
                    return true;//si hay resenas en periodo y son de sommelier
                }
            }
        }
        return false; // no hay resenas en periodo
    }


    public String[] buscarInfoBodega() {
        String[] infoBodega = new String[3];
        infoBodega[0] = bodega.getNombre();
        String[] infoRegionPais = new String[2];
        infoRegionPais = bodega.buscarRegionYPais();
        infoBodega[1] = infoRegionPais[0];
        infoBodega[2] = infoRegionPais[1];
        return infoBodega;

    }

    public String[] buscarVarietal() {
        String[] infoVarietales = new String[varietales.size()];
        int i = 0;
        for(VarietalBd varietal: varietales) {
            infoVarietales[i] = varietal.getDescripcion();
            i++;
        }
        return infoVarietales;
    }
}