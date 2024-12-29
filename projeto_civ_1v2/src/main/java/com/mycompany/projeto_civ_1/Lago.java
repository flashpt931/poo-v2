/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Lago extends Terreno {
    public Lago() {
        super("Lago", 'L', 3, 0);
    }

    @Override
    public int getCustoMovimento() {
        return 4;
    }

    @Override
    public int getAlimento() {
        return 3;
    }

    @Override
    public int getMaterial() {
        return 0;
    }
}
