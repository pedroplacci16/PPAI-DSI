package org.example;

import javax.swing.*;

public class PantallaGenerarRanking {
    public void habilitarVentana() {
        // Crear una instancia de JFrame (la ventana)
        JFrame ventana = new JFrame("Mi Ventana Simple");

        // Configurar el tamaño de la ventana
        ventana.setSize(300, 200);

        // Crear una etiqueta con texto
        JLabel etiqueta = new JLabel("¡Hola, mundo!");

        // Agregar la etiqueta a la ventana
        ventana.add(etiqueta);

        // Hacer que la ventana se cierre al hacer clic en la "X"
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
    public void opcGenerarRenkingVinos(){
        this.habilitarVentana();
    }
}
