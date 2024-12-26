/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author Flash
 */
public class colono {
    public colono (){
        super ("colono",'H');
        
    }
    
     @Override
   public int getCustoMovimento(){
       return 3;
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

        // Cria a cidade na posição do colono
        mapa[posX][posY] = new Cidades();
        System.out.println("Cidade criada com sucesso na posição (" + posX + ", " + posY + ").");
        return true;
    }
 
}
