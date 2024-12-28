package com.mycompany.projeto_civ_1;

public abstract class Tropa extends Terreno {
    private String nome;
    private char simbolo;
    private int vida;
    private int dano;
    private int x; // Coordenada X no mapa
    private int y; // Coordenada Y no mapa
    private int pontosMovimento; // Pontos de movimento da tropa
    private int pontosMovimentoMaximo; // Pontos de movimento máximo da tropa
    private boolean jaAtacou; // Indica se a tropa já atacou no turno atual
    private Terreno terrenoOriginal; // Terreno original da tropa

    public Tropa(String nome, char simbolo, int vida, int dano, int x, int y, int pontosMovimento) {
        super(nome, simbolo, 0, 0); // Call the constructor of the superclass Terreno
        this.nome = nome;
        this.simbolo = simbolo;
        this.vida = vida;
        this.dano = dano;
        this.x = x;
        this.y = y;
        this.pontosMovimento = pontosMovimento;
        this.pontosMovimentoMaximo = pontosMovimento; // Inicializa o máximo com o valor inicial
        this.jaAtacou = false; // Inicializa como não tendo atacado
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public char getSimbolo() {
        return simbolo;
    }

    public int getPontosMovimento() {
        return pontosMovimento;
    }

    public void setPontosMovimento(int pontosMovimento) {
        this.pontosMovimento = pontosMovimento;
    }

    public void reduzirPontosMovimento(int custo) {
        this.pontosMovimento -= custo;
    }

    public void recarregarPontosMovimento() {
        this.pontosMovimento = this.pontosMovimentoMaximo;
    }

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

    public boolean estaViva() {
        return vida > 0;
    }

    public void resetarAtaque() {
        jaAtacou = false; // Reseta o estado de ataque no início de cada turno
    }

    public void setTerrenoOriginal(Terreno terrenoOriginal) {
        this.terrenoOriginal = terrenoOriginal;
    }

    public Terreno getTerrenoOriginal() {
        return terrenoOriginal;
    }
}