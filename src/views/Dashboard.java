package views;

import helper.Koneksi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author rezarizkip
 */
public class Dashboard extends javax.swing.JFrame implements MouseListener, ActionListener {
    
    private final String paramNama, welcome;
    int nPengguna, nMasuk, nKeluar;
    Color ctPengguna, ctMasuk, ctKeluar;
    String[] columnNames = {"Nomor Surat", "Pengirim", "Penerima", "Tanggal Masuk", "Tanggal Keluar", "Tanggal Surat", "Perihal", "Kode Surat", "Jenis Surat"};

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
        setTitle("Beranda");
    }
    
    private void initListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doExit();
            }
        });
        setPaddingButton();
        setRandomTotal();
        setToday();
        setChart();
        getWarnaAwal();
        checkPanelActive(pacTambahSurat, navTambahSurat, navSuratMasuk, navSuratKeluar);
        
        KodeSurat();
        JenisSurat1();
        
        navDashboard.addMouseListener(this);
        navArsipSurat.addMouseListener(this);
        navDisposisi.addMouseListener(this);
        navLaporan.addMouseListener(this);
        navInstansi.addMouseListener(this);
        navKeluar.addMouseListener(this);
        
        navDashboard.addActionListener(this);
        navArsipSurat.addActionListener(this);
        navDisposisi.addActionListener(this);
        navLaporan.addActionListener(this);
        navInstansi.addActionListener(this);
        navKeluar.addActionListener(this);
        
        navTambahSurat.addActionListener(this);
        btnSimpanSurat.addActionListener(this);
        btnBatalkanSurat.addActionListener(this);
        cKodeSurat.addActionListener(this);
        cJenisSurat.addActionListener(this);
        
        navSuratMasuk.addActionListener(this);
        navSuratKeluar.addActionListener(this);
        
        pdPengguna.addMouseListener(this);
        pdSMasuk.addMouseListener(this);
        pdSKeluar.addMouseListener(this);
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
    
    public void doLogout() {
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
    
    public void setPanelMain(JPanel main, JPanel jp, String judul) {
        main.removeAll();
        main.repaint();
        main.revalidate();
        main.add(jp);
        main.repaint();
        main.revalidate();
        setTitle(judul);
    }
    
    public void setScrollMain(JPanel main, JScrollPane jp, String judul) {
        main.removeAll();
        main.repaint();
        main.revalidate();
        main.add(jp);
        main.repaint();
        main.revalidate();
        setTitle(judul);
    }
    
    public void checkPanelActive(JPanel panel, JButton btnActive, JButton btnInactive1, JButton btnInactive2) {
        if (panel.isVisible() || panel.getParent().getParent().getParent().getParent().isVisible()) {
            btnActive.setBackground(new Color(0,150,136));
            btnActive.setOpaque(true);
            btnInactive1.setBackground(new Color(204,204,204));
            btnInactive1.setOpaque(true);
            btnInactive2.setBackground(new Color(204,204,204));
            btnInactive2.setOpaque(true);
        } else if (!panel.isVisible() && panel.getParent().isVisible()) {
            btnActive.setBackground(new Color(204,204,204));
            btnActive.setOpaque(true);
            btnInactive1.setBackground(new Color(0,150,136));
            btnInactive1.setOpaque(true);
            btnInactive2.setBackground(new Color(0,150,136));
            btnInactive2.setOpaque(true);
        }
    }
    
    public void setPaddingButton() {
        navDashboard.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        navArsipSurat.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        navDisposisi.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        navLaporan.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        navInstansi.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        navKeluar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    }
    
    public void getWarnaAwal() {
        ctPengguna = pdPengguna.getBackground();
        ctMasuk = pdSMasuk.getBackground();
        ctKeluar = pdSKeluar.getBackground();
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
    
    public void setChart() {
        String judul = "Laporan hari ini";
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT count(*) FROM `pengguna` WHERE DATE(`created_at`) = CURDATE()";
        
        try {
            ps = Koneksi.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                int hasil = rs.getInt(1);
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

                pdLaporanHarian.setLayout(new java.awt.BorderLayout());
                ChartPanel cp = new ChartPanel(dataChart);
                pdLaporanHarian.add(cp, BorderLayout.CENTER);
                pdLaporanHarian.validate();
            } else {}
        } catch (SQLException ex) {
            Logger.getLogger(Masuk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setToday() {
        nPengguna = nomorRandom(1, 99);
        nMasuk = nomorRandom(1, 99);
        nKeluar = nomorRandom(1, 99);
    }
    
    public void exitedPanel(JPanel panel, Color color) {
        panel.setBackground(color);
        panel.setOpaque(true);
    }
    
    public void clearInputSurat() {
        cKodeSurat.setSelectedIndex(-1);
        inNomorSurat.setText("");
        inPengirim.setText("");
        inPenerima.setText("");
        inTglBuat.setCalendar(null);
        inTglSurat.setCalendar(null);
        cJenisSurat.setSelectedIndex(-1);
        inPerihalSurat.setText("");
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
        panelNavigasiMenu = new javax.swing.JPanel();
        sp2 = new javax.swing.JSeparator();
        lblDinas = new javax.swing.JLabel();
        navDashboard = new javax.swing.JButton();
        navArsipSurat = new javax.swing.JButton();
        navDisposisi = new javax.swing.JButton();
        navLaporan = new javax.swing.JButton();
        navInstansi = new javax.swing.JButton();
        navKeluar = new javax.swing.JButton();
        panelMain = new javax.swing.JPanel();
        panelDashboard = new javax.swing.JPanel();
        pdHeader = new javax.swing.JPanel();
        pdPengguna = new javax.swing.JPanel();
        lblTotalPengguna = new javax.swing.JLabel();
        totalPengguna = new javax.swing.JLabel();
        icPengguna = new javax.swing.JLabel();
        pdSMasuk = new javax.swing.JPanel();
        lblTotalSuratMasuk = new javax.swing.JLabel();
        totalSuratMasuk = new javax.swing.JLabel();
        icSuratMasuk = new javax.swing.JLabel();
        pdSKeluar = new javax.swing.JPanel();
        lblTotalSuratKeluar = new javax.swing.JLabel();
        totalSuratKeluar = new javax.swing.JLabel();
        icSuratKeluar = new javax.swing.JLabel();
        pdSKeluar1 = new javax.swing.JPanel();
        lblTotalSuratKeluar1 = new javax.swing.JLabel();
        totalSuratKeluar1 = new javax.swing.JLabel();
        icSuratKeluar1 = new javax.swing.JLabel();
        pdContent = new javax.swing.JPanel();
        pdLaporanHarian = new javax.swing.JPanel();
        panelArsip = new javax.swing.JPanel();
        paHeader = new javax.swing.JPanel();
        navTambahSurat = new javax.swing.JButton();
        navSuratMasuk = new javax.swing.JButton();
        navSuratKeluar = new javax.swing.JButton();
        paContent = new javax.swing.JPanel();
        pacTambahSurat = new javax.swing.JPanel();
        TITLE1 = new javax.swing.JLabel();
        lbKodeSurat1 = new javax.swing.JLabel();
        lbNoSurat1 = new javax.swing.JLabel();
        lbPengirim1 = new javax.swing.JLabel();
        lbPenerima1 = new javax.swing.JLabel();
        lbTglMasuk1 = new javax.swing.JLabel();
        lbTglSurat1 = new javax.swing.JLabel();
        lbJenisSurat1 = new javax.swing.JLabel();
        lbPerihalSurat1 = new javax.swing.JLabel();
        cKodeSurat = new javax.swing.JComboBox<>();
        inNomorSurat = new javax.swing.JTextField();
        inPengirim = new javax.swing.JTextField();
        inPenerima = new javax.swing.JTextField();
        inTglBuat = new com.toedter.calendar.JDateChooser();
        inTglSurat = new com.toedter.calendar.JDateChooser();
        cJenisSurat = new javax.swing.JComboBox<>();
        inPerihalSurat = new javax.swing.JTextField();
        btnSimpanSurat = new javax.swing.JButton();
        btnBatalkanSurat = new javax.swing.JButton();
        pacLihatSurat = new javax.swing.JPanel();
        jscrollpaneMasuk = new javax.swing.JScrollPane();
        tableMasuk = new javax.swing.JTable();
        jscrollpaneKeluar = new javax.swing.JScrollPane();
        tableKeluar = new javax.swing.JTable();
        panelDisposisi = new javax.swing.JPanel();
        panelLaporan = new javax.swing.JPanel();
        panelInstansi = new javax.swing.JPanel();

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
        bodyPanel.add(lblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 930, 30));

        panelNavigasiMenu.setBackground(new java.awt.Color(0, 77, 64));
        panelNavigasiMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sp2.setBackground(new java.awt.Color(153, 153, 153));
        sp2.setForeground(new java.awt.Color(153, 153, 153));
        panelNavigasiMenu.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 240, 5));

        lblDinas.setForeground(new java.awt.Color(255, 255, 255));
        lblDinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_small.png"))); // NOI18N
        lblDinas.setText("<html>\nSistem Informasi<br>\nAdministrasi Surat<br>\n<b>Dinas Lingkungan Hidup</b><br>\nKabupaten Bogor\n</html>");
        lblDinas.setIconTextGap(10);
        panelNavigasiMenu.add(lblDinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 130));

        navDashboard.setBackground(new java.awt.Color(0, 77, 64));
        navDashboard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navDashboard.setForeground(new java.awt.Color(255, 255, 255));
        navDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Speedometer_24px.png"))); // NOI18N
        navDashboard.setText("Dashboard");
        navDashboard.setBorder(null);
        navDashboard.setBorderPainted(false);
        navDashboard.setContentAreaFilled(false);
        navDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        navDashboard.setIconTextGap(30);
        panelNavigasiMenu.add(navDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 250, 45));

        navArsipSurat.setBackground(new java.awt.Color(0, 77, 64));
        navArsipSurat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navArsipSurat.setForeground(new java.awt.Color(255, 255, 255));
        navArsipSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Documents_24px.png"))); // NOI18N
        navArsipSurat.setText("Arsip Surat");
        navArsipSurat.setBorder(null);
        navArsipSurat.setBorderPainted(false);
        navArsipSurat.setContentAreaFilled(false);
        navArsipSurat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navArsipSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        navArsipSurat.setIconTextGap(30);
        panelNavigasiMenu.add(navArsipSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 250, 45));

        navDisposisi.setBackground(new java.awt.Color(0, 77, 64));
        navDisposisi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navDisposisi.setForeground(new java.awt.Color(255, 255, 255));
        navDisposisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Document_24px.png"))); // NOI18N
        navDisposisi.setText("Disposisi");
        navDisposisi.setBorder(null);
        navDisposisi.setBorderPainted(false);
        navDisposisi.setContentAreaFilled(false);
        navDisposisi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navDisposisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        navDisposisi.setIconTextGap(30);
        panelNavigasiMenu.add(navDisposisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 250, 45));

        navLaporan.setBackground(new java.awt.Color(0, 77, 64));
        navLaporan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navLaporan.setForeground(new java.awt.Color(255, 255, 255));
        navLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Documents_24px.png"))); // NOI18N
        navLaporan.setText("Laporan");
        navLaporan.setBorder(null);
        navLaporan.setBorderPainted(false);
        navLaporan.setContentAreaFilled(false);
        navLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        navLaporan.setIconTextGap(30);
        panelNavigasiMenu.add(navLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 275, 250, 45));

        navInstansi.setBackground(new java.awt.Color(0, 77, 64));
        navInstansi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navInstansi.setForeground(new java.awt.Color(255, 255, 255));
        navInstansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Museum_24px.png"))); // NOI18N
        navInstansi.setText("Instansi");
        navInstansi.setBorder(null);
        navInstansi.setBorderPainted(false);
        navInstansi.setContentAreaFilled(false);
        navInstansi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navInstansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        navInstansi.setIconTextGap(30);
        panelNavigasiMenu.add(navInstansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 250, 45));

        navKeluar.setBackground(new java.awt.Color(0, 77, 64));
        navKeluar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navKeluar.setForeground(new java.awt.Color(255, 255, 255));
        navKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Shutdown_24px_1.png"))); // NOI18N
        navKeluar.setText("Keluar");
        navKeluar.setBorder(null);
        navKeluar.setBorderPainted(false);
        navKeluar.setContentAreaFilled(false);
        navKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        navKeluar.setIconTextGap(30);
        panelNavigasiMenu.add(navKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 655, 250, 45));

        bodyPanel.add(panelNavigasiMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 700));

        panelMain.setLayout(new java.awt.CardLayout());

        panelDashboard.setBackground(new java.awt.Color(255, 255, 255));
        panelDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pdHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pdPengguna.setBackground(new java.awt.Color(244, 143, 177));
        pdPengguna.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        pdPengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pdPengguna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalPengguna.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalPengguna.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalPengguna.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalPengguna.setText("TOTAL MEMBER");
        lblTotalPengguna.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdPengguna.add(lblTotalPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 45));

        totalPengguna.setBackground(new java.awt.Color(255, 255, 255));
        totalPengguna.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalPengguna.setForeground(new java.awt.Color(255, 255, 255));
        totalPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalPengguna.setText("5");
        totalPengguna.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdPengguna.add(totalPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 140, 45));

        icPengguna.setForeground(new java.awt.Color(255, 255, 255));
        icPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icPengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Person_48px_2.png"))); // NOI18N
        icPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdPengguna.add(icPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 85, 100));

        pdHeader.add(pdPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 225, 100));

        pdSMasuk.setBackground(new java.awt.Color(255, 204, 128));
        pdSMasuk.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        pdSMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pdSMasuk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalSuratMasuk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuratMasuk.setText("SURAT MASUK");
        lblTotalSuratMasuk.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSMasuk.add(lblTotalSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 45));

        totalSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));
        totalSuratMasuk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        totalSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSuratMasuk.setText("5");
        totalSuratMasuk.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSMasuk.add(totalSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 140, 45));

        icSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        icSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icSuratMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Gmail_Login_48px.png"))); // NOI18N
        icSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSMasuk.add(icSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 85, 100));

        pdHeader.add(pdSMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 10, 225, 100));

        pdSKeluar.setBackground(new java.awt.Color(188, 170, 164));
        pdSKeluar.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        pdSKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pdSKeluar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuratKeluar.setText("SURAT KELUAR");
        lblTotalSuratKeluar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSKeluar.add(lblTotalSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 45));

        totalSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSuratKeluar.setText("5");
        totalSuratKeluar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSKeluar.add(totalSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 140, 45));

        icSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        icSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icSuratKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Feedback_48px.png"))); // NOI18N
        icSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSKeluar.add(icSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 85, 100));

        pdHeader.add(pdSKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 225, 100));

        pdSKeluar1.setBackground(new java.awt.Color(129, 199, 132));
        pdSKeluar1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        pdSKeluar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pdSKeluar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalSuratKeluar1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalSuratKeluar1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuratKeluar1.setText("NEW PANEL");
        lblTotalSuratKeluar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalSuratKeluar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSKeluar1.add(lblTotalSuratKeluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 45));

        totalSuratKeluar1.setBackground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalSuratKeluar1.setForeground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSuratKeluar1.setText("5");
        totalSuratKeluar1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalSuratKeluar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSKeluar1.add(totalSuratKeluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 140, 45));

        icSuratKeluar1.setForeground(new java.awt.Color(255, 255, 255));
        icSuratKeluar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icSuratKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Feedback_48px.png"))); // NOI18N
        icSuratKeluar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdSKeluar1.add(icSuratKeluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 85, 100));

        pdHeader.add(pdSKeluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 10, 225, 100));

        panelDashboard.add(pdHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 120));

        pdContent.setLayout(new java.awt.CardLayout());

        pdLaporanHarian.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pdLaporanHarianLayout = new javax.swing.GroupLayout(pdLaporanHarian);
        pdLaporanHarian.setLayout(pdLaporanHarianLayout);
        pdLaporanHarianLayout.setHorizontalGroup(
            pdLaporanHarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        pdLaporanHarianLayout.setVerticalGroup(
            pdLaporanHarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        pdContent.add(pdLaporanHarian, "card2");

        panelDashboard.add(pdContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 930, 520));

        panelMain.add(panelDashboard, "card2");

        panelArsip.setBackground(new java.awt.Color(255, 255, 255));
        panelArsip.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paHeader.setBackground(new java.awt.Color(204, 204, 204));
        paHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navTambahSurat.setBackground(new java.awt.Color(204, 204, 204));
        navTambahSurat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navTambahSurat.setForeground(new java.awt.Color(255, 255, 255));
        navTambahSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Plus_Math_24px.png"))); // NOI18N
        navTambahSurat.setText("Tambah Surat");
        navTambahSurat.setBorder(null);
        navTambahSurat.setBorderPainted(false);
        navTambahSurat.setContentAreaFilled(false);
        navTambahSurat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navTambahSurat.setIconTextGap(10);
        navTambahSurat.setOpaque(true);
        paHeader.add(navTambahSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 317, 60));

        navSuratMasuk.setBackground(new java.awt.Color(204, 204, 204));
        navSuratMasuk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        navSuratMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Documents_24px.png"))); // NOI18N
        navSuratMasuk.setText("Lihat Surat Masuk");
        navSuratMasuk.setBorder(null);
        navSuratMasuk.setBorderPainted(false);
        navSuratMasuk.setContentAreaFilled(false);
        navSuratMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navSuratMasuk.setIconTextGap(10);
        navSuratMasuk.setOpaque(true);
        paHeader.add(navSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 0, 316, 60));

        navSuratKeluar.setBackground(new java.awt.Color(204, 204, 204));
        navSuratKeluar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        navSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        navSuratKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Documents_24px.png"))); // NOI18N
        navSuratKeluar.setText("Lihat Surat Keluar");
        navSuratKeluar.setBorder(null);
        navSuratKeluar.setBorderPainted(false);
        navSuratKeluar.setContentAreaFilled(false);
        navSuratKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navSuratKeluar.setIconTextGap(10);
        navSuratKeluar.setOpaque(true);
        paHeader.add(navSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 0, 317, 60));

        panelArsip.add(paHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 60));

        paContent.setBackground(new java.awt.Color(204, 204, 204));
        paContent.setToolTipText("");
        paContent.setLayout(new java.awt.CardLayout());

        pacTambahSurat.setBackground(new java.awt.Color(255, 255, 255));
        pacTambahSurat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TITLE1.setBackground(new java.awt.Color(255, 255, 255));
        TITLE1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        TITLE1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TITLE1.setText("Tambah Arsip Surat");
        pacTambahSurat.add(TITLE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 930, 25));

        lbKodeSurat1.setBackground(new java.awt.Color(255, 255, 255));
        lbKodeSurat1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbKodeSurat1.setText("Kode Surat");
        pacTambahSurat.add(lbKodeSurat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 75, 110, 40));

        lbNoSurat1.setBackground(new java.awt.Color(255, 255, 255));
        lbNoSurat1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNoSurat1.setText("Nomor Surat");
        pacTambahSurat.add(lbNoSurat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 135, 110, 40));

        lbPengirim1.setBackground(new java.awt.Color(255, 255, 255));
        lbPengirim1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbPengirim1.setText("Pengirim");
        pacTambahSurat.add(lbPengirim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 195, 110, 40));

        lbPenerima1.setBackground(new java.awt.Color(255, 255, 255));
        lbPenerima1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbPenerima1.setText("Penerima");
        pacTambahSurat.add(lbPenerima1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 255, 110, 40));

        lbTglMasuk1.setBackground(new java.awt.Color(255, 255, 255));
        lbTglMasuk1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTglMasuk1.setText("Tanggal Keluar");
        pacTambahSurat.add(lbTglMasuk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 315, 110, 40));

        lbTglSurat1.setBackground(new java.awt.Color(255, 255, 255));
        lbTglSurat1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTglSurat1.setText("Tanggal Surat");
        pacTambahSurat.add(lbTglSurat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 315, -1, 40));

        lbJenisSurat1.setBackground(new java.awt.Color(255, 255, 255));
        lbJenisSurat1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbJenisSurat1.setText("Jenis Surat");
        pacTambahSurat.add(lbJenisSurat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 375, 110, 40));

        lbPerihalSurat1.setBackground(new java.awt.Color(255, 255, 255));
        lbPerihalSurat1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbPerihalSurat1.setText("Perihal Surat");
        pacTambahSurat.add(lbPerihalSurat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 435, 110, 40));

        pacTambahSurat.add(cKodeSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 75, 260, 40));

        inNomorSurat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inNomorSurat.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        inNomorSurat.setEnabled(false);
        pacTambahSurat.add(inNomorSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 135, 640, 40));

        inPengirim.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inPengirim.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        inPengirim.setEnabled(false);
        pacTambahSurat.add(inPengirim, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 195, 640, 40));

        inPenerima.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inPenerima.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        inPenerima.setEnabled(false);
        pacTambahSurat.add(inPenerima, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 255, 640, 40));

        inTglBuat.setEnabled(false);
        pacTambahSurat.add(inTglBuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 315, 260, 40));

        inTglSurat.setEnabled(false);
        pacTambahSurat.add(inTglSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 315, 260, 40));

        cJenisSurat.setEnabled(false);
        pacTambahSurat.add(cJenisSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 375, 260, 40));

        inPerihalSurat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inPerihalSurat.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        inPerihalSurat.setEnabled(false);
        pacTambahSurat.add(inPerihalSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 435, 640, 40));

        btnSimpanSurat.setBackground(new java.awt.Color(0, 77, 64));
        btnSimpanSurat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSimpanSurat.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpanSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Save_24px.png"))); // NOI18N
        btnSimpanSurat.setText("Simpan");
        btnSimpanSurat.setBorder(null);
        btnSimpanSurat.setBorderPainted(false);
        btnSimpanSurat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpanSurat.setEnabled(false);
        btnSimpanSurat.setIconTextGap(10);
        pacTambahSurat.add(btnSimpanSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 495, 315, 40));

        btnBatalkanSurat.setBackground(new java.awt.Color(0, 77, 64));
        btnBatalkanSurat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBatalkanSurat.setForeground(new java.awt.Color(255, 255, 255));
        btnBatalkanSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Unavailable_24px.png"))); // NOI18N
        btnBatalkanSurat.setText("Batalkan");
        btnBatalkanSurat.setBorder(null);
        btnBatalkanSurat.setBorderPainted(false);
        btnBatalkanSurat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatalkanSurat.setEnabled(false);
        btnBatalkanSurat.setIconTextGap(10);
        pacTambahSurat.add(btnBatalkanSurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 495, 315, 40));

        paContent.add(pacTambahSurat, "card3");

        pacLihatSurat.setLayout(new java.awt.CardLayout());

        jscrollpaneMasuk.setBorder(null);
        jscrollpaneMasuk.setOpaque(false);

        tableMasuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tableMasuk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No Surat", "Pengirim", "Penerima", "Tanggal Masuk", "Tanggal Surat", "Perihal", "Jenis"
            }
        ));
        tableMasuk.setFocusable(false);
        tableMasuk.setGridColor(new java.awt.Color(255, 255, 255));
        tableMasuk.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableMasuk.setOpaque(false);
        tableMasuk.setRowHeight(30);
        tableMasuk.setSelectionBackground(new java.awt.Color(128, 203, 196));
        tableMasuk.setShowVerticalLines(false);
        tableMasuk.getTableHeader().setReorderingAllowed(false);
        jscrollpaneMasuk.setViewportView(tableMasuk);

        pacLihatSurat.add(jscrollpaneMasuk, "card2");

        jscrollpaneKeluar.setBorder(null);
        jscrollpaneKeluar.setOpaque(false);

        tableKeluar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tableKeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No Surat", "Pengirim", "Penerima", "Tanggal Keluar", "Tanggal Surat", "Perihal", "Jenis"
            }
        ));
        tableKeluar.setFocusable(false);
        tableKeluar.setGridColor(new java.awt.Color(255, 255, 255));
        tableKeluar.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableKeluar.setOpaque(false);
        tableKeluar.setRowHeight(30);
        tableKeluar.setSelectionBackground(new java.awt.Color(128, 203, 196));
        tableKeluar.setShowVerticalLines(false);
        tableKeluar.getTableHeader().setReorderingAllowed(false);
        jscrollpaneKeluar.setViewportView(tableKeluar);

        pacLihatSurat.add(jscrollpaneKeluar, "card3");

        paContent.add(pacLihatSurat, "card4");

        panelArsip.add(paContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 930, 570));

        panelMain.add(panelArsip, "card2");

        panelDisposisi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelMain.add(panelDisposisi, "card4");

        panelLaporan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelMain.add(panelLaporan, "card4");

        panelInstansi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelMain.add(panelInstansi, "card4");

        bodyPanel.add(panelMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 950, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel TITLE1;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnBatalkanSurat;
    private javax.swing.JButton btnSimpanSurat;
    private javax.swing.JComboBox<String> cJenisSurat;
    private javax.swing.JComboBox<String> cKodeSurat;
    private javax.swing.JLabel icPengguna;
    private javax.swing.JLabel icSuratKeluar;
    private javax.swing.JLabel icSuratKeluar1;
    private javax.swing.JLabel icSuratMasuk;
    private javax.swing.JTextField inNomorSurat;
    private javax.swing.JTextField inPenerima;
    private javax.swing.JTextField inPengirim;
    private javax.swing.JTextField inPerihalSurat;
    private com.toedter.calendar.JDateChooser inTglBuat;
    private com.toedter.calendar.JDateChooser inTglSurat;
    private javax.swing.JScrollPane jscrollpaneKeluar;
    private javax.swing.JScrollPane jscrollpaneMasuk;
    private javax.swing.JLabel lbJenisSurat1;
    private javax.swing.JLabel lbKodeSurat1;
    private javax.swing.JLabel lbNoSurat1;
    private javax.swing.JLabel lbPenerima1;
    private javax.swing.JLabel lbPengirim1;
    private javax.swing.JLabel lbPerihalSurat1;
    private javax.swing.JLabel lbTglMasuk1;
    private javax.swing.JLabel lbTglSurat1;
    private javax.swing.JLabel lblDinas;
    private javax.swing.JLabel lblTotalPengguna;
    private javax.swing.JLabel lblTotalSuratKeluar;
    private javax.swing.JLabel lblTotalSuratKeluar1;
    private javax.swing.JLabel lblTotalSuratMasuk;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JButton navArsipSurat;
    private javax.swing.JButton navDashboard;
    private javax.swing.JButton navDisposisi;
    private javax.swing.JButton navInstansi;
    private javax.swing.JButton navKeluar;
    private javax.swing.JButton navLaporan;
    private javax.swing.JButton navSuratKeluar;
    private javax.swing.JButton navSuratMasuk;
    private javax.swing.JButton navTambahSurat;
    private javax.swing.JPanel paContent;
    private javax.swing.JPanel paHeader;
    private javax.swing.JPanel pacLihatSurat;
    private javax.swing.JPanel pacTambahSurat;
    private javax.swing.JPanel panelArsip;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelDisposisi;
    private javax.swing.JPanel panelInstansi;
    private javax.swing.JPanel panelLaporan;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelNavigasiMenu;
    private javax.swing.JPanel pdContent;
    private javax.swing.JPanel pdHeader;
    private javax.swing.JPanel pdLaporanHarian;
    private javax.swing.JPanel pdPengguna;
    private javax.swing.JPanel pdSKeluar;
    private javax.swing.JPanel pdSKeluar1;
    private javax.swing.JPanel pdSMasuk;
    private javax.swing.JSeparator sp2;
    private javax.swing.JTable tableKeluar;
    private javax.swing.JTable tableMasuk;
    private javax.swing.JLabel totalPengguna;
    private javax.swing.JLabel totalSuratKeluar;
    private javax.swing.JLabel totalSuratKeluar1;
    private javax.swing.JLabel totalSuratMasuk;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {}

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
             if (e.getSource() == pdPengguna) {
                    exitedPanel(pdPengguna, ctPengguna);
                } else if (e.getSource() == pdSKeluar) {
                    exitedPanel(pdSKeluar, ctKeluar);
                } else if (e.getSource() == pdSMasuk) {
                    exitedPanel(pdSMasuk, ctMasuk);
                }
        } else if (hasil.equals("Button")) {
            JButton btn = (JButton) e.getSource();
            btn.setBackground(new java.awt.Color(0,77,64));
            btn.setOpaque(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // NAVIGASI SIDE-MENU
        if (e.getSource() == navDashboard) {
            setPanelMain(panelMain, panelDashboard, "Beranda");
        } else if (e.getSource() == navArsipSurat) {
            setPanelMain(panelMain, panelArsip, "Arsip Surat");
        } else if (e.getSource() == navDisposisi) {
            setPanelMain(panelMain, panelDisposisi, "Disposisi");
        } else if (e.getSource() == navLaporan) {
            setPanelMain(panelMain, panelLaporan, "Laporan");
        } else if (e.getSource() == navInstansi) {
            setPanelMain(panelMain, panelInstansi, "Instansi");
        } else if (e.getSource() == navKeluar) {
            doLogout();
        }
        
        // NAVIGASI SUB SIDE-MENU
        else if (e.getSource() == navTambahSurat) {
            setPanelMain(paContent, pacTambahSurat, "Tambah Surat");
            checkPanelActive(pacTambahSurat, navTambahSurat, navSuratMasuk, navSuratKeluar);
            cKodeSurat.setSelectedIndex(-1);
        } else if (e.getSource() == cKodeSurat) {
            if (cKodeSurat.getSelectedIndex() == -1) {
                genFieldInput(false);
            } else if (cKodeSurat.getSelectedIndex() == 0) {
                genFieldInput(true);
                lbTglMasuk1.setText("Tanggal Masuk");
            } else {
                genFieldInput(true);
                lbTglMasuk1.setText("Tanggal Keluar");
            }
        } else if (e.getSource() == btnSimpanSurat) {
            
            Date buat = inTglBuat.getDate();
            Date surat = inTglSurat.getDate();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            String noSurat = inNomorSurat.getText();
            String pengirim = inPengirim.getText();
            String penerima = inPenerima.getText();
            String tgl = dateFormat.format(buat);
            String tglSurat = dateFormat.format(surat);
            String perihalSurat = inPerihalSurat.getText();
            String kodeSurat = cKodeSurat.getSelectedItem().toString();
            String jenisSurat = cJenisSurat.getSelectedItem().toString();
            
            PreparedStatement ps;
            String query = "INSERT INTO `data_arsip`"
                    + "(`nomor_surat`, `pengirim`, `penerima`, `tgl_masuk`, `tgl_keluar`, `tgl_surat`, `perihal_surat`, `kode_surat`, `jenis_surat`)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            
            try {
                ps = Koneksi.getConnection().prepareStatement(query);
                
                if (cKodeSurat.getSelectedIndex() == 0) {
                    ps.setString(1, noSurat);
                    ps.setString(2, pengirim);
                    ps.setString(3, penerima);
                    ps.setString(4, tgl);
                    ps.setString(5, "");
                    ps.setString(6, tglSurat);
                    ps.setString(7, perihalSurat);
                    ps.setString(8, kodeSurat);
                    ps.setString(9, jenisSurat);
                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(getContentPane(), "Data berhasil disimpan");
                        clearInputSurat();
                    } else {
                        JOptionPane.showMessageDialog(getContentPane(), "Data gagal disimpan");
                    }
                } else {
                    ps.setString(1, noSurat);
                    ps.setString(2, pengirim);
                    ps.setString(3, penerima);
                    ps.setString(4, "");
                    ps.setString(5, tgl);
                    ps.setString(6, tglSurat);
                    ps.setString(7, perihalSurat);
                    ps.setString(8, kodeSurat);
                    ps.setString(9, jenisSurat);
                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(getContentPane(), "Data berhasil disimpan");
                        clearInputSurat();
                    } else {
                        JOptionPane.showMessageDialog(getContentPane(), "Data gagal disimpan");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Masuk.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnBatalkanSurat) {
            clearInputSurat();
        }
        
        else if (e.getSource() == navSuratMasuk) {
            String judul = "Lihat Surat Masuk";
            setPanelMain(paContent, pacLihatSurat, judul);
            checkPanelActive(pacLihatSurat, navSuratMasuk, navTambahSurat, navSuratKeluar);
            setScrollMain(pacLihatSurat, jscrollpaneMasuk, judul);
            showTableDataMasuk();
        } else if (e.getSource() == navSuratKeluar) {
            String judul = "Lihat Surat Keluar";
            setPanelMain(paContent, pacLihatSurat, "Lihat Surat Keluar");
            checkPanelActive(pacLihatSurat, navSuratKeluar, navTambahSurat, navSuratMasuk);
            setScrollMain(pacLihatSurat, jscrollpaneKeluar, judul);
            showTableDataKeluar();
        }
    }
    
    private void KodeSurat() {
        cKodeSurat.addItem("1 (Surat Masuk)");
        cKodeSurat.addItem("2 (Surat Keluar)");
        cKodeSurat.setSelectedIndex(-1);
    }

    private void JenisSurat1() {
        cJenisSurat.addItem("Resmi");
        cJenisSurat.addItem("Umum");
        cJenisSurat.addItem("Pribadi");
        cJenisSurat.setSelectedIndex(-1);
    }
    
    private void showTableDataMasuk() {
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `data_arsip` WHERE `kode_surat` = '1 (Surat Masuk)'";
        
        try {
            ps = Koneksi.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            // SHOW TABLE
            DefaultTableModel tbModel = (DefaultTableModel) tableMasuk.getModel();
            tbModel.setRowCount(0);
            while (rs.next()) {
                Object o[] = {
                    rs.getString("nomor_surat"),
                    rs.getString("pengirim"),
                    rs.getString("penerima"),
                    rs.getString("tgl_masuk"),
                    rs.getString("tgl_surat"),
                    rs.getString("perihal_surat"),
                    rs.getString("jenis_surat")
                };
                tbModel.addRow(o);
                
//                tableMasuk.getTableHeader().setBackground(new Color(0,77,64));
//                tableMasuk.getTableHeader().setForeground(new Color(0,77,64));
//                tableMasuk.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
//                tableMasuk.setFont(new Font("Tahoma", Font.PLAIN, 12));
//                ((DefaultTableCellRenderer)tableMasuk.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
//                tableMasuk.setRowHeight(25);

                tableMasuk.getTableHeader().setDefaultRenderer(new HeaderColor());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showTableDataKeluar() {
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `data_arsip` WHERE `kode_surat` = '2 (Surat Keluar)'";
        
        try {
            ps = Koneksi.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            // SHOW TABLE
            DefaultTableModel tbModel = (DefaultTableModel) tableKeluar.getModel();
            tbModel.setRowCount(0);
            
            while (rs.next()) {
                Object o[] = {
                    rs.getString("nomor_surat"),
                    rs.getString("pengirim"),
                    rs.getString("penerima"),
                    rs.getString("tgl_keluar"),
                    rs.getString("tgl_surat"),
                    rs.getString("perihal_surat"),
                    rs.getString("jenis_surat")
                };
                tbModel.addRow(o);
                
                tableKeluar.getTableHeader().setDefaultRenderer(new HeaderColor());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void genFieldInput(boolean check) {
        inNomorSurat.setEnabled(check);
        inPengirim.setEnabled(check);
        inPenerima.setEnabled(check);
        inTglBuat.setEnabled(check);
        inTglSurat.setEnabled(check);
        cJenisSurat.setEnabled(check);
        inPerihalSurat.setEnabled(check);
        btnSimpanSurat.setEnabled(check);
        btnBatalkanSurat.setEnabled(check);
    }
    
    static public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable mytable, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(mytable, value, selected, focused, row, column);
            setBackground(new Color(0,150,136));
            setForeground(Color.WHITE);
            setFont(new Font("Tahoma", Font.BOLD, 12));
            setHorizontalAlignment(JLabel.CENTER);
            return this;
        }
        
    }
}
