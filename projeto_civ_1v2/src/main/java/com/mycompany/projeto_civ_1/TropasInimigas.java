package com.mycompany.projeto_civ_1;

public class TropasInimigas extends Tropa {
    private boolean jaAtacou = false;

    public TropasInimigas(String nome, char simbolo, int vida, int dano, int x, int y, int pontosMovimento) {
        super(nome, simbolo, vida, dano, x, y, pontosMovimento);
    }

    @Override
    public void atacar(Tropa outraTropa, mapa mapa) {
        if (jaAtacou) {
            System.out.println(getNome() + " já atacou neste turno.");
            return;
        }
        if (!outraTropa.estaViva()) {
            System.out.println(outraTropa.getNome() + " já está derrotado. O ataque foi ignorado.");
            return;
        }
        outraTropa.setVida(outraTropa.getVida() - this.getDano());
        if (outraTropa.getVida() <= 0) {
            System.out.println(outraTropa.getNome() + " foi derrotado!");
            mapa.removerTropa(outraTropa); // Remove a tropa do mapa
        } else {
            System.out.println(getNome() + " atacou " + outraTropa.getNome() +
                               " e causou " + getDano() + " de dano. Vida restante: " + outraTropa.getVida());
        }
        jaAtacou = true; // Marca que a tropa já atacou neste turno
    }

    @Override
    public void resetarAtaque() {
        jaAtacou = false; // Reseta o estado de ataque no início de cada turno
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }
}
