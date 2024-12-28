package com.mycompany.projeto_civ_1;

public class Edificio {
    private String nome;
    private int turnosRestantes;
    private boolean construido;

    public Edificio(String nome, int turnosRestantes) {
        this.nome = nome;
        this.turnosRestantes = turnosRestantes;
        this.construido = false;
    }

    public String getNome() {
        return nome;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public boolean isConstruido() {
        return construido;
    }

    public void reduzirTurno() {
        if (turnosRestantes > 0) {
            turnosRestantes--;
            if (turnosRestantes == 0) {
                construido = true;
            }
        }
    }
}
