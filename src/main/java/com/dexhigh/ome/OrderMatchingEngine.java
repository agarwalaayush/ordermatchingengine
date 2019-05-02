package com.dexhigh.ome;

import com.dexhigh.ome.order.Order;
import com.dexhigh.ome.order.OrderType;
import com.dexhigh.ome.orderbook.BuyOrderBook;
import com.dexhigh.ome.orderbook.SellOrderBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OrderMatchingEngine {

    /*public static void main(String args[]) {
        BuyOrderBook tree = new BuyOrderBook();


        tree.root = tree.addOrder(tree.root, new Order(OrderType.BUY, 10, 1000));
        tree.root = tree.addOrder(tree.root, new Order(OrderType.BUY, 20, 2000));
        tree.root = tree.addOrder(tree.root, new Order(OrderType.BUY, 30, 3000));
        tree.root = tree.addOrder(tree.root, new Order(OrderType.BUY, 40, 4000));
        tree.root = tree.addOrder(tree.root, new Order(OrderType.BUY, 50, 5000));
        tree.root = tree.addOrder(tree.root, new Order(OrderType.BUY, 60, 6000));
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
        System.out.println("\nHighest BUY:" + tree.highestBuy(tree.root));
        tree.root = tree.cancelOrder(tree.root, 1);
        System.out.println("\nPreorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
        System.out.println("\nHighest BUY:" + tree.highestBuy(tree.root));
        tree.root = tree.cancelAll();
        System.out.println("\nPreorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
        Order highest_buy = tree.highestBuy(tree.root);
        System.out.println("\nHighest BUY:" + ((highest_buy != null) ? highest_buy : "NA"));
        tree.root = tree.addOrder(tree.root, new Order(OrderType.BUY, 70, 7000));
        System.out.println("\nPreorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
        System.out.println("\nHighest BUY:" + tree.highestBuy(tree.root));
        SellOrderBook tree1 = new SellOrderBook();

    tree1.root =tree1.addOrder(tree1.root,new

    Order(OrderType.SELL, 10,1000));
    tree1.root =tree1.addOrder(tree1.root,new

    Order(OrderType.SELL, 20,2000));
    tree1.root =tree1.addOrder(tree1.root,new

    Order(OrderType.SELL, 30,3000));
        System.out.println("\nPreorder traversal"+
                " of constructed tree is : ");
        tree.preOrder(tree1.root);
    Order lowest_sell = tree1.lowestSell(tree1.root);
        System.out.println("\nLowest SELL:"+((lowest_sell !=null)?lowest_sell :"NA"));
}*/
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ch;
        BuyOrderBook buyOrderBook = new BuyOrderBook();
        SellOrderBook sellOrderBook = new SellOrderBook();

        do {
            System.out.println("***Select an option from below***");
            System.out.println("1. Add Order");
            System.out.println("2. Execute Order");
            System.out.println("3. Cancel Order");
            System.out.println("4. Cancel ALL Orders");
            System.out.println("5. Get Lowest Sell");
            System.out.println("6. Get Highest Buy");
            System.out.println("7. Exit");
            ch = Integer.parseInt(br.readLine());

            switch (ch) {
                case 1:
                    System.out.println("Enter Order Type (BUY/SELL)");
                    String s = br.readLine();
                    OrderType type = s.equalsIgnoreCase("BUY") ? OrderType.BUY : OrderType.SELL;
                    System.out.println("Enter the Order quantity");
                    int qty = Integer.parseInt(br.readLine());
                    System.out.println("Enter the Order price");
                    double price = Double.parseDouble(br.readLine());

                    Order order = new Order(type, qty, price);
                    if (type == OrderType.BUY) {
                        buyOrderBook.root = buyOrderBook.addOrder(buyOrderBook.root, order);
                    } else {
                        sellOrderBook.root = sellOrderBook.addOrder(sellOrderBook.root, order);
                    }
                    System.out.println("Order added. Id: " + order.getId());
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Enter Order ID to be cancelled");
                    int id = Integer.parseInt(br.readLine());
                    if (buyOrderBook.cancelOrder(buyOrderBook.root, id) == null && sellOrderBook.cancelOrder(sellOrderBook.root, id) == null) {
                        System.out.println("Order ID:" + id + " not found in Order Book");
                    } else {
                        System.out.println("Order ID:" + id + " successfully cancelled");
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.exit(0);
            }
        }
        while (ch >= 1 && ch <= 6);
    }
}