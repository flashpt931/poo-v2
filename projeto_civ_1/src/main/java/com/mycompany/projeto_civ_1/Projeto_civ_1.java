/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto_civ_1;

/**
 *
 * @author franc
 */
public class Projeto_civ_1 {
    public static void main(String[] args) {
        mapa Mapa = new mapa();
        Mapa.definirTamanhoAleatorio(); // Inicializa o tamanho e o array
        Mapa.GerarMap(Mapa.getTamanho()); // Usa o tamanho definido no objeto
    }
}