/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public abstract class Terreno {
    private String nome;
    private char simbolo;
    private int alimento;
    private int material;
    
    public Terreno(String nome, char simbolo, int alimento, int material){
        this.nome = nome;
        this.simbolo = simbolo;
        this.alimento = alimento;
        this.material = material;
    }
     public String getNome(){
         return nome;
     }
     public char getSimbolo(){
         return simbolo;
     }
     public int getAlimento(){
         return alimento;
     }
     public int getMaterial(){
         return material;
     }
     public abstract int getCustoMovimento();

}
