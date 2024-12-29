package com.mycompany.projeto_civ_1;

public class Arqueiro extends Tropa {
    public Arqueiro() {
        super("Arqueiro", 'A', 8, 4, 0, 0, 8);
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }
}