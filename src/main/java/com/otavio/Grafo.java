package com.otavio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    
    private Map<Character, List<Character>> graph;

    public Grafo() {
        graph = new HashMap<>();
    }

    public void addEdge(Character vertex1, Character vertex2) {
        graph.computeIfAbsent(vertex1, k -> new ArrayList<>()).add(vertex2);
    }

    public List<Character> getAdjacentVertices(Character vertex) {
        return graph.getOrDefault(vertex, new ArrayList<>());
    }
}
