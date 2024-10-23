package org.example.bd.clasesMapeadas;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provincia")
public class ProvinciaBd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pais_id")
    private PaisBd pais;


    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegionBd> regiones = new ArrayList<>();

    // Constructors
    public ProvinciaBd() {}

    public ProvinciaBd(String nombre, List<RegionBd> region) {
        this.nombre = nombre;
        this.regiones = region;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PaisBd getPais() {
        return pais;
    }

    public void setPais(PaisBd pais) {
        this.pais = pais;
    }

    public List<RegionBd> getRegiones() {
        return regiones;
    }

    public void setRegiones(List<RegionBd> regiones) {
        this.regiones = regiones;
    }

    // Additional method
    public String buscarPais() {
        return pais.getNombre();
    }
}
