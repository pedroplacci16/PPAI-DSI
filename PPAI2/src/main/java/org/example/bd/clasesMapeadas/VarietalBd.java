package org.example.bd.clasesMapeadas;

import jakarta.persistence.*;
import org.example.TipoUva;
@Entity
@Table(name = "varietal")
public class VarietalBd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "porcentaje")
    private double porcentajeComposicion;
    @ManyToOne
    @JoinColumn(name = "tipo_uva_id")
    private TipoUvaBd tipoUva;

    public VarietalBd(String descripcion, double porcentajeComposicion, TipoUvaBd tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
    }

    public VarietalBd() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPorcentajeComposicion() {
        return porcentajeComposicion;
    }

    public void setPorcentajeComposicion(double porcentajeComposicion) {
        this.porcentajeComposicion = porcentajeComposicion;
    }

    public TipoUvaBd getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(TipoUvaBd tipoUva) {
        this.tipoUva = tipoUva;
    }
}
