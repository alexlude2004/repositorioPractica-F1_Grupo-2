package vista;

import controlador.EstacionController;
import controlador.TDA.Listas.LinkedList;
import controlador.util.Utilidades;
import java.awt.Dimension;
import java.io.File;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.grafos.tablas.ModeloTablaEstacion;

/**
 *
 * @author alexg
 */
public class FrmEstacion extends javax.swing.JFrame {

    private File foto1, foto2, foto3, foto4;
    private EstacionController ec = new EstacionController();
    private ModeloTablaEstacion mte = new ModeloTablaEstacion();

    /**
     * Creates new form FrmEstacion
     */
    public FrmEstacion() {
        initComponents();
        setLocationRelativeTo(null);
        buttonTransluceIcon1Agregar.setIcon(new ImageIcon("icono/agregar.png"));
        buttonTransluceIcon2Agregar.setIcon(new ImageIcon("icono/agregar.png"));
        buttonTransluceIconEliminar1.setIcon(new ImageIcon("icono/borrar.png"));
        buttonTransluceIconEliminar2.setIcon(new ImageIcon("icono/borrar.png"));
        panelImage1.setIcon(new ImageIcon("icono/fondo_naturaleza.jpg"));
        jPanel2.setPreferredSize(new Dimension(920, 210));
        limpiar();
    }

    private void cargarTabla() {
        try {
            mte.setLista(ec.getEstaciones());
            tblTabla.setModel(mte);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void fileChooserFoto1() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("multimedia", "jpg", "png", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int i = fileChooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto1 = fileChooser.getSelectedFile();
            txtFoto1.setText(foto1.getName());
        }
    }

    private void fileChooserFoto2() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("multimedia", "jpg", "png", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int i = fileChooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto2 = fileChooser.getSelectedFile();
            txtFoto2.setText(foto2.getName());
        }
    }

