/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;
import static java.lang.Math.random;
import java.util.Random;
/**
 *
 * @author franc
 */
public class mapa {
    private int tamanho;
    private Terreno[][] mapa;
    public int getTamanho() {
    return tamanho;
}
    
    public void definirTamanhoAleatorio(){
      Random random = new Random();
      this.tamanho = random.nextInt(51)+10;
      System.out.println("Tamanho do mapa: " + tamanho + "X" + tamanho);
      mapa = new Terreno [tamanho][tamanho];
    }
    
    public  void GerarMap (int tamanho){
        Random random = new Random();
        if (mapa == null) {
    throw new IllegalStateException("O array 'mapa' não foi inicializado!");
    }
        // Gera o mapa preenchendo a matriz com terrenos aleatórios
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                mapa[i][j] = gerarTerrenoAleatorio(random);
            }
        }
        
        // Exibe o mapa no console
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(mapa[i][j].getSimbolo() + " ");
            }
            System.out.println();
        }
    }
   
    private Terreno gerarTerrenoAleatorio(Random random){
        int sorteio = random.nextInt(100);
        if (sorteio < 5){
            return new Mar();
        }else if (sorteio <50){
            return new Planice();
        }else if (sorteio < 80){
            return new Floresta();
        /*}else if (sorteio < 5){
            return new Montanha();*/
        
        }else{
            return new Deserto();
        }
    }
}




