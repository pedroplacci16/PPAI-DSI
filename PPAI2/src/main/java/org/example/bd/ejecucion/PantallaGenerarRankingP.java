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
        // Panel principal con esquinas redondeadas y tamaño ajustado
        JPanel panel = new RoundedPanel(30, new Color(245, 245, 245));
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 200));
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
        labelDesde.setFont(new Font("Garamond", Font.BOLD, 26));
        innerPanel.add(labelDesde, gbcInner);

        gbcInner.gridx = 1;
        pantalla.dateChooserDesde.setFont(pantalla.fuenteGrande);
        pantalla.dateChooserDesde.setPreferredSize(new Dimension(150, 30));
        innerPanel.add(pantalla.dateChooserDesde, gbcInner);

        // Etiqueta y campo de fecha "Fecha hasta:"
        gbcInner.gridx = 0;
        gbcInner.gridy = 1;
        JLabel labelHasta = new JLabel("Fecha Hasta: ");
        labelHasta.setFont(new Font("Garamond", Font.BOLD, 26));
        innerPanel.add(labelHasta, gbcInner);

        gbcInner.gridx = 1;
        pantalla.dateChooserHasta.setFont(pantalla.fuenteGrande);
        pantalla.dateChooserHasta.setPreferredSize(new Dimension(150, 30));
        innerPanel.add(pantalla.dateChooserHasta, gbcInner);

        panel.add(innerPanel, BorderLayout.CENTER);

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);


        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(pantalla.fuenteGrande);
        btnFiltrar.setBackground(new Color(34, 139, 34));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setFocusPainted(false);
        btnFiltrar.setPreferredSize(new Dimension(150, 40));
        btnFiltrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnFiltrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnFiltrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFiltrar.setBackground(new Color(60, 179, 113));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFiltrar.setBackground(new Color(34, 139, 34));
            }
        });

        btnFiltrar.addActionListener(e -> {
            Date fechaDesde = pantalla.dateChooserDesde.getDate();
            Date fechaHasta = pantalla.dateChooserHasta.getDate();
            gestor.tomarSeleccionFechas(pantalla, gestor, fechaDesde, fechaHasta);
        });


        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(pantalla.fuenteGrande);
        btnCancelar.setBackground(new Color(255, 102, 102));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(150, 40));
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
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(130, 0, -30, 0);

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
        panel.setPreferredSize(new Dimension(600, 200));
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
        labelTipo.setFont(new Font("Garamond", Font.BOLD, 26));
        innerPanel.add(labelTipo, gbcInner);

        // JComboBox para seleccionar tipo de reseña
        String[] tipos = { "Resenas normales", "Resenas sommelier", "Resenas amigos" };
        JComboBox<String> comboBoxTipos = new JComboBox<>(tipos);
        comboBoxTipos.setFont(pantalla.fuenteGrande);
        comboBoxTipos.setBackground(new Color(240, 248, 255));
        comboBoxTipos.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true)); // Borde azul claro
        comboBoxTipos.setFocusable(false);
        gbcInner.gridx = 1;
        innerPanel.add(comboBoxTipos, gbcInner);

        panel.add(innerPanel, BorderLayout.CENTER);

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);

        // Botón "Confirmar"
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(pantalla.fuenteGrande);
        btnConfirmar.setBackground(new Color(34, 139, 34));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setPreferredSize(new Dimension(150, 40));
        btnConfirmar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(60, 179, 113));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(34, 139, 34));
            }
        });

        btnConfirmar.addActionListener(e -> {
            String tipoSeleccionado = (String) comboBoxTipos.getSelectedItem();
            gestor.tomarSeleccionTipoResena(pantalla, gestor, tipoSeleccionado);
        });

        // Botón "Volver"
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.setBackground(new Color(102, 178, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(150, 40));
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(153, 204, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(102, 178, 255));
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
        btnCancelar.setPreferredSize(new Dimension(150, 40));
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
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(100, 0, -60, 0);

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
        panel.setPreferredSize(new Dimension(600, 200));
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
        labelTipo.setFont(new Font("Garamond", Font.BOLD, 26));
        innerPanel.add(labelTipo, gbcInner);

        // JComboBox para seleccionar la forma de visualización
        String[] tipos = { "Excel", "Pantalla", "PDF" };
        JComboBox<String> comboBoxTipos = new JComboBox<>(tipos);
        comboBoxTipos.setFont(pantalla.fuenteGrande);
        comboBoxTipos.setBackground(new Color(240, 248, 255));
        comboBoxTipos.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true)); // Borde azul claro
        comboBoxTipos.setFocusable(false);
        gbcInner.gridx = 1;
        innerPanel.add(comboBoxTipos, gbcInner);

        panel.add(innerPanel, BorderLayout.CENTER);

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);

        // Botón "Confirmar"
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(pantalla.fuenteGrande);
        btnConfirmar.setBackground(new Color(34, 139, 34));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setPreferredSize(new Dimension(150, 40));
        btnConfirmar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(60, 179, 113));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(new Color(34, 139, 34));
            }
        });

        btnConfirmar.addActionListener(e -> {
            String tipoSeleccionado = (String) comboBoxTipos.getSelectedItem();
            gestor.tomarSeleccionFormaVisualizacion(pantalla, gestor, tipoSeleccionado);
        });

        // Botón "Volver"
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.setBackground(new Color(102, 178, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(150, 40));
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(153, 204, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(102, 178, 255));
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
        btnCancelar.setPreferredSize(new Dimension(150, 40));
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
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(100, 0, -60, 0);

        contenedor.add(panel, gbc);
        pantalla.ventana.setVisible(true);
    }


    public void solicitarConfirmacionReporte(PantallaGenerarRankingP pantalla, GestorGenerarRankingP gestor) {
        // Borra todo lo que está en la ventana
        pantalla.ventana.getContentPane().removeAll();
        pantalla.ventana.repaint();

        // Panel principal con esquinas redondeadas y tamaño ajustado
        JPanel panel = new RoundedPanel(30, new Color(245, 245, 245));
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 150));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel interno para la etiqueta de mensaje (alineado centrado)
        JPanel mensajePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mensajePanel.setOpaque(false);
        JLabel mensaje = new JLabel("¿Desea generar el reporte?");
        mensaje.setFont(new Font("Garamond", Font.BOLD, 26));
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        mensaje.setForeground(Color.BLACK);
        mensajePanel.add(mensaje);

        // Agregar el panel del mensaje al panel principal
        panel.add(mensajePanel, BorderLayout.NORTH);

        // Panel de botones en la parte inferior
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonesPanel.setOpaque(false);

        // Botón "Sí"
        JButton btnSi = new JButton("Sí");
        btnSi.setFont(pantalla.fuenteGrande);
        btnSi.setBackground(new Color(34, 139, 34));
        btnSi.setForeground(Color.WHITE);
        btnSi.setPreferredSize(new Dimension(150, 40));
        btnSi.setFocusPainted(false);
        btnSi.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnSi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSi.setBackground(new Color(60, 179, 113));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSi.setBackground(new Color(34, 139, 34));
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
        btnNo.setBackground(new Color(255, 102, 102));
        btnNo.setForeground(Color.WHITE);
        btnNo.setPreferredSize(new Dimension(150, 40));
        btnNo.setFocusPainted(false);
        btnNo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnNo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNo.setBackground(new Color(255, 153, 153));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNo.setBackground(new Color(255, 102, 102));
            }
        });
        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.ventana.dispose();
            }
        });

        // Botón "Volver"
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(pantalla.fuenteGrande);
        btnVolver.setBackground(new Color(102, 178, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setPreferredSize(new Dimension(150, 40));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(153, 204, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(102, 178, 255));
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
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Configuración final del contenedor y la ventana
        Container contenedor = pantalla.ventana.getContentPane();
        contenedor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(100, 0, -60, 0);

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

            g2.setColor(new Color(200, 200, 200));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        }
    }
}
