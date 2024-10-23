package org.example.bd.clasesMapeadas;
import jakarta.persistence.*;

@Entity
@Table(name = "region")
public class RegionBd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private ProvinciaBd provincia;

    public RegionBd() {
    }

    public RegionBd(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ProvinciaBd getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciaBd provincia) {
        this.provincia = provincia;
    }
    public String buscarPais() {
        return provincia.buscarPais();
    }

}

