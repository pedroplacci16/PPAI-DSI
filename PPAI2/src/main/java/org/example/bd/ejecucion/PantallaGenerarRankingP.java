package org.example.bd.ejecucion;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PantallaGenerarRankingP {
    private final JFrame ventana;
    private final JDateChooser dateChooserDesde;
    private final JDateChooser dateChooserHasta;
    private final Font fuenteGrande;


    public PantallaGenerarRankingP() {
        ventana = new JFrame("Mi Ventana Simple");
        ventana.setSize(600, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        fuenteGrande = new Font("Arial", Font.PLAIN, 20);

        dateChooserDesde = new JDateChooser();
        dateChooserHasta = new JDateChooser();

        JLabel fondo = new JLabel(new ImageIcon("/home/pedro/Desktop/PPAI-DSI/PPAI-ULTIMAVERSION/PPAI-DSI/PPAI2/src/main/java/imagen/FondoBonVino.png"));
        ventana.setContentPane(fondo);
        fondo.setLayout(new FlowLayout());

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new FlowLayout());

        fondo.add(dateChooserDesde);
        fondo.add(dateChooserHasta);

        fondo.add(panelContenido, BorderLayout.CENTER);

        ventana.setVisible(true);

    }


    public void opcGenerarRankingVinos(GestorGenerarRankingP gestor, PantallaGenerarRankingP pantalla){
        this.habilitarVentana(gestor, pantalla);
    }


    public void habilitarVentana(GestorGenerarRankingP gestor, PantallaGenerarRankingP pantalla) {
        // Creamos un contenedor para la ventana
        Container contenedor = ventana.getContentPane();

        // Configuramos el layout como GridBagLayout
        contenedor.setLayout(new GridBagLayout());

        // Hacemos visible la ventana
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        gestor.opcGenerarRankingVinos(pantalla, gestor);
    }


    public void solicitarSeleccionFechas(GestorGenerarRankingP gestor, PantallaGenerarRankingP pantalla) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 10));

        JLabel labelDesde = new JLabel("Fecha desde:");
        labelDesde.setFont(pantalla.fuenteGrande);
        panel.add(labelDesde);
        pantalla.dateChooserDesde.setFont(pantalla.fuenteGrande);
        panel.add(pantalla.dateChooserDesde);

        JLabel labelHasta = new JLabel("Fecha hasta:");
        labelHasta.setFont(pantalla.fuenteGrande);
        panel.add(labelHasta);
        pantalla.dateChooserHasta.setFont(pantalla.fuenteGrande);
        panel.add(pantalla.dateChooserHasta);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(pantalla.fuenteGrande);
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fechaDesde = pantalla.dateChooserDesde.getDate();
                Date fechaHasta = pantalla.dateChooserHasta.getDate();
                gestor.tomarSeleccionFechas(pantalla, gestor, fechaDesde, fechaHasta);
            }
        });


        // Agrega el botón "Cancelar" que llama a la función cerrarVentana()
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(pantalla.fuenteGrande);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });

        panel.add(btnFiltrar);
        panel.add(btnCancelar);


        // Creamos un contenedor para la ventana
        Container contenedor = pantalla.ventana.getContentPane();

        // Configuramos el layout como GridBagLayout
        contenedor.setLayout(new GridBagLayout());

        // Creamos un GridBagConstraints para configurar el posicionamiento del panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(130, 0, 0, 0);

        // Agregamos el panel al contenedor con los GridBagConstraints
        contenedor.add(panel, gbc);

        // Hacemos visible la ventana
        pantalla.ventana.setVisible(true);
    }


    public void solicitarSeleccionTipoResena(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor){

        //Borra todo lo que está en la ventana
        pantalla.ventana.getContentPane().removeAll();
        pantalla.ventana.repaint();

        // Crea un panel para agregar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crea un JComboBox para permitir al usuario seleccionar el tipo de resena"
        String[] tipos = { "Resenas normales", "Resenas sommelier", "Resenas amigos" };
        JComboBox<String> comboBoxTipos = new JComboBox<>(tipos);
        comboBoxTipos.setFont(pantalla.fuenteGrande);

        // Agrega el JComboBox al panel
        panel.add(comboBoxTipos);

        // Boton confirmar
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(pantalla.fuenteGrande);
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) comboBoxTipos.getSelectedItem();
                gestor.tomarSeleccionTipoResena(pantalla, gestor, tipoSeleccionado);
            }
        });

        // Boton cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(pantalla.fuenteGrande);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });

        // Boton volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAPantallaAnterior(pantalla, () -> solicitarSeleccionFechas(gestor, pantalla));
            }
        });

        panel.add(btnConfirmar);
        panel.add(btnVolver);
        panel.add(btnCancelar);

        // Creamos un contenedor para la ventana
        Container contenedor = pantalla.ventana.getContentPane();

        // Configuramos el layout como GridBagLayout
        contenedor.setLayout(new GridBagLayout());

        // Creamos un GridBagConstraints para configurar el posicionamiento del panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(130, 0, 0, 0);

        // Agregamos el panel al contenedor con los GridBagConstraints
        contenedor.add(panel, gbc);

        // Hacemos visible la ventana
        pantalla.ventana.setVisible(true);
    }


    public void solicitarSeleccionFormaVisualizacion(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor){
        // Borra t0do lo que está en la ventana
        pantalla.ventana.getContentPane().removeAll();
        pantalla.ventana.repaint();

        // Crea un panel para agregar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crea un JComboBox para permitir al usuario seleccionar la forma de visualizacion
        String[] tipos = { "Excel", "Pantalla", "PDF" };
        JComboBox<String> comboBoxTipos = new JComboBox<>(tipos);
        comboBoxTipos.setFont(pantalla.fuenteGrande);

        // Agrega el JComboBox al panel
        panel.add(comboBoxTipos);

        // Boton confirmar
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(pantalla.fuenteGrande);
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) comboBoxTipos.getSelectedItem();
                gestor.tomarSeleccionFormaVisualizacion(pantalla, gestor, tipoSeleccionado);
            }
        });
        // Boton Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(pantalla.fuenteGrande);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });

        // Boton volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAPantallaAnterior(pantalla, () -> solicitarSeleccionTipoResena(pantalla, gestor));
            }
        });


        panel.add(btnConfirmar);
        panel.add(btnVolver);
        panel.add(btnCancelar);

        // Creamos un contenedor para la ventana
        Container contenedor = pantalla.ventana.getContentPane();

        // Configuramos el layout como GridBagLayout
        contenedor.setLayout(new GridBagLayout());

        // Creamos un GridBagConstraints para configurar el posicionamiento del panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(130, 0, 0, 0);

        // Agregamos el panel al contenedor con los GridBagConstraints
        contenedor.add(panel, gbc);

        // Hacemos visible la ventana
        pantalla.ventana.setVisible(true);

    }


    public void solicitarConfirmacionReporte(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {

        // Crea un panel con un GridLayout para organizar los componentes en una columna
        JPanel panel = new JPanel(new GridLayout(4, 1, 2, 10));

        // Crea un mensaje de confirmación
        JLabel mensaje = new JLabel("¿Desea generar el reporte?");
        mensaje.setFont(pantalla.fuenteGrande);
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        panel.add(mensaje);

        // Crea botones para confirmar o cancelar
        JButton btnSi = new JButton("Sí");
        btnSi.setFont(pantalla.fuenteGrande);
        btnSi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestor.tomarConfirmacionReporte(pantalla, gestor);
            }
        });
        panel.add(btnSi);

        JButton btnNo = new JButton("No");
        btnNo.setFont(pantalla.fuenteGrande);
        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.ventana.dispose();
            }
        });
        panel.add(btnNo);

        // Boton volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAPantallaAnterior(pantalla, () -> solicitarSeleccionFormaVisualizacion(pantalla, gestor));
            }
        });
        panel.add(btnVolver);

        // Creamos un contenedor para la ventana
        Container contenedor = pantalla.ventana.getContentPane();

        // Configuramos el layout como GridBagLayout
        contenedor.setLayout(new GridBagLayout());

        // Creamos un GridBagConstraints para configurar el posicionamiento del panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(140, 0, 0, 0);

        // Limpiamos el contenedor, agregamos el panel y lo hacemos visible
        contenedor.removeAll();
        pantalla.ventana.repaint();
        contenedor.add(panel, gbc);
        pantalla.ventana.setVisible(true);
    }



    public void error(String mensaje, boolean cierre){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        if (cierre){
            System.exit(0);
        }
    }


    public void cerrarVentana() {
        ventana.dispose();
    }

    public void volverAPantallaAnterior(PantallaGenerarRankingP pantalla, Runnable metodoAnterior) {
        pantalla.ventana.getContentPane().removeAll();
        pantalla.ventana.repaint();
        metodoAnterior.run();
    }


    public void informarExportacionExitosa(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
        // Muestra un mensaje de dialogo
        JOptionPane.showMessageDialog(null, "Exportado a excel exitosamente");
        ventana.dispose();
    }
}