package org.example.iterator;

import java.util.Date;
import java.util.List;

public interface IIterador {
    boolean cumpleFiltro(Object[] filtro);

    Object elementoActual();
    void primero();
    void siguiente();
    boolean haTerminado();

}
