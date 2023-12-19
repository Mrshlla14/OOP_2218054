/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS_4;

/**
 *
 * @author Marshella Poa
 */
public class Penilaian extends Student{
    public String Nama, NIS, Mapel, Jurusan, Kelas;
    public int Tugas1, Tugas2, Tugas3, Tugas4, UTS, UAS, Praktek;
       
    
    public String CetakKelas(){
       return Kelas;
    }
    public double nilaiProses(){
        return((Tugas1 * 0.1) + (Tugas2 * 0.1) + (Tugas3 * 0.1) + (Tugas4 * 0.1) + (UTS * 0.2) + (UAS * 0.4) + (Praktek * 0.3));
    }
    public double nilaiAkhir (){
        return (nilaiProses() * 0.6) + (UAS * 0.4);
    }
    public double nilaiKeseluruhan (){
        return nilaiAkhir();
    }
    
    //public double nilaiIndividu(){
        // return 0;
     
    public Penilaian(){
        this.Jurusan = "IPA";
    }
    
    //OVERIDDE
    @Override
    public String getNama() {
        return "Override: " + super.getNama();
    }
    public String getJenisKelamin() {
        return "Override: " + super.getJenisKelamin();
    }
    public String getAgama() {
        return "Override: " + super.getAgama();
    }
}
