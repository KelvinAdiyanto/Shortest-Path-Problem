package com.springframework.sp.node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node> {
    private final String name;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node> ShortestPath = new LinkedList<Node>();
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    
    public Node(String name) {
        this.name = name;
    }

    public void addAdjacentNode(Node node, int Weight) {
        adjacentNodes.put(node, Weight);
    }

    public Map getAdjacentNode() {
        return adjacentNodes;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

    public String getName() {
        return name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return ShortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        ShortestPath = shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

}