package org.example.bd.clasesMapeadas;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "novedad_evento")
public class NovedadEventoBd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_descuento")
    private String codigoDescuentoPremium;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "es_evento")
    private boolean esSoloPremium;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fechaHoraEvento;

    @Column(name = "nombre_evento")
    private String nombreEvento;

    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private BodegaBd bodega;

    // Constructors
    public NovedadEventoBd() {}

    public NovedadEventoBd(String codigoDescuentoPremium, String descripcion, boolean esSoloPremium, Date fechaHoraEvento, String nombreEvento) {
        this.codigoDescuentoPremium = codigoDescuentoPremium;
        this.descripcion = descripcion;
        this.esSoloPremium = esSoloPremium;
        this.fechaHoraEvento = fechaHoraEvento;
        this.nombreEvento = nombreEvento;
    }

    // Getters and setters
    // ... (implement getters and setters for all fields)

    public String getCodigoDescuentoPremium() {
        return codigoDescuentoPremium;
    }

    public void setCodigoDescuentoPremium(String codigoDescuentoPremium) {
        this.codigoDescuentoPremium = codigoDescuentoPremium;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsSoloPremium() {
        return esSoloPremium;
    }

    public void setEsSoloPremium(boolean esSoloPremium) {
        this.esSoloPremium = esSoloPremium;
    }

    public Date getFechaHoraEvento() {
        return fechaHoraEvento;
    }

    public void setFechaHoraEvento(Date fechaHoraEvento) {
        this.fechaHoraEvento = fechaHoraEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public BodegaBd getBodega() {
        return bodega;
    }

    public void setBodega(BodegaBd bodega) {
        this.bodega = bodega;
    }
}
