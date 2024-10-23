package org.example.bd.clasesMapeadas;

import jakarta.persistence.*;
import org.example.Provincia;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pais")
public class PaisBd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProvinciaBd> provincias = new ArrayList<>();


    // Constructors
    public PaisBd() {}
    public PaisBd(String nombre, ArrayList<ProvinciaBd> provincias) {
        this.nombre = nombre;
        this.provincias = provincias;
    }

    public PaisBd(String nombre) {
        this.nombre = nombre;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProvinciaBd> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<ProvinciaBd> provincias) {
        this.provincias = provincias;
    }
}

