package com.otavio;

import java.util.ArrayList;

public class Viagens {

    private ArrayList<ArrayList<Character>> mapa;;
    private final Porto[] portos = new Porto[9];

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

        imprimirMapa();


        // System.out.println(mapa.get(14));
        // System.out.println(mapa.get(14).get(12));
        // System.out.println(mapa.size());
    }


    public void achaPortos(){
        for(int i = 0; i < mapa.size(); i++){
            for(int j = 0; j < mapa.get(i).size(); j++) {
                int number_ = mapa.get(i).get(j);
                if ((number_ >= 49 && number_ <= 57) && (mapa.get(i-1).get(j) != '*' || mapa.get(i+1).get(j) != '*'
                || mapa.get(i).get(j-1) != '*' || mapa.get(i).get(j+1) != '*')){
                    int porto = Integer.parseInt(Character.toString((char) number_)) - 1;
                    portos[porto] = new Porto(mapa.get(i).get(j),i,j);
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