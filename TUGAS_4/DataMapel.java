/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS_4;

/**
 *
 * @author Marshella Poa
 */
public class DataMapel extends TUGAS_3.DataMapel {
    String JenisKelamin;
    void setJenisKelamin(String JenisKelamin){
       this.JenisKelamin = JenisKelamin;
   }
   
   String getJenisKelamin(){
       return JenisKelamin;
   }
   @Override
   void setNamaMapel(String NamaMapel){
       super.setNamaMapel (NamaMapel);
   }
   @Override
   String getNamaMapel(){
       return super.getNamaMapel();
   }
}
