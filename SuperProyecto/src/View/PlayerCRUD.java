/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ModelUML.Player;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Esta clase se encarga de las operaciones CRUD de los Player
 *
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class PlayerCRUD extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;
    /**
     * El modo de la ventana, que determina qué función del CRUD se supone que
     * realice.
     */
    private static byte mode;

    private static ArrayList<Player> players;
    private static boolean[] errors = {false, false, false};

    /**
     * Creates new form PlayerCRUD
     *
     * @param parent Generado automáticamente
     * @param modal Generado automáticamente
     * @param mode Generado automáticamente
     */
    public PlayerCRUD(java.awt.Frame parent, boolean modal, byte mode) {
        super(parent, modal);
        initComponents();
        this.mode = mode;
        //<editor-fold defaultstate="collapsed" desc=" System look and feel setting code ">
        try {
            /* Set the System look and feel */
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
        mode();

    }

    /**
     * the return status of this dialog - one of RET_OK or RET_CANCEL
     *
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("Aceptar");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre Completo");

        jLabel2.setText("Nickname");

        jLabel3.setText("Salario");

        jLabel4.setText("Dirección de e-mail");

        jLabel5.setText("Equipo");

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("######.##"))));

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField2)
                                .addComponent(jTextField1)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField1)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Ejecuta la operacion CRUD que este seleccionada
     *
     * @param evt Generado automáticamente
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        boolean gud = true;
        for (boolean b : errors) {
            if (b) {
                gud = false;
            }
        }

        if (gud) {
            if (validar()) {
                switch (mode) {//cdru
                    case 0:
                        try {
                            if (jComboBox1.getSelectedIndex() == -1 || jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Ninguno")) {
                                ViewController.insertPlayer(jTextField1.getText(), jTextField2.getText(), BigDecimal.valueOf(Double.parseDouble(jFormattedTextField1.getText())), jTextField3.getText());
                            } else {
                                ViewController.insertPlayerT(jTextField1.getText(), jTextField2.getText(), BigDecimal.valueOf(Double.parseDouble(jFormattedTextField1.getText())), jTextField3.getText(), jComboBox1.getSelectedItem().toString());
                            }
                            JOptionPane.showMessageDialog(this, "Jugador insertado.");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(PlayerCRUD.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(PlayerCRUD.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(this, "Error no controlado:\n" + ex.toString());
                        }
                        mode();
                        clear();
                        break;
                    case 1:
                        try {

                            jComboBox2.setSelectedIndex(-1);
                            jComboBox1.setSelectedIndex(-1);
                            ViewController.deletePlayer(jTextField1.getText(), jTextField2.getText());
                            JOptionPane.showMessageDialog(this, "Jugador'" + jComboBox2.getSelectedItem() + "' eliminado.");
                            mode();
                            clear();

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(PlayerCRUD.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(PlayerCRUD.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(this, "Error no controlado:\n" + ex.toString());
                        }
                        break;
                    case 2:
                        dispose();
                        break;
                    case 3:
                        try {
                            if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("ninguno")) {
                                ViewController.updatePlayerNT(jTextField1.getText(), jTextField2.getText(), jComboBox2.getSelectedItem().toString(), BigDecimal.valueOf(Double.parseDouble(jFormattedTextField1.getText())), jTextField3.getText());

                            } else if (jComboBox1.getSelectedIndex() > -1) {
                                ViewController.updatePlayerT(jTextField1.getText(), jTextField2.getText(), jComboBox2.getSelectedItem().toString(), BigDecimal.valueOf(Double.parseDouble(jFormattedTextField1.getText())), jTextField3.getText(), jComboBox1.getSelectedItem().toString());

                            } else {
                                ViewController.updatePlayer(jTextField1.getText(), jTextField2.getText(), jComboBox2.getSelectedItem().toString(), BigDecimal.valueOf(Double.parseDouble(jFormattedTextField1.getText())), jTextField3.getText());

                            }
                            JOptionPane.showMessageDialog(this, "Jugador'" + jComboBox2.getSelectedItem() + "' actualizado.");
                            clear();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(PlayerCRUD.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(PlayerCRUD.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(this, "Error no controlado:\n" + ex.toString());
                        }
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Introduce datos por favor.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Uno de los campos es demasiado largo, o la dirección de correo es inválida.");
        }
    }//GEN-LAST:event_okButtonActionPerformed
    /**
     * Cierra la ventana
     *
     * @param evt Generado automáticamente
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     *
     * @param evt Generado automáticamente
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog
    /**
     * Rellena los campos con los datos del Player seleccionado
     *
     * @param evt Generado automáticamente
     */
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        players.stream().filter(p -> p.getNickName().equals((String) jComboBox2.getSelectedItem())).findFirst().ifPresent(c -> { //juro por todos los santos que esto no lo busqué en google
            jTextField1.setText(c.getFullName());
            jTextField2.setText(c.getNickName());
            jFormattedTextField1.setText(c.getSalary().toString());
            jTextField3.setText(c.getEmail());
            String s = c.getTeam().getTeamName();
            jComboBox1.setSelectedItem((s == null) ? "Ninguno" : s);
        });
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * Verifica la validez del campo.
     *
     * @param evt
     */
    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        //gracias, google
        if (!jTextField3.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            jTextField3.setBackground(Color.RED);
            errors[2] = true;
        } else {
            jTextField3.setBackground(Color.WHITE);
            errors[2] = false;
        }
    }//GEN-LAST:event_jTextField3FocusLost
    /**
     * Verifica la validez del campo.
     *
     * @param evt
     */
    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (jTextField1.getText().length() > 30) {
            jTextField1.setBackground(Color.RED);
            errors[0] = true;
        } else {
            jTextField1.setBackground(Color.WHITE);
            errors[0] = false;
        }    }//GEN-LAST:event_jTextField1CaretUpdate
    /**
     * Verifica la validez del campo.
     *
     * @param evt
     */
    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        if (jTextField2.getText().length() > 12) {
            jTextField2.setBackground(Color.RED);
            errors[1] = true;
        } else {
            jTextField2.setBackground(Color.WHITE);
            errors[1] = false;
        }    }//GEN-LAST:event_jTextField2CaretUpdate

    /**
     * Cierra la ventana
     *
     * @param retStatus Generado automáticamente
     */
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /**
     * Actualiza la funcionalidad de la ventana en funcion del modo con el que
     * se ha iniciado
     */
    private void mode() {
        jComboBox2.removeAllItems();
        try {
            if (mode != 0) {
                players = ViewController.selectDBPlayers();
                players.forEach(e -> jComboBox2.addItem(e.getNickName()));
                if (mode != 3) {
                    jFormattedTextField1.setEnabled(false);
                    jComboBox1.setEnabled(false);
                    jTextField1.setEnabled(false);
                    jTextField2.setEnabled(false);
                    jTextField3.setEnabled(false);
                }
                jComboBox2.setSelectedIndex(-1);
                clear();
            } else {
                jComboBox2.setVisible(false);
                pack();
            }
            jComboBox1.addItem("Ninguno");
            ViewController.selectDBTeams().forEach(e -> jComboBox1.addItem(e.getTeamName()));
            jComboBox1.setSelectedIndex(-1);
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * El main de la clase
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlayerCRUD dialog = new PlayerCRUD(new javax.swing.JFrame(), true, mode);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Valida que los campos obligatorios no esten vacios
     *
     * @return devuelve true si estan llenos, false si alguno esta vacio
     */
    private boolean validar() {
        if (mode == 2) {
            return true;
        } else if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jFormattedTextField1.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Limpia los campos
     */
    private void clear() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jFormattedTextField1.setText("");
        jComboBox2.setSelectedIndex(-1);
        jComboBox1.setSelectedIndex(-1);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
