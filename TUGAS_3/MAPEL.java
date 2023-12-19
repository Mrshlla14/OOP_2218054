/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TUGAS_3;

/**
 *
 * @author Marshella Poa
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MAPEL extends javax.swing.JFrame {
String mapel1, kode1, guru1, jk1;


    /**
     * Creates new form MAPEL
     */
    public MAPEL() {
        initComponents();
        tampil();
//        DefaultTableModel dataModel = (DefaultTableModel) tabel.getModel();
// // Mendapatkan jumlah baris yang ada dalam model data saat ini
// int rowCount = dataModel.getRowCount();
// while (rowCount > 0) {
// // Menghapus baris terakhir dari model data
// dataModel.removeRow(rowCount - 1);
// // Memperbarui nilai rowCount setelah penghapusan baris terakhir
// rowCount = dataModel.getRowCount(); // Update rowCount after removal
// }
// DataMapel dtMapel= new DataMapel();
// txtmapel.setText(dtMapel.NamaMapel);
// kode_mapel.setText(dtMapel.KodeMP);
// guru.setText(dtMapel.NamaPengajar);
// //txt_jenkel.(dtMapel.));
//    }
//    public void clear(){
//    txtmapel.setText("");
//    kode_mapel.setText(""); 
//    guru.setText("");}
   // txt_jenkel.setText("");
    }
    public Connection conn;
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/OOP_2118112?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MAPEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(MAPEL.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(MAPEL.class.getName()).log(Level.SEVERE, null, es);
        }
    }
public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("KODE MK");
        tabelhead.addColumn("NAMA MK");
        tabelhead.addColumn("DOSEN");
        tabelhead.addColumn("JML SKS");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_matkul";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5),});
            }
            tabel.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
public void refresh() {
        new MAPEL().setVisible(true);
        this.setVisible(false);
    }
public void insert() {
        String NamaMapel = txtmapel.getText();
        String KodeMapel = kode_mapel.getText();
        String NamaGuru = guru.getText();
        String JenisKelamin = txt_jenkel.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_matkul(kode_mk, matakuliah, dosenpengajar,jmlsks)"
                    + "VALUES('" + NamaMapel + "','" + KodeMapel + "','" + NamaGuru + "','" + JenisKelamin + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Matakuliah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
public void update() {
        String NamaMapel = txtmapel.getText();
        String KodeMapel = kode_mapel.getText();
        String NamaGuru = guru.getText();
        String JenisKelamin = txt_jenkel.getText();
        String KodeMapelLama = kode1;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_matkul SET kode_mk='" + NamaMapel + "'," + "matakuliah='" + KodeMapel + "',"
                    + "dosenpengajar='" + NamaGuru + "'" + ",jmlsks='" + JenisKelamin + "'WHERE kode_mk = '" + KodeMapelLama + "'");

            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data MataKuliah!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_matkul WHERE kode_mk='" + kode_mapel.getText() + "'";
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
                String sql = "SELECT * FROM tb_matkul WHERE `kode_mk`  LIKE '%" + kode_mapel.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    txtmapel.setText(rs.getString(2));
                    kode_mapel.setText(rs.getString(3));
                    guru.setText(rs.getString(4));
                    txt_jenkel.setText(rs.getString(5));
                } else {
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }
public void itempilih() {
        txtmapel.setText(mapel1);
        kode_mapel.setText(kode1);
        guru.setText(guru1);
        txt_jenkel.setText(jk1);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmapel = new javax.swing.JTextField();
        kode_mapel = new javax.swing.JTextField();
        guru = new javax.swing.JTextField();
        cetak = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        btn_hps = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_jenkel = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_form = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        cari1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("MATA PELAJARAN IPA");

        jLabel2.setText("NAMA MAPEL ");

        jLabel3.setText("KODE MAPEL");

        jLabel4.setText("NAMA GURU");

        txtmapel.setText(" ");

        kode_mapel.setText(" ");

        guru.setText(" ");

        cetak.setText("CETAK");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NAMA MAPEL", "KODE MAPEL", "NAMA GURU", "JENIS KELAMIN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        btn_hps.setText("HAPUS");
        btn_hps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hpsActionPerformed(evt);
            }
        });

        jLabel5.setText("JENIS KELAMIN");

        txt_jenkel.setText(" ");

        jButton1.setText("BATAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_ubah.setText("UBAH");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_form.setText("Form Nilai");
        btn_form.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_formActionPerformed(evt);
            }
        });

        btn_cari.setText("CARI");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        cari1.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtmapel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(kode_mapel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guru, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_jenkel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cari1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_hps, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_form)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtmapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(kode_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(guru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_jenkel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cari)
                            .addComponent(cari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(cetak)
                    .addComponent(btn_hps)
                    .addComponent(btn_ubah)
                    .addComponent(btn_form))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(null, "Data anda Ditambahkan Ke tabel");
// // Mengambil model data dari tabel
// DefaultTableModel dataModel = (DefaultTableModel) tabel.getModel();
// // Inisialisasi sebuah ArrayList bernama 'list'
// List list = new ArrayList<>();
// // Mengatur tabel untuk membuat kolom dari model secara otomatis
//  tabel.setAutoCreateColumnsFromModel(true);
// DataMapel dtMapel = new DataMapel();
// list.add(dtMapel.NamaMapel);
// list.add(dtMapel.KodeMP);
// list.add(dtMapel.NamaPengajar);
 //list.add(dtMapel.JenisKelamin);
 // Menambahkan baris baru ke model tabel menggunakan data dari ArrayList 'list'
 //dataModel.addRow(list.toArray());
 // Memanggil fungsi 'clear' untuk membersihkan nilai dari komponen
 //clear();
//        DataMapel mapel = new DataMapel();
//        cetak.setText("");
//       mapel.setNamaMapel(txtmapel.getText());
//       mapel.setKodeMP(kode_mapel.getText());
//       mapel.setNamaPengajar(guru.getText());
//
//    memo.append("Nama Mapel = " + mapel.getNamaMapel()+"\n");
//    memo.append("Kode Mapel = " + mapel.getKodeMP()+"\n");
//    memo.append("Nama Pengajar = " + mapel.getNamaPengajar()+"\n");
    }//GEN-LAST:event_cetakActionPerformed

    private void btn_hpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hpsActionPerformed
        // TODO add your handling code here:
        delete();
//        DefaultTableModel dataModel = (DefaultTableModel) tabel.getModel();
//    int rowCount = dataModel.getRowCount();
//    while (rowCount > 0) {
//    dataModel.removeRow(rowCount - 1);
//    rowCount = dataModel.getRowCount(); // Update
    }//GEN-LAST:event_btn_hpsActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       //clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int tabel = this.tabel.getSelectedRow();
        mapel1 = this.tabel.getValueAt(tabel, 0).toString();
        kode1= this.tabel.getValueAt(tabel, 1).toString();
        guru1 = this.tabel.getValueAt(tabel, 2).toString();
        jk1 = this.tabel.getValueAt(tabel, 3).toString();
        itempilih();

    }//GEN-LAST:event_tabelMouseClicked

    private void btn_formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_formActionPerformed
        // TODO add your handling code here:
        new MAPEL().setVisible(true);
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
            java.util.logging.Logger.getLogger(MAPEL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MAPEL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MAPEL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MAPEL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MAPEL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_form;
    private javax.swing.JButton btn_hps;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JTextField cari1;
    private javax.swing.JButton cetak;
    private javax.swing.JTextField guru;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kode_mapel;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txt_jenkel;
    private javax.swing.JTextField txtmapel;
    // End of variables declaration//GEN-END:variables
}
