/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Montanha extends Terreno{
   public Montanha(){
        super ("Montanha",'M',0,5);
    }

    @Override
    public int getCustoMovimento() {
        // Implement the method with appropriate logic
        return 100; // Example value, replace with actual logic if needed
    }
}