/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TUGAS_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Marshella Poa
 */
public class GUI_Student extends javax.swing.JFrame {
     String nama1, ttl1, nis1, agama1, jk1, alamat1, tahun1;
    /**
     * Creates new form GUI_Student
     */
    public GUI_Student() {
        initComponents();
        tampil();
        String nama1, ttl1, nis1, agama1, jk1, alamat1, tahun1;
//        //mengambil model data dari tabel dan menyimpan dalam object DefaultTableModel dataModel
//        DefaultTableModel dataModel = (DefaultTableModel) tabel.getModel();
//        //mendapatkan jmlh baris yg ada dalam model data saat ini
//        int rowCount = dataModel.getRowCount();
//        while (rowCount > 0) {
//            dataModel.removeRow(rowCount - 1);
//            rowCount = dataModel.getRowCount();
//        }
//        }
//    public void clear (){
//        txt_nama.setText("");
//        txt_ttl.setText("");
//        txt_nis.setText("");
//        txt_agama.setText("");
//        txt_alamat.setText("");
//        txt_tahun.setText("");
//        radiobtnlaki.setText("");
//        radiobtnperempuan.setText("");
//    }
    }
    public Connection conn;
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/OOP_2118112?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Student.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Student.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Student.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("Nama");
        tabelhead.addColumn("Tanggal Lahir");
        tabelhead.addColumn("NIS");
        tabelhead.addColumn("Agama");
        tabelhead.addColumn("Jenis Kelamin");
        tabelhead.addColumn("Alamat Rumah");
         tabelhead.addColumn("Tahun Masuk");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_mahasiswa";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7),});
            }
            tabel.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    public void refresh() {
        new GUI_Student().setVisible(true);
        this.setVisible(false);
    }
    public void insert() {
        String Nama = txt_nama.getText();
        String TanggaLahir = txt_ttl.getText();
        String jk;
        if (radiobtnlaki.isSelected()) {
            jk = "L";
        } else {
            jk = "P";
        }
        String NIS = txt_nis.getText();
        String Agama = txt_agama.getText();
        String AlamatRumah = txt_alamat.getText();
        String TahunMasuk = txt_tahun.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_mahasiswa (nim, nama,jk, prodi, th_angkatan,alamat)"
                    + "VALUES('" + Nama + "','" + TanggaLahir + "','" + jk + "','" + Agama + "','" + AlamatRumah + "','" + TahunMasuk + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Mahasiswa!" + "\n" + AlamatRumah);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
public void update() {
        String Nama = txt_nama.getText();
        String TanggalLahir = txt_ttl.getText();
        String jk;
        if (radiobtnlaki.isSelected()) {
            jk = "L";
        } else {
            jk = "P";
        }
        String NIS = txt_nis.getText();
        String Agama = txt_agama.getText();
        String AlamatRumah = txt_alamat.getText();
        String TahunMasuk = txt_tahun.getText();
        String Nislama = nis1;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_mahasiswa SET nama='" + Nama + "'," + "tanggallahir='" + TanggalLahir + "',"
                    + "jk='" + jk + "'" + ",nis='" + NIS + ",agama='" + Agama + "',alamat='" + AlamatRumah + "',tahunmasuk='"
                    + TahunMasuk + "' WHERE NIS = '" + Nislama + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Mahasiswa Berhasil!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_mahasiswa WHERE nis='" + txt_nis.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
               tampil();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }
public void cari() {
        try {
            try ( Statement statement = conn.createStatement()) {
                String sql = "SELECT * FROM tb_mahasiswa WHERE `nim`  LIKE '%" + cari.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    txt_nama.setText(rs.getString(2));
                    txt_ttl.setText(rs.getString(3));
                    String jk = rs.getString(4);
                    if (jk.equalsIgnoreCase("L")) {
                        radiobtnlaki.setSelected(true);
                    } else {
                        radiobtnperempuan.setSelected(true);
                    }
                    txt_nis.setText(rs.getString(4));
                    txt_agama.setText(rs.getString(5));
                    txt_alamat.setText(rs.getString(6));
                    txt_tahun.setText(rs.getString(7));
                } else {
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }
public void itempilih() {
        txt_nama.setText(nama1);
        txt_nis.setText(nis1);
        txt_ttl.setText(ttl1);
        txt_agama.setText(agama1);
        txt_alamat.setText(alamat1);
        txt_tahun.setText(tahun1);
        if (jk1.equalsIgnoreCase("L")) {
            radiobtnlaki.setSelected(true);
        } else {
          radiobtnperempuan.setSelected(true);

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

        btnGroupJk = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_ttl = new javax.swing.JTextField();
        txt_nis = new javax.swing.JTextField();
        txt_agama = new javax.swing.JTextField();
        radiobtnlaki = new javax.swing.JRadioButton();
        radiobtnperempuan = new javax.swing.JRadioButton();
        txt_alamat = new javax.swing.JTextField();
        txt_tahun = new javax.swing.JTextField();
        btn_cetak = new javax.swing.JButton();
        btn_close = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        btn_hps = new javax.swing.JButton();
        btn_btl = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        cari = new javax.swing.JTextField();
        btn_form = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("DATA SISWA");

        jLabel2.setText("Nama");

        jLabel3.setText("Tanggal Lahir");

        jLabel4.setText("NIS");

        jLabel5.setText("Agama");

        jLabel6.setText("Jenis Kelamin");

        jLabel7.setText("Alamat Rumah");

        jLabel8.setText("Tahun Masuk");

        btnGroupJk.add(radiobtnlaki);
        radiobtnlaki.setText("Laki-Laki");

        btnGroupJk.add(radiobtnperempuan);
        radiobtnperempuan.setText("Perempuan");

        txt_tahun.setEditable(false);

        btn_cetak.setText("Cetak");
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });

        btn_close.setText("Tutup");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "Tanggal Lahir", "NIS", "Alamat Rumah", "Agama", "Jenis Kelamin", "Tahun Masuk"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel);

        btn_hps.setText("Hapus");
        btn_hps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hpsActionPerformed(evt);
            }
        });

        btn_btl.setText("Batal");
        btn_btl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_btlActionPerformed(evt);
            }
        });

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        cari.setText(" ");

        btn_form.setText("Form Nilai");
        btn_form.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_formActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_alamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(txt_agama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nis, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ttl, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tahun)
                    .addComponent(radiobtnperempuan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(radiobtnlaki, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btn_hps, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btn_form)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_btl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(267, 267, 267)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_nis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_agama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(radiobtnlaki))
                        .addGap(4, 4, 4)
                        .addComponent(radiobtnperempuan)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_cari)
                                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cetak)
                            .addComponent(btn_hps)
                            .addComponent(btn_btl)
                            .addComponent(btn_close)
                            .addComponent(btn_form))))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
