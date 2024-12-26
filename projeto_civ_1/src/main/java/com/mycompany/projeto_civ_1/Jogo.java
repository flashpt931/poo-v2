package com.mycompany.projeto_civ_1;

public class Jogo {
    public static void main(String[] args) {
        Tropa tropa1 = new Arqueiro();
        Tropa tropa2 = new Cavaleiro();

        System.out.println("Iniciando combate!");
        tropa1.combate(tropa2);
    }
}