    private void fileChooserFoto3() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("multimedia", "jpg", "png", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int i = fileChooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto3 = fileChooser.getSelectedFile();
            txtFoto3.setText(foto3.getName());
        }
    }

    private void fileChooserFoto4() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("multimedia", "jpg", "png", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int i = fileChooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto4 = fileChooser.getSelectedFile();
            txtFoto4.setText(foto4.getName());
        }
    }

    private Boolean validar() {
        return !txtNombre.getText().trim().isEmpty()
                && !txtLatitud.getText().trim().isEmpty()
                && !txtLongitud.getText().trim().isEmpty()
                && !txtFoto1.getText().trim().isEmpty()
                && !txtFoto2.getText().trim().isEmpty()
                && foto1 != null
                && foto2 != null;
    }

    private void limpiar() {
        foto1 = null;
        foto2 = null;
        foto3 = null;
        foto4 = null;
        txtFoto1.setText("");
        txtFoto2.setText("");
        txtFoto3.setText("");
        txtFoto4.setText("");
        txtNombre.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
        jblFoto3.setVisible(false);
        txtFoto3.setVisible(false);
        buttonRoundFoto3.setVisible(false);
        jblFoto4.setVisible(false);
        txtFoto4.setVisible(false);
        buttonRoundFoto4.setVisible(false);
        buttonTransluceIcon1Agregar.setVisible(true);
        buttonTransluceIcon2Agregar.setVisible(false);
        buttonTransluceIconEliminar1.setVisible(false);
        buttonTransluceIconEliminar2.setVisible(false);
        jPanel2.setPreferredSize(new Dimension(920, 210));
        ec.setEstacion(null);
        ec.setEstaciones(new LinkedList<>());
        cargarTabla();
    }

    private void guardar() {
        if (validar()) {
            try {
                ec.getEstacion().setLatitud(Double.parseDouble(txtLatitud.getText()));
                ec.getEstacion().setLongitud(Double.parseDouble(txtLongitud.getText()));
                ec.getEstacion().setNombre(txtNombre.getText().trim());

                String uuidF1 = UUID.randomUUID().toString();
                ec.getEstacion().setFoto1(uuidF1 + "." + Utilidades.extension(foto1.getName()));
                Utilidades.copiarArchivo(foto1, new File("multimedia/" + uuidF1 + "." + Utilidades.extension(foto1.getName())));

                String uuidF2 = UUID.randomUUID().toString();
                ec.getEstacion().setFoto2(uuidF2 + "." + Utilidades.extension(foto2.getName()));
                Utilidades.copiarArchivo(foto2, new File("multimedia/" + uuidF2 + "." + Utilidades.extension(foto2.getName())));

                if (!txtFoto3.getText().isEmpty()) {
                    String uuidF3 = UUID.randomUUID().toString();
                    ec.getEstacion().setFoto3(uuidF3 + "." + Utilidades.extension(foto3.getName()));
                    Utilidades.copiarArchivo(foto3, new File("multimedia/" + uuidF3 + "." + Utilidades.extension(foto3.getName())));
                } else {
                    ec.getEstacion().setFoto3(null);
                }

                if (!txtFoto4.getText().isEmpty()) {
                    String uuidF4 = UUID.randomUUID().toString();
                    ec.getEstacion().setFoto4(uuidF4 + "." + Utilidades.extension(foto4.getName()));
                    Utilidades.copiarArchivo(foto4, new File("multimedia/" + uuidF4 + "." + Utilidades.extension(foto4.getName())));
                } else {
                    ec.getEstacion().setFoto4(null);
                }

                if (ec.getEstacion().getId() == null) {
                    if (ec.save()) {
                        limpiar();
                        JOptionPane.showMessageDialog(null,
                                "Se ha guardado la estacion correctamente", "Ok",
                                JOptionPane.INFORMATION_MESSAGE);
                        ec.setEstacion(null);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se ha podido guardar correctamente",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (ec.update()) {
                        if (ec.update()) {
                            if (foto1 != null && !txtFoto1.getText().equals(ec.getEstacion().getFoto1())) {
                                uuidF1 = UUID.randomUUID().toString();
                                String nombreFoto1 = foto1.getName();
                                if (nombreFoto1.startsWith("multimedia/")) {
                                    nombreFoto1 = nombreFoto1.substring(11);
                                }
                                Utilidades.copiarArchivo(foto1, new File("multimedia/" + uuidF1 + "." + Utilidades.extension(nombreFoto1)));
                                ec.getEstacion().setFoto1(uuidF1 + "." + Utilidades.extension(nombreFoto1));
                            } else if (foto1 != null) {
                                uuidF1 = UUID.randomUUID().toString();
                                Utilidades.copiarArchivo(foto1, new File("multimedia/" + uuidF1 + "." + Utilidades.extension(foto1.getName())));
                                ec.getEstacion().setFoto1(uuidF1 + "." + Utilidades.extension(foto1.getName()));
                            } else {
                                ec.getEstacion().setFoto1(null);
                            }

                            if (foto2 != null && !txtFoto2.getText().equals(ec.getEstacion().getFoto2())) {
                                uuidF2 = UUID.randomUUID().toString();
                                String nombreFoto2 = foto2.getName();
                                if (nombreFoto2.startsWith("multimedia/")) {
                                    nombreFoto2 = nombreFoto2.substring(11);
                                }
                                Utilidades.copiarArchivo(foto2, new File("multimedia/" + uuidF2 + "." + Utilidades.extension(nombreFoto2)));
                                ec.getEstacion().setFoto2(uuidF2 + "." + Utilidades.extension(nombreFoto2));
                            } else if (foto2 != null) {
                                uuidF2 = UUID.randomUUID().toString();
                                Utilidades.copiarArchivo(foto2, new File("multimedia/" + uuidF2 + "." + Utilidades.extension(foto2.getName())));
                                ec.getEstacion().setFoto2(uuidF2 + "." + Utilidades.extension(foto2.getName()));
                            } else {
                                ec.getEstacion().setFoto2(null);
                            }

                            if (foto3 != null && !txtFoto3.getText().equals(ec.getEstacion().getFoto3())) {
                                String uuidF3 = UUID.randomUUID().toString();
                                String nombreFoto3 = foto3.getName();
                                if (nombreFoto3.startsWith("multimedia/")) {
                                    nombreFoto3 = nombreFoto3.substring(11);
                                }
                                Utilidades.copiarArchivo(foto3, new File("multimedia/" + uuidF3 + "." + Utilidades.extension(nombreFoto3)));
                                ec.getEstacion().setFoto3(uuidF3 + "." + Utilidades.extension(nombreFoto3));
                            } else if (foto3 != null) {
                                String uuidF3 = UUID.randomUUID().toString();
                                Utilidades.copiarArchivo(foto3, new File("multimedia/" + uuidF3 + "." + Utilidades.extension(foto3.getName())));
                                ec.getEstacion().setFoto3(uuidF3 + "." + Utilidades.extension(foto3.getName()));
                            } else {
                                ec.getEstacion().setFoto3(null);
                            }

                            if (foto4 != null && !txtFoto4.getText().equals(ec.getEstacion().getFoto4())) {
                                String uuidF4 = UUID.randomUUID().toString();
                                String nombreFoto4 = foto4.getName();
                                if (nombreFoto4.startsWith("multimedia/")) {
                                    nombreFoto4 = nombreFoto4.substring(11);
                                }
                                Utilidades.copiarArchivo(foto4, new File("multimedia/" + uuidF4 + "." + Utilidades.extension(nombreFoto4)));
                                ec.getEstacion().setFoto4(uuidF4 + "." + Utilidades.extension(nombreFoto4));
                            } else if (foto4 != null) {
                                String uuidF4 = UUID.randomUUID().toString();
                                Utilidades.copiarArchivo(foto4, new File("multimedia/" + uuidF4 + "." + Utilidades.extension(foto4.getName())));
                                ec.getEstacion().setFoto4(uuidF4 + "." + Utilidades.extension(foto4.getName()));
                            } else {
                                ec.getEstacion().setFoto4(null);
                            }
                        }
                        limpiar();
                        JOptionPane.showMessageDialog(null,
                                "Se ha editado correctamente", "Ok",
                                JOptionPane.INFORMATION_MESSAGE);
                        ec.setEstacion(null);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se ha podido editar correctamente",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Llene todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarVista() {
        int fila = tblTabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ec.setEstacion(mte.getLista().get(ec.buscarIndex(fila) + 1));
                txtNombre.setText(ec.getEstacion().getNombre());
                txtLatitud.setText(ec.getEstacion().getLatitud().toString());
                txtLongitud.setText(ec.getEstacion().getLongitud().toString());

                foto1 = new File("multimedia/" + ec.getEstacion().getFoto1());
                txtFoto1.setText(foto1.getName());

                foto2 = new File("multimedia/" + ec.getEstacion().getFoto2());
                txtFoto2.setText(foto2.getName());

                if (ec.getEstacion().getFoto3() != null) {
                    foto3 = new File("multimedia/" + ec.getEstacion().getFoto3());
                    txtFoto3.setText(foto3.getName());
                } else {
                    txtFoto3.setText(null);
                }

                if (ec.getEstacion().getFoto4() != null) {
                    foto4 = new File("multimedia/" + ec.getEstacion().getFoto4());
                    txtFoto4.setText(foto4.getName());
                } else {
                    txtFoto4.setText(null);
                }

                if (ec.getEstacion().getFoto3() == null && ec.getEstacion().getFoto4() == null) {
                    jPanel2.setPreferredSize(new Dimension(920, 210));
                    buttonTransluceIcon1Agregar.setVisible(true);
                    buttonTransluceIcon2Agregar.setVisible(false);
                    buttonTransluceIconEliminar1.setVisible(false);
                    buttonTransluceIconEliminar2.setVisible(false);
                } else if (ec.getEstacion().getFoto3() != null && ec.getEstacion().getFoto4() == null) {
                    txtFoto3.setVisible(true);
                    jblFoto3.setVisible(true);
                    buttonRoundFoto3.setVisible(true);
                    buttonTransluceIcon1Agregar.setVisible(false);
                    buttonTransluceIcon2Agregar.setVisible(true);
                    buttonTransluceIconEliminar2.setVisible(true);
                    buttonTransluceIconEliminar1.setVisible(false);
                    jPanel2.setPreferredSize(new Dimension(920, 280));
                } else {
                    txtFoto3.setVisible(true);
                    jblFoto3.setVisible(true);
                    buttonRoundFoto3.setVisible(true);
                    buttonRoundFoto4.setVisible(true);
                    txtFoto4.setVisible(true);
                    jblFoto4.setVisible(true);
                    buttonTransluceIcon1Agregar.setVisible(false);
                    buttonTransluceIcon2Agregar.setVisible(false);
                    buttonTransluceIconEliminar2.setVisible(false);
                    buttonTransluceIconEliminar1.setVisible(true);
                    jPanel2.setPreferredSize(new Dimension(920, 358));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new org.edisoncor.gui.textField.TextField();
        Latitud = new javax.swing.JLabel();
        Longitud = new javax.swing.JLabel();
        txtLatitud = new org.edisoncor.gui.textField.TextField();
        txtLongitud = new org.edisoncor.gui.textField.TextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jblFoto3 = new javax.swing.JLabel();
        jblFoto4 = new javax.swing.JLabel();
        txtFoto1 = new org.edisoncor.gui.textField.TextField();
        txtFoto2 = new org.edisoncor.gui.textField.TextField();
        txtFoto3 = new org.edisoncor.gui.textField.TextField();
        txtFoto4 = new org.edisoncor.gui.textField.TextField();
        buttonRoundFoto1 = new org.edisoncor.gui.button.ButtonRound();
        buttonRoundFoto2 = new org.edisoncor.gui.button.ButtonRound();
        buttonRoundFoto3 = new org.edisoncor.gui.button.ButtonRound();
        buttonRoundFoto4 = new org.edisoncor.gui.button.ButtonRound();
        buttonTransluceIcon1Agregar = new org.edisoncor.gui.button.ButtonTransluceIcon();
        buttonTransluceIcon2Agregar = new org.edisoncor.gui.button.ButtonTransluceIcon();
        buttonTransluceIconEliminar1 = new org.edisoncor.gui.button.ButtonTransluceIcon();
        buttonTransluceIconEliminar2 = new org.edisoncor.gui.button.ButtonTransluceIcon();
        buttonColoredActionGuardar = new org.edisoncor.gui.button.ButtonColoredAction();
        buttonColoredActionCancelar = new org.edisoncor.gui.button.ButtonColoredAction();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        buttonColoredActionSeleccionar = new org.edisoncor.gui.button.ButtonColoredAction();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estaciones");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(932, 620));

        jPanel2.setBackground(new java.awt.Color(255, 204, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Estacion de Descanso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 24, -1, -1));

        txtNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 24, 222, -1));

        Latitud.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Latitud.setText("Latitud:");
        jPanel2.add(Latitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 24, -1, -1));

        Longitud.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Longitud.setText("Longitud:");
        jPanel2.add(Longitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 64, -1, -1));

        txtLatitud.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel2.add(txtLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 24, 222, -1));

        txtLongitud.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel2.add(txtLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 64, 222, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Foto 1:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 64, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Foto 2:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 132, -1, -1));

        jblFoto3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblFoto3.setText("Foto 3:");
        jPanel2.add(jblFoto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 217, -1, -1));

        jblFoto4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblFoto4.setText("Foto 4:");
        jPanel2.add(jblFoto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 302, -1, -1));

        txtFoto1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFoto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto1MouseClicked(evt);
            }
        });
        jPanel2.add(txtFoto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 64, 222, -1));

        txtFoto2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFoto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto2MouseClicked(evt);
            }
        });
        jPanel2.add(txtFoto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 130, 222, -1));

        txtFoto3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFoto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto3MouseClicked(evt);
            }
        });
        jPanel2.add(txtFoto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 215, 222, -1));

        txtFoto4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFoto4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFoto4MouseClicked(evt);
            }
        });
        jPanel2.add(txtFoto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 300, 222, -1));

        buttonRoundFoto1.setBackground(new java.awt.Color(255, 102, 102));
        buttonRoundFoto1.setText("cargar");
        buttonRoundFoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRoundFoto1ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonRoundFoto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 64, -1, -1));

        buttonRoundFoto2.setBackground(new java.awt.Color(255, 102, 102));
        buttonRoundFoto2.setText("cargar");
        buttonRoundFoto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRoundFoto2ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonRoundFoto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 128, -1, -1));

        buttonRoundFoto3.setBackground(new java.awt.Color(0, 204, 204));
        buttonRoundFoto3.setText("cargar");
        buttonRoundFoto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRoundFoto3ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonRoundFoto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 213, -1, -1));

        buttonRoundFoto4.setBackground(new java.awt.Color(0, 204, 204));
        buttonRoundFoto4.setText("cargar");
        buttonRoundFoto4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRoundFoto4ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonRoundFoto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 298, -1, -1));

        buttonTransluceIcon1Agregar.setBackground(new java.awt.Color(255, 204, 51));
        buttonTransluceIcon1Agregar.setText("buttonTransluceIcon1");
        buttonTransluceIcon1Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransluceIcon1AgregarActionPerformed(evt);
            }
        });
        jPanel2.add(buttonTransluceIcon1Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 108, 67, 65));

        buttonTransluceIcon2Agregar.setBackground(new java.awt.Color(255, 204, 51));
        buttonTransluceIcon2Agregar.setText("buttonTransluceIcon1");
        buttonTransluceIcon2Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransluceIcon2AgregarActionPerformed(evt);
            }
        });
        jPanel2.add(buttonTransluceIcon2Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 67, 65));

        buttonTransluceIconEliminar1.setBackground(new java.awt.Color(255, 204, 51));
        buttonTransluceIconEliminar1.setText("buttonTransluceIcon1");
        buttonTransluceIconEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransluceIconEliminar1ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonTransluceIconEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 67, 65));

        buttonTransluceIconEliminar2.setBackground(new java.awt.Color(255, 204, 51));
        buttonTransluceIconEliminar2.setText("buttonTransluceIcon1");
        buttonTransluceIconEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransluceIconEliminar2ActionPerformed(evt);
            }
        });
        jPanel2.add(buttonTransluceIconEliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 67, 65));

        buttonColoredActionGuardar.setBackground(new java.awt.Color(0, 153, 153));
        buttonColoredActionGuardar.setText("Guardar");
        buttonColoredActionGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColoredActionGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(buttonColoredActionGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, -1, -1));

        buttonColoredActionCancelar.setBackground(new java.awt.Color(0, 153, 153));
        buttonColoredActionCancelar.setText("Cancelar");
        buttonColoredActionCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColoredActionCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(buttonColoredActionCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, -1, -1));

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de estaciones de descanso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
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
        jScrollPane2.setViewportView(tblTabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = -250;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jScrollPane2, gridBagConstraints);

        buttonColoredActionSeleccionar.setBackground(new java.awt.Color(0, 153, 153));
        buttonColoredActionSeleccionar.setText("Seleccionar");
        buttonColoredActionSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColoredActionSeleccionarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(buttonColoredActionSeleccionar, gridBagConstraints);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFoto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto1MouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(null, true, foto1).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto1MouseClicked

    private void txtFoto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto2MouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(null, true, foto2).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto2MouseClicked

    private void txtFoto3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto3MouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(null, true, foto3).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto3MouseClicked

    private void txtFoto4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFoto4MouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(null, true, foto4).setVisible(true);
        }
    }//GEN-LAST:event_txtFoto4MouseClicked

    private void buttonRoundFoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRoundFoto1ActionPerformed
        fileChooserFoto1();
    }//GEN-LAST:event_buttonRoundFoto1ActionPerformed

    private void buttonRoundFoto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRoundFoto2ActionPerformed
        fileChooserFoto2();
    }//GEN-LAST:event_buttonRoundFoto2ActionPerformed

    private void buttonRoundFoto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRoundFoto3ActionPerformed
        fileChooserFoto3();
    }//GEN-LAST:event_buttonRoundFoto3ActionPerformed

    private void buttonRoundFoto4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRoundFoto4ActionPerformed
        fileChooserFoto4();
    }//GEN-LAST:event_buttonRoundFoto4ActionPerformed

    private void buttonTransluceIcon1AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransluceIcon1AgregarActionPerformed
        jblFoto3.setVisible(true);
        txtFoto3.setVisible(true);
        buttonRoundFoto3.setVisible(true);
        buttonTransluceIcon1Agregar.setVisible(false);
        buttonTransluceIcon2Agregar.setVisible(true);
        buttonTransluceIconEliminar2.setVisible(true);
        jPanel2.setPreferredSize(new Dimension(920, 280));
    }//GEN-LAST:event_buttonTransluceIcon1AgregarActionPerformed

    private void buttonTransluceIcon2AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransluceIcon2AgregarActionPerformed
        jblFoto4.setVisible(true);
        txtFoto4.setVisible(true);
        buttonRoundFoto4.setVisible(true);
        buttonTransluceIcon2Agregar.setVisible(false);
        buttonTransluceIconEliminar1.setVisible(true);
        buttonTransluceIconEliminar2.setVisible(false);
        jPanel2.setPreferredSize(new Dimension(920, 358));
    }//GEN-LAST:event_buttonTransluceIcon2AgregarActionPerformed

    private void buttonTransluceIconEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransluceIconEliminar1ActionPerformed
        jblFoto4.setVisible(false);
        txtFoto4.setVisible(false);
        txtFoto4.setText("");
        buttonRoundFoto4.setVisible(false);
        buttonTransluceIcon1Agregar.setVisible(false);
        buttonTransluceIcon2Agregar.setVisible(true);
        buttonTransluceIconEliminar1.setVisible(false);
        buttonTransluceIconEliminar2.setVisible(true);
        jPanel2.setPreferredSize(new Dimension(920, 280));
    }//GEN-LAST:event_buttonTransluceIconEliminar1ActionPerformed

    private void buttonTransluceIconEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransluceIconEliminar2ActionPerformed
        jblFoto3.setVisible(false);
        txtFoto3.setVisible(false);
        txtFoto3.setText("");
        buttonRoundFoto3.setVisible(false);
        buttonTransluceIcon1Agregar.setVisible(true);
        buttonTransluceIcon2Agregar.setVisible(false);
        buttonTransluceIconEliminar1.setVisible(false);
        buttonTransluceIconEliminar2.setVisible(false);
        jPanel2.setPreferredSize(new Dimension(920, 210));
    }//GEN-LAST:event_buttonTransluceIconEliminar2ActionPerformed

    private void buttonColoredActionGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_buttonColoredActionGuardarActionPerformed

    private void buttonColoredActionSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColoredActionSeleccionarActionPerformed
        cargarVista();
    }//GEN-LAST:event_buttonColoredActionSeleccionarActionPerformed

    private void buttonColoredActionCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColoredActionCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_buttonColoredActionCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Latitud;
    private javax.swing.JLabel Longitud;
    private org.edisoncor.gui.button.ButtonColoredAction buttonColoredActionCancelar;
    private org.edisoncor.gui.button.ButtonColoredAction buttonColoredActionGuardar;
    private org.edisoncor.gui.button.ButtonColoredAction buttonColoredActionSeleccionar;
    private org.edisoncor.gui.button.ButtonRound buttonRoundFoto1;
    private org.edisoncor.gui.button.ButtonRound buttonRoundFoto2;
    private org.edisoncor.gui.button.ButtonRound buttonRoundFoto3;
    private org.edisoncor.gui.button.ButtonRound buttonRoundFoto4;
    private org.edisoncor.gui.button.ButtonTransluceIcon buttonTransluceIcon1Agregar;
    private org.edisoncor.gui.button.ButtonTransluceIcon buttonTransluceIcon2Agregar;
    private org.edisoncor.gui.button.ButtonTransluceIcon buttonTransluceIconEliminar1;
    private org.edisoncor.gui.button.ButtonTransluceIcon buttonTransluceIconEliminar2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jblFoto3;
    private javax.swing.JLabel jblFoto4;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tblTabla;
    private org.edisoncor.gui.textField.TextField txtFoto1;
    private org.edisoncor.gui.textField.TextField txtFoto2;
    private org.edisoncor.gui.textField.TextField txtFoto3;
    private org.edisoncor.gui.textField.TextField txtFoto4;
    private org.edisoncor.gui.textField.TextField txtLatitud;
    private org.edisoncor.gui.textField.TextField txtLongitud;
    private org.edisoncor.gui.textField.TextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
