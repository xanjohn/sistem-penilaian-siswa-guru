/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormSimulasi.java
 *
 * Created on Jul 29, 2022, 8:42:10 PM
 */
package kemahasiswaan_deri_10121069;
import javax.swing.*;
//Fungsi Import Digunakan Untuk Sql
import java.sql.*;
//Fungsi Import Digunakan Untuk Tanggal
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Deri
 */
public class FormSimulasi extends javax.swing.JFrame {
    //Deklarasi Variabel
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;

    /** Creates new form FormSimulasi */
    public FormSimulasi() {
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        settableload();
        tabel_simulasi.setModel(tableModel);
        ComboBoxMK();
        
    }
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()
    {
     //Membuat Judul Header
     return new javax.swing.table.DefaultTableModel
     (
        new Object [][] {},
        new String [] {"Nama MK",
                       "Presentasi Absen",
                       "Presentasi Tugas",
                       "Presentasi UTS",
                       "Presentasi UAS",
                       "Absensi",
                       "Tgs 1",
                       "Tgs 2",
                       "Tgs 3",
                       "UTS",
                       "UAS",
                       "Nilai Absen",
                       "Nilai Tugas",
                       "Nilai UTS",
                       "Nilai UAS",
                       "Nilai Akhir",
                       "Indeks",
                       "Keterangan",
                       }
     )
     //Disabel Perubahan Pada Grid
     {
         boolean[] canEdit = new boolean[]
         {
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, 
         };
         
         public boolean isCellEditable(int rowIndex, int columnIndex)
         {
           return canEdit[columnIndex];
         }
      };
    }
    String data[] = new String[20];
    private void settableload()
    {
        String stat = "";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM simulasi_nilai";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                data[8] = res.getString(9);
                data[9] = res.getString(10);
                data[10] = res.getString(11);
                data[11] = res.getString(12);
                data[12] = res.getString(13);
                data[13] = res.getString(14);
                data[14] = res.getString(15);
                data[15] = res.getString(16);
                data[16] = res.getString(17);
                data[17] = res.getString(18);
                
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
           
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    public void membersihkan_teks()
    {
        kode_mk.setText("");
        p_absen.setText("");
        p_tugas.setText("");
        p_uts.setText("");
        p_uas.setText("");
        kehadiran.setText("");
        tgs1.setText("");
        tgs2.setText("");
        tgs3.setText("");
        uts.setText("");
        uas.setText("");
    }
    //Membuat Nama Mata Kuliah di Database Muncul Pada ComboBox
    public void ComboBoxMK(){
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM mata_kuliah";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                box_matkul.addItem(res.getString("nama_mk"));
            }
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
            JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void nonaktif_text()
    {
        kode_mk.setEnabled(false);
        p_absen.setEnabled(false);
        p_tugas.setEnabled(false);
        p_uts.setEnabled(false);
        p_uas.setEnabled(false);
        kehadiran.setEnabled(false);
        tgs1.setEnabled(false);
        tgs2.setEnabled(false);
        tgs3.setEnabled(false);
        uts.setEnabled(false);
        uas.setEnabled(false);
    }        
    public void aktif_teks()
    {
        kode_mk.setEnabled(true);
        p_absen.setEnabled(true);
        p_tugas.setEnabled(true);
        p_uts.setEnabled(true);
        p_uas.setEnabled(true);
        kehadiran.setEnabled(true);
        tgs1.setEnabled(true);
        tgs2.setEnabled(true);
        tgs3.setEnabled(true);
        uts.setEnabled(true);
        uas.setEnabled(true);
    }
    int row = 0;
    public void tampil_field()
    {
        row = tabel_simulasi.getSelectedRow();
        kehadiran.setText(tableModel.getValueAt(row, 2).toString());
        tgs1.setText(tableModel.getValueAt(row, 3).toString());
        tgs2.setText(tableModel.getValueAt(row, 4).toString());
        tgs3.setText(tableModel.getValueAt(row, 5).toString());
        uts.setText(tableModel.getValueAt(row, 6).toString());
        uas.setText(tableModel.getValueAt(row, 7).toString());
        simpan.setEnabled(false);
        ubah.setEnabled(true);
        hapus.setEnabled(true);
        batal.setEnabled(false);
        aktif_teks();
                
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        box_matkul = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        kode_mk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        p_absen = new javax.swing.JTextField();
        p_tugas = new javax.swing.JTextField();
        p_uts = new javax.swing.JTextField();
        p_uas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        kehadiran = new javax.swing.JTextField();
        tugas1 = new javax.swing.JTextField();
        tgs1 = new javax.swing.JTextField();
        tgs2 = new javax.swing.JTextField();
        tgs3 = new javax.swing.JTextField();
        uts = new javax.swing.JTextField();
        uas = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_simulasi = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        keluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(-8355712,true));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24));
        jLabel1.setForeground(new java.awt.Color(-1,true));
        jLabel1.setText("FORM SIMULASI NILAI AKHIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(jLabel1)
                .addContainerGap(556, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel2.setText("NAMA MATA KULIAH");

        box_matkul.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                box_matkulPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        box_matkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_matkulActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel3.setText("KODE MK");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel4.setText("PRESENTASE ABSEN");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel5.setText("PRESENTASE TUGAS");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel6.setText("PRESENTASE UTS");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel7.setText("PRESENTASE UAS");

        p_absen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_absenActionPerformed(evt);
            }
        });

        p_tugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_tugasActionPerformed(evt);
            }
        });

        p_uts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_utsActionPerformed(evt);
            }
        });

        p_uas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_uasActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel8.setText("%");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel9.setText("%");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel10.setText("%");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel11.setText("%");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel12.setText("KEHADIRAN");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel13.setText("TUGAS 1");

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel14.setText("TUGAS 2");

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel15.setText("TUGAS 3");

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel16.setText("UTS");

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel17.setText("UAS");

        kehadiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kehadiranActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel18.setText("PERTEMUAN");

        tabel_simulasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama MK", "Persentase Absen", "Persentase Tugas", "Persentase UTS", "Persentase UAS", "Absensi", "Tgs 1", "Tgs 2", "Tgs 3", "UTS", "UAS", "Nilai Absen", "Nilai Tugas", "Nilai UTS", "Nilai UAS", "Nilai Akhir", "Indeks", "Keterangan"
            }
        ));
        tabel_simulasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_simulasiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_simulasi);

        tambah.setFont(new java.awt.Font("SansSerif", 0, 18));
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        ubah.setFont(new java.awt.Font("SansSerif", 0, 18));
        ubah.setText("UBAH");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        hapus.setFont(new java.awt.Font("SansSerif", 0, 18));
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        simpan.setFont(new java.awt.Font("SansSerif", 0, 18));
        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        batal.setFont(new java.awt.Font("SansSerif", 0, 18));
        batal.setText("BATAL");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        keluar.setFont(new java.awt.Font("SansSerif", 0, 18));
        keluar.setText("KELUAR");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(p_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(p_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(p_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(p_absen, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(250, 250, 250)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(270, 270, 270)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel16))))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(uts, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tgs3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tgs2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tgs1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(kehadiran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel18)
                                        .addGap(339, 339, 339))
                                    .addComponent(uas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(box_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 831, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(box_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(p_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(p_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(p_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(tgs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tgs3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ubah)
                    .addComponent(tambah)
                    .addComponent(hapus)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(batal)
                        .addComponent(simpan))
                    .addComponent(keluar))
                .addGap(214, 214, 214))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void p_absenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_absenActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_p_absenActionPerformed

private void p_utsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_utsActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_p_utsActionPerformed

private void p_tugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_tugasActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_p_tugasActionPerformed

private void kehadiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kehadiranActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_kehadiranActionPerformed

private void box_matkulPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_box_matkulPopupMenuWillBecomeInvisible
// TODO add your handling code here:
    //Membuat Nama Kode Matkul Muncul Ketika Combo Box dipilih
        String item = (String)box_matkul.getSelectedItem();
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "SELECT mata_kuliah.kode_mk from mata_kuliah where nama_mk="
                + "'"+item+"'";
        ResultSet res = stt.executeQuery(SQL);
        if(res.next())
        {
            String txt_kodeMK = res.getString("kode_mk");
            kode_mk.setText(txt_kodeMK);
        }
    }
    catch (Exception ex)
    {
        System.err.println(ex.getMessage());
        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
        JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}//GEN-LAST:event_box_matkulPopupMenuWillBecomeInvisible

private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    simpan.setEnabled(true);
    ubah.setEnabled(false);
    hapus.setEnabled(false);
    keluar.setEnabled(false);
    aktif_teks();
    
}//GEN-LAST:event_tambahActionPerformed

private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    keluar.setEnabled(true);
}//GEN-LAST:event_batalActionPerformed

