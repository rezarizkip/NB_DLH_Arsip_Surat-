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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author rezar
 */
public class Daftar extends javax.swing.JFrame {
    
    /**
     * Creates new form Daftar
     */
    public Daftar() {
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
        inNama.setUI(new TextFieldHintUI("Masukkan nama lengkap"));
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
    
    public String tanggal() {
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String a = s.format(ys);
        return a;
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
        Logo1 = new javax.swing.JLabel();
        lblDinas1 = new javax.swing.JLabel();
        panelBody = new javax.swing.JPanel();
        lblDaftar = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        inNama = new javax.swing.JTextField();
        sp1 = new javax.swing.JSeparator();
        lblEmail = new javax.swing.JLabel();
        inEmail = new javax.swing.JTextField();
        sp2 = new javax.swing.JSeparator();
        lblPass = new javax.swing.JLabel();
        inPassword = new javax.swing.JPasswordField();
        sp3 = new javax.swing.JSeparator();
        cbTOS = new javax.swing.JCheckBox();
        btnDaftar = new javax.swing.JButton();
        linkMasuk = new javax.swing.JLabel();

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

        Logo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        panelLogo.add(Logo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 182, 156, 186));

        lblDinas1.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblDinas1.setForeground(new java.awt.Color(255, 255, 255));
        lblDinas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDinas1.setText("<html><center>\n<b>Dinas Lingkungan Hidup</b><br>\nKabupaten Bogor\n</center></html>");
        lblDinas1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelLogo.add(lblDinas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 406, 450, 54));

        panelBG.add(panelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 550));

        panelBody.setBackground(new java.awt.Color(0, 77, 64));
        panelBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDaftar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDaftar.setForeground(new java.awt.Color(204, 204, 204));
        lblDaftar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDaftar.setText("DAFTAR");
        panelBody.add(lblDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 30));

        lblNama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNama.setForeground(new java.awt.Color(204, 204, 204));
        lblNama.setText("Nama Lengkap");
        panelBody.add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 55, 200, 20));

        inNama.setBackground(new java.awt.Color(0, 77, 64));
        inNama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inNama.setForeground(new java.awt.Color(0, 150, 136));
        inNama.setBorder(null);
        panelBody.add(inNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 75, 295, 25));
        panelBody.add(sp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 100, 300, 5));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(204, 204, 204));
        lblEmail.setText("Email");
        panelBody.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 130, 200, 20));

        inEmail.setBackground(new java.awt.Color(0, 77, 64));
        inEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inEmail.setForeground(new java.awt.Color(0, 150, 136));
        inEmail.setBorder(null);
        panelBody.add(inEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 295, 25));
        panelBody.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 175, 300, 5));

        lblPass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPass.setForeground(new java.awt.Color(204, 204, 204));
        lblPass.setText("Password");
        panelBody.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 205, 200, 20));

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
        panelBody.add(inPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 225, 295, 25));
        panelBody.add(sp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 250, 300, 5));

        cbTOS.setBackground(new java.awt.Color(0, 77, 64));
        cbTOS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbTOS.setForeground(new java.awt.Color(204, 204, 204));
        cbTOS.setText("<html> Saya setuju untuk mematuhi Kesepakatan Pengguna<br>dan Pernyataan Privasi. </html>");
        cbTOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbTOS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panelBody.add(cbTOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 280, 300, -1));

        btnDaftar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnDaftar.png"))); // NOI18N
        btnDaftar.setBorder(null);
        btnDaftar.setBorderPainted(false);
        btnDaftar.setContentAreaFilled(false);
        btnDaftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarActionPerformed(evt);
            }
        });
        panelBody.add(btnDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 323, 300, 38));

        linkMasuk.setForeground(new java.awt.Color(204, 204, 204));
        linkMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linkMasuk.setText("<html>Sudah memiliki akun? <b>Masuk.</b></html>");
        linkMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        linkMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkMasukMouseClicked(evt);
            }
        });
        panelBody.add(linkMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 375, 450, 15));

        panelBG.add(panelBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 450, 390));

        getContentPane().add(panelBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void linkMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkMasukMouseClicked
        Masuk df = new Masuk();
        df.setVisible(true);
        dispose();
    }//GEN-LAST:event_linkMasukMouseClicked

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarActionPerformed
        String nama = inNama.getText().trim();
        String email = inEmail.getText().trim();
        String pass = String.valueOf(inPassword.getPassword());
        String created_ = tanggal();
        if (nama.equals("") || email.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Harap lengkapi data pendaftaran!");
        } else {
            if (!cbTOS.isSelected()) {
                JOptionPane.showMessageDialog(rootPane, "Setujui persyaratan pengguna!");
            } else {
                PreparedStatement ps;
                String query = "INSERT INTO `pengguna`(`nama`, `email`, `pass`, `created_at`) VALUES (?,?,?,?)";

                try {
                    ps = Koneksi.getConnection().prepareStatement(query);

                    ps.setString(1, nama);
                    ps.setString(2, email);
                    ps.setString(3, pass);
                    ps.setString(4, created_);

                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Pendaftaran berhasil ...");
                        int confirm = JOptionPane.showConfirmDialog(this,"Ingin masuk sekarang?","Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (confirm == JOptionPane.YES_OPTION) {
                            Masuk df = new Masuk();
                            df.setVisible(true);
                            dispose();
                        } else {
                            inNama.setText("");
                            inEmail.setText("");
                            inPassword.setText("");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Daftar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }//GEN-LAST:event_btnDaftarActionPerformed

    private void inPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inPasswordFocusGained
        focusGained(inPassword, "password");
    }//GEN-LAST:event_inPasswordFocusGained

    private void inPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inPasswordFocusLost
        focusLost(inPassword, "password");
    }//GEN-LAST:event_inPasswordFocusLost

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
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Daftar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo1;
    private javax.swing.JButton btnDaftar;
    private javax.swing.JCheckBox cbTOS;
    private javax.swing.JTextField inEmail;
    private javax.swing.JTextField inNama;
    private javax.swing.JPasswordField inPassword;
    private javax.swing.JLabel lblDaftar;
    private javax.swing.JLabel lblDinas1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblSI;
    private javax.swing.JLabel linkMasuk;
    private javax.swing.JPanel panelBG;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JSeparator sp1;
    private javax.swing.JSeparator sp2;
    private javax.swing.JSeparator sp3;
    // End of variables declaration//GEN-END:variables
}
