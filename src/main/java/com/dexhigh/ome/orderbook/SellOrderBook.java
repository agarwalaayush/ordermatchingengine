package com.dexhigh.ome.orderbook;

import com.dexhigh.ome.order.Node;
import com.dexhigh.ome.order.Order;

public class SellOrderBook extends OrderBook {
    public Node root;

    public Order lowestSell(Node node) {

        if (node == null)
            return null;

        Order result = node.order;
        Order left_result = lowestSell(node.left);
        Order right_result = lowestSell(node.right);

        if (left_result != null && left_result.getPrice() < result.getPrice()) {
            result = left_result;
        }
        if (right_result != null && right_result.getPrice() < result.getPrice()) {
            result = right_result;
        }
        return result;
    }
}