private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
// TODO add your handling code here:
    String data [] = new String[20];
    String s_namaMK = box_matkul.getSelectedItem().toString();
    int persentase_absen = Integer.parseInt(p_absen.getText());
    double persentase_tugas = Double.parseDouble(p_tugas.getText());
    int persentase_uts = Integer.parseInt(p_uts.getText());
    int persentase_uas = Integer.parseInt(p_uas.getText());
    
    double s_kehadiran = Double.parseDouble(kehadiran.getText());
    if(s_kehadiran > 14)
    {
        JOptionPane.showMessageDialog(this, "Kehadiran Tidak Boleh Lebih dari 14"
                       + " Silahkan Isi Kembali ");
        kehadiran.setText("");
    }
    double s_tgs1 = Double.parseDouble(tgs1.getText());
    double s_tgs2 = Double.parseDouble(tgs2.getText());
    double s_tgs3 = Double.parseDouble(tgs3.getText());
    double s_uts = Double.valueOf(uts.getText());
    double s_uas = Double.valueOf(uas.getText());
    
    double n_absen = (((s_kehadiran/14)*100*persentase_absen)/100);
    double n_tugas = ((s_tgs1 + s_tgs2 + s_tgs3)/3.0)*(persentase_tugas/100.0);
    double n_uts = s_uts * persentase_uts / 100;
    double n_uas = s_uas * persentase_uas / 100;
    double n_akhir = n_absen + n_tugas + n_uts + n_uas ;
    
    
    String indeks;
    if ((n_akhir >= 80)&&(n_akhir <= 100))
    {
        indeks = "A";
    }
    else if ((n_akhir >= 68)&&(n_akhir <= 79))
    {
        indeks = "B";
    }
    else if ((n_akhir >= 56)&&(n_akhir <= 67))
    {
        indeks = "C";
    }
    else if ((n_akhir >= 45)&&(n_akhir <= 55))
    {
        indeks = "D";
    }
    else 
    {
        indeks = "E";
    }
    String ket = null;
    if ((indeks == "A") || (indeks == "B") || (indeks == "C"))
    {
        ket = "LULUS";
    }
    else if ((indeks == "D") || (indeks == "E"))
    {
       ket = "TIDAK LULUS";      
    }
    else if (s_kehadiran < 11)
    {
        ket = "TIDAK LULUS";
    }

    
    //Memasukkan Data ke SQL
    if ((kehadiran.getText().isEmpty()) || (tgs1.getText().isEmpty()) ||
            (tgs2.getText().isEmpty()) || (tgs3.getText().isEmpty()) ||
            (uts.getText().isEmpty()) || (uas.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(this,
                "Data tidak boleh kosong, silahkan dilengkapi");
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(
                               database,
                               user,
                               pass);
            Statement stt = kon.createStatement();
            String    SQL = "INSERT INTO simulasi_nilai(nama_mk, p_absen, p_tugas,"
                    + "p_uts, p_uas, absensi, tgs1, tgs2, tgs3, uts, uas, "
                    + "n_absen, n_tugas, n_uts, n_uas, n_akhir, indeks, keterangan) "
                    + "VALUES("
                    + "'"+s_namaMK+"',"
                    + "'"+persentase_absen+"',"
                    + "'"+persentase_tugas+"',"
                    + "'"+persentase_uts+"',"
                    + "'"+persentase_uas+"',"
                    + "'"+s_kehadiran+"',"
                    + "'"+s_tgs1+"',"
                    + "'"+s_tgs2+"',"
                    + "'"+s_tgs3+"',"
                    + "'"+s_uts+"',"
                    + "'"+s_uas+"',"
                    + "'"+n_absen+"',"
                    + "'"+n_tugas+"',"
                    + "'"+n_uts+"',"
                    + "'"+n_uas+"',"
                    + "'"+n_akhir+"',"
                    + "'"+indeks+"',"
                    + "'"+ket+"')";
            stt.executeUpdate(SQL);
            data[0] = s_namaMK;
            data[1] = Integer.toString(persentase_absen);
            data[2] = Double.toString(persentase_tugas);
            data[3] = Integer.toString(persentase_uts);
            data[4] = Integer.toString(persentase_uas);
            data[5] = Double.toString(s_kehadiran);
            data[6] = Double.toString(s_tgs1);
            data[7] = Double.toString(s_tgs2);
            data[8] = Double.toString(s_tgs3);
            data[9] = Double.toString(s_uts);
            data[10] = Double.toString(s_uas);
            data[11] = Double.toString(n_absen);
            data[12] = Double.toString(n_tugas);
            data[13] = Double.toString(n_uts);
            data[14] = Double.toString(n_uas);
            data[15] = Double.toString(n_akhir);
            data[16] = indeks;
            data[17] = ket;
            tableModel.insertRow(0, data);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            stt.close();
            kon.close();
            membersihkan_teks();
            simpan.setEnabled(false);
            nonaktif_text();
            simpan.setEnabled(false);
            nonaktif_text();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}//GEN-LAST:event_simpanActionPerformed

private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
// TODO add your handling code here:
    int row = tabel_simulasi.getSelectedRow();
    String cell = tabel_simulasi.getModel().getValueAt(row,0).toString();
    try
    {
           Class.forName(driver);
           Connection kon = DriverManager.getConnection(database,user,pass);
           Statement stt = kon.createStatement();
           String SQL = "Delete from simulasi_nilai "                     
                   + "WHERE "
                   + " nama_mk ='"+box_matkul.getSelectedItem().toString()+"'";
           stt.executeUpdate(SQL);
           tableModel.removeRow(row);
           JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
           stt.close();
           kon.close();
           membersihkan_teks();
    }
    catch (Exception ex)
       {
         System.err.println(ex.getMessage());
       }
}//GEN-LAST:event_hapusActionPerformed

