package org.example.bd.clasesMapeadas;
//create the same class as in the other files, but in this case, will implement JPA with Hibernate to do the mapping to a database
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "bodega")
public class BodegaBd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coordenadas")
    private String coordenadasUbicacion;

    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @Column(name = "historia",length = 2000)
    private String historia;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "anio_fundacion")
    private int periodoActualizacion;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionBd region;

    @OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NovedadEventoBd> novedad;

    // Constructors
    public BodegaBd() {}

    public BodegaBd(String coordenadasUbicacion, String descripcion, String historia, String nombre, int periodoActualizacion, RegionBd region, List<NovedadEventoBd> novedad) {
        this.coordenadasUbicacion = coordenadasUbicacion;
        this.descripcion = descripcion;
        this.historia = historia;
        this.nombre = nombre;
        this.periodoActualizacion = periodoActualizacion;
        this.region = region;
        this.novedad = novedad;
    }

    // Getters and setters
    // ... (implement getters and setters for all fields)

    public void setId(Long id) {
        this.id = id;
    }
    public String getCoordenadasUbicacion() {
        return coordenadasUbicacion;
    }

    public void setCoordenadasUbicacion(String coordenadasUbicacion) {
        this.coordenadasUbicacion = coordenadasUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeriodoActualizacion() {
        return periodoActualizacion;
    }

    public void setPeriodoActualizacion(int periodoActualizacion) {
        this.periodoActualizacion = periodoActualizacion;
    }

    public RegionBd getRegion() {
        return region;
    }

    public void setRegion(RegionBd region) {
        this.region = region;
    }

    public List<NovedadEventoBd> getNovedad() {
        return novedad;
    }

    public void setNovedad(List<NovedadEventoBd> novedad) {
        this.novedad = novedad;
    }
    public String[] buscarRegionYPais() {
        String[] infoRegion = new String[2];
        infoRegion[0] = region.getNombre();
        infoRegion[1] = region.buscarPais();
        return infoRegion;

    }
}

