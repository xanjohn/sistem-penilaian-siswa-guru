/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormNilai.java
 *
 * Created on Jul 28, 2022, 10:25:48 AM
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
public class FormNilai extends javax.swing.JFrame {
    //Deklarasi Variabel
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;

    /** Creates new form frm_mhs */
        
        

    /** Creates new form FormNilai */
    public FormNilai()
    {
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_nilai.setModel(tableModel);
        ComboBoxNama();
        ComboBoxMataKuliah();
        settableload();
        angkatan.setText("2021");
        
    }
    String data[] = new String[15];
    private void settableload()
    {
        String stat = "";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT mhs.nama, mk.nama_mk, n.absensi, n.tgs1, "
                    + "n.tgs2, n.tgs3, n.uts, n.uas, n.nilai_absensi,"
                    + "n.nilai_tugas,n.nilai_uts, n.nilai_uas, n.nilai_akhir, "
                    + "n.indeks, n.keterangan "
                    + "FROM mahasiswa mhs "
                    + "INNER JOIN nilai n "
                    + "INNER JOIN mata_kuliah mk "
                    + "ON mhs.nim = n.nim AND mk.kode_mk = n.kode_mk";
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
        nim.setText("");
        kehadiran.setText("");
        tugas1.setText("");
        tugas2.setText("");
        tugas3.setText("");
        kodemk.setText("");
        uts.setText("");
        uas.setText("");
        angkatan.setText("");
    }
    //Membuat Nama di Database Muncul Pada ComboBox
    public void ComboBoxNama(){
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM mahasiswa";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                box_nama.addItem(res.getString("nama"));
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
    public void ComboBoxMataKuliah(){
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
        nim.setEnabled(false);
        kehadiran.setEnabled(false);
        tugas1.setEnabled(false);
        tugas2.setEnabled(false);
        tugas3.setEnabled(false);
        kodemk.setEnabled(false);
        uts.setEnabled(false);
        uas.setEnabled(false);
        angkatan.setEnabled(false);
    }        
    public void aktif_teks()
    {
        nim.setEnabled(true);
        kehadiran.setEnabled(true);
        tugas1.setEnabled(true);
        tugas2.setEnabled(true);
        tugas3.setEnabled(true);
        kodemk.setEnabled(true);
        uts.setEnabled(true);
        uas.setEnabled(true);
        angkatan.setEnabled(true);
    }
    int row = 0;
    public void tampil_field()
    {
        row = tabel_nilai.getSelectedRow();
        kehadiran.setText(tableModel.getValueAt(row, 2).toString());
        tugas1.setText(tableModel.getValueAt(row, 3).toString());
        tugas2.setText(tableModel.getValueAt(row, 4).toString());
        tugas3.setText(tableModel.getValueAt(row, 5).toString());
        uts.setText(tableModel.getValueAt(row, 6).toString());
        uas.setText(tableModel.getValueAt(row, 7).toString());
        simpan.setEnabled(false);
        ubah.setEnabled(true);
        hapus.setEnabled(true);
        batal.setEnabled(false);
        aktif_teks();
                
    }
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()
    {
     //Membuat Judul Header
     return new javax.swing.table.DefaultTableModel
     (
        new Object [][] {},
        new String [] {"Nama",
                       "Nama MK",
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
                       "Index",
                       "Keterangan",
                       }
     )
     //Disabel Perubahan Pada Grid
     {
         boolean[] canEdit = new boolean[]
         {
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false
         };
         
         public boolean isCellEditable(int rowIndex, int columnIndex)
         {
           return canEdit[columnIndex];
         }
      };
    //Update ComboBox
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cari_data = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        box_nama = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        nim = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        kehadiran = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tugas1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tugas2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tugas3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        box_matkul = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        kodemk = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        uts = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        uas = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        angkatan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_nilai = new javax.swing.JTable();
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
        jLabel1.setText("FORM NILAI MAHASISWA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(jLabel1)
                .addContainerGap(427, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true), 2), "Pencarian Data"));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel2.setText("MASUKKAN DATA (NIM)");

