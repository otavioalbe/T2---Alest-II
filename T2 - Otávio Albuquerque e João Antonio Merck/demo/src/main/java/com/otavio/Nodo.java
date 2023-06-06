package com.otavio;

import java.util.ArrayList;

public class Nodo implements Comparable<Nodo> {
	private Character caracter;
	private int x;
	private int y;
	private int distancia = 0;
	private Nodo pai;
	private ArrayList<Nodo> vizinhos = new ArrayList<Nodo>();
	private boolean visitado = false;

	public Nodo(Character caracter, int x, int y){
		this.caracter = caracter;
		this.x = x;
		this.y = y;
	}
	public Nodo(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Character getChar(){
		return caracter;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	@Override
	public String toString(){
		return "Caracter: " + getChar();
	}
	
	public int getDistancia() {
		return distancia;
	}
	
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public Nodo getPai() {
		return pai;
	}
	
	public void setPai(Nodo pai) {
		this.pai = pai;
	}
	
	public boolean isVisitado() {
		return visitado;
	}
	
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	public void addVizinhos(Nodo vizinho) {
	 	this.vizinhos.add(vizinho);
	 }

	 public ArrayList<Nodo> getVizinhos() {
	 	return vizinhos;
	 }
	
	@Override
	public int compareTo(Nodo nodo) {
		
        if(this.getDistancia() < nodo.getDistancia())
        	return -1;
        else if(this.getDistancia() == nodo.getDistancia())
        	return 0;

        return 1;  
	}
}
