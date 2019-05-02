package com.dexhigh.ome.order;

public class Node {

    public Order order;
    public int height;
    public Node left, right;

    public Node (Order o) {
        order = o;
        height = 1;
    }
}