package com.dexhigh.ome.orderbook;

import com.dexhigh.ome.order.Node;
import com.dexhigh.ome.order.Order;

public class BuyOrderBook extends OrderBook {
    public Node root;

    public Order highestBuy(Node node) {

        if (node == null)
            return null;

        Order result = node.order;
        Order left_result = highestBuy(node.left);
        Order right_result = highestBuy(node.right);

        if (left_result != null && left_result.getPrice() > result.getPrice()) {
            result = left_result;
        }
        if (right_result != null && right_result.getPrice() > result.getPrice()) {
            result = right_result;
        }
        return result;
    }
}