private void tabel_simulasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_simulasiMouseClicked
// TODO add your handling code here:
    int row = tabel_simulasi.getSelectedRow();
    String selection = tabel_simulasi.getModel().getValueAt(row, 0).toString();
    
    String SQL = "select * from mata_kuliah "
            + "where nama_mk = '"+selection+"'";
    try
    {
           Class.forName(driver);
           Connection kon = DriverManager.getConnection(database,user,pass);
           Statement stt = kon.createStatement();
           ResultSet res = stt.executeQuery(SQL);
           if(res.next())
           {
               box_matkul.setSelectedItem(res.getString("nama_mk"));
               kode_mk.setText(res.getString("kode_mk"));
               row = tabel_simulasi.getSelectedRow();
               p_absen.setText(tableModel.getValueAt(row, 1).toString());
               p_tugas.setText(tableModel.getValueAt(row, 2).toString());
               p_uts.setText(tableModel.getValueAt(row, 3).toString());
               p_uas.setText(tableModel.getValueAt(row, 4).toString());
               kehadiran.setText(tableModel.getValueAt(row, 5).toString());
               tgs1.setText(tableModel.getValueAt(row, 6).toString());
               tgs2.setText(tableModel.getValueAt(row, 7).toString());
               tgs3.setText(tableModel.getValueAt(row, 8).toString());
               uts.setText(tableModel.getValueAt(row, 9).toString());
               uas.setText(tableModel.getValueAt(row, 10).toString());
               simpan.setEnabled(false);
               ubah.setEnabled(true);
               hapus.setEnabled(true);
               batal.setEnabled(true);
               aktif_teks();
           }
    }
    catch (Exception ex)
    {
        
    }
}//GEN-LAST:event_tabel_simulasiMouseClicked

