package com.mycompany.projeto_civ_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cidades extends Terreno {
    private int nivel;
    private int comida;
    private int producao;
    private int ouro;
    private int tesouro;
    private int x;
    private int y;
    private List<int[]> posicoesCivis; // Lista para armazenar as posições dos civis
    private List<Edificio> edificios; // Lista para armazenar os edifícios

    public Cidades(int x, int y) {
        super("Cidade", 'C', 0, 0);
        this.x = x;
        this.y = y;
        this.nivel = 1;
        this.comida = 0;
        this.producao = 0;
        this.ouro = 0;
        this.tesouro = 0;
        this.posicoesCivis = new ArrayList<>(); // Inicializa a lista de posições dos civis
        this.edificios = new ArrayList<>(); // Inicializa a lista de edifícios
    }

    // Getters para comida, produção e ouro
    public int getComida() {
        return comida;
    }

    public int getProducao() {
        return producao;
    }

    public int getOuro() {
        return ouro;
    }

    public int getTesouro() {
        return tesouro;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }

    public void evoluirNivel() {
        if (this.ouro >= 100) {
            this.nivel++;
            this.ouro -= 100;
            System.out.println("Cidade evoluiu para o nível " + this.nivel);
        } else {
            System.out.println("Ouro insuficiente para evoluir a cidade.");
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void transformarOuroEmTesouro() {
        tesouro += ouro;
        ouro = 0;
    }

    public boolean gerarTropa(String tipoTropa, int cidadeX, int cidadeY, mapa mapa) {
        int custoComida;
        Tropa novaTropa;

        switch (tipoTropa.toLowerCase()) {
            case "colono":
                custoComida = 50;
                novaTropa = new colono();
                break;
            case "infantaria":
                custoComida = 10;
                novaTropa = new Infantaria();
                break;
            case "arqueiro":
                custoComida = 20;
                novaTropa = new Arqueiro();
                break;
            default:
                System.out.println("Tipo de tropa desconhecido.");
                return false;
        }

        if (this.comida < custoComida) {
            System.out.println("Comida insuficiente para gerar a tropa.");
            return false;
        }

        Random random = new Random();
        int novoX = 0, novoY = 0;
        boolean posicaoValida = false;

        // Tenta encontrar uma posição válida dentro do raio de 2 da cidade
        for (int tentativa = 0; tentativa < 10; tentativa++) {
            novoX = cidadeX + random.nextInt(5) - 2; // Gera uma posição aleatória no raio de 2
            novoY = cidadeY + random.nextInt(5) - 2;

            if (novoX >= 0 && novoX < mapa.getTamanho() && novoY >= 0 && novoY < mapa.getTamanho() &&
                !(mapa.getMapa()[novoX][novoY] instanceof Tropa) && !(novoX == cidadeX && novoY == cidadeY)) {
                posicaoValida = true;
                break;
            }
        }

        if (!posicaoValida) {
            System.out.println("Não foi possível encontrar uma posição válida para gerar a tropa.");
            return false;
        }

        novaTropa.setX(novoX);
        novaTropa.setY(novoY);
        mapa.adicionarTropa(novaTropa);
        this.comida -= custoComida;
        System.out.println("Tropa " + tipoTropa + " gerada na posição (" + novoX + ", " + novoY + ").");
        return true;
    }

    public void alocarCivis(Scanner scanner, Terreno[][] mapa) {
        int civisDisponiveis = this.nivel * 2; // Número de civis disponíveis baseado no nível da cidade
        int totalAlimento = 0;
        int totalMaterial = 0;

        posicoesCivis.clear(); // Limpa a lista de posições dos civis

        while (civisDisponiveis > 0) {
            System.out.println("Civis disponíveis: " + civisDisponiveis);
            System.out.print("Digite a posição X para alocar um civil: ");
            int x = scanner.nextInt();
            System.out.print("Digite a posição Y para alocar um civil: ");
            int y = scanner.nextInt();

            // Verifica se a posição está dentro dos limites do mapa e dentro do raio de 3
            int distancia = Math.abs(x - this.x) + Math.abs(y - this.y);
            if (x >= 0 && x < mapa.length && y >= 0 && y < mapa[x].length && distancia <= 3) {
                totalAlimento += mapa[x][y].getAlimento();
                totalMaterial += mapa[x][y].getMaterial();
                civisDisponiveis--;
                posicoesCivis.add(new int[]{x, y}); // Adiciona a posição do civil à lista
                System.out.println("Civil alocado na posição (" + x + ", " + y + ").");
            } else {
                System.out.println("Posição inválida. Tente novamente.");
            }
        }

        // Atualiza comida e produção com os totais calculados
        this.comida += totalAlimento;
        this.producao += totalMaterial;

        System.out.println("Civis alocados. Comida: " + this.comida + ", Produção: " + this.producao);
    }

    public void recolherRecursos(Terreno[][] mapa) {
        int totalAlimento = 0;
        int totalMaterial = 0;

        // Percorre as posições dos civis alocados
        for (int[] posicao : posicoesCivis) {
            int x = posicao[0];
            int y = posicao[1];
            totalAlimento += mapa[x][y].getAlimento();
            totalMaterial += mapa[x][y].getMaterial();
        }

        // Acumula comida e produção com os totais calculados
        this.comida += totalAlimento;
        this.producao += totalMaterial;

        // Aplica os efeitos dos edifícios
        for (Edificio edificio : edificios) {
            if (edificio.getNome().equals("Serralheria") && edificio.isConstruido()) {
                this.producao += this.producao * 0.2; // Aumenta 20% da produção
            } else if (edificio.getNome().equals("Talho") && edificio.isConstruido()) {
                this.comida += this.comida * 0.2; // Aumenta 20% da comida
            } else if (edificio.getNome().equals("Mina") && edificio.isConstruido()) {
                this.ouro += 25; // Gera 25 ouro
            }
        }

        System.out.println("Recursos recolhidos. Comida: " + this.comida + ", Produção: " + this.producao + ", Ouro: " + this.ouro);
    }

    // Métodos para listar e construir edifícios
    public void listarEdificios() {
        if (edificios.isEmpty()) {
            System.out.println("Nenhum edifício construído.");
        } else {
            System.out.println("Edifícios construídos:");
            for (Edificio edificio : edificios) {
                System.out.println("- " + edificio.getNome() + (edificio.isConstruido() ? " (Construído)" : " (Em construção, faltam " + edificio.getTurnosRestantes() + " turnos)"));
            }
        }
    }

    public void construirEdificio(String nomeEdificio) {
        int custoProducao = 10; // Custo de produção para construir um edifício

        if (this.producao < custoProducao) {
            System.out.println("Produção insuficiente para construir " + nomeEdificio + ".");
            return;
        }

        this.producao -= custoProducao;
        int turnosParaConstruir = nomeEdificio.equals("Mina") ? 5 : 3; // 5 turnos para Mina, 3 turnos para outros
        Edificio edificio = new Edificio(nomeEdificio, turnosParaConstruir);
        edificios.add(edificio);
        System.out.println("Construção do edifício " + nomeEdificio + " iniciada. Faltam " + turnosParaConstruir + " turnos para ser concluída.");
    }

    public void atualizarEdificios() {
        for (Edificio edificio : edificios) {
            boolean wasConstruido = edificio.isConstruido();
            edificio.reduzirTurno();
            if (!wasConstruido && edificio.isConstruido()) {
                System.out.println("Edifício " + edificio.getNome() + " foi concluído.");
            }
        }
    }
}