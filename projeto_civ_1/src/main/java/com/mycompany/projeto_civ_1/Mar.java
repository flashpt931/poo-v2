/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Mar extends Terreno {
   public Mar(){
       super("MAR",'M',3,0);
   } 
   @Override
   public int getCustoMovimento(){
       return 3;
   }
}