        cari.setText("CARI");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(cari_data, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192)
                .addComponent(cari)
                .addContainerGap(381, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cari_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel3.setText("NAMA");

        box_nama.setToolTipText("");
        box_nama.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                box_namaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        box_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_namaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel4.setText("NIM");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel5.setText("KEHADIRAN");

        kehadiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kehadiranActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel6.setText("PERTEMUAN");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel7.setText("TUGAS 1");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel8.setText("TUGAS 2");

        tugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tugas2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel9.setText("TUGAS 3");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel10.setText("NAMA MATA KULIAH");

        box_matkul.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                box_matkulPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel11.setText("KODE MK");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel12.setText("UTS");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel13.setText("UAS");

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel14.setText("ANGKATAN");

        tabel_nilai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "Nama M.K", "Absensi", "Tgs 1", "Tgs 2", "Tgs 3", "UTS", "UAS", "Nilai Absen", "Nilai Tugas", "Nilai UTS", "Nilai UAS", "Nilai Akhir", "Indeks", "Keterangan"
            }
        ));
        tabel_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_nilai);

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
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(box_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nim, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tugas2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(tugas1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(kehadiran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(tugas3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addComponent(jLabel6)
                                .addGap(316, 316, 316)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kodemk, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(box_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(uts, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(uas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                                    .addComponent(angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel13))))
                .addGap(89, 89, 89))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(keluar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(box_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(kodemk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel6))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(box_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ubah)
                    .addComponent(hapus)
                    .addComponent(simpan)
                    .addComponent(batal)
                    .addComponent(keluar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    keluar.setEnabled(true);
    
}//GEN-LAST:event_batalActionPerformed

private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
// TODO add your handling code here:
    FormUtama utama = new FormUtama();
    utama.setVisible(true);
    this.setVisible(false);
}//GEN-LAST:event_keluarActionPerformed

private void box_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_namaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_box_namaActionPerformed

private void kehadiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kehadiranActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_kehadiranActionPerformed

private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    nim.requestFocus();
    tambah.setEnabled(false);
    simpan.setEnabled(true);
    ubah.setEnabled(false);
    hapus.setEnabled(false);
    keluar.setEnabled(false);
    aktif_teks();
}//GEN-LAST:event_tambahActionPerformed

private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
// TODO add your handling code here:
    String data [] = new String[16];
    String s_nim = nim.getText();
    String s_nama = box_nama.getSelectedItem().toString();
    double s_kehadiran = Integer.parseInt(kehadiran.getText());
    if(s_kehadiran > 14)
    {
        JOptionPane.showMessageDialog(this, "Kehadiran Tidak Boleh Lebih dari 14"
                       + " Silahkan Isi Kembali ");
        kehadiran.setText("");
    }
    int s_tgs1 = Integer.valueOf(tugas1.getText());
    int s_tgs2 = Integer.valueOf(tugas2.getText());
    int s_tgs3 = Integer.valueOf(tugas3.getText());
    String s_matkul = box_matkul.getSelectedItem().toString();
    String s_kodeMK = kodemk.getText();
    double s_uts = Double.valueOf(uts.getText());
    double s_uas = Double.valueOf(uas.getText());
    
    double n_absen = (((s_kehadiran/14)*100*5)/100);
    double n_tugas = ((s_tgs1 + + s_tgs2 + s_tgs3)/3)*(25/100);
    double n_uts = s_uts * 0.3;
    double n_uas = s_uas * 0.4;
    double n_akhir = n_tugas + n_uts + n_uas ;
    
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
    if ((kehadiran.getText().isEmpty()) || (tugas1.getText().isEmpty()) ||
            (tugas2.getText().isEmpty()) || (tugas3.getText().isEmpty()) ||
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
           Connection kon = DriverManager.getConnection(database,user,pass);
           Statement stt = kon.createStatement();
           String SQL = "UPDATE nilai "+ 
                   "SET absensi = '"+s_kehadiran+"',"
                   + " tgs1 = '"+s_tgs1+"',"
                   + " tgs2 = '"+s_tgs2+"',"
                   + " tgs3 = '"+s_tgs3+"',"
                   + " uts = '"+s_uts+"',"
                   + " uas = '"+s_uas+"',"
                   + " nilai_absensi = '"+n_absen+"',"
                   + " nilai_tugas = '"+n_tugas+"',"
                   + " nilai_uts = '"+n_uts+"',"
                   + " nilai_uas = '"+n_uas+"',"
                   + " nilai_akhir = '"+n_akhir+"',"
                   + " indeks = '"+indeks+"',"
                   + " keterangan = '"+ket+"'"
                   + " WHERE nim = '"+nim.getText()+"'";
           stt.executeUpdate(SQL);
            data[0] = s_nama;
            data[1] = s_matkul;
            data[2] = Double.toString(s_kehadiran);
            data[3] = Double.toString(s_tgs1);
            data[4] = Double.toString(s_tgs2);
            data[5] = Double.toString(s_tgs3);
            data[6] = Double.toString(s_uts);
            data[7] = Double.toString(s_uas);
            data[8] = Double.toString(n_absen);
            data[9] = Double.toString(n_tugas);
            data[10] = Double.toString(n_uts);
            data[11] = Double.toString(n_uas);
            data[12] = Double.toString(n_akhir);
            data[13] = indeks;
            data[14] = ket;
           tableModel.removeRow(row);
           tableModel.insertRow(row, data);
           JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
           stt.close();
           kon.close();
           membersihkan_teks();
           simpan.setEnabled(false);
           nonaktif_text();
       }
       catch (Exception ex)
       {
         System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);;
       }
   }
    
}//GEN-LAST:event_ubahActionPerformed

