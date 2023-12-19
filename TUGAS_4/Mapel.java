/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUGAS_4;

/**
 *
 * @author Marshella Poa
 */
public class Mapel {
public String NamaMapel, NamaPengajar;
private String KodeMP;
    
 void dataNamaMapel(String NamaMapel){
 this.NamaMapel = NamaMapel;
 }
 void dataKodeMP(String KodeMP){
 this.KodeMP = KodeMP;
 }
 void dataNamaPengajar(String NamaPengajar){
 this.NamaPengajar = NamaPengajar;
 } 
 
 String getNamaMapel (){
     return NamaMapel;
 }
 String getKodeMP (){
     return KodeMP;
 }
 String getNamaPengajar (){
     return NamaPengajar;
 }
}
