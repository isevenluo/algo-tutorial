package org.luoxiaohei.java.queue;

/**
 * @description: 基于链表实现队列
 * @outhor: luoxiaohei
 * @create: 2020-04-21 0:22
 */
public class QueueBasedOnLinkedList {

    public static void main(String[] args) {
        QueueBasedOnLinkedList queueBasedOnLinkedList = new QueueBasedOnLinkedList();
        queueBasedOnLinkedList.enqueue("1");
        queueBasedOnLinkedList.enqueue("2");
        queueBasedOnLinkedList.enqueue("3");
        queueBasedOnLinkedList.enqueue("4");
        queueBasedOnLinkedList.enqueue("5");
        queueBasedOnLinkedList.printAll();
        queueBasedOnLinkedList.dequeue();
        queueBasedOnLinkedList.dequeue();
        queueBasedOnLinkedList.printAll();

    }

    private Node head;
    private Node tail;

    // 入队，tail->next= new_node, tail = tail->next
    public void enqueue(String value) {
        Node newNode = new Node(value,null);
        if (this.tail == null) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }

    public String dequeue() {
        if (head == null) {
            return null;
        }
        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public void printAll() {
        // 必须新声明一个节点，如果直接使用head则会改变当前队列的头节点
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }
}
