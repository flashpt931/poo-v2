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
        super("colono", 'H', 5, 1, 1, 1,6);
    }

    public boolean gerarCidade(int posX, int posY, Terreno[][] mapa) {
        int tamanhoMapa = mapa.length;
        int raio = 2; // Define o raio de alcance para verificar cidades existentes

        // Verifica se a posição está dentro dos limites do mapa
        if (posX < 0 || posX >= tamanhoMapa || posY < 0 || posY >= tamanhoMapa) {
            System.out.println("Posição inválida para criar uma cidade.");
            return false;
        }

        for (int i = posX - raio; i <= posX + raio; i++) {
            for (int j = posY - raio; j <= posY + raio; j++) {
                // Verifica se a posição atual está dentro dos limites do mapa
                if (i >= 0 && i < tamanhoMapa && j >= 0 && j < tamanhoMapa) {
                    // Verifica se já existe uma cidade nesta posição
                    if (mapa[i][j] instanceof Cidades) {
                        System.out.println("Já existe uma cidade dentro do raio de 2 posições em (" + i + ", " + j + ").");
                        return false;
                    }
                }
            }
        }

        // Cria a cidade na posição do colono com nível inicial
        Cidades novaCidade = new Cidades();
        novaCidade.setNivel(1); // Define o nível inicial como 1
        mapa[posX][posY] = novaCidade;
        System.out.println("Cidade criada com sucesso na posição (" + posX + ", " + posY + ").");
        return true;
    }

    public void imprimirMapa(Terreno[][] mapa) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] instanceof Cidades) {
                    System.out.print("C ");
                } else if (mapa[i][j] instanceof Tropa) {
                    System.out.print(((Tropa) mapa[i][j]).getSimbolo() + " ");
                } else if (mapa[i][j] != null) {
                    System.out.print(mapa[i][j].getSimbolo() + " ");
                } else {
                    System.out.print(". "); // Representa um espaço vazio no mapa
                }
            }
            System.out.println();
        }
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }
}
