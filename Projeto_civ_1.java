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
            System.out.println("2. Gerir Ciddades");
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
                    gerirCidade(scanner);
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
        System.out.print("Digite a nova posição X: ");
        int novoX = scanner.nextInt();
        System.out.print("Digite a nova posição Y: ");
        int novoY = scanner.nextInt();

        // Verifica se a nova posição está dentro dos limites do mapa
        if (novoX < 0 || novoX >= mapa.getTamanho() || novoY < 0 || novoY >= mapa.getTamanho()) {
            System.out.println("Posição inválida. Movimento cancelado.");
            return;
        }

        // Verifica se a nova posição está ocupada por outra tropa
        if (mapa.getMapa()[novoX][novoY] instanceof Tropa) {
            System.out.println("A posição (" + novoX + ", " + novoY + ") já está ocupada por outra tropa. Movimento cancelado.");
            return;
        }

        // Calcula a distância de movimento
        int distancia = Math.abs(novoX - tropa.getX()) + Math.abs(novoY - tropa.getY());

        // Calcula o custo de movimento com base no terreno
        int custoMovimento = 0;
        int xAtual = tropa.getX();
        int yAtual = tropa.getY();
        for (int i = 0; i < distancia; i++) {
            if (xAtual != novoX) {
                xAtual += (novoX > xAtual) ? 1 : -1;
            } else if (yAtual != novoY) {
                yAtual += (novoY > yAtual) ? 1 : -1;
            }
            custoMovimento += mapa.getMapa()[xAtual][yAtual].getCustoMovimento();
        }

        // Verifica se a tropa tem pontos de movimento suficientes
        if (tropa.getPontosMovimento() < custoMovimento) {
            System.out.println("Pontos de movimento insuficientes. Movimento cancelado.");
            return;
        }

        // Atualiza o mapa e a posição da tropa
        Terreno terrenoAnterior = mapa.getMapa()[novoX][novoY]; // Salva o terreno anterior
        mapa.getMapa()[tropa.getX()][tropa.getY()] = terrenoAnterior; // Substitui a posição anterior da tropa pelo terreno anterior
        tropa.setX(novoX);
        tropa.setY(novoY);
        mapa.getMapa()[novoX][novoY] = tropa; // Adiciona a tropa na nova posição
        tropa.reduzirPontosMovimento(custoMovimento); // Reduz os pontos de movimento da tropa

        System.out.println("Unidade movida para a posição (" + novoX + ", " + novoY + ").");
    }
    private void gerirCidade(Scanner scanner) {
        System.out.println("Gerindo cidade...");
        // Assuming you have a method to get the city at the current position of the colono
        Cidades cidade = mapa.getCidade(colono.getX(), colono.getY());
        if (cidade != null) {
            cidade.setX(colono.getX());
            cidade.setY(colono.getY());
            boolean voltar = false;
            while (!voltar) {
                System.out.println("Nível da cidade: " + cidade.getNivel());
                System.out.println("recursos:"+ cidade.getComida()+" "+cidade.getProducao());
                System.out.println("1. Gerir Edifícios");
                System.out.println("2. Gerir População");
                System.out.println("3. Gerar Unidade");
                System.out.println("4. Voltar");
                System.out.print("Escolha uma opção: ");
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
                        voltar = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } else {
            System.out.println("Nenhuma cidade encontrada na posição atual.");
        }
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

    private void gerirEdificios(Scanner scanner, Cidades cidade) {
    boolean voltar = false;
    while (!voltar) {
        System.out.println("Gerindo edifícios da cidade...");
        cidade.listarEdificios();
        System.out.println("1. Construir Serraria");
        System.out.println("2. Construir Talho");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                cidade.construirEdificio("Serraria");
                System.out.println("Serraria construída com sucesso.");
                break;
            case 2:
                cidade.construirEdificio("Talho");
                System.out.println("Talho construído com sucesso.");
                break;
            case 3:
                voltar = true;
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
    
    private void gerirPopulacao(Scanner scanner, Cidades cidade) {
        System.out.println("Gerindo população da cidade...");
        cidade.alocarCivis(scanner, mapa.getMapa());
    }
    private void gerarUnidade(Scanner scanner, Cidades cidade) {
        System.out.println("Gerar Unidade...");
        System.out.println("1. Colono - 50 comida");
        System.out.println("2. Infantaria - 10 comida");
        System.out.println("3. Arqueiro - 20 comida");
        System.out.println("4. Voltar");
        System.out.print("Escolha o tipo de tropa: ");
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
                System.out.println("Opção inválida. Tente novamente.");
                return;
        }

        if (!sucesso) {
            System.out.println("Falha ao gerar a tropa.");
        }
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
                        Cidades cidade = (Cidades) mapa.getMapa()[i][j];
                        cidade.recolherRecursos(mapa.getMapa());
                        cidade.evoluirNivel();
                        System.out.println("Cidade na posição ("+i+","+j+") tem comida: "+cidade.getComida()+" e producao: " + cidade.getProducao());
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