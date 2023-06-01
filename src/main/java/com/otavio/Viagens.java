package com.otavio;

import java.util.ArrayList;
import java.util.List;

public class Viagens {

    private ArrayList<ArrayList<Character>> mapa;
    private final Porto[] portos = new Porto[9];
    private Grafo mapGraph = new Grafo();
    private int fileiras;
    private int colunas;
    

    public void lerArquivo(String nomeArquivo){
        try{
            In arq = new In(nomeArquivo);
            String linha = arq.readLine();
            String[] tamanho = linha.split(" ");
            int y = Integer.parseInt(tamanho[0]);
            int x = Integer.parseInt(tamanho[1]);
            mapa = new ArrayList<>(y);
            
            for(int i = 0; i < y; i++){
                linha = arq.readLine();
                tamanho = linha.split("");
                mapa.add(new ArrayList<Character>());
                for(int j = 0; j < x; j++){
                    mapa.get(i).add(tamanho[j].charAt(0));
                }
            }
            
        }catch(Exception e){
            System.err.println("Erro: " + e);
        }
        achaPortos();
        linkarVertices();
        imprimirMapa();
    }


    private void linkarVertices() {
        for(int i = 0; i < fileiras; i++){
            for(int j = 0; j < colunas; j++){
                char atual = mapa.get(i).get(j);
                if (i > 0 && mapa.get(i-1).get(j) != '*') {
                    mapGraph.addEdge(atual, mapa.get(i-1).get(j));
                }
                if (i < fileiras - 1 && mapa.get(i+1).get(j) != '*') {
                    mapGraph.addEdge(atual, mapa.get(i+1).get(j));
                }
                if (j > 0 && mapa.get(i).get(j-1) != '*') {
                    mapGraph.addEdge(atual, mapa.get(i).get(j-1));
                }
                if (j < colunas - 1 && mapa.get(i).get(j+1) != '*') {
                    mapGraph.addEdge(atual, mapa.get(i).get(j+1));
                }
            }
        }
        List<Character> adjacentVertices = mapGraph.getAdjacentVertices('6');
        System.out.println("Adjacentes de '1': " + adjacentVertices);
    }


    private void achaPortos(){
        fileiras = mapa.size();
        colunas = mapa.get(0).size();
        for(int i = 0; i < fileiras; i++){
            for(int j = 0; j < colunas; j++) {
                int number_ = mapa.get(i).get(j);
                if (number_ >= 49 && number_ <= 57){
                    int porto = Integer.parseInt(Character.toString((char) number_)) - 1;
                    portos[porto] = new Porto(mapa.get(i).get(j),j,i);
                }
            }
        }
        for(int i = 0; i < portos.length; i++){
            System.out.println(portos[i]);
        }
    }

    public void imprimirMapa(){
        System.out.println("IMPRESSÃO DO MAPA:");
        for(int i = 0; i < mapa.size(); i++){
            System.out.println("");
            for(int j = 0; j < mapa.get(i).size(); j++) {
                System.out.print(mapa.get(i).get(j));
            }
        }
        System.out.println("");
    }
}