/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto_civ_1;

import java.util.Scanner;

/**
 *
 * @autor franc
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
        // Defina a posição inicial do colono
        colono.setX(mapa.getTamanho() / 2);
        colono.setY(mapa.getTamanho() / 2);
        // Adicione o colono ao mapa
        mapa.adicionarTropa(colono);

        // Gerar tropas inimigas
        mapa.gerarTropasInimigas(5); // Gera 5 tropas inimigas
    }

    private void exibirMapa() {
        mapa.imprimirMapa(mapa.getMapa());
    }

    private void finalizarTurno() {
        turno++;
        // Verifica e evolui o nível das cidades no final de cada turno
        for (int i = 0; i < mapa.getTamanho(); i++) {
            for (int j = 0; j < mapa.getTamanho(); j++) {
                if (mapa.getMapa()[i][j] instanceof Cidades) {
                    Cidades cidade = (Cidades) mapa.getMapa()[i][j];
                    cidade.recolherRecursos(mapa.getMapa());
                    cidade.atualizarEdificios();
                } else if (mapa.getMapa()[i][j] instanceof Tropa) {
                    Tropa tropa = (Tropa) mapa.getMapa()[i][j];
                    tropa.recarregarPontosMovimento();
                    tropa.resetarAtaque(); // Reseta o estado de ataque
                } else if (mapa.getMapa()[i][j] instanceof Barbaros) {
                    Barbaros barbaros = (Barbaros) mapa.getMapa()[i][j];
                    barbaros.recarregarPontosMovimento();
                    barbaros.resetarAtaque(); // Reseta o estado de ataque
                    // Encontre uma tropa para atacar
                    Tropa alvo = encontrarTropaParaAtacar(barbaros);
                    if (alvo != null) {
                        barbaros.atacar(alvo, mapa);
                    }
                }
            }
        }
    }

    private Tropa encontrarTropaParaAtacar(Barbaros barbaros) {
        // Implementar lógica para encontrar a tropa mais próxima para atacar
        // Por simplicidade, vamos apenas retornar a primeira tropa encontrada
        for (int i = 0; i < mapa.getTamanho(); i++) {
            for (int j = 0; j < mapa.getTamanho(); j++) {
                if (mapa.getMapa()[i][j] instanceof Tropa && !(mapa.getMapa()[i][j] instanceof TropasInimigas)) {
                    return (Tropa) mapa.getMapa()[i][j];
                }
            }
        }
        return null;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        while (!sair) {
            System.out.println("=== Civilizacao - Turno " + turno + " ===");
            System.out.println("1. Lista de unidades");
            System.out.println("2. Gerir Cidades");
            System.out.println("3. Ver o mapa");
            System.out.println("4. Finalizar turno");
            System.out.println("5. Exibir tesouro total");
            System.out.println("0. Sair do jogo");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    Tropa tropa = mapa.escolherTropa(scanner);
                    if (tropa != null) {
                        mapa.menuTropa(scanner, tropa);
                    }
                    break;
                case 2:
                    gerirCidade(scanner);
                    break;
                case 3:
                    exibirMapa();
                    break;
                case 4:
                    finalizarTurno();
                    break;
                case 5:
                    int tesouroTotal = mapa.calcularTesouroTotal();
                    System.out.println("Tesouro total: " + tesouroTotal);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private void gerirCidade(Scanner scanner) {
        Cidades cidade = mapa.escolherCidade(scanner);
        if (cidade != null) {
            boolean voltar = false;
            while (!voltar) {
                System.out.println("Gerindo cidade na posicao (" + cidade.getX() + ", " + cidade.getY() + ")");
                System.out.println("Nivel da cidade: " + cidade.getNivel());
                System.out.println("Recursos: " + cidade.getComida() + " comida, " + cidade.getProducao() + " producao, " + cidade.getOuro() + " ouro");
                System.out.println("1. Gerir Edificios");
                System.out.println("2. Gerir Populacao");
                System.out.println("3. Gerar Unidade");
                System.out.println("4. Evoluir Cidade (100 ouro)");
                System.out.println("5. Voltar");
                System.out.print("Escolha uma opcao: ");
                int opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        gerirEdificios(scanner, cidade);
                        break;
                    case 2:
                        gerirPopulacao(scanner, cidade);
                        break;
                    case 3:
                        gerarUnidade(scanner, cidade);
                        break;
                    case 4:
                        cidade.evoluirNivel();
                        break;
                    case 5:
                        voltar = true;
                        break;
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }
            }
        } else {
            System.out.println("Nenhuma cidade encontrada.");
        }
    }

    private void gerirEdificios(Scanner scanner, Cidades cidade) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("1. Listar Edificios");
            System.out.println("2. Construir Serralheria (3 turnos, 10 producao)");
            System.out.println("3. Construir Talho (3 turnos, 10 producao)");
            System.out.println("4. Construir Mina (5 turnos, 10 producao)");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cidade.listarEdificios();
                    break;
                case 2:
                    cidade.construirEdificio("Serralheria");
                    break;
                case 3:
                    cidade.construirEdificio("Talho");
                    break;
                case 4:
                    cidade.construirEdificio("Mina");
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
    }

    private void gerirPopulacao(Scanner scanner, Cidades cidade) {
        System.out.println("Gerindo populacao da cidade...");
        cidade.alocarCivis(scanner, mapa.getMapa());
    }

    private void gerarUnidade(Scanner scanner, Cidades cidade) {
        System.out.println("Gerar Unidade...");
        System.out.println("1. Colono (50 comida)");
        System.out.println("2. Infantaria (10 comida)");
        System.out.println("3. Arqueiro (20 comida)");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opcao: ");
        int tipoTropa = scanner.nextInt();
        boolean sucesso = false;

        switch (tipoTropa) {
            case 1:
                sucesso = cidade.gerarTropa("colono", cidade.getX(), cidade.getY(), mapa);
                break;
            case 2:
                sucesso = cidade.gerarTropa("infantaria", cidade.getX(), cidade.getY(), mapa);
                break;
            case 3:
                sucesso = cidade.gerarTropa("arqueiro", cidade.getX(), cidade.getY(), mapa);
                break;
            case 4:
                return; // Voltar
            default:
                System.out.println("Opcao invalida. Tente novamente.");
                return;
        }

        if (!sucesso) {
            System.out.println("Falha ao gerar a tropa.");
        } else {
            Tropa novaTropa = null;
            switch (tipoTropa) {
                case 1:
                    novaTropa = new colono();
                    break;
                case 2:
                    novaTropa = new Infantaria();
                    break;
                case 3:
                    novaTropa = new Arqueiro();
                    break;
            }
            if (novaTropa != null) {
                novaTropa.setX(cidade.getX());
                novaTropa.setY(cidade.getY());
            }
        }
    }

    public static void main(String[] args) {
        Projeto_civ_1 jogo = new Projeto_civ_1();
        jogo.iniciar();
    }
}