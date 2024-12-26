/*package com.mycompany.projeto_civ_1;

public class Arqueiro extends Tropa {
    private int vida;
    private int dano;

    public Arqueiro() {
        super("Arqueiro", 'A');
        this.vida = 100; // valor inicial de vida
        this.dano = 10;  // valor inicial de dano
    }

    @Override
    public int getCustoMovimento() {
        return 3;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }
}*/

package com.mycompany.projeto_civ_1;

public class Arqueiro extends Tropa {
    public Arqueiro() {
        super("Arqueiro", 'A', 100, 10);
    }

    public int getCustoMovimento() {
        return 3;
    }
}