private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
// TODO add your handling code here:
    FormUtama utama = new FormUtama();
    utama.setVisible(true);
    this.setVisible(false);
}//GEN-LAST:event_keluarActionPerformed

private void p_uasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_uasActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_p_uasActionPerformed

private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
// TODO add your handling code here:
    
    String data [] = new String[20];
    String s_namaMK = box_matkul.getSelectedItem().toString();
    int persentase_absen = Integer.parseInt(p_absen.getText());
    double persentase_tugas = Double.parseDouble(p_tugas.getText());
    int persentase_uts = Integer.parseInt(p_uts.getText());
    int persentase_uas = Integer.parseInt(p_uas.getText());
    
    double s_kehadiran = Double.parseDouble(kehadiran.getText());
    if(s_kehadiran > 14)
    {
        JOptionPane.showMessageDialog(this, "Kehadiran Tidak Boleh Lebih dari 14"
                       + " Silahkan Isi Kembali ");
        kehadiran.setText("");
    }
    double s_tgs1 = Double.parseDouble(tgs1.getText());
    double s_tgs2 = Double.parseDouble(tgs2.getText());
    double s_tgs3 = Double.parseDouble(tgs3.getText());
    double s_uts = Double.valueOf(uts.getText());
    double s_uas = Double.valueOf(uas.getText());
    
    double n_absen = (((s_kehadiran/14)*100*persentase_absen)/100);
    double n_tugas = ((s_tgs1 + s_tgs2 + s_tgs3)/3.0)*(persentase_tugas/100.0);
    double n_uts = s_uts * persentase_uts / 100;
    double n_uas = s_uas * persentase_uas / 100;
    double n_akhir = n_absen + n_tugas + n_uts + n_uas ;
    
    
    String indeks;
    if ((n_akhir >= 80)&&(n_akhir <= 100))
    {
        indeks = "A";
    }
    else if ((n_akhir >= 68)&&(n_akhir <= 79))
    {
        indeks = "B";
    }
    else if ((n_akhir >= 56)&&(n_akhir <= 67))
    {
        indeks = "C";
    }
    else if ((n_akhir >= 45)&&(n_akhir <= 55))
    {
        indeks = "D";
    }
    else 
    {
        indeks = "E";
    }
    String ket = null;
    if ((indeks == "A") || (indeks == "B") || (indeks == "C"))
    {
        ket = "LULUS";
    }
    else if ((indeks == "D") || (indeks == "E"))
    {
       ket = "TIDAK LULUS";      
    }
    else if (s_kehadiran < 11)
    {
        ket = "TIDAK LULUS";
    }

    
    //Memasukkan Data ke SQL
    if ((kehadiran.getText().isEmpty()) || (tgs1.getText().isEmpty()) ||
            (tgs2.getText().isEmpty()) || (tgs3.getText().isEmpty()) ||
            (uts.getText().isEmpty()) || (uas.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(this,
                "Data tidak boleh kosong, silahkan dilengkapi");
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(
                               database,
                               user,
                               pass);
            Statement stt = kon.createStatement();
            String    SQL = "update simulasi_nilai set "
                    + "p_absen = '"+persentase_absen+"',"
                   + " p_tugas = '"+persentase_tugas+"',"
                   + " p_uts = '"+persentase_uts+"',"
                   + " p_uas = '"+persentase_uas+"',"
                   + " absensi = '"+s_kehadiran+"',"
                   + " tgs1 = '"+s_tgs1+"',"
                   + " tgs2 = '"+s_tgs2+"',"
                   + " tgs3 = '"+s_tgs3+"',"
                   + " uts = '"+s_uts+"',"
                   + " uas = '"+s_uas+"',"
                   + " n_absen = '"+n_absen+"',"
                   + " n_tugas = '"+n_tugas+"',"
                   + " n_uts = '"+n_uts+"',"
                   + " n_uas = '"+n_uas+"',"
                   + " n_akhir = '"+n_akhir+"',"
                   + " indeks = '"+indeks+"',"
                   + " keterangan = '"+ket+"' "
                   + " where nama_mk = '"+s_namaMK+"'";
            stt.executeUpdate(SQL);
            data[0] = s_namaMK;
            data[1] = Integer.toString(persentase_absen);
            data[2] = Double.toString(persentase_tugas);
            data[3] = Integer.toString(persentase_uts);
            data[4] = Integer.toString(persentase_uas);
            data[5] = Double.toString(s_kehadiran);
            data[6] = Double.toString(s_tgs1);
            data[7] = Double.toString(s_tgs2);
            data[8] = Double.toString(s_tgs3);
            data[9] = Double.toString(s_uts);
            data[10] = Double.toString(s_uas);
            data[11] = Double.toString(n_absen);
            data[12] = Double.toString(n_tugas);
            data[13] = Double.toString(n_uts);
            data[14] = Double.toString(n_uas);
            data[15] = Double.toString(n_akhir);
            data[16] = indeks;
            data[17] = ket;
            tableModel.removeRow(row);
            tableModel.insertRow(row, data);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            stt.close();
            kon.close();
            membersihkan_teks();
            simpan.setEnabled(false);
            nonaktif_text();
            simpan.setEnabled(false);
            nonaktif_text();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}//GEN-LAST:event_ubahActionPerformed

private void box_matkulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_matkulActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_box_matkulActionPerformed

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
            java.util.logging.Logger.getLogger(FormSimulasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSimulasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSimulasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSimulasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormSimulasi().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JComboBox box_matkul;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kehadiran;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField kode_mk;
    private javax.swing.JTextField p_absen;
    private javax.swing.JTextField p_tugas;
    private javax.swing.JTextField p_uas;
    private javax.swing.JTextField p_uts;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabel_simulasi;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField tgs1;
    private javax.swing.JTextField tgs2;
    private javax.swing.JTextField tgs3;
    private javax.swing.JTextField tugas1;
    private javax.swing.JTextField uas;
    private javax.swing.JButton ubah;
    private javax.swing.JTextField uts;
    // End of variables declaration//GEN-END:variables
}
