/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS_1;

/**
 *
 * @author Marshella Poa
 */
public class DataSiswa {
    public static void main (String[] args) {
 // membuat object dengan nama “mhs” dari class Mahasiswa
  Student stdnt = new Student(); 
 //ketika akan menggunakan method dari class Mahasiswa maka harus menyertakan nama object
 stdnt.setNama("Marshella Poa");
 stdnt.setTanggalLahir("14-03-2004");
 stdnt.setNIS("27304");
 stdnt.setAgama("Katolik");
 stdnt.setJenisKelamin("Perempuan ");
 stdnt.setAlamatRumah("Jl. Wirajaya, Kel Onekore, Kab Ende ");

 
 System.out.println("Data Pelajar");
 System.out.println("------------------------------------");
 System.out.println("Nama : "+ stdnt.getNama());
 System.out.println("Tanggal Lahir : "+ stdnt.getTanggallahir()); 
 System.out.println("NIS : "+ stdnt.getNIS());  
 System.out.println("Agama : "+ stdnt.getAgama());
 System.out.println("Jenis Kelamin : "+ stdnt.getJenisKelamin());  
 System.out.println("Alamat Rumah : "+ stdnt.getAlamatRumah()); 
 //System.out.println("Tahun Masuk : "+ stdnt.cetakTahunMasuk()); 
}
}
