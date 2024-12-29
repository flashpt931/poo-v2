package com.mycompany.projeto_civ_1;

public class Infantaria extends Tropa {
    public Infantaria() {
        super("Infantaria", 'I', 10, 5, 0, 0, 5);
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }
}
