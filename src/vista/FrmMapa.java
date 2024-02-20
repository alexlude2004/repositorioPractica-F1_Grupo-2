package vista;

import controlador.EstacionController;
import controlador.TDA.Listas.LinkedList;
import controlador.TDA.Listas.exception.VacioException;
import controlador.TDA.grafos.DibujarGrafo;
import controlador.util.Utilidades;
import java.io.File;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import vista.grafos.tablas.ModeloTablaAdyacencia;

/**
 *
 * @author alexg
 */
public class FrmMapa extends javax.swing.JFrame {

    private EstacionController ed = new EstacionController();
    private ModeloTablaAdyacencia mta = new ModeloTablaAdyacencia();

    /**
     * Creates new form FrmMapa
     */
    public FrmMapa() {
        initComponents();
        setLocationRelativeTo(null);
        panelImage1.setIcon(new ImageIcon("icono/fondo_pp_sec-fd.png"));
        limpiar();
    }

    private void guardarGrafo() {
        int i = JOptionPane.showConfirmDialog(null, "Esta de acuerdo con guardar el grafo?", "Pregunta", JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            try {
                ed.guardarGrafo();
                JOptionPane.showMessageDialog(null, "Grafo guardado", "OK", JOptionPane.OK_OPTION);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarTabla() {
        try {
            mta.setGrafo(ed.getGrafoEstacion());
            mta.fireTableDataChanged();
            tblTabla.setModel(mta);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void limpiar() {
        try {
            UtilesEstacionVista.cargarComboEstacion(cbxOrigen);
            UtilesEstacionVista.cargarComboEstacion(cbxDestino);
            cargarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar escuelas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adyacencia() {
        Integer posO = cbxOrigen.getSelectedIndex();
        Integer posD = cbxDestino.getSelectedIndex();
        if (posO != posD) {
            try {
                Double peso = UtilesEstacionVista.distanciaEstaciones(UtilesEstacionVista.getComboEstacion(cbxOrigen), UtilesEstacionVista.getComboEstacion(cbxDestino));
                ed.getGrafoEstacion().insertarAristaE(ed.getEstaciones().get(posO), ed.getEstaciones().get(posD), peso);
                limpiar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Adyacencia agregada", "OK", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede agregar la misma adyacencia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adyacenciaAleatoria() {
        try {
            Random rand = new Random();
            for (int i = 0; i < ed.getEstaciones().getSize(); i++) {
                int nodosConectados = 0;
                while (nodosConectados < 4) {
                    int posD = rand.nextInt(ed.getEstaciones().getSize() - 1);
                    posD = (posD >= i) ? posD + 1 : posD;
                    Double peso = UtilesEstacionVista.distanciaEstaciones(ed.getEstaciones().get(i), ed.getEstaciones().get(posD));
                    ed.getGrafoEstacion().insertarAristaE(ed.getEstaciones().get(i), ed.getEstaciones().get(posD), peso);
                    nodosConectados++;
                    if (nodosConectados != 1 && nodosConectados <= 4) {
                        break;
                    }
                }
            }
            limpiar();
            JOptionPane.showMessageDialog(null, "Adyacencias agregadas", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarGrafo() {
        int i = JOptionPane.showConfirmDialog(null, "Esta de acuerdo con cargar el grafo?", "Pregunta", JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            try {
                ed.cargarGrafo();
                limpiar();
                JOptionPane.showMessageDialog(null, "Grafo cargado", "OK", JOptionPane.OK_OPTION);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void dijkstra() throws Exception {
        if (ed.getGrafoEstacion() != null) {
            Integer posO = cbxOrigen.getSelectedIndex() + 1;
            Integer posD = cbxDestino.getSelectedIndex() + 1;
            HashMap<String, Object> mapa = ed.getGrafoEstacion().dijkstra(posD, posO);

            if (mapa.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No existe camino", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                LinkedList<Integer> caminos = (LinkedList<Integer>) mapa.get("camino");
                StringBuilder caminoStr = new StringBuilder();
                for (int i = 0; i < caminos.getSize(); i++) {
                    Integer v = caminos.get(i);
                    caminoStr.append(ed.getGrafoEstacion().obtenerEtiqueta(v)).append("  ----  ");
                }
                String posicionOrigen = cbxOrigen.getSelectedItem().toString();
                String posicionDestino = cbxDestino.getSelectedItem().toString();
                String mensaje = "Punto de Origen: " + posicionOrigen + "\n" + "Punto de Destino: " + posicionDestino + "\n\nEl camino corto es: \n" + caminoStr.toString();
                JOptionPane.showMessageDialog(null, mensaje, "Algoritmo Dijkstra", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Grafo nulo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void floyd() throws Exception {
        if (ed.getGrafoEstacion() != null) {
            Integer posO = cbxOrigen.getSelectedIndex() + 1;
            Integer posD = cbxDestino.getSelectedIndex() + 1;
            HashMap<String, Object> mapa = ed.getGrafoEstacion().Floyd(posD, posO);

            if (mapa.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No existe camino", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                LinkedList<Integer> caminos = (LinkedList<Integer>) mapa.get("camino");
                StringBuilder caminoStr = new StringBuilder();
                for (int i = 0; i < caminos.getSize(); i++) {
                    Integer v = caminos.get(i);
                    caminoStr.append(ed.getGrafoEstacion().obtenerEtiqueta(v)).append("  ----  ");
                }
                String posicionOrigen = cbxOrigen.getSelectedItem().toString();
                String posicionDestino = cbxDestino.getSelectedItem().toString();
                String mensaje = "Punto de Origen: " + posicionOrigen + "\n" + "Punto de Destino: " + posicionDestino + "\n\nEl camino corto es: \n" + caminoStr.toString();
                JOptionPane.showMessageDialog(null, mensaje, "Algoritmo Floyd", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Grafo nulo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void camino() throws VacioException, Exception {
        String metodo = cbxAlgoritmoCamino.getSelectedItem().toString();

        long startTime = System.nanoTime();
        if (metodo.equalsIgnoreCase("Dijkstra")) {
            dijkstra();
        } else if (metodo.equalsIgnoreCase("Floyd")) {
            floyd();
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("\nMetodo de Busqueda: " + metodo);
        System.out.println("Tiempo de ejecucion " + metodo + ": " + timeElapsed + " nanosegundos");
        System.out.println("Tiempo de ejecucion " + metodo + ": " + timeElapsed / 1e6 + " milisegundos");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cbxDestino = new org.edisoncor.gui.comboBox.ComboBoxRect();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxOrigen = new org.edisoncor.gui.comboBox.ComboBoxRect();
        btnAdyacencia = new org.edisoncor.gui.button.ButtonAero();
        btnConectarAleatoriamente = new org.edisoncor.gui.button.ButtonAero();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnGuardarGrafo = new org.edisoncor.gui.button.ButtonAero();
        btnCamino = new org.edisoncor.gui.button.ButtonAero();
        buttonAero1 = new org.edisoncor.gui.button.ButtonAero();
        jLabel3 = new javax.swing.JLabel();
        cbxAlgoritmoCamino = new javax.swing.JComboBox<>();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel4 = new javax.swing.JPanel();
        btnVerMapa = new org.edisoncor.gui.button.ButtonAero();
        btnVerGrafo = new org.edisoncor.gui.button.ButtonAero();
        labelHeader1 = new org.edisoncor.gui.label.LabelHeader();
        jPanel5 = new javax.swing.JPanel();
        btnBuscar = new org.edisoncor.gui.button.ButtonAero();
        jLabel4 = new javax.swing.JLabel();
        cbxAlgoritmoRecorrido = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mapa");

        jPanel2.setBackground(java.awt.Color.white);

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Construir grafo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        cbxDestino.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(cbxDestino, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Destino:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Origen:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

        cbxOrigen.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(cbxOrigen, gridBagConstraints);

        btnAdyacencia.setBackground(new java.awt.Color(204, 153, 0));
        btnAdyacencia.setText("Relacionar Adyacencia");
        btnAdyacencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdyacenciaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(btnAdyacencia, gridBagConstraints);

        btnConectarAleatoriamente.setBackground(new java.awt.Color(204, 153, 0));
        btnConectarAleatoriamente.setText("Relacionar Aleatoriamente");
        btnConectarAleatoriamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarAleatoriamenteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(btnConectarAleatoriamente, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));
        jPanel3.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = -100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        btnGuardarGrafo.setBackground(new java.awt.Color(153, 0, 0));
        btnGuardarGrafo.setText("Guardar");
        btnGuardarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarGrafoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 50);
        jPanel3.add(btnGuardarGrafo, gridBagConstraints);

        btnCamino.setBackground(new java.awt.Color(0, 102, 102));
        btnCamino.setText("Camino");
        btnCamino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaminoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnCamino, gridBagConstraints);

        buttonAero1.setBackground(new java.awt.Color(51, 153, 0));
        buttonAero1.setText("Cargar");
        buttonAero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(buttonAero1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Buscar camino minimo por :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel3, gridBagConstraints);

        cbxAlgoritmoCamino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DIJKSTRA", "FLOYD" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel3.add(cbxAlgoritmoCamino, gridBagConstraints);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 153));
        jPanel4.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnVerMapa.setBackground(new java.awt.Color(0, 153, 153));
        btnVerMapa.setText("Ver mapa");
        btnVerMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMapaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 300);
        jPanel4.add(btnVerMapa, gridBagConstraints);

        btnVerGrafo.setBackground(new java.awt.Color(0, 153, 153));
        btnVerGrafo.setText("Ver grafo");
        btnVerGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerGrafoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.insets = new java.awt.Insets(10, 300, 10, 10);
        jPanel4.add(btnVerGrafo, gridBagConstraints);

        labelHeader1.setText("Ubicaciones de las estaciones de descanso");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel4.add(labelHeader1, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(204, 255, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analizar conexion del grafo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel5.setLayout(new java.awt.GridBagLayout());

        btnBuscar.setBackground(new java.awt.Color(255, 102, 102));
        btnBuscar.setText("Verificar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(btnBuscar, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Buscar por:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel4, gridBagConstraints);

        cbxAlgoritmoRecorrido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ANCHURA", "PROFUNDIDAD" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel5.add(cbxAlgoritmoRecorrido, gridBagConstraints);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdyacenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdyacenciaActionPerformed
        adyacencia();
    }//GEN-LAST:event_btnAdyacenciaActionPerformed

    private void btnVerMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMapaActionPerformed
        try {
            String dir = Utilidades.getDirProject();
            UtilesEstacionVista.crearMapaEstacion(ed.getGrafoEstacion());
            if (Utilidades.getOS().equalsIgnoreCase("windos 11")) {
                Utilidades.abrirNavegadorPredeterminadorWindows(dir + File.separatorChar + "mapas" + File.separatorChar + "index.html");
            } else {
                Utilidades.abrirNavegadorPredeterminadorWindows(dir + File.separatorChar + "mapas" + File.separatorChar + "index.html");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVerMapaActionPerformed

    private void btnVerGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerGrafoActionPerformed
        try {
            String dir = Utilidades.getDirProject();
            DibujarGrafo dg = new DibujarGrafo();
            dg.crearArchivo(ed.getGrafoEstacion());
            Utilidades.abrirNavegadorPredeterminadorWindows(dir + File.separatorChar + "d3" + File.separatorChar + "grafo.html");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVerGrafoActionPerformed

    private void btnGuardarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarGrafoActionPerformed
        guardarGrafo();
    }//GEN-LAST:event_btnGuardarGrafoActionPerformed

    private void btnCaminoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaminoActionPerformed
        try {
            camino();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCaminoActionPerformed

    private void buttonAero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero1ActionPerformed
        cargarGrafo();
    }//GEN-LAST:event_buttonAero1ActionPerformed

    private void btnConectarAleatoriamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarAleatoriamenteActionPerformed
        adyacenciaAleatoria();
    }//GEN-LAST:event_btnConectarAleatoriamenteActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMapa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero btnAdyacencia;
    private org.edisoncor.gui.button.ButtonAero btnBuscar;
    private org.edisoncor.gui.button.ButtonAero btnCamino;
    private org.edisoncor.gui.button.ButtonAero btnConectarAleatoriamente;
    private org.edisoncor.gui.button.ButtonAero btnGuardarGrafo;
    private org.edisoncor.gui.button.ButtonAero btnVerGrafo;
    private org.edisoncor.gui.button.ButtonAero btnVerMapa;
    private org.edisoncor.gui.button.ButtonAero buttonAero1;
    private javax.swing.JComboBox<String> cbxAlgoritmoCamino;
    private javax.swing.JComboBox<String> cbxAlgoritmoRecorrido;
    private org.edisoncor.gui.comboBox.ComboBoxRect cbxDestino;
    private org.edisoncor.gui.comboBox.ComboBoxRect cbxOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.label.LabelHeader labelHeader1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tblTabla;
    // End of variables declaration//GEN-END:variables
}
