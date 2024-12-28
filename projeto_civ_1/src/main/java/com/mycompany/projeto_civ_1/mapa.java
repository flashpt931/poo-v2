/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author franc
 */
public class mapa {
    private int tamanho;
    private Terreno[][] mapa;
    private ArrayList<Tropa> tropas;

    public mapa() {
        tropas = new ArrayList<>();
    }

    public int getTamanho() {
        return tamanho;
    }

    public Terreno[][] getMapa() {
        return mapa;
    }

    public void adicionarTropa(Tropa tropa) {
        tropas.add(tropa);
        mapa[tropa.getX()][tropa.getY()] = tropa; // Adiciona a tropa ao mapa
    }

    public void removercolono(Tropa tropa) {
        tropas.remove(tropa);
    }

    public void definirTamanhoAleatorio() {
        Random random = new Random();
        this.tamanho = random.nextInt(21) + 10;
        System.out.println("Tamanho do mapa: " + tamanho + "X" + tamanho);
        mapa = new Terreno[tamanho][tamanho];
    }

    public void GerarMap(int tamanho) {
        Random random = new Random();
        if (mapa == null) {
            throw new IllegalStateException("O array 'mapa' não foi inicializado!");
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                mapa[i][j] = gerarTerrenoAleatorio(random); // Preenchendo o terreno aleatório
            }
        }
        System.out.println();
    }

    private Terreno gerarTerrenoAleatorio(Random random) {
        int sorteio = random.nextInt(100);
        if (sorteio < 5) {
            return new Mar();
        } else if (sorteio < 50) {
            return new Planice();
        } else if (sorteio < 80) {
            return new Floresta();
        } else {
            return new Deserto();
        }
    }

    public void listarTropas() {
        if (tropas.isEmpty()) {
            System.out.println("Não há tropas no mapa.");
        } else {
            System.out.println("Tropas presentes no mapa:");
            for (int i = 0; i < tropas.size(); i++) {
                Tropa tropa = tropas.get(i);
                System.out.println(i + ". Nome: " + tropa.getNome() + ", Símbolo: " + tropa.getSimbolo() + ", Vida: " + tropa.getVida() + ", Posição: (" + tropa.getX() + ", " + tropa.getY() + ")");
            }
        }
    }

    public Tropa escolherTropa(Scanner scanner) {
        listarTropas();
        if (tropas.isEmpty()) {
            return null;
        }
        System.out.print("Escolha o índice da tropa: ");
        int indice = scanner.nextInt();
        if (indice < 0 || indice >= tropas.size()) {
            System.out.println("Índice inválido. Nenhuma tropa selecionada.");
            return null;
        }
        return tropas.get(indice);
    }
}