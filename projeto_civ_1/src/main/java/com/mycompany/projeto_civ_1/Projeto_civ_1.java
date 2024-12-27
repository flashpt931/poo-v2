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
        colono = new colono(); // Criando o colono
        int mid = mapa.getTamanho() / 2; // Calculando o centro do mapa
        colono.setX(mid); // Definindo coordenada X
        colono.setY(mid); // Definindo coordenada Y
        mapa.adicionarTropa(colono); // Adicionando o colono à lista de tropas no mapa
        turno = 1;
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            // Display main menu
            System.out.println("\n=== Civilização - Turno " + turno + " ===");
            System.out.println("1. Lista de unidades");
            System.out.println("2. Construir ou melhorar um edifício na cidade");
            System.out.println("3. Ver o mapa");
            System.out.println("4. Finalizar turno");
            System.out.println("0. Sair do jogo");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    listadetropas(scanner);
                    break;
                case 2:
                    construirEdificio(scanner);
                    break;
                case 3:
                    exibirMapa();
                    break;
                case 4:
                    finalizarTurno();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
    private void listadetropas(Scanner scanner) {
        mapa.listarTropas();
        Tropa tropaEscolhida = mapa.escolherTropa(scanner);
        if (tropaEscolhida != null) {
            System.out.println("Tropa escolhida: " + tropaEscolhida.getNome() + " na posição (" + tropaEscolhida.getX() + ", " + tropaEscolhida.getY() + ")");
            if (tropaEscolhida instanceof colono) {
                boolean voltar = false;
                while (!voltar) {
                    System.out.println("1. Mover tropa");
                    System.out.println("2. Criar cidade");
                    System.out.println("3. Voltar");
                    System.out.print("Escolha uma opção: ");
                    int opcao = scanner.nextInt();
                    switch (opcao) {
                        case 1:
                            moverUnidade(scanner, tropaEscolhida);
                            break;
                        case 2:
                            criarCidade(scanner, tropaEscolhida);
                            break;
                        case 3:
                            voltar = true;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }
        }
    }

    private void moverUnidade(Scanner scanner, Tropa tropa) {
        System.out.println("Movendo unidade...");
        // Implement logic for moving a unit
        // For example, move the 'colono' by its movement cost and current position
    }

    private void criarCidade(Scanner scanner, Tropa tropa) {
        if (tropa instanceof colono) {
            colono colono = (colono) tropa;
            boolean sucesso = colono.gerarCidade(colono.getX(), colono.getY(), mapa.getMapa());
            if (sucesso) {
                mapa.removercolono(colono); // Remove o colono do mapa
            }
        } 
    }

    private void construirEdificio(Scanner scanner) {
        System.out.println("Construindo edifício...");
        // Implement logic for building or upgrading a building
    }
    
    private void exibirMapa() {
        colono.imprimirMapa(mapa.getMapa());
    }

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

