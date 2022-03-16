package com.qiang.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式 处理树结构 8 3   6 1 4 5 7
 *
 * @author: liq
 * @date: 2021/6/10 21:24
 */
public class CompositeTest {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        Node node3 = new Node(3).addChild(node1).addChild(node4);
        Node node6 = new Node(6).addChild(node5).addChild(node7);
        Node node8 = new Node(8).addChild(node3).addChild(node6);
        node8.show();
    }

}

class Node {
    private int value;
    private List<Node> child = new ArrayList<>();

    public int getValue() {
        return value;
    }

    public List<Node> getChild() {
        return child;
    }

    public void setChild(List<Node> child) {
        this.child = child;
    }

    public Node(int value) {
        this.value = value;
    }

    public Node addChild(Node e) {
        child.add(e);
        return this;
    }

    public void show() {
        show(this);
    }

    private void show(Node e) {
        System.out.print(e.getValue() + " ");
        List<Node> nodes = e.getChild();
        if (nodes != null && !nodes.isEmpty()) {
            System.out.println();
            for (Node node : nodes) {
                show(node);
            }
        }
    }
}
