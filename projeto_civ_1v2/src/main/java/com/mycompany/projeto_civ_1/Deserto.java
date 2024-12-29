/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Deserto extends Terreno {
    public Deserto() {
        super("Deserto", 'D', 0, 1);
    }

    @Override
    public int getCustoMovimento() {
        return 3;
    }

    @Override
    public int getAlimento() {
        return 0;
    }

    @Override
    public int getMaterial() {
        return 1;
    }
}
