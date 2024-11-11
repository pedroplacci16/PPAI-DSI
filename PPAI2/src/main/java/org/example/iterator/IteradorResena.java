package org.example.iterator;

import org.example.bd.clasesMapeadas.ResenaBd;

import java.util.Date;

public class IteradorResena implements IIterador{
    private ResenaBd[] elementos;
    private int posicionActual = 0;
    private Object[] filtros;

    public IteradorResena(ResenaBd[] elementos, Object[] filtros) {
        this.elementos = elementos;
        this.filtros = filtros;
    }

    @Override
    public boolean cumpleFiltro(Object[] fechas) {
        Date fechaInicio = (Date) fechas[0];
        Date fechaFin = (Date) fechas[1];
        return elementos[posicionActual].sosDePeriodo(fechaInicio, fechaFin) && elementos[posicionActual].sosSommelierVerificado();
    }

    @Override
    public Object elementoActual() {
        if(this.cumpleFiltro(filtros)) {
            return elementos[posicionActual];
        } else {
            return null;
        }
    }

    @Override
    public void primero() {
        this.posicionActual = 0;
    }

    @Override
    public void siguiente() {
        this.posicionActual++;
    }

    @Override
    public boolean haTerminado() {
        return posicionActual >= elementos.length;
    }
}
