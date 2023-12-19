/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS_4;

/**
 *
 * @author Marshella Poa
 */
public class PerJur extends Student {
   String Jurusan, Peminatan, Kelas;
   
   void setJurusan(String Jurusan){
       this.Jurusan = Jurusan;
   }
    void setPeminatan(String Peminatan){
       this.Peminatan = Peminatan;
   }
    void setKelas(String Kelas){
       this.Kelas = Kelas;
   }
   String getJurusan(){
       return Jurusan;
   }
   String getPeminatan(){
       return Peminatan;
   }
    String getKelas(){
       return Kelas;
   }
}

