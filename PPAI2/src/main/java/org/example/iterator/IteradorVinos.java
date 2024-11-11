package org.example.iterator;
import org.example.bd.clasesMapeadas.VinoBd;

import java.util.Date;
import java.util.List;

public class IteradorVinos implements IIterador {
    private final VinoBd[] elementos;
    private int posicionActual = 0;
    private Object[] filtros;

    public IteradorVinos(Object[] elementos, Object[] filtros) {
        this.elementos = (VinoBd[]) elementos;
        this.filtros = filtros;
    }

    @Override
    public boolean cumpleFiltro(Object[] fechas) {
        Date fechaInicio = (Date) fechas[0];
        Date fechaFin = (Date) fechas[1];
        return elementos[posicionActual].tenesResenaEnPeriodo(fechaInicio, fechaFin);
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
