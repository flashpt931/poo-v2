/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
package com.mycompany.projeto_civ_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cidades extends Terreno {
    private int nivel;
    private int comida;
    private int producao;
    private int X;
    private int Y;
    private int raioDominacao;
    private List<int[]> posicoesCivis; // Lista para armazenar as posições dos civis

    public Cidades() {
        super("Cidade", 'C', 0, 0);
        this.nivel = 1;
        this.comida = 0;
        this.producao = 0;
        this.raioDominacao = 3; // Define o raio de dominação da cidade
        this.posicoesCivis = new ArrayList<>(); // Inicializa a lista de posições dos civis
    }
   
    // Getters para comida e produção
    public int getComida() {
        return comida;
    }

    public int getProducao() {
        return producao;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }

    public void evoluirNivel() {
        if (this.comida >= 100 && this.producao >= 50) {
            this.nivel++;
            this.comida -= 100;
            this.producao -= 50;
            System.out.println("Cidade evoluiu para o nível " + this.nivel);
        } else {
            System.out.println("Recursos insuficientes para evoluir a cidade.");
        }
    }

    public int getNivel() {
        return nivel;
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

            if (novoX >= 0 && novoX < mapa.getTamanho() && novoY >= 0 && novoY < mapa.getTamanho() && !(mapa.getMapa()[novoX][novoY] instanceof Tropa)) {
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
            int distancia = Math.abs(x - this.X) + Math.abs(y - this.Y);
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

        System.out.println("Recursos recolhidos. Comida: " + this.comida + ", Produção: " + this.producao);
    }

}*/
package com.mycompany.projeto_civ_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cidades extends Terreno {
    private int nivel;
    private int comida;
    private int producao;
    private int X;
    private int Y;
    private int raioDominacao;
    private List<int[]> posicoesCivis; // Lista para armazenar as posições dos civis
    private List<String> edificios; // Lista para armazenar os edifícios

    public Cidades() {
        super("Cidade", 'C', 0, 0);
        this.nivel = 1;
        this.comida = 0;
        this.producao = 0;
        this.raioDominacao = 3; // Define o raio de dominação da cidade
        this.posicoesCivis = new ArrayList<>(); // Inicializa a lista de posições dos civis
        this.edificios = new ArrayList<>(); // Inicializa a lista de edifícios
    }

    // Getters para comida e produção
    public int getComida() {
        return comida;
    }

    public int getProducao() {
        return producao;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }

    public void evoluirNivel() {
        if (this.comida >= 100 && this.producao >= 50) {
            this.nivel++;
            this.comida -= 100;
            this.producao -= 50;
            System.out.println("Cidade evoluiu para o nível " + this.nivel);
        } else {
            System.out.println("Recursos insuficientes para evoluir a cidade.");
        }
    }

    public int getNivel() {
        return nivel;
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

            if (novoX >= 0 && novoX < mapa.getTamanho() && novoY >= 0 && novoY < mapa.getTamanho() && !(mapa.getMapa()[novoX][novoY] instanceof Tropa)) {
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
            int distancia = Math.abs(x - this.X) + Math.abs(y - this.Y);
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

        System.out.println("Recursos recolhidos. Comida: " + this.comida + ", Produção: " + this.producao);
    }

    // Métodos para listar e construir edifícios
    public List<String> getEdificios() {
        return edificios;
    }

    public void construirEdificio(String edificio) {
        edificios.add(edificio);
    }

    public void listarEdificios() {
        if (edificios.isEmpty()) {
            System.out.println("Nenhum edifício construído.");
        } else {
            System.out.println("Edifícios construídos:");
            for (String edificio : edificios) {
                System.out.println("- " + edificio);
            }
        }
    }
}


/*
import java.util.HashMap;
import java.util.Map;

public class Cidades extends Terreno {
    private int nivel;
    private int comida;
    private int producao;
    private int X;
    private int Y;
    private int raioDominacao;
    private List<int[]> posicoesCivis; // Lista para armazenar as posições dos civis
    private List<String> edificios; // Lista para armazenar os edifícios
    private Map<String, int[]> producaoEdificios; // Mapa para armazenar a produção de recursos por edifício

    public Cidades() {
        super("Cidade", 'C', 0, 0);
        this.nivel = 1;
        this.comida = 0;
        this.producao = 0;
        this.raioDominacao = 3; // Define o raio de dominação da cidade
        this.posicoesCivis = new ArrayList<>(); // Inicializa a lista de posições dos civis
        this.edificios = new ArrayList<>(); // Inicializa a lista de edifícios
        this.producaoEdificios = new HashMap<>(); // Inicializa o mapa de produção de edifícios

        // Adiciona a produção de recursos para cada tipo de edifício
        producaoEdificios.put("Serraria", new int[]{0, 10}); // Serraria produz 10 de produção
        producaoEdificios.put("Talho", new int[]{10, 0}); // Talho produz 10 de comida
    }

    // Getters para comida e produção
    public int getComida() {
        return comida;
    }

    public int getProducao() {
        return producao;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int getCustoMovimento() {
        return 1;
    }

    public void evoluirNivel() {
        if (this.comida >= 100 && this.producao >= 50) {
            this.nivel++;
            this.comida -= 100;
            this.producao -= 50;
            System.out.println("Cidade evoluiu para o nível " + this.nivel);
        } else {
            System.out.println("Recursos insuficientes para evoluir a cidade.");
        }
    }

    public int getNivel() {
        return nivel;
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

            if (novoX >= 0 && novoX < mapa.getTamanho() && novoY >= 0 && novoY < mapa.getTamanho() && !(mapa.getMapa()[novoX][novoY] instanceof Tropa)) {
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
            int distancia = Math.abs(x - this.X) + Math.abs(y - this.Y);
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

        // Adiciona a produção dos edifícios
        for (String edificio : edificios) {
            int[] producaoEdificio = producaoEdificios.get(edificio);
            this.comida += producaoEdificio[0];
            this.producao += producaoEdificio[1];
        }

        System.out.println("Recursos recolhidos. Comida: " + this.comida + ", Produção: " + this.producao);
    }

    // Métodos para listar e construir edifícios
    public List<String> getEdificios() {
        return edificios;
    }

    public void construirEdificio(String edificio) {
        edificios.add(edificio);
    }

    public void listarEdificios() {
        if (edificios.isEmpty()) {
            System.out.println("Nenhum edifício construído.");
        } else {
            System.out.println("Edifícios construídos:");
            for (String edificio : edificios) {
                System.out.println("- " + edificio);
            }
        }
    }
}
 */