// TODO add your handling code here:
   insert();

// Menampilkan pesan dialog bahwa data telah ditambahkan ke tabelJOptionPane.showMessageDialog(null, "Data anda Ditambahkan Ke tabel");
 // Mengambil model data dari tabel
// DefaultTableModel dataModel = (DefaultTableModel) 
// tabel.getModel();
// // Inisialisasi sebuah ArrayList bernama 'list'
// List list = new ArrayList<>();
// // Mengatur tabel untuk membuat kolom dari model secara otomatis
// 
// tabel.setAutoCreateColumnsFromModel(true);
// // Membuat instance dari kelas Mahasiswa
// Student stdnt = new Student();
// // Mengatur data NIM menggunakan nilai dari komponen txtNim
// stdnt.setNama(txt_nama.getText());
// // Mengatur data Nama menggunakan nilai dari komponen txtNama
//  stdnt.setTanggalLahir(txt_ttl.getText());
// // Mengatur jenis kelamin sesuai dengan radio button yang dipilih
// String JenKel = "";
// if (radiobtnlaki.isSelected()) {
// stdnt.setJenisKelamin(radiobtnlaki.getText());
// } else {
//  stdnt.setJenisKelamin(radiobtnperempuan.getText());
// }
// // Mengatur data Prodi menggunakan nilai dari komponen txtProdi
// stdnt.setNIS(txt_nis.getText());
// // Mengatur data Angkatan menggunakan nilai dari komponen txtAngkatan
//  stdnt.setAgama(txt_agama.getText());
// // Mengatur data Alamat menggunakan nilai dari komponen txtAlamat
//  stdnt.setAlamatRumah(txt_alamat.getText());
// // tabel.("Tahun Masuk     : " + txt_tahun.getText()+ "\n");
// 
// // Menambahkan data-data dari objek Mahasiswa ke dalam ArrayList 'list'
// list.add( stdnt.getNama());
// list.add( stdnt.getTanggallahir());
// list.add( stdnt.getNIS());
// list.add( stdnt.getAgama());
// list.add( stdnt.getJenisKelamin());
// list.add( stdnt.getAlamatRumah());
// list.add(stdnt.TahunMasuk);
// // Menambahkan baris baru ke model tabel menggunakan data dari ArrayList 'list'
// dataModel.addRow(list.toArray());
// // Memanggil fungsi 'clear' untuk membersihkan nilai dari komponen
// clear();
//

