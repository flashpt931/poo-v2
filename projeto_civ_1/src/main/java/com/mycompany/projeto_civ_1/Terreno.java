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
    
    public Terreno(String nome, char simbolo){
        this.nome = nome;
        this.simbolo = simbolo;
    }
     public String getNome(){
         return nome;
     }
     public char getSimbolo(){
         return simbolo;
     }
     public abstract int getCustoMovimento();

}
