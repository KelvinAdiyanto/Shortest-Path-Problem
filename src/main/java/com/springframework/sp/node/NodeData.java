package com.springframework.sp.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NodeData {

    private List<Node> nodeList = new ArrayList<Node>();
    private List<Integer> data = new ArrayList<>();
    private int nodeAmount;
    private int counter = 0;
    private String result;

    private static void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(
                Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList()
            );
        }
    }

    public static void calculateShortestPath(Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<Node>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes().entrySet().stream().filter(entry -> !settledNodes.contains(entry.getKey())).forEach(entry -> {
                evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                unsettledNodes.add(entry.getKey());
            });
            settledNodes.add(currentNode);
        }
    }

    public static String getPaths(Node node) {
        String path = node.getShortestPath().stream().map(Node::getName).collect(Collectors.joining(" -> "));
        if (path.isBlank()) { 
            return "%s : %s".formatted(node.getName(), node.getDistance());
        } else {
            return "%s -> %s : %s".formatted(path, node.getName(), node.getDistance());
        }
    }

    public List<Integer> getWeight() {
        return data;
    }
    public void setWeight(ArrayList<Integer> data) {
        this.data = data;
    }
    public int getNodeAmount() {
        return nodeAmount;
    }
    public void setNodeAmount(Integer nodeAmount) {
        this.nodeAmount = nodeAmount;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void createNode() {
        for (int i=0; i<nodeAmount; i++) {
            nodeList.add(new Node(Character.toString((char) (65+i))));
        }
    }

    public void createAdjNode() {
        for (int i = 0; i < nodeAmount; i++) {
            for (int j = 0; j < nodeAmount; j++) {
                if (j != i) {
                    if (data.get(counter) != -1) {
                        nodeList.get(i).addAdjacentNode(nodeList.get(j), data.get(counter));
                        counter++;
                    } else if (data.get(counter) == -1) {
                        counter++;
                    }
                }
            }
        }
    }

    public void runApp() {
        createNode();
        createAdjNode();
        calculateShortestPath(nodeList.get(data.get(data.size()-2)-65));
        this.result = getPaths(nodeList.get(data.get(data.size()-1)-65));
    }

}
