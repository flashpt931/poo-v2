/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Cidades extends Terreno{
    private int comida;
    private int producao;
    private int raioDominacao;
    public Cidades(){
        super ("Cidades",'C',0,0);
        this.comida = 0;
        this.producao = 0;
        this.raioDominacao = 2;
    }
    public int getRaioDominacao() {
        return raioDominacao;
    }

    public void setRaioDominacao(int raioDominacao) {
        this.raioDominacao = raioDominacao;
    }

    // Método para calcular comida e produção com base nos recursos na área de dominação
    public void calcularRecursos(int posX, int posY, Terreno[][] mapa) {
        int tamanhoMapa = mapa.length;

        int totalAlimento = 0;
        int totalMaterial = 0;

        // Percorre o quadrado ao redor da cidade
        for (int i = posX - raioDominacao; i <= posX + raioDominacao; i++) {
            for (int j = posY - raioDominacao; j <= posY + raioDominacao; j++) {
                // Verifica se a posição está dentro dos limites do mapa
                if (i >= 0 && i < tamanhoMapa && j >= 0 && j < tamanhoMapa) {
                    // Soma os recursos do terreno
                    totalAlimento += mapa[i][j].getAlimento();
                    totalMaterial += mapa[i][j].getMaterial();
                }
            }
        }

        // Atualiza comida e produção com os totais calculados
        this.comida = totalAlimento; // Alimento convertido diretamente em comida
        this.producao = totalMaterial; // Material convertido diretamente em produção
    }

    // Getters para comida e produção
    public int getComida() {
        return comida;
    }

    public int getProducao() {
        return producao;
    }
    @Override
    public int getCustoMovimento(){
        return 1;
    }
}
    
    
