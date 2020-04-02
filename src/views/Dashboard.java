package views;

import helper.Koneksi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author rezarizkip
 */
public class Dashboard extends javax.swing.JFrame implements MouseListener {
    
    private static final long serialVersionUID = 1L;
    private final String paramNama, welcome;
    int mouseX, mouseY, nPengguna, nMasuk, nKeluar;
    Color ctPengguna, ctMasuk, ctKeluar;

    /**
     * Creates new form Daftar
     * @param nama
     */
    public Dashboard(String nama) {
        initComponents();
        initListener();
        this.paramNama = nama;
        welcome = "<html>Selamat datang <b>" + nama + "</b></html>";
        lblWelcome.setText(welcome);
        lbInstansi.setText("Dinas Lingkungan Hidup");
        setPaddingButton();
        setTitle("Beranda");
        setRandomTotal();
        setChart();
    }
    
    private void initListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doExit();
            }
        });
        
        ctPengguna = ppPengguna.getBackground();
        ctMasuk = ppSMasuk.getBackground();
        ctKeluar = ppSKeluar.getBackground();
        
        btnDashboard.addMouseListener(this);
        btnSuratMasuk.addMouseListener(this);
        btnSuratKeluar.addMouseListener(this);
        btnRekap.addMouseListener(this);
        btnInstansi.addMouseListener(this);
        btnKeluar.addMouseListener(this);
        
        ppPengguna.addMouseListener(this);
        ppSMasuk.addMouseListener(this);
        ppSKeluar.addMouseListener(this);
        
        nPengguna = nomorRandom(1, 99);
        nMasuk = nomorRandom(1, 99);
        nKeluar = nomorRandom(1, 99);
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
    
    public int nomorRandom(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        
        return result;
    }
    
    public void setRandomTotal() {
        totalPengguna.setText(String.valueOf(nomorRandom(100, 1000)));
        totalSuratMasuk.setText(String.valueOf(nomorRandom(100, 1000)));
        totalSuratKeluar.setText(String.valueOf(nomorRandom(100, 1000)));
    }
    
    public String today(Date ys, SimpleDateFormat s) {
        ys = new Date();
        s = new SimpleDateFormat("yyyy-MM-dd");
        String a = s.format(ys);
        return a;
    }
    
    public void setChart() {
        
        String judul = "Laporan hari ini";
        
        PreparedStatement ps;
        ResultSet rs;
        
//        String today = tanggal();
        
        String query = "SELECT count(*) FROM `pengguna` WHERE `created_at` BETWEEN '2020-03-28 00:00:00' and '2020-03-28 23:59:59'";
        
        try {
            ps = Koneksi.getConnection().prepareStatement(query);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int hasil = rs.getInt(1);
//                int total = Integer.valueOf(hasil);
                DefaultPieDataset dataSet = new DefaultPieDataset();

                dataSet.setValue("Pengguna baru hari ini: " + hasil, hasil);
                dataSet.setValue("Masuk hari ini : " + nMasuk, nMasuk);
                dataSet.setValue("Keluar hari ini : " + nKeluar, nKeluar);

                JFreeChart dataChart = ChartFactory.createPieChart(judul, dataSet, true, false, false );

                dataChart.getPlot().setBackgroundPaint(SystemColor.inactiveCaption); // change background color

                PiePlot cPlot = (PiePlot)dataChart.getPlot();
                cPlot.setSectionPaint(0, new java.awt.Color(244,143,177));
                cPlot.setSectionPaint(1, new java.awt.Color(255,204,128));
                cPlot.setSectionPaint(2, new java.awt.Color(188,170,164));

                panelLaporanHarian.setLayout(new java.awt.BorderLayout());
                ChartPanel cp = new ChartPanel(dataChart);
                panelLaporanHarian.add(cp, BorderLayout.CENTER);
                panelLaporanHarian.validate();
            } else {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Masuk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // BAR CHART
        /*
            DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
         
            dataSet.setValue(nomorRandom(1, 99), "Pengguna", "Pengguna baru");
            dataSet.setValue(nomorRandom(1, 99), "Surat Masuk", "Surat Masuk");
            dataSet.setValue(nomorRandom(1, 99), "Surat Keluar", "Surat Keluar");

            JFreeChart dataChart = ChartFactory.createBarChart(judul, "", "", dataSet);
        
            dataChart.getPlot().setBackgroundPaint(SystemColor.inactiveCaption); // change background color

            BarRenderer renderer = (BarRenderer)dataChart.getCategoryPlot().getRenderer();
            renderer.setSeriesPaint(0, new java.awt.Color(244,143,177));
            renderer.setSeriesPaint(1, new java.awt.Color(255,204,128));
            renderer.setSeriesPaint(2, new java.awt.Color(188,170,164));
        */
        
        // PIE CHART
        
//            DefaultPieDataset dataSet = new DefaultPieDataset();
//
//            dataSet.setValue("Pengguna baru hari ini: " + 0, 0);
//            dataSet.setValue("Masuk hari ini : " + nMasuk, nMasuk);
//            dataSet.setValue("Keluar hari ini : " + nKeluar, nKeluar);
//
//            JFreeChart dataChart = ChartFactory.createPieChart(judul, dataSet, true, false, false );
//            
//            dataChart.getPlot().setBackgroundPaint(SystemColor.inactiveCaption); // change background color
//            
//            PiePlot cPlot = (PiePlot)dataChart.getPlot();
//            cPlot.setSectionPaint(0, new java.awt.Color(244,143,177));
//            cPlot.setSectionPaint(1, new java.awt.Color(255,204,128));
//            cPlot.setSectionPaint(2, new java.awt.Color(188,170,164));
//        
//        panelLaporanHarian.setLayout(new java.awt.BorderLayout());
//        ChartPanel cp = new ChartPanel(dataChart);
//        panelLaporanHarian.add(cp, BorderLayout.CENTER);
//        panelLaporanHarian.validate();
    }
    
    public void setPaddingButton() {
        btnDashboard.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        btnSuratMasuk.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        btnSuratKeluar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        btnRekap.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        btnInstansi.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        btnKeluar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    }
    
    public void exitedPanel(JPanel panel, Color color) {
        panel.setBackground(color);
        panel.setOpaque(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyPanel = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        sp2 = new javax.swing.JSeparator();
        lblDinas = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnSuratMasuk = new javax.swing.JButton();
        btnSuratKeluar = new javax.swing.JButton();
        btnRekap = new javax.swing.JButton();
        btnInstansi = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        panelMain = new javax.swing.JPanel();
        panelDashboard = new javax.swing.JPanel();
        ppPengguna = new javax.swing.JPanel();
        lblTotalPengguna = new javax.swing.JLabel();
        totalPengguna = new javax.swing.JLabel();
        icPengguna = new javax.swing.JLabel();
        ppSMasuk = new javax.swing.JPanel();
        lblTotalSuratMasuk = new javax.swing.JLabel();
        totalSuratMasuk = new javax.swing.JLabel();
        icSuratMasuk = new javax.swing.JLabel();
        ppSKeluar = new javax.swing.JPanel();
        lblTotalSuratKeluar = new javax.swing.JLabel();
        totalSuratKeluar = new javax.swing.JLabel();
        icSuratKeluar = new javax.swing.JLabel();
        ppStatistik = new javax.swing.JPanel();
        panelLaporanHarian = new javax.swing.JPanel();
        panelSuratMasuk = new javax.swing.JPanel();
        panelSuratKeluar = new javax.swing.JPanel();
        lblSuratKeluar = new javax.swing.JLabel();
        panelRekap = new javax.swing.JPanel();
        lblBeranda1 = new javax.swing.JLabel();
        panelInstansi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbInstansi = new javax.swing.JLabel();
        lbKab = new javax.swing.JLabel();
        lbTelp = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbAlamat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Surat Masuk");
        setAlwaysOnTop(true);
        setResizable(false);

        bodyPanel.setBackground(new java.awt.Color(255, 255, 255));
        bodyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWelcome.setBackground(new java.awt.Color(255, 255, 255));
        lblWelcome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg_welcome.png"))); // NOI18N
        lblWelcome.setText("TEST");
        lblWelcome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblWelcome.setIconTextGap(0);
        lblWelcome.setOpaque(true);
        bodyPanel.add(lblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 730, 30));

        panelMenu.setBackground(new java.awt.Color(0, 77, 64));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sp2.setBackground(new java.awt.Color(153, 153, 153));
        sp2.setForeground(new java.awt.Color(153, 153, 153));
        panelMenu.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 240, 5));

        lblDinas.setForeground(new java.awt.Color(255, 255, 255));
        lblDinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_small.png"))); // NOI18N
        lblDinas.setText("<html>\nSistem Informasi<br>\nAdministrasi Surat<br>\n<b>Dinas Lingkungan Hidup</b><br>\nKabupaten Bogor\n</html>");
        lblDinas.setIconTextGap(10);
        panelMenu.add(lblDinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 130));

        btnDashboard.setBackground(new java.awt.Color(0, 77, 64));
        btnDashboard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Speedometer_24px.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorder(null);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.setIconTextGap(30);
        panelMenu.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 250, 45));

        btnSuratMasuk.setBackground(new java.awt.Color(0, 77, 64));
        btnSuratMasuk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        btnSuratMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Document_24px.png"))); // NOI18N
        btnSuratMasuk.setText("Surat Masuk");
        btnSuratMasuk.setBorder(null);
        btnSuratMasuk.setBorderPainted(false);
        btnSuratMasuk.setContentAreaFilled(false);
        btnSuratMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSuratMasuk.setIconTextGap(30);
        panelMenu.add(btnSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 250, 45));

        btnSuratKeluar.setBackground(new java.awt.Color(0, 77, 64));
        btnSuratKeluar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        btnSuratKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Document_24px.png"))); // NOI18N
        btnSuratKeluar.setText("Surat Keluar");
        btnSuratKeluar.setBorder(null);
        btnSuratKeluar.setBorderPainted(false);
        btnSuratKeluar.setContentAreaFilled(false);
        btnSuratKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSuratKeluar.setIconTextGap(30);
        panelMenu.add(btnSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 250, 45));

        btnRekap.setBackground(new java.awt.Color(0, 77, 64));
        btnRekap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRekap.setForeground(new java.awt.Color(255, 255, 255));
        btnRekap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Documents_24px.png"))); // NOI18N
        btnRekap.setText("Rekapitulasi");
        btnRekap.setBorder(null);
        btnRekap.setBorderPainted(false);
        btnRekap.setContentAreaFilled(false);
        btnRekap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRekap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRekap.setIconTextGap(30);
        panelMenu.add(btnRekap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 275, 250, 45));

        btnInstansi.setBackground(new java.awt.Color(0, 77, 64));
        btnInstansi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInstansi.setForeground(new java.awt.Color(255, 255, 255));
        btnInstansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Museum_24px.png"))); // NOI18N
        btnInstansi.setText("Instansi");
        btnInstansi.setBorder(null);
        btnInstansi.setBorderPainted(false);
        btnInstansi.setContentAreaFilled(false);
        btnInstansi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInstansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInstansi.setIconTextGap(30);
        panelMenu.add(btnInstansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 250, 45));

        btnKeluar.setBackground(new java.awt.Color(0, 77, 64));
        btnKeluar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnKeluar.setForeground(new java.awt.Color(255, 255, 255));
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Shutdown_24px_1.png"))); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.setBorder(null);
        btnKeluar.setBorderPainted(false);
        btnKeluar.setContentAreaFilled(false);
        btnKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKeluar.setIconTextGap(30);
        panelMenu.add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 505, 250, 45));

        bodyPanel.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 550));

        panelMain.setLayout(new java.awt.CardLayout());

        panelDashboard.setBackground(new java.awt.Color(255, 255, 255));
        panelDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ppPengguna.setBackground(new java.awt.Color(244, 143, 177));
        ppPengguna.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        ppPengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ppPengguna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalPengguna.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalPengguna.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalPengguna.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalPengguna.setText("TOTAL MEMBER");
        lblTotalPengguna.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppPengguna.add(lblTotalPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 136, 45));

        totalPengguna.setBackground(new java.awt.Color(255, 255, 255));
        totalPengguna.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalPengguna.setForeground(new java.awt.Color(255, 255, 255));
        totalPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalPengguna.setText("5");
        totalPengguna.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppPengguna.add(totalPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 136, 45));

        icPengguna.setForeground(new java.awt.Color(255, 255, 255));
        icPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icPengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Person_48px_2.png"))); // NOI18N
        icPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppPengguna.add(icPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 100, 100));

        panelDashboard.add(ppPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 236, 100));

        ppSMasuk.setBackground(new java.awt.Color(255, 204, 128));
        ppSMasuk.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        ppSMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ppSMasuk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalSuratMasuk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuratMasuk.setText("SURAT MASUK");
        lblTotalSuratMasuk.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSMasuk.add(lblTotalSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 136, 45));

        totalSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));
        totalSuratMasuk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        totalSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSuratMasuk.setText("5");
        totalSuratMasuk.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSMasuk.add(totalSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 136, 45));

        icSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        icSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icSuratMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Gmail_Login_48px.png"))); // NOI18N
        icSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSMasuk.add(icSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 100, 100));

        panelDashboard.add(ppSMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 0, 236, 100));

        ppSKeluar.setBackground(new java.awt.Color(188, 170, 164));
        ppSKeluar.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        ppSKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ppSKeluar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuratKeluar.setText("SURAT KELUAR");
        lblTotalSuratKeluar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSKeluar.add(lblTotalSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 136, 45));

        totalSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSuratKeluar.setText("5");
        totalSuratKeluar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSKeluar.add(totalSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 136, 45));

        icSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        icSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icSuratKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Feedback_48px.png"))); // NOI18N
        icSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSKeluar.add(icSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 100, 100));

        panelDashboard.add(ppSKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 0, 236, 100));

        ppStatistik.setBackground(new java.awt.Color(255, 255, 255));
        ppStatistik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLaporanHarian.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLaporanHarianLayout = new javax.swing.GroupLayout(panelLaporanHarian);
        panelLaporanHarian.setLayout(panelLaporanHarianLayout);
        panelLaporanHarianLayout.setHorizontalGroup(
            panelLaporanHarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panelLaporanHarianLayout.setVerticalGroup(
            panelLaporanHarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        ppStatistik.add(panelLaporanHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 380));

        panelDashboard.add(ppStatistik, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 730, 380));

        panelMain.add(panelDashboard, "card2");

        panelSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));
        panelSuratMasuk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelMain.add(panelSuratMasuk, "card3");

        panelSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));

        lblSuratKeluar.setText("Surat Keluar");

        javax.swing.GroupLayout panelSuratKeluarLayout = new javax.swing.GroupLayout(panelSuratKeluar);
        panelSuratKeluar.setLayout(panelSuratKeluarLayout);
        panelSuratKeluarLayout.setHorizontalGroup(
            panelSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(panelSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSuratKeluarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblSuratKeluar)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelSuratKeluarLayout.setVerticalGroup(
            panelSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(panelSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSuratKeluarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblSuratKeluar)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelMain.add(panelSuratKeluar, "card2");

        panelRekap.setBackground(new java.awt.Color(255, 255, 255));

        lblBeranda1.setText("Rekap");

        javax.swing.GroupLayout panelRekapLayout = new javax.swing.GroupLayout(panelRekap);
        panelRekap.setLayout(panelRekapLayout);
        panelRekapLayout.setHorizontalGroup(
            panelRekapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(panelRekapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRekapLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblBeranda1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelRekapLayout.setVerticalGroup(
            panelRekapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(panelRekapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRekapLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblBeranda1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelMain.add(panelRekap, "card2");

        panelInstansi.setBackground(new java.awt.Color(255, 255, 255));
        panelInstansi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Instansi");
        panelInstansi.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 20));

        jLabel2.setText("Kabupaten");
        panelInstansi.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 100, 20));

        jLabel3.setText("Telp");
        panelInstansi.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 100, 20));

        jLabel6.setText("Email");
        panelInstansi.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 100, 20));

        jLabel4.setText("Alamat");
        panelInstansi.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 100, 20));

        lbInstansi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbInstansi.setText("Instansi");
        panelInstansi.add(lbInstansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 510, 20));

        lbKab.setText("Kabupaten");
        panelInstansi.add(lbKab, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 510, 20));

        lbTelp.setText("Telp");
        panelInstansi.add(lbTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 510, 20));

        lbEmail.setText("Email");
        panelInstansi.add(lbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 510, 20));

        lbAlamat.setText("Alamat");
        panelInstansi.add(lbAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 510, 20));

        panelMain.add(panelInstansi, "card2");

        bodyPanel.add(panelMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 730, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnInstansi;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnRekap;
    private javax.swing.JButton btnSuratKeluar;
    private javax.swing.JButton btnSuratMasuk;
    private javax.swing.JLabel icPengguna;
    private javax.swing.JLabel icSuratKeluar;
    private javax.swing.JLabel icSuratMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbAlamat;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbInstansi;
    private javax.swing.JLabel lbKab;
    private javax.swing.JLabel lbTelp;
    private javax.swing.JLabel lblBeranda1;
    private javax.swing.JLabel lblDinas;
    private javax.swing.JLabel lblSuratKeluar;
    private javax.swing.JLabel lblTotalPengguna;
    private javax.swing.JLabel lblTotalSuratKeluar;
    private javax.swing.JLabel lblTotalSuratMasuk;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelInstansi;
    private javax.swing.JPanel panelLaporanHarian;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelRekap;
    private javax.swing.JPanel panelSuratKeluar;
    private javax.swing.JPanel panelSuratMasuk;
    private javax.swing.JPanel ppPengguna;
    private javax.swing.JPanel ppSKeluar;
    private javax.swing.JPanel ppSMasuk;
    private javax.swing.JPanel ppStatistik;
    private javax.swing.JSeparator sp2;
    private javax.swing.JLabel totalPengguna;
    private javax.swing.JLabel totalSuratKeluar;
    private javax.swing.JLabel totalSuratMasuk;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == btnDashboard) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelDashboard);
            panelMain.repaint();
            panelMain.revalidate();
            setTitle("Beranda");
            
        } else if (e.getSource() == btnSuratMasuk) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelSuratMasuk);
            panelMain.repaint();
            panelMain.revalidate();
            setTitle("Surat Masuk");
            
        } else if (e.getSource() == btnSuratKeluar) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelSuratKeluar);
            panelMain.repaint();
            panelMain.revalidate();
            setTitle("Surat Keluar");
            
        } else if (e.getSource() == btnRekap) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelRekap);
            panelMain.repaint();
            panelMain.revalidate();
            
        } else if (e.getSource() == btnInstansi) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelInstansi);
            panelMain.repaint();
            panelMain.revalidate();
            
        } else if (e.getSource() == btnKeluar) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Keluar dari " + paramNama + "?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                new Masuk().setVisible(true);
                dispose();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        String bahan = String.valueOf(c);
        String tanda = "[";
        int tipe = bahan.indexOf(tanda);
        String hasil = bahan.substring(13, tipe).trim();
        
        if (hasil.equals("Panel")) {
            JPanel pnl = (JPanel) e.getSource();
            pnl.setBackground(new java.awt.Color(128, 203, 196));
            pnl.setOpaque(true);
        } else if (hasil.equals("Button")) {
            JButton btn = (JButton) e.getSource();
            btn.setBackground(new java.awt.Color(0,150,136));
            btn.setOpaque(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        String bahan = String.valueOf(c);
        String tanda = "[";
        int tipe = bahan.indexOf(tanda);
        String hasil = bahan.substring(13, tipe).trim();
        
        if (hasil.equals("Panel")) {
             if (e.getSource() == ppPengguna) {
                    exitedPanel(ppPengguna, ctPengguna);
                } else if (e.getSource() == ppSKeluar) {
                    exitedPanel(ppSKeluar, ctKeluar);
                } else if (e.getSource() == ppSMasuk) {
                    exitedPanel(ppSMasuk, ctMasuk);
                }
        } else if (hasil.equals("Button")) {
            JButton btn = (JButton) e.getSource();
            btn.setBackground(new java.awt.Color(0,77,64));
            btn.setOpaque(true);
        }
    }
}
