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

        JLabel fondo = new JLabel(new ImageIcon("C:\\Users\\juanp\\IdeaProjects\\PPAI-DSI\\PPAI2\\src\\main\\java\\imagen\\FondoBonVino.png"));
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
        // Panel principal con esquinas redondeadas y tamaño ajustado
        JPanel panel = new RoundedPanel(30, new Color(245, 245, 245));
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 200)); // Panel más ancho y menos alto
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel interno para etiquetas y selectores de fecha (alineación horizontal)
        JPanel innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setOpaque(false);

        GridBagConstraints gbcInner = new GridBagConstraints();
        gbcInner.fill = GridBagConstraints.HORIZONTAL;
        gbcInner.insets = new Insets(10, 0, 10, 0);

        // Etiqueta y campo de fecha "Fecha desde:"
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        JLabel labelDesde = new JLabel("Fecha Desde: ");
        labelDesde.setFont(new Font("Garamond", Font.BOLD, 26)); // Fuente Serif, tamaño 18, negrita
        innerPanel.add(labelDesde, gbcInner);

        gbcInner.gridx = 1;
        pantalla.dateChooserDesde.setFont(pantalla.fuenteGrande);
        pantalla.dateChooserDesde.setPreferredSize(new Dimension(150, 30)); // Ajustar tamaño del JDateChooser
        innerPanel.add(pantalla.dateChooserDesde, gbcInner);

        // Etiqueta y campo de fecha "Fecha hasta:"
        gbcInner.gridx = 0;
        gbcInner.gridy = 1;
        JLabel labelHasta = new JLabel("Fecha Hasta: ");
        labelHasta.setFont(new Font("Garamond", Font.BOLD, 26)); // Fuente Serif, tamaño 18, negrita
        innerPanel.add(labelHasta, gbcInner);

        gbcInner.gridx = 1;
        pantalla.dateChooserHasta.setFont(pantalla.fuenteGrande);
        pantalla.dateChooserHasta.setPreferredSize(new Dimension(150, 30)); // Ajustar tamaño del JDateChooser
        innerPanel.add(pantalla.dateChooserHasta, gbcInner);

        panel.add(innerPanel, BorderLayout.CENTER); // Panel con las fechas en el centro

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);

        // Botón "Filtrar" con mismo tamaño que "Cancelar"
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(pantalla.fuenteGrande);
        btnFiltrar.setBackground(new Color(102, 178, 255));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFocusPainted(false);
        btnFiltrar.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnFiltrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnFiltrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnFiltrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFiltrar.setBackground(new Color(153, 204, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFiltrar.setBackground(new Color(102, 178, 255));
            }
        });

        btnFiltrar.addActionListener(e -> {
            Date fechaDesde = pantalla.dateChooserDesde.getDate();
            Date fechaHasta = pantalla.dateChooserHasta.getDate();
            gestor.tomarSeleccionFechas(pantalla, gestor, fechaDesde, fechaHasta);
        });

        // Botón "Cancelar" con mismo tamaño que "Filtrar"
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(pantalla.fuenteGrande);
        btnCancelar.setBackground(new Color(255, 102, 102));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(255, 153, 153));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(255, 102, 102));
            }
        });

        btnCancelar.addActionListener(e -> cerrarVentana());

        botonesPanel.add(btnFiltrar);
        botonesPanel.add(btnCancelar);
        panel.add(botonesPanel, BorderLayout.SOUTH); // Botones al fondo

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH; // Anclamos el panel al sur para bajarlo
        gbc.insets = new Insets(130, 0, -30, 0); // Ajustamos el espaciado para bajarlo más

        contenedor.add(panel, gbc);
        pantalla.ventana.setVisible(true);
    }




    public void solicitarSeleccionTipoResena(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
        // Borra todo lo que está en la ventana
        pantalla.ventana.getContentPane().removeAll();
        pantalla.ventana.repaint();

        // Panel principal con esquinas redondeadas y tamaño ajustado
        JPanel panel = new RoundedPanel(30, new Color(245, 245, 245));
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 200)); // Panel más ancho y menos alto
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel interno para etiquetas y JComboBox (alineación horizontal)
        JPanel innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setOpaque(false);

        GridBagConstraints gbcInner = new GridBagConstraints();
        gbcInner.fill = GridBagConstraints.HORIZONTAL;
        gbcInner.insets = new Insets(10, 0, 10, 0);

        // Etiqueta y JComboBox para seleccionar el tipo de reseña
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        JLabel labelTipo = new JLabel("Seleccionar tipo de reseña: ");
        labelTipo.setFont(new Font("Garamond", Font.BOLD, 26)); // Fuente Serif, tamaño 18, negrita
        innerPanel.add(labelTipo, gbcInner);

        // JComboBox para seleccionar tipo de reseña
        String[] tipos = { "Resenas normales", "Resenas sommelier", "Resenas amigos" };
        JComboBox<String> comboBoxTipos = new JComboBox<>(tipos);
        comboBoxTipos.setFont(pantalla.fuenteGrande);
        comboBoxTipos.setBackground(new Color(240, 248, 255)); // Color de fondo
        comboBoxTipos.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true)); // Borde azul claro
        comboBoxTipos.setFocusable(false); // Para evitar el borde de selección
        gbcInner.gridx = 1;
        innerPanel.add(comboBoxTipos, gbcInner);

        panel.add(innerPanel, BorderLayout.CENTER); // Panel con la selección de tipo de reseña en el centro

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);

        // Botón "Confirmar"
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(pantalla.fuenteGrande);
        btnConfirmar.setBackground(new Color(102, 178, 255));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnConfirmar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(153, 204, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(102, 178, 255));
            }
        });

        btnConfirmar.addActionListener(e -> {
            String tipoSeleccionado = (String) comboBoxTipos.getSelectedItem();
            gestor.tomarSeleccionTipoResena(pantalla, gestor, tipoSeleccionado);
        });

        // Botón "Volver"
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.setBackground(new Color(34, 139, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(60, 179, 113));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(34, 139, 34));
            }
        });

        btnVolver.addActionListener(e -> {
            volverAPantallaAnterior(pantalla, () -> solicitarSeleccionFechas(gestor, pantalla));
        });

        // Botón "Cancelar"
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(pantalla.fuenteGrande);
        btnCancelar.setBackground(new Color(255, 102, 102));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(255, 153, 153));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(255, 102, 102));
            }
        });

        btnCancelar.addActionListener(e -> cerrarVentana());

        botonesPanel.add(btnConfirmar);
        botonesPanel.add(btnVolver);
        botonesPanel.add(btnCancelar);
        panel.add(botonesPanel, BorderLayout.SOUTH); // Botones al fondo

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; // Ajustado para que el panel se muestre en la parte superior
        gbc.anchor = GridBagConstraints.CENTER; // Centra el panel
        gbc.insets = new Insets(100, 0, -60, 0); // Ajustar el espaciado para que esté centrado en la ventana

        contenedor.add(panel, gbc);
        pantalla.ventana.setVisible(true);
    }


    public void solicitarSeleccionFormaVisualizacion(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
        // Borra todo lo que está en la ventana
        pantalla.ventana.getContentPane().removeAll();
        pantalla.ventana.repaint();

        // Panel principal con esquinas redondeadas y tamaño ajustado
        JPanel panel = new RoundedPanel(30, new Color(245, 245, 245));
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 200)); // Panel más ancho y menos alto
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel interno para etiquetas y JComboBox (alineación horizontal)
        JPanel innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setOpaque(false);

        GridBagConstraints gbcInner = new GridBagConstraints();
        gbcInner.fill = GridBagConstraints.HORIZONTAL;
        gbcInner.insets = new Insets(10, 0, 10, 0);

        // Etiqueta y JComboBox para seleccionar la forma de visualización
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        JLabel labelTipo = new JLabel("Seleccionar forma de visualización: ");
        labelTipo.setFont(new Font("Garamond", Font.BOLD, 26)); // Fuente Serif, tamaño 18, negrita
        innerPanel.add(labelTipo, gbcInner);

        // JComboBox para seleccionar la forma de visualización
        String[] tipos = { "Excel", "Pantalla", "PDF" };
        JComboBox<String> comboBoxTipos = new JComboBox<>(tipos);
        comboBoxTipos.setFont(pantalla.fuenteGrande);
        comboBoxTipos.setBackground(new Color(240, 248, 255)); // Color de fondo
        comboBoxTipos.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true)); // Borde azul claro
        comboBoxTipos.setFocusable(false); // Para evitar el borde de selección
        gbcInner.gridx = 1;
        innerPanel.add(comboBoxTipos, gbcInner);

        panel.add(innerPanel, BorderLayout.CENTER); // Panel con la selección de forma de visualización en el centro

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);

        // Botón "Confirmar"
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(pantalla.fuenteGrande);
        btnConfirmar.setBackground(new Color(102, 178, 255));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnConfirmar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(153, 204, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(102, 178, 255));
            }
        });

        btnConfirmar.addActionListener(e -> {
            String tipoSeleccionado = (String) comboBoxTipos.getSelectedItem();
            gestor.tomarSeleccionFormaVisualizacion(pantalla, gestor, tipoSeleccionado);
        });

        // Botón "Volver"
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.setBackground(new Color(34, 139, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(60, 179, 113));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(34, 139, 34));
            }
        });

        btnVolver.addActionListener(e -> {
            volverAPantallaAnterior(pantalla, () -> solicitarSeleccionTipoResena(pantalla, gestor));
        });

        // Botón "Cancelar"
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(pantalla.fuenteGrande);
        btnCancelar.setBackground(new Color(255, 102, 102));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(255, 153, 153));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(255, 102, 102));
            }
        });

        btnCancelar.addActionListener(e -> cerrarVentana());

        botonesPanel.add(btnConfirmar);
        botonesPanel.add(btnVolver);
        botonesPanel.add(btnCancelar);
        panel.add(botonesPanel, BorderLayout.SOUTH); // Botones al fondo

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; // Ajustado para que el panel se muestre en la parte superior
        gbc.anchor = GridBagConstraints.CENTER; // Centra el panel
        gbc.insets = new Insets(100, 0, -60, 0); // Ajustar el espaciado para que esté centrado en la ventana

        contenedor.add(panel, gbc);
        pantalla.ventana.setVisible(true);
    }


    public void solicitarConfirmacionReporte(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
        // Borra todo lo que está en la ventana
        pantalla.ventana.getContentPane().removeAll();
        pantalla.ventana.repaint();

        // Panel principal con esquinas redondeadas y tamaño ajustado
        JPanel panel = new RoundedPanel(30, new Color(245, 245, 245)); // Color claro de fondo
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 150)); // Ajustar tamaño
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Bordes internos

        // Panel interno para la etiqueta de mensaje (alineado centrado)
        JPanel mensajePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mensajePanel.setOpaque(false); // Hacer transparente el panel de mensaje
        JLabel mensaje = new JLabel("¿Desea generar el reporte?");
        mensaje.setFont(new Font("Garamond", Font.BOLD, 26)); // Fuente Serif, tamaño 26, negrita
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        mensaje.setForeground(Color.BLACK); // Color de texto negro
        mensajePanel.add(mensaje);

        // Agregar el panel del mensaje al panel principal
        panel.add(mensajePanel, BorderLayout.NORTH);

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);

        // Botón "Sí"
        JButton btnSi = new JButton("Sí");
        btnSi.setFont(pantalla.fuenteGrande);
        btnSi.setBackground(new Color(102, 178, 255)); // Color de fondo azul
        btnSi.setForeground(Color.WHITE); // Color del texto blanco
        btnSi.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnSi.setFocusPainted(false); // Quitar borde de selección
        btnSi.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Bordes internos
        btnSi.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar el mouse
        btnSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSi.setBackground(new Color(153, 204, 255)); // Efecto al pasar el mouse
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSi.setBackground(new Color(102, 178, 255)); // Vuelve al color original
            }
        });
        btnSi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestor.tomarConfirmacionReporte(pantalla, gestor);
            }
        });

        // Botón "No"
        JButton btnNo = new JButton("No");
        btnNo.setFont(pantalla.fuenteGrande);
        btnNo.setBackground(new Color(255, 102, 102)); // Color de fondo rojo
        btnNo.setForeground(Color.WHITE); // Color del texto blanco
        btnNo.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnNo.setFocusPainted(false); // Quitar borde de selección
        btnNo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Bordes internos
        btnNo.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar el mouse
        btnNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNo.setBackground(new Color(255, 153, 153)); // Efecto al pasar el mouse
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNo.setBackground(new Color(255, 102, 102)); // Vuelve al color original
            }
        });
        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.ventana.dispose(); // Cierra la ventana si se presiona No
            }
        });

        // Botón "Volver"
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.setBackground(new Color(34, 139, 34)); // Color de fondo verde
        btnVolver.setForeground(Color.WHITE); // Color del texto blanco
        btnVolver.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo para los botones
        btnVolver.setFocusPainted(false); // Quitar borde de selección
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Bordes internos
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar el mouse
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(60, 179, 113)); // Efecto al pasar el mouse
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(34, 139, 34)); // Vuelve al color original
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAPantallaAnterior(pantalla, () -> solicitarSeleccionFormaVisualizacion(pantalla, gestor));
            }
        });

        // Agregar botones al panel de botones
        botonesPanel.add(btnSi);
        botonesPanel.add(btnNo);
        botonesPanel.add(btnVolver);
        panel.add(botonesPanel, BorderLayout.SOUTH); // Botones al fondo

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; // Ajustado para que el panel se muestre en la parte superior
        gbc.anchor = GridBagConstraints.CENTER; // Centra el panel
        gbc.insets = new Insets(100, 0, -60, 0); // Ajustar el espaciado para que esté centrado en la ventana

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

    // Clase personalizada para paneles con esquinas redondeadas
    class RoundedPanel extends JPanel {
        private int cornerRadius;
        private Color backgroundColor;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            this.cornerRadius = radius;
            this.backgroundColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

            g2.setColor(new Color(200, 200, 200)); // Borde gris claro
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        }
    }
}
