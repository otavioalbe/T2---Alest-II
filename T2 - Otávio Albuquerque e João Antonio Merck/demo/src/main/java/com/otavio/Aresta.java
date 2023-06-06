package com.otavio;

public class Aresta {
	
	private int peso;
	private Nodo origem;
	private Nodo destino;
	
	public Aresta(int peso, Nodo origem, Nodo destino) {
		this.setPeso(peso);
		this.setOrigem(origem);
		this.setDestino(destino);
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Nodo getOrigem() {
		return origem;
	}

	public void setOrigem(Nodo origem) {
		
		this.origem = origem;
	}

	public Nodo getDestino() {
		return destino;
	}

	public void setDestino(Nodo destino) {
		
		this.destino = destino;
	}

}