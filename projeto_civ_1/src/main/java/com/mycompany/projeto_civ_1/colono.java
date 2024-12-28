/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author Flash
 */
public class colono extends Tropa {
    public colono() {
        super("colono", 'H', 5, 0, 0, 0, 2);
    }

    public boolean gerarCidade(int x, int y, mapa mapa) {
        if (mapa.getMapa()[x][y] instanceof Terreno) {
            Cidades novaCidade = new Cidades(x, y);
            mapa.getMapa()[x][y] = novaCidade;
            return true;
        }
        return false;
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }
}
