/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Floresta extends Terreno {
    public Floresta(){
        super ("Floreta",'F',2,1);
    }
    @Override
    public int getCustoMovimento(){
        return 3;
    }
}