// tabel.setText("");
// Student stdnt = new Student();
// stdnt.setNama(txt_nama.getText());
// stdnt.setTanggalLahir(txt_ttl.getText());
// stdnt.setNIS(txt_nis.getText());
// stdnt.setAgama(txt_agama.getText());
// String JenKel ="";
// if (radiobtnlaki.isSelected()){
// stdnt.setJenisKelamin(JenKel); 
// stdnt.setJenisKelamin(radiobtnlaki.getText());
// }else{
// stdnt.setJenisKelamin(JenKel);
// stdnt.setJenisKelamin(radiobtnperempuan.getText());
// }
// stdnt.setAlamatRumah(txt_alamat.getText());
//
// tabel.append("                      DATA SISWA                \n");
// tabel.append("-------------------------------------------------------------------\n");
// tabel.append("Nama            : " + stdnt.getNama()+ "\n");
// tabel.append("Tanggal Lahir   : " + stdnt.getTanggallahir()+ "\n");
// tabel.append("NIS             : " + stdnt.getNIS()+ "\n");
// tabel.append("Agama           : " + stdnt.getAgama()+ "\n");
// tabel.append("Jenis Kelamin   : " + stdnt.getJenisKelamin()+ "\n");
// tabel.append("Alamat          : " + stdnt.getAlamatRumah()+ "\n");
// tabel.append("Tahun Masuk     : " + txt_tahun.getText()+ "\n");
    }//GEN-LAST:event_btn_cetakActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
{ 
 // TODO add your handling code here:
 //exit
// dispose();
 }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_hpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hpsActionPerformed
        // TODO add your handling code here:
          delete();
//        DefaultTableModel dataModel = (DefaultTableModel) tabel.getModel();
//        int rowCount = dataModel.getRowCount();
//        while (rowCount > 0) {
//            dataModel.removeRow(rowCount - 1);
//            rowCount = dataModel.getRowCount();
//        }

    }//GEN-LAST:event_btn_hpsActionPerformed

    private void btn_btlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_btlActionPerformed
        // TODO add your handling code here:
       // clear();
        update();
    }//GEN-LAST:event_btn_btlActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cariActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int tabel = this.tabel.getSelectedRow();
        nis1 = this.tabel.getValueAt(tabel, 0).toString();
        nama1 = this.tabel.getValueAt(tabel, 1).toString();
        jk1 = this.tabel.getValueAt(tabel, 2).toString();
        agama1 = this.tabel.getValueAt(tabel, 3).toString();
        ttl1 = this.tabel.getValueAt(tabel, 4).toString();
        alamat1 = this.tabel.getValueAt(tabel, 5).toString();
        tahun1 = this.tabel.getValueAt(tabel, 6).toString();
        itempilih();
    }//GEN-LAST:event_tabelMouseClicked

    private void btn_formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_formActionPerformed
        // TODO add your handling code here:
        new GUI_Student().setVisible(true);
    }//GEN-LAST:event_btn_formActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Student().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupJk;
    private javax.swing.JButton btn_btl;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_form;
    private javax.swing.JButton btn_hps;
    private javax.swing.JTextField cari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton radiobtnlaki;
    private javax.swing.JRadioButton radiobtnperempuan;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txt_agama;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nis;
    private javax.swing.JTextField txt_tahun;
    private javax.swing.JTextField txt_ttl;
    // End of variables declaration//GEN-END:variables
}