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
    public Floresta() {
        super("Floresta", 'F', 3, 4);
    }

    @Override
    public int getCustoMovimento() {
        return 2;
    }

    @Override
    public int getAlimento() {
        return 3;
    }

    @Override
    public int getMaterial() {
        return 4;
    }
}
