package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class Dashboard extends javax.swing.JFrame {
    
    private final String paramNama, welcome;
    int mouseX, mouseY, pengguna, sMasuk, sKeluar;
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
        paddingButton();
        setTitle("Beranda");
        getRandomTotal();
        setRandomTotal();
        setChart();
        ctPengguna = ppPengguna.getBackground();
        ctMasuk = ppSMasuk.getBackground();
        ctKeluar = ppSKeluar.getBackground();
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
    
    public int nomorRandom(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        
        return result;
    }
    
    public void setRandomTotal() {
        totalPengguna.setText(String.valueOf(pengguna));
        totalSuratMasuk.setText(String.valueOf(sMasuk));
        totalSuratKeluar.setText(String.valueOf(sKeluar));
    }
    
    public void getRandomTotal() {
        pengguna = nomorRandom(1, 1000);
        sMasuk = nomorRandom(1, 1000);
        sKeluar = nomorRandom(1, 1000);
    }
    
    public void setChart() {
        
        String judul = "Statistik";
        
        // BAR CHART
        /*
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         
            dataset.setValue(pengguna, "Total User", "Pengguna");
            dataset.setValue(sMasuk, "Surat Masuk", "Surat Masuk");
            dataset.setValue(sKeluar, "Surat Keluar", "Surat Keluar");

            JFreeChart chart = ChartFactory.createBarChart(judul, "Keterangan", "Total", dataset);
        
            CategoryPlot cPlot = (CategoryPlot)chart.getPlot();
            ((BarRenderer)cPlot.getRenderer()).setBarPainter(new StandardBarPainter()); // set bar chart color

            BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
            r.setSeriesPaint(0, new java.awt.Color(66,165,245));
            r.setSeriesPaint(1, new java.awt.Color(239,154,154));
            r.setSeriesPaint(2, new java.awt.Color(255,167,38));
        */
        
        // PIE CHART
        
            DefaultPieDataset dataset = new DefaultPieDataset();

            dataset.setValue("Total Pengguna : " + pengguna, pengguna);
            dataset.setValue("Surat Masuk : " + sMasuk, sMasuk);
            dataset.setValue("Surat Keluar : " + sKeluar, sKeluar);

            JFreeChart chart = ChartFactory.createPieChart(judul, dataset, true, true, false );
            
            PiePlot cPlot = (PiePlot)chart.getPlot();
            cPlot.setSectionPaint(0, new java.awt.Color(244, 143, 177));
            cPlot.setSectionPaint(1, new java.awt.Color(255, 204, 128));
            cPlot.setSectionPaint(2, new java.awt.Color(197, 225, 165));
            
        cPlot.setBackgroundPaint(SystemColor.inactiveCaption); // change background color
        ppStatistik.setLayout(new java.awt.BorderLayout());
        ChartPanel CP = new ChartPanel(chart);
        ppStatistik.add(CP, BorderLayout.CENTER);
        ppStatistik.validate();
        
        
    }
    
    public void setPaddingButton(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    }
    
    public void paddingButton() {
        setPaddingButton(btnBeranda);
        setPaddingButton(btnSuratMasuk);
        setPaddingButton(btnKeluar);
        setPaddingButton(btnSuratKeluar);
        setPaddingButton(btnRekap);
        setPaddingButton(btnInstansi);
    }
    
    public void enteredBtn(JButton button) {
        button.setBackground(new java.awt.Color(0,150,136));
        button.setOpaque(true);
    }
    
    public void exitedBtn(JButton button) {
        button.setBackground(new java.awt.Color(0,77,64));
        button.setOpaque(true);
    }
    
    public void enteredPanel(JPanel panel) {
        panel.setBackground(new java.awt.Color(128, 203, 196));
        panel.setOpaque(true);
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
        btnBeranda = new javax.swing.JButton();
        btnSuratMasuk = new javax.swing.JButton();
        btnSuratKeluar = new javax.swing.JButton();
        btnRekap = new javax.swing.JButton();
        btnInstansi = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        panelMain = new javax.swing.JPanel();
        panelBeranda = new javax.swing.JPanel();
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
        panelSuratMasuk = new javax.swing.JPanel();
        panelSuratKeluar = new javax.swing.JPanel();
        lblSuratKeluar = new javax.swing.JLabel();
        panelRekap = new javax.swing.JPanel();
        lblBeranda1 = new javax.swing.JLabel();
        panelInstansi = new javax.swing.JPanel();
        lblBeranda2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Surat Masuk");
        setAlwaysOnTop(true);
        setResizable(false);

        bodyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWelcome.setFont(new java.awt.Font("SF Pro Display", 0, 12)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg_welcome.png"))); // NOI18N
        lblWelcome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblWelcome.setIconTextGap(0);
        lblWelcome.setOpaque(true);
        bodyPanel.add(lblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 730, 30));

        panelMenu.setBackground(new java.awt.Color(0, 77, 64));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sp2.setBackground(new java.awt.Color(153, 153, 153));
        sp2.setForeground(new java.awt.Color(153, 153, 153));
        panelMenu.add(sp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 240, 5));

        lblDinas.setFont(new java.awt.Font("SF Pro Display", 0, 12)); // NOI18N
        lblDinas.setForeground(new java.awt.Color(255, 255, 255));
        lblDinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_small.png"))); // NOI18N
        lblDinas.setText("<html>\nSistem Informasi<br>\nAdministrasi Surat<br>\n<b>Dinas Lingkungan Hidup</b><br>\nKabupaten Bogor\n</html>");
        lblDinas.setIconTextGap(10);
        panelMenu.add(lblDinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 130));

        btnBeranda.setBackground(new java.awt.Color(0, 77, 64));
        btnBeranda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBeranda.setForeground(new java.awt.Color(255, 255, 255));
        btnBeranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mn_Speedometer_24px.png"))); // NOI18N
        btnBeranda.setText("Beranda");
        btnBeranda.setBorder(null);
        btnBeranda.setBorderPainted(false);
        btnBeranda.setContentAreaFilled(false);
        btnBeranda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBeranda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBeranda.setIconTextGap(30);
        btnBeranda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        btnBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionSideMenu(evt);
            }
        });
        panelMenu.add(btnBeranda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 250, 45));

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
        btnSuratMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        btnSuratMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionSideMenu(evt);
            }
        });
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
        btnSuratKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        btnSuratKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionSideMenu(evt);
            }
        });
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
        btnRekap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        btnRekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionSideMenu(evt);
            }
        });
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
        btnInstansi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        btnInstansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionSideMenu(evt);
            }
        });
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
        btnKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionSideMenu(evt);
            }
        });
        panelMenu.add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 505, 250, 45));

        bodyPanel.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 550));

        panelMain.setLayout(new java.awt.CardLayout());

        panelBeranda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ppPengguna.setBackground(new java.awt.Color(244, 143, 177));
        ppPengguna.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        ppPengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ppPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        ppPengguna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalPengguna.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalPengguna.setFont(new java.awt.Font("SF Pro Display", 1, 12)); // NOI18N
        lblTotalPengguna.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalPengguna.setText("TOTAL PENGGUNA");
        lblTotalPengguna.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppPengguna.add(lblTotalPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 118, 45));

        totalPengguna.setBackground(new java.awt.Color(255, 255, 255));
        totalPengguna.setFont(new java.awt.Font("SF Pro Display", 0, 24)); // NOI18N
        totalPengguna.setForeground(new java.awt.Color(255, 255, 255));
        totalPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalPengguna.setText("5");
        totalPengguna.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppPengguna.add(totalPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 118, 45));

        icPengguna.setForeground(new java.awt.Color(255, 255, 255));
        icPengguna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icPengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Person_48px_2.png"))); // NOI18N
        icPengguna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppPengguna.add(icPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 0, 118, 100));

        panelBeranda.add(ppPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 236, 100));

        ppSMasuk.setBackground(new java.awt.Color(255, 204, 128));
        ppSMasuk.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        ppSMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ppSMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        ppSMasuk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalSuratMasuk.setFont(new java.awt.Font("SF Pro Display", 1, 12)); // NOI18N
        lblTotalSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuratMasuk.setText("SURAT MASUK");
        lblTotalSuratMasuk.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSMasuk.add(lblTotalSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 118, 45));

        totalSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));
        totalSuratMasuk.setFont(new java.awt.Font("SF Pro Display", 0, 24)); // NOI18N
        totalSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        totalSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSuratMasuk.setText("5");
        totalSuratMasuk.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSMasuk.add(totalSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 118, 45));

        icSuratMasuk.setForeground(new java.awt.Color(255, 255, 255));
        icSuratMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icSuratMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Gmail_Login_48px.png"))); // NOI18N
        icSuratMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSMasuk.add(icSuratMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 0, 118, 100));

        panelBeranda.add(ppSMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 0, 236, 100));

        ppSKeluar.setBackground(new java.awt.Color(197, 225, 165));
        ppSKeluar.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        ppSKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ppSKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExited(evt);
            }
        });
        ppSKeluar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar.setFont(new java.awt.Font("SF Pro Display", 1, 12)); // NOI18N
        lblTotalSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuratKeluar.setText("SURAT KELUAR");
        lblTotalSuratKeluar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblTotalSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSKeluar.add(lblTotalSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 118, 45));

        totalSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar.setFont(new java.awt.Font("SF Pro Display", 0, 24)); // NOI18N
        totalSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        totalSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSuratKeluar.setText("5");
        totalSuratKeluar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        totalSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSKeluar.add(totalSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 118, 45));

        icSuratKeluar.setForeground(new java.awt.Color(255, 255, 255));
        icSuratKeluar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icSuratKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Feedback_48px.png"))); // NOI18N
        icSuratKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppSKeluar.add(icSuratKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 0, 118, 100));

        panelBeranda.add(ppSKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 0, 236, 100));

        javax.swing.GroupLayout ppStatistikLayout = new javax.swing.GroupLayout(ppStatistik);
        ppStatistik.setLayout(ppStatistikLayout);
        ppStatistikLayout.setHorizontalGroup(
            ppStatistikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        ppStatistikLayout.setVerticalGroup(
            ppStatistikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        panelBeranda.add(ppStatistik, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 710, 370));

        panelMain.add(panelBeranda, "card2");

        javax.swing.GroupLayout panelSuratMasukLayout = new javax.swing.GroupLayout(panelSuratMasuk);
        panelSuratMasuk.setLayout(panelSuratMasukLayout);
        panelSuratMasukLayout.setHorizontalGroup(
            panelSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        panelSuratMasukLayout.setVerticalGroup(
            panelSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        panelMain.add(panelSuratMasuk, "card3");

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

        lblBeranda2.setText("Instansi");

        javax.swing.GroupLayout panelInstansiLayout = new javax.swing.GroupLayout(panelInstansi);
        panelInstansi.setLayout(panelInstansiLayout);
        panelInstansiLayout.setHorizontalGroup(
            panelInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInstansiLayout.createSequentialGroup()
                .addGap(0, 346, Short.MAX_VALUE)
                .addComponent(lblBeranda2)
                .addGap(0, 346, Short.MAX_VALUE))
        );
        panelInstansiLayout.setVerticalGroup(
            panelInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInstansiLayout.createSequentialGroup()
                .addGap(0, 238, Short.MAX_VALUE)
                .addComponent(lblBeranda2)
                .addGap(0, 238, Short.MAX_VALUE))
        );

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

    private void btnEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntered
        if (evt.getSource() == btnBeranda) {
            enteredBtn(btnBeranda);
        } else if (evt.getSource() == btnSuratMasuk) {
            enteredBtn(btnSuratMasuk);
        } else if (evt.getSource() == btnSuratKeluar) {
            enteredBtn(btnSuratKeluar);
        } else if (evt.getSource() == btnRekap) {
            enteredBtn(btnRekap);
        } else if (evt.getSource() == btnInstansi) {
            enteredBtn(btnInstansi);
        } else if (evt.getSource() == btnKeluar) {
            enteredBtn(btnKeluar);
        } else if (evt.getSource() == ppPengguna) {
            enteredPanel(ppPengguna);
        } else if (evt.getSource() == ppSKeluar) {
            enteredPanel(ppSKeluar);
        } else if (evt.getSource() == ppSMasuk) {
            enteredPanel(ppSMasuk);
        }
    }//GEN-LAST:event_btnEntered

    private void btnExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExited
        if (evt.getSource() == btnBeranda) {
            exitedBtn(btnBeranda);
        } else if (evt.getSource() == btnSuratMasuk) {
            exitedBtn(btnSuratMasuk);
        } else if (evt.getSource() == btnSuratKeluar) {
            exitedBtn(btnSuratKeluar);
        } else if (evt.getSource() == btnRekap) {
            exitedBtn(btnRekap);
        } else if (evt.getSource() == btnInstansi) {
            exitedBtn(btnInstansi);
        } else if (evt.getSource() == btnKeluar) {
            exitedBtn(btnKeluar);
        } else if (evt.getSource() == ppPengguna) {
            exitedPanel(ppPengguna, ctPengguna);
        } else if (evt.getSource() == ppSKeluar) {
            exitedPanel(ppSKeluar, ctKeluar);
        } else if (evt.getSource() == ppSMasuk) {
            exitedPanel(ppSMasuk, ctMasuk);
        }
    }//GEN-LAST:event_btnExited

    private void btnActionSideMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionSideMenu
        if (evt.getSource() == btnBeranda) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelBeranda);
            panelMain.repaint();
            panelMain.revalidate();
            setTitle("Beranda");
            
        } else if (evt.getSource() == btnSuratMasuk) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelSuratMasuk);
            panelMain.repaint();
            panelMain.revalidate();
            setTitle("Surat Masuk");
            
        } else if (evt.getSource() == btnSuratKeluar) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelSuratKeluar);
            panelMain.repaint();
            panelMain.revalidate();
            setTitle("Surat Keluar");
            
        } else if (evt.getSource() == btnRekap) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelRekap);
            panelMain.repaint();
            panelMain.revalidate();
            
        } else if (evt.getSource() == btnInstansi) {
            
            // remove panel
            panelMain.removeAll();
            panelMain.repaint();
            panelMain.revalidate();
            
            // add panel
            panelMain.add(panelInstansi);
            panelMain.repaint();
            panelMain.revalidate();
            
        } else if (evt.getSource() == btnKeluar) {
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
    }//GEN-LAST:event_btnActionSideMenu

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
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnInstansi;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnRekap;
    private javax.swing.JButton btnSuratKeluar;
    private javax.swing.JButton btnSuratMasuk;
    private javax.swing.JLabel icPengguna;
    private javax.swing.JLabel icSuratKeluar;
    private javax.swing.JLabel icSuratMasuk;
    private javax.swing.JLabel lblBeranda1;
    private javax.swing.JLabel lblBeranda2;
    private javax.swing.JLabel lblDinas;
    private javax.swing.JLabel lblSuratKeluar;
    private javax.swing.JLabel lblTotalPengguna;
    private javax.swing.JLabel lblTotalSuratKeluar;
    private javax.swing.JLabel lblTotalSuratMasuk;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel panelBeranda;
    private javax.swing.JPanel panelInstansi;
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

}
