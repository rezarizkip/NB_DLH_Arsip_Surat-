/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helper.Koneksi;
import helper.TextFieldHintUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rezar
 */
public class Masuk extends javax.swing.JFrame {
    
    /**
     * Creates new form Daftar
     */
    public Masuk() {
        initComponents();
        initListener();
        textHint();
    }
    
    private void initListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doExit();
            }
        });
    }
    
    public void doExit() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Anda yakin ingin keluar?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public void textHint() {
        inEmail.setUI(new TextFieldHintUI("Masukkan email"));
    }
    
    public void focusGained(JPasswordField field, String params) {
        String pass = String.valueOf(field.getPassword());
        if (pass.equals(params)) {
            field.setText("");
            field.setForeground(new java.awt.Color(0,150,136));
        } else {
            field.setForeground(new java.awt.Color(0,150,136));
        }
    }
    public void focusLost(JPasswordField field, String params) {
        String pass = String.valueOf(field.getPassword());
        if (pass.equals("")) {
            field.setText(params);
            field.setForeground(new java.awt.Color(0,150,136));
        } else {
            field.setCaretPosition(0);
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

        panelBG = new javax.swing.JPanel();
        panelLogo = new javax.swing.JPanel();
        lblSI = new javax.swing.JLabel();
        Logo2 = new javax.swing.JLabel();
        lblDinas2 = new javax.swing.JLabel();
        panelBody = new javax.swing.JPanel();
        lblMasuk = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        inEmail = new javax.swing.JTextField();
        sp2 = new javax.swing.JSeparator();
        lblPass = new javax.swing.JLabel();
        inPassword = new javax.swing.JPasswordField();
        sp3 = new javax.swing.JSeparator();
        cbIngatSaya = new javax.swing.JCheckBox();
        btnMasuk = new javax.swing.JButton();
        linkDaftar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBG.setBackground(new java.awt.Color(0, 77, 64));
        panelBG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLogo.setBackground(new java.awt.Color(0, 150, 136));
        panelLogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSI.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblSI.setForeground(new java.awt.Color(255, 255, 255));
        lblSI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSI.setText("Sistem  Informasi  Administrasi  Surat");
        panelLogo.add(lblSI, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 117, 450, -1));

        Logo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        panelLogo.add(Logo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 182, 156, 186));

        lblDinas2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblDinas2.setForeground(new java.awt.Color(255, 255, 255));
        lblDinas2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDinas2.setText("<html><center>\n<b>Dinas Lingkungan Hidup</b><br>\nKabupaten Bogor\n</center></html>");
        lblDinas2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelLogo.add(lblDinas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 406, 450, 54));

        panelBG.add(panelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 550));

        panelBody.setBackground(new java.awt.Color(0, 77, 64));
        panelBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMasuk.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMasuk.setForeground(new java.awt.Color(204, 204, 204));
        lblMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMasuk.setText("MASUK");
        panelBody.add(lblMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 30));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(204, 204, 204));
        lblEmail.setText("Email");
        panelBody.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 65, 200, 20));

        inEmail.setBackground(new java.awt.Color(0, 77, 64));
        inEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inEmail.setForeground(new java.awt.Color(0, 150, 136));
        inEmail.setBorder(null);
        panelBody.add(inEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 85, 295, 25));
        panelBody.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 110, 300, 5));

        lblPass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPass.setForeground(new java.awt.Color(204, 204, 204));
        lblPass.setText("Password");
        panelBody.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 150, 200, 20));

        inPassword.setBackground(new java.awt.Color(0, 77, 64));
        inPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inPassword.setForeground(new java.awt.Color(0, 150, 136));
        inPassword.setText("password");
        inPassword.setBorder(null);
        inPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inPasswordFocusLost(evt);
            }
        });
        panelBody.add(inPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 295, 25));
        panelBody.add(sp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 195, 300, 5));

        cbIngatSaya.setBackground(new java.awt.Color(0, 77, 64));
        cbIngatSaya.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbIngatSaya.setForeground(new java.awt.Color(204, 204, 204));
        cbIngatSaya.setText("Ingat saya");
        cbIngatSaya.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbIngatSaya.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelBody.add(cbIngatSaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 235, -1, -1));

        btnMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnMasuk.png"))); // NOI18N
        btnMasuk.setBorder(null);
        btnMasuk.setBorderPainted(false);
        btnMasuk.setContentAreaFilled(false);
        btnMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasukActionPerformed(evt);
            }
        });
        panelBody.add(btnMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 262, 300, 38));

        linkDaftar.setForeground(new java.awt.Color(204, 204, 204));
        linkDaftar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linkDaftar.setText("<html>Belum memiliki akun? <b>Daftar.</b></html>");
        linkDaftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        linkDaftar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkDaftarMouseClicked(evt);
            }
        });
        panelBody.add(linkDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 335, 450, -1));

        panelBG.add(panelBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 450, 350));

        getContentPane().add(panelBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void linkDaftarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkDaftarMouseClicked
        Daftar df = new Daftar();
        df.setVisible(true);
        dispose();
    }//GEN-LAST:event_linkDaftarMouseClicked

    private void inPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inPasswordFocusGained
        focusGained(inPassword, "password");
    }//GEN-LAST:event_inPasswordFocusGained

    private void inPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inPasswordFocusLost
        focusLost(inPassword, "password");
    }//GEN-LAST:event_inPasswordFocusLost

    private void btnMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukActionPerformed
        PreparedStatement ps;
        ResultSet rs;
        
        String email = inEmail.getText().trim();
        String pass = String.valueOf(inPassword.getPassword());
        
        String query = "SELECT * FROM `pengguna` WHERE `email` = ? AND `pass` = ?";
        
        try {
            ps = Koneksi.getConnection().prepareStatement(query);
            
            ps.setString(1, email);
            ps.setString(2, pass);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String nama = rs.getString("nama");
                Dashboard db = new Dashboard(nama);
                db.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "No");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Masuk.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_btnMasukActionPerformed

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
            java.util.logging.Logger.getLogger(Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Masuk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo2;
    private javax.swing.JButton btnMasuk;
    private javax.swing.JCheckBox cbIngatSaya;
    private javax.swing.JTextField inEmail;
    private javax.swing.JPasswordField inPassword;
    private javax.swing.JLabel lblDinas2;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblMasuk;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblSI;
    private javax.swing.JLabel linkDaftar;
    private javax.swing.JPanel panelBG;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JSeparator sp2;
    private javax.swing.JSeparator sp3;
    // End of variables declaration//GEN-END:variables
}
