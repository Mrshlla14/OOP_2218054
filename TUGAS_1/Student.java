/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS_1;

/**
 *
 * @author Marshella Poa
 */
public class Student {
    //atribute dari Student
      String Nama, TanggalLahir, NIS, Agama, JenisKelamin, AlamatRumah, TahunMasuk,Norum,Kopos;

 //method
 void setNama(String Nama){
 this.Nama = Nama;
 }
 void setTanggalLahir(String TanggalLahir){
 this.TanggalLahir = TanggalLahir;
 }
 void setNIS(String NIS){
 this.NIS = NIS;
 } 
 void setAgama(String Agama){
 this.Agama = Agama;
 }
 void setJenisKelamin(String JenisKelamin){
 this.JenisKelamin = JenisKelamin;
 } 
 void setAlamatRumah(String AlamatRumah){
 this.AlamatRumah = AlamatRumah;
 } 
// menambahkan overloading pada Alamat Rumah
void setAlamatRumah(String AlamatRumah, String NoRumah ){
 this.AlamatRumah = AlamatRumah;
 this.Norum = NoRumah;
 } 
void setAlamatRumah(String AlamatRumah, String NoRum, String Kopos ){
 this.AlamatRumah = AlamatRumah;
 this.Norum = NoRum;
 this.Kopos = Kopos;
 } 
 

String getNama(){
 return Nama;
 }
String getTanggallahir(){
 return TanggalLahir;
 }
String getNIS(){
 return NIS;
 }
String getAgama(){
 return Agama;
 }
String getJenisKelamin(){
 return JenisKelamin;
 }
String getAlamatRumah(){
 return AlamatRumah;
}
String getNorum(){
    return Norum;
}
String getKopos(){
    return Kopos;
}
 //konstruktor
 public Student () {
     this.TahunMasuk ="2019";
 }
}
//    String Nama, TanggalLahir, NIS, Agama, JenisKelamin, AlamatRumah, TahunMasuk;
//public Student(String Nama,String TanggalLahir, String NIS, String Agama,String JenisKelamin,String AlamatRumah,String TahunMasuk){ 
//
// this.TanggalLahir = TanggalLahir;
// this.NIS = NIS;
// this.Agama = Agama;
// this.JenisKelamin = JenisKelamin;
// this.AlamatRumah = AlamatRumah;
////void dataTahunMasuk(String TahunMasuk){
//// this.TahunMasuk = TahunMasuk;
//}  
// 
// public String getNama(){
// return Nama;
// }
// public String getTanggallahir(){
// return TanggalLahir;
// }
// public String getkNIS(){
// return NIS;
// }
// public String getAgama(){
// return Agama;
// }
// public String getJenisKelamin(){
// return JenisKelamin;
// }
// public String getAlamatRumah(){
// return AlamatRumah;
//}
// String cetakTahunMasuk(){
// return TahunMasuk;
// }
 
// public Student () {
//     this.TahunMasuk ="2023";
// }
    
