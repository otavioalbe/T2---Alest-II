package com.otavio;

import java.util.ArrayList;

public class Viagens {

    private ArrayList<ArrayList<Character>> mapa;
    private Nodo[] portos = new Nodo[9];
    private Grafo grafo;
    private int fileiras;
    private int colunas;
    
    public Viagens(String nomeArquivo) {
        this.grafo = new Grafo();
        lerArquivo(nomeArquivo);
    }

    public void lerArquivo(String nomeArquivo){
        try{
            In arq = new In(nomeArquivo);
            String linha = arq.readLine();
            String[] tamanho = linha.split(" ");
            int y = Integer.parseInt(tamanho[0]);
            int x = Integer.parseInt(tamanho[1]);
            mapa = new ArrayList<>(y);
            fileiras = y;
            colunas = x;
            
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
        double tempo = System.currentTimeMillis();
        criacaoNodos();
        navegar();
        //imprimirMapa();
        tempo = System.currentTimeMillis() - tempo;
        System.out.println(tempo*0.001 + " segundos");
    }


    private void criacaoNodos() {
        for(int i = 0; i < fileiras; i++){
            for(int j = 0; j < colunas; j++){
                Character atual = mapa.get(i).get(j);
                Nodo nodo;
                if (Character.isDigit(atual)) {
                    nodo = new Nodo(atual, i, j);
                    grafo.addNodo(nodo);
                    int a = Integer.parseInt(Character.toString(atual));
                    portos[a - 1] = nodo;
                } else if (atual == '.'){
                    nodo = new Nodo(i, j);
                    grafo.addNodo(nodo);
                }
            }
        }
    }
    
    public void navegar(){
        int combustivelMinimo = 0;
        int last = 0;
        int combustivelTotal = 0;
        int j = 1;
        int k = 0;
        for(int i = 0; i < portos.length-1; i++){
            if(k > 0){
                while(grafo.DijkstraAlgoritmo(this.portos[i-1], this.portos[i+j+k-1]) == 0){
                    System.out.println((i) + " --> " + (i+j+k) + " | Sem caminho | Combustível: " + combustivelMinimo);
                    k++;
                }
                combustivelMinimo = grafo.DijkstraAlgoritmo(this.portos[i-1], this.portos[i+j+k-1]);
                System.out.println((i) + " --> " + (i+j+k) + " | Combustível: " + combustivelMinimo);
                if(k>1){
                    i++;
                    k=0;
                }else
                    k=0;
                combustivelTotal = combustivelTotal + combustivelMinimo;
            }else{
                combustivelMinimo = grafo.DijkstraAlgoritmo(this.portos[i], this.portos[i+j]);
                if(combustivelMinimo != 0){
                    System.out.println((i+1) + " --> " + (i+j+1) + " | Combustível: " + combustivelMinimo);
                }
                if(combustivelMinimo == 0){
                    System.out.println((i+1) + " --> " + (i+j+1) + " | Sem caminho | Combustível: " + combustivelMinimo);
                    k++;
                }
                if(i == 7){
                    last = grafo.DijkstraAlgoritmo(this.portos[i+1-k], this.portos[0]);
                    System.out.println((i+2-k) + " --> " + 1 + " | Combustível: " + last);
                }
                combustivelTotal = combustivelTotal + combustivelMinimo + last;
            }
        }
        System.out.println("Combustível total usado: " + combustivelTotal);
    }

    public void imprimirMapa(){
        System.out.println("\nIMPRESSÃO DO MAPA:");
        for(int i = 0; i < mapa.size(); i++){
            System.out.println("");
            for(int j = 0; j < mapa.get(i).size(); j++) {
                System.out.print(mapa.get(i).get(j));
            }
        }
        System.out.println("");
    }
}
