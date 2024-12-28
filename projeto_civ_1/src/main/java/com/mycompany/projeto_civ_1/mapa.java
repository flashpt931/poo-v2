package com.mycompany.projeto_civ_1;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @autor franc
 */
public class mapa {
    private int tamanho;
    private Terreno[][] mapa;
    private ArrayList<Tropa> tropas;
    private ArrayList<Cidades> cidades;

    public mapa() {
        tropas = new ArrayList<>();
        cidades = new ArrayList<>();
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

    public void adicionarCidade(Cidades cidade) {
        cidades.add(cidade);
    }

    public void removerCidade(Cidades cidade) {
        cidades.remove(cidade);
    }

    public ArrayList<Cidades> getCidades() {
        return cidades;
    }

    public void definirTamanhoAleatorio() {
        Random random = new Random();
        this.tamanho = random.nextInt(11) + 10;
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
            return new Lago();
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
                if (!(tropa instanceof TropasInimigas)) {
                    System.out.println(i + ". Nome: " + tropa.getNome() + ", Símbolo: " + tropa.getSimbolo() + ", Vida: " + tropa.getVida() + ", Posição: (" + tropa.getX() + ", " + tropa.getY() + ")");
                }
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
        if (indice < 0 || indice >= tropas.size() || tropas.get(indice) instanceof TropasInimigas) {
            System.out.println("Índice inválido. Nenhuma tropa selecionada.");
            return null;
        }
        return tropas.get(indice);
    }

    public void menuTropa(Scanner scanner, Tropa tropa) {
        boolean voltar = false;
        while (!voltar) {
            if (tropa instanceof Arqueiro || tropa instanceof Infantaria) {
                System.out.println("1. Escolha unidade para atacar");
                System.out.println("2. Mover unidade");
                System.out.println("3. Voltar");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        escolherUnidadeParaAtacar(scanner, tropa);
                        break;
                    case 2:
                        moverUnidade(scanner, tropa);
                        break;
                    case 3:
                        voltar = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else if (tropa instanceof colono) {
                System.out.println("1. Criar cidade");
                System.out.println("2. Mover unidade");
                System.out.println("3. Voltar");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        criarCidade(scanner, (colono) tropa);
                        break;
                    case 2:
                        moverUnidade(scanner, tropa);
                        break;
                    case 3:
                        voltar = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Esta tropa não possui ações especiais.");
                voltar = true;
            }
        }
    }

    private void escolherUnidadeParaAtacar(Scanner scanner, Tropa tropa) {
        ArrayList<Tropa> inimigosProximos = new ArrayList<>();
        int x = tropa.getX();
        int y = tropa.getY();
        int raio = (tropa instanceof Arqueiro) ? 2 : 1;

        // Verifica as posições ao redor da tropa (raio de 1 ou 2)
        for (int i = -raio; i <= raio; i++) {
            for (int j = -raio; j <= raio; j++) {
                int novoX = x + i;
                int novoY = y + j;
                if (novoX >= 0 && novoX < tamanho && novoY >= 0 && novoY < tamanho) {
                    if (mapa[novoX][novoY] instanceof TropasInimigas) {
                        inimigosProximos.add((Tropa) mapa[novoX][novoY]);
                    }
                }
            }
        }

        if (inimigosProximos.isEmpty()) {
            System.out.println("Não há tropas inimigas próximas para atacar.");
            return;
        }

        System.out.println("Tropas inimigas próximas:");
        for (int i = 0; i < inimigosProximos.size(); i++) {
            Tropa inimigo = inimigosProximos.get(i);
            System.out.println(i + ". Nome: " + inimigo.getNome() + ", Símbolo: " + inimigo.getSimbolo() + ", Vida: " + inimigo.getVida() + ", Posição: (" + inimigo.getX() + ", " + inimigo.getY() + ")");
        }

        System.out.print("Escolha o índice da tropa inimiga para atacar: ");
        int indice = scanner.nextInt();
        if (indice < 0 || indice >= inimigosProximos.size()) {
            System.out.println("Índice inválido. Nenhuma tropa atacada.");
            return;
        }

        Tropa alvo = inimigosProximos.get(indice);
        tropa.atacar(alvo, this); // Passa o mapa para a função atacar
    }

    private void moverUnidade(Scanner scanner, Tropa tropa) {
        System.out.print("Digite a nova posição X: ");
        int novoY = scanner.nextInt();
        System.out.print("Digite a nova posição Y: ");
        int novoX = scanner.nextInt();

        if (novoX >= 0 && novoX < tamanho && novoY >= 0 && novoY < tamanho && !(mapa[novoX][novoY] instanceof Tropa)) {
            int custoMovimento = mapa[novoX][novoY].getCustoMovimento();
            if (tropa.getPontosMovimento() >= custoMovimento) {
                // Armazena o terreno original antes de mover a unidade
                Terreno terrenoOriginal = tropa.getTerrenoOriginal();
                if (terrenoOriginal == null) {
                    terrenoOriginal = new Planice(); // Define um terreno padrão se não houver um terreno original
                }
                // Remove a tropa da posição atual e restaura o terreno original
                mapa[tropa.getX()][tropa.getY()] = terrenoOriginal;
                // Atualiza a posição da tropa
                tropa.setX(novoX);
                tropa.setY(novoY);
                // Armazena o novo terreno original
                tropa.setTerrenoOriginal(mapa[novoX][novoY]);
                // Adiciona a tropa à nova posição
                mapa[novoX][novoY] = tropa;
                // Reduz os pontos de movimento da tropa
                tropa.reduzirPontosMovimento(custoMovimento);
                System.out.println("Unidade movida para a posição (" + novoY + ", " + novoX + ").");
            } else {
                System.out.println("Pontos de movimento insuficientes. Tente novamente.");
            }
        } else {
            System.out.println("Posição inválida ou ocupada. Tente novamente.");
        }
    }

    private void criarCidade(Scanner scanner, colono colono) {
        int x = colono.getX();
        int y = colono.getY();
        if (colono.gerarCidade(x, y, this)) {
            Cidades novaCidade = (Cidades) mapa[x][y];
            adicionarCidade(novaCidade);
            removercolono(colono);
            System.out.println("cidade criada na posicao("+x+","+y+")");
        }
    }

    public Cidades getCidade(int x, int y) {
        if (x < 0 || x >= tamanho || y < 0 || y >= tamanho) {
            return null; // Fora dos limites do mapa
        }
        if (mapa[x][y] instanceof Cidades) {
            return (Cidades) mapa[x][y];
        }
        return null; // Não há cidade nesta posição
    }

    public void gerarTropasInimigas(int quantidade) {
        Random random = new Random();
        for (int i = 0; i < quantidade; i++) {
            int x, y;
            do {
                x = random.nextInt(tamanho);
                y = random.nextInt(tamanho);
            } while (mapa[x][y] instanceof Tropa || mapa[x][y] instanceof Cidades); // Garante que a posição não esteja ocupada

            TropasInimigas tropaInimiga = new TropasInimigas("Barbaros", 'B', 10, 2, x, y, 5);
            adicionarTropa(tropaInimiga);
        }
    }

    public void removerTropa(Tropa tropa) {
        int x = tropa.getX();
        int y = tropa.getY();
        Terreno terrenoOriginal = tropa.getTerrenoOriginal();
        if (terrenoOriginal == null) {
            terrenoOriginal = new Planice(); // Define um terreno padrão se não houver um terreno original
        }
        mapa[x][y] = terrenoOriginal; // Restaura o terreno original
        tropas.remove(tropa); // Remove a tropa da lista de tropas
    }

    public Cidades escolherCidade(Scanner scanner) {
        if (cidades.isEmpty()) {
            System.out.println("Não há cidades no mapa.");
            return null;
        }

        System.out.println("Cidades presentes no mapa:");
        for (int i = 0; i < cidades.size(); i++) {
            Cidades cidade = cidades.get(i);
            System.out.println(i + ". Cidade na posição (" + cidade.getX() + ", " + cidade.getY() + "), Nível: " + cidade.getNivel());
        }

        System.out.print("Escolha o índice da cidade: ");
        int indice = scanner.nextInt();
        if (indice < 0 || indice >= cidades.size()) {
            System.out.println("Índice inválido. Nenhuma cidade selecionada.");
            return null;
        }
        return cidades.get(indice);
    }

    public void imprimirMapa(Terreno[][] mapa) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j].getSimbolo() + " ");
            }
            System.out.println();
        }
    }

    public void transformarOuroEmTesouro() {
        for (Cidades cidade : cidades) {
            cidade.transformarOuroEmTesouro();
            System.out.println("Cidade na posição (" + cidade.getX() + ", " + cidade.getY() + ") transformou ouro em tesouro. Tesouro atual: " + cidade.getTesouro());
        }
    }

    public int calcularTesouroTotal() {
        int tesouroTotal = 0;
        for (Cidades cidade : cidades) {
            tesouroTotal += cidade.getOuro();
        }
        return tesouroTotal;
    }
}