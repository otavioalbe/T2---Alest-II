package com.otavio;

import java.util.*;

public class Grafo {
    private final ArrayList<Aresta> arestas;
    private final HashMap<String, Nodo> nodos;

    public Grafo() {
        this.arestas = new ArrayList<>();
        this.nodos = new HashMap<>();
    }

    public void addAresta(Nodo origem, Nodo destino) {
        Aresta aresta = new Aresta(1, origem, destino);

        origem.addVizinhos(destino);
        destino.addVizinhos(origem);
        this.arestas.add(aresta);
    }

    public void addNodo(Nodo nodo) {
        String key = nodo.getX() + "-" + nodo.getY();
        this.nodos.put(key, nodo);
        this.criaArestas(nodo);
    }

    public Nodo getNodo(int x, int y) {
        String key = x + "-" + y;
        return nodos.get(key);
    }

    public void criaArestas(Nodo n) {
        int x = n.getX();
        int y = n.getY();

        Nodo destino = this.getNodo(x, y - 1);
        if (destino != null) {
            this.addAresta(n, destino);
        }

        destino = this.getNodo(x - 1, y);
        if (destino != null) {
            this.addAresta(n, destino);
        }
    }

    public Aresta achaAresta(Nodo n1, Nodo n2) {
        for (Aresta aresta : arestas) {
            if ((aresta.getOrigem() == n1 || aresta.getDestino() == n1)
            &&  (aresta.getOrigem() == n2 || aresta.getDestino() == n2)){
                return aresta;
            }
        }
        return null;
    }

    public int DijkstraAlgoritmo(Nodo v1, Nodo v2) {

        Nodo nodoCaminho;
        Nodo atual;
        Nodo vizinho;
        Aresta ligacao;
        HashSet<Nodo> naoVisitados = new HashSet<>();

        String v1Key = v1.getX()+"-"+v1.getY();
        
        nodos.forEach((key, nodo) -> {
            if(Objects.equals(key, v1Key)){
                nodo.setDistancia(0);
                nodo.setVisitado(true);
            } else {
                nodo.setDistancia(9999);
                nodo.setVisitado(false);
            }

            naoVisitados.add(nodo);
        });

        while (!naoVisitados.isEmpty()) {
            atual = Collections.min(naoVisitados);

            for (int i = 0; i < atual.getVizinhos().size(); i++) {

                vizinho = atual.getVizinhos().get(i);

                if (!vizinho.isVisitado()) {

                    ligacao = this.achaAresta(atual,vizinho);
                    if (vizinho.getDistancia() > (atual.getDistancia() + ligacao.getPeso())) {

                        vizinho.setDistancia(atual.getDistancia()
                                + ligacao.getPeso());
                        vizinho.setPai(atual);

                        if (vizinho.getX() == v2.getX()
                                && vizinho.getY() == v2.getY()) {
                            nodoCaminho = vizinho;
                            while (nodoCaminho.getPai() != null) {
                                nodoCaminho = nodoCaminho.getPai();
                            }
                        }
                    }
                }

            }

            atual.setVisitado(true);
            naoVisitados.remove(atual);
        }

        this.limparVerticesPai();

        if(v2.getDistancia()==9999)
            return 0;
        return v2.getDistancia();
    }

    public void limparVerticesPai(){
        nodos.forEach((key, nodo) -> nodo.setPai(null));
    }

    public void grafoImpressao() {
        for (Nodo n : nodos.values()) {
            System.out.println(n);
        }
    }
}
