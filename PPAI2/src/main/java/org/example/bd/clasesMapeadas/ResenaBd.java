package org.example.bd.clasesMapeadas;

import jakarta.persistence.*;
import org.example.Vino;

import java.util.Date;
@Entity
@Table(name = "resena")
public class ResenaBd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "es_destacada")
    private boolean esPremium;
    @Column(name = "fecha")
    private Date fechaResena;
    @Column(name = "puntuacion")
    private double puntaje;
    @ManyToOne
    @JoinColumn(name = "vino_id")
    private VinoBd vino;

    public ResenaBd(String comentario, boolean esPremium, Date fechaResena, double puntaje, VinoBd vino) {
        this.comentario = comentario;
        this.esPremium = esPremium;
        this.fechaResena = fechaResena;
        this.puntaje = puntaje;
        this.vino = vino;
    }

    public ResenaBd() {

    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEsPremium() {
        return esPremium;
    }

    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }

    public Date getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(Date fechaResena) {
        this.fechaResena = fechaResena;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public VinoBd getVino() {
        return vino;
    }

    public void setVino(VinoBd vino) {
        this.vino = vino;
    }

    public boolean sosDePeriodo(Date fechaDesde, Date fechaHasta) {
        // Comprobar si this.fechaResena está en el rango [fechaDesde, fechaHasta]
        return !this.fechaResena.before(fechaDesde) && !this.fechaResena.after(fechaHasta);
    }

    public boolean sosSommelierVerificado() {
        return this.esPremium;
    }
}
