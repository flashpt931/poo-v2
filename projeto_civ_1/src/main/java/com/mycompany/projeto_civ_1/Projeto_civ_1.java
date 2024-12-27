/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto_civ_1;

import java.util.Scanner;
/**
 *
 * @author franc
 */
public class Projeto_civ_1 {
    private int turno;
    private colono colono;  // Using the 'colono' class
    private mapa mapa;

    public Projeto_civ_1() {
        mapa = new mapa();  // Create a new map object
        mapa.definirTamanhoAleatorio(); // Set random size for the map
        mapa.GerarMap(mapa.getTamanho());  // Generate the map with the specified size
        inicializarJogo();
    }

    private void inicializarJogo() {
        colono = new colono(); // Create a new "colono" (settler) object
        // Assuming the "colono" starts at the center of the map (or another logical position)
        int mid = mapa.getTamanho() / 2;
        // Put the "colono" on the map at the center (or another starting position)
        mapa.getMapa()[mid][mid] = new Planice() {
        };  // Placeholder for the "colono" position (can be updated with a symbol)
        turno = 1;
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            // Display main menu
            System.out.println("\n=== Civilização - Turno " + turno + " ===");
            System.out.println("1. Mover uma unidade");
            System.out.println("2. Atacar com uma unidade");
            System.out.println("3. Construir ou melhorar um edifício na cidade");
            System.out.println("4. Ver o mapa");
            System.out.println("5. Finalizar turno");
            System.out.println("0. Sair do jogo");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    moverUnidade(scanner);
                    break;
                case 2:
                    atacarUnidade(scanner);
                    break;
                case 3:
                    construirEdificio(scanner);
                    break;
                case 4:
                    exibirMapa();
                    break;
                case 5:
                    finalizarTurno();
                    break;
                case 0:
                    sair = true;
                    System.out.println("Obrigado por jogar!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private void moverUnidade(Scanner scanner) {
        System.out.println("Movendo unidade...");
        // Implement logic for moving a unit
        // For example, move the 'colono' by its movement cost and current position
    }

    private void atacarUnidade(Scanner scanner) {
        System.out.println("Atacando com unidade...");
        // Implement logic for attacking with a unit
    }

    private void construirEdificio(Scanner scanner) {
        System.out.println("Construindo edifício...");
        // Implement logic for building or upgrading a building
    }

    private void exibirMapa() {
        System.out.println("Exibindo mapa:");
        // Use the new mapa class to display the map
        for (int i = 0; i < mapa.getTamanho(); i++) {
            for (int j = 0; j < mapa.getTamanho(); j++) {
                System.out.print(mapa.getMapa()[i][j].getSimbolo() + " ");
            }
            System.out.println();
        }
    }

    /*private void finalizarTurno() {
        turno++;
        System.out.println("Turno " + turno + " iniciado!");
    }*/
    private void finalizarTurno() {
    turno++;
    // Verifica e evolui o nível das cidades no final de cada turno
    for (int i = 0; i < mapa.getTamanho(); i++) {
        for (int j = 0; j < mapa.getTamanho(); j++) {
            if (mapa.getMapa()[i][j] instanceof Cidades) {
                ((Cidades) mapa.getMapa()[i][j]).evoluirNivel();
            }
        }
    }
    System.out.println("Turno " + turno + " iniciado!");
}

    public static void main(String[] args) {
        Projeto_civ_1 jogo = new Projeto_civ_1();
        jogo.jogar();
    }
}
