package com.mycompany.projeto_civ_1;

public abstract class Tropa {
    private String nome;
    private char simbolo;
    private int vida;
    private int dano;

    public Tropa(String nome, char simbolo, int vida, int dano) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.vida = vida;
        this.dano = dano;
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

    public String getNome() {
        return nome;
    }

    public void atacar(Tropa outraTropa) {
        if (!outraTropa.estaViva()) {
            System.out.println(outraTropa.getNome() + " já está derrotado. O ataque foi ignorado.");
            return;
        }
        outraTropa.setVida(outraTropa.getVida() - this.getDano());
        if (outraTropa.getVida() <= 0) {
            System.out.println(outraTropa.getNome() + " foi derrotado!");
        } else {
            System.out.println(getNome() + " atacou " + outraTropa.getNome() +
                               " e causou " + getDano() + " de dano. Vida restante: " + outraTropa.getVida());
        }
    }

    public boolean estaViva() {
        return this.vida > 0;
    }

    public void combate(Tropa outraTropa) {
        while (this.estaViva() && outraTropa.estaViva()) {
            this.atacar(outraTropa);
            if (outraTropa.estaViva()) {
                outraTropa.atacar(this);
            }
        }
        if (this.estaViva()) {
            System.out.println(this.getNome() + " venceu o combate!");
        } else {
            System.out.println(outraTropa.getNome() + " venceu o combate!");
        }
    }
}