private void box_namaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_box_namaPopupMenuWillBecomeInvisible
// TODO add your handling code here:
    //Membuat Nim muncul ketika Combo Box dipilih
    String item = (String)box_nama.getSelectedItem();
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "SELECT mahasiswa.nim from mahasiswa where nama = '"+item+"'";
        ResultSet res = stt.executeQuery(SQL);
        if(res.next())
        {
            String txt_nim = res.getString("nim");
            nim.setText(txt_nim);
        }
    }
    catch (Exception ex)
    {
        System.err.println(ex.getMessage());
        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
        JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
      
}//GEN-LAST:event_box_namaPopupMenuWillBecomeInvisible

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
            kodemk.setText(txt_kodeMK);
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

private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
// TODO add your handling code here:
    //Menghitung dan mendeklarasikan variabel
    String data [] = new String[16];
    String s_nim = nim.getText();
    String s_nama = box_nama.getSelectedItem().toString();
    double s_kehadiran = Double.parseDouble(kehadiran.getText());
    if(s_kehadiran > 14)
    {
        JOptionPane.showMessageDialog(this, "Kehadiran Tidak Boleh Lebih dari 14"
                       + " Silahkan Isi Kembali ");
        kehadiran.setText("");
    }
    int s_tgs1 = Integer.valueOf(tugas1.getText());
    int s_tgs2 = Integer.valueOf(tugas2.getText());
    int s_tgs3 = Integer.valueOf(tugas3.getText());
    String s_matkul = box_matkul.getSelectedItem().toString();
    String s_kodeMK = kodemk.getText();
    double s_uts = Double.valueOf(uts.getText());
    double s_uas = Double.valueOf(uas.getText());
    
    double n_absen = (((s_kehadiran/14)*100*5)/100);
    double n_tugas = ((s_tgs1 + s_tgs2 + s_tgs3)/3)*0.25;
    double n_uts = s_uts * 0.3;
    double n_uas = s_uas * 0.4;
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
    if ((kehadiran.getText().isEmpty()) || (tugas1.getText().isEmpty()) ||
            (tugas2.getText().isEmpty()) || (tugas3.getText().isEmpty()) ||
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
            String    SQL = "INSERT INTO nilai(nim, kode_mk, absensi, tgs1,"
                    + "tgs2, tgs3, uts, uas, nilai_absensi, nilai_tugas,"
                    + "nilai_uts, nilai_uas, nilai_akhir, indeks, keterangan) "
                    + "VALUES("
                    + "'"+s_nim+"',"
                    + "'"+s_kodeMK+"',"
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
            data[0] = s_nama;
            data[1] = s_matkul;
            data[2] = Double.toString(s_kehadiran);
            data[3] = Double.toString(s_tgs1);
            data[4] = Double.toString(s_tgs2);
            data[5] = Double.toString(s_tgs3);
            data[6] = Double.toString(s_uts);
            data[7] = Double.toString(s_uas);
            data[8] = Double.toString(n_absen);
            data[9] = Double.toString(n_tugas);
            data[10] = Double.toString(n_uts);
            data[11] = Double.toString(n_uas);
            data[12] = Double.toString(n_akhir);
            data[13] = indeks;
            data[14] = ket;
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
    int row = tabel_nilai.getSelectedRow();
    String cell = tabel_nilai.getModel().getValueAt(row,0).toString();
    try
    {
           Class.forName(driver);
           Connection kon = DriverManager.getConnection(database,user,pass);
           Statement stt = kon.createStatement();
           String SQL = "Delete from nilai "                     
                   + "WHERE "
                   + " nim ='"+nim.getText()+"'";
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

private void tabel_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilaiMouseClicked
// TODO add your handling code here:
    int row = tabel_nilai.getSelectedRow();
    String selection = tabel_nilai.getModel().getValueAt(row, 0).toString();
    String selection2 = tabel_nilai.getModel().getValueAt(row, 1).toString();
    
    String SQL = "select * from mahasiswa, mata_kuliah where nama = '"+selection+"'"
            + "and nama_mk = '"+selection2+"'";
    try
    {
           Class.forName(driver);
           Connection kon = DriverManager.getConnection(database,user,pass);
           Statement stt = kon.createStatement();
           ResultSet res = stt.executeQuery(SQL);
           if(res.next())
           {
               box_nama.setSelectedItem(res.getString("nama"));
               nim.setText(res.getString("nim"));
               box_matkul.setSelectedItem(res.getString("nama_mk"));
               kodemk.setText(res.getString("kode_mk"));
               row = tabel_nilai.getSelectedRow();
               kehadiran.setText(tableModel.getValueAt(row, 2).toString());
               tugas1.setText(tableModel.getValueAt(row, 3).toString());
               tugas2.setText(tableModel.getValueAt(row, 4).toString());
               tugas3.setText(tableModel.getValueAt(row, 5).toString());
               uts.setText(tableModel.getValueAt(row, 6).toString());
               uas.setText(tableModel.getValueAt(row, 7).toString());
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
}//GEN-LAST:event_tabel_nilaiMouseClicked

private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
// TODO add your handling code here:
    //Gunakan Querry untuk mencari
    String cari = cari_data.getText();
    if (cari.isEmpty())
    {
        JOptionPane.showMessageDialog(this,"Data Tidak Boleh Kosong");
    }
    else
    {
    tableModel.setRowCount(0);    
    try
    {
     Class.forName(driver);
     Connection kon = DriverManager.getConnection(database,user,pass);
     Statement stt = kon.createStatement();
     String SQL = "SELECT mhs.nama, mk.nama_mk, n.absensi, n.tgs1, "
                    + "n.tgs2, n.tgs3, n.uts, n.uas, n.nilai_absensi,"
                    + "n.nilai_tugas,n.nilai_uts, n.nilai_uas, n.nilai_akhir, "
                    + "n.indeks, n.keterangan "
                    + "FROM mahasiswa mhs "
                    + "INNER JOIN nilai n "
                    + "INNER JOIN mata_kuliah mk "
                    + "ON mhs.nim = n.nim AND mk.kode_mk = n.kode_mk "
                    + "Where mhs.nim = '"+cari+"'";
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
}//GEN-LAST:event_cariActionPerformed

private void tugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tugas2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tugas2ActionPerformed

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
            java.util.logging.Logger.getLogger(FormNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormNilai().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField angkatan;
    private javax.swing.JButton batal;
    private javax.swing.JComboBox box_matkul;
    private javax.swing.JComboBox box_nama;
    private javax.swing.JButton cari;
    private javax.swing.JTextField cari_data;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kehadiran;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField kodemk;
    private javax.swing.JTextField nim;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabel_nilai;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField tugas1;
    private javax.swing.JTextField tugas2;
    private javax.swing.JTextField tugas3;
    private javax.swing.JTextField uas;
    private javax.swing.JButton ubah;
    private javax.swing.JTextField uts;
    // End of variables declaration//GEN-END:variables
}
