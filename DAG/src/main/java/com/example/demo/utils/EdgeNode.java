package com.example.demo.utils;

public class EdgeNode {
    private int tail;
    private int head;
    private EdgeNode next;//这个是引用
    
    public int getTail() {
        return tail;
    }
    
    public void setTail(int tail) {
        this.tail = tail;
    }
    
    public int getHead() {
        return head;
    }
    
    public void setHead(int head) {
        this.head = head;
    }
    
    public EdgeNode getNext() {
        return next;
    }
    
    public void setNext(EdgeNode next) {
        this.next = next;
    }
    
    //next自己指定
    public EdgeNode(int tail, int head) {
        this.tail = tail;
        this.head = head;
        this.next = null;
    }
}
