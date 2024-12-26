/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Planice extends Terreno {
    public Planice(){
        super ("Planice",'P');
    }
    @Override
    public int getCustoMovimento(){
        return 1;
    }
}
