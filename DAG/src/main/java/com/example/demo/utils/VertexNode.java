package com.example.demo.utils;

public class VertexNode {
    private String data;//储存具体信息，即模块名称
    private EdgeNode firstEdge;//指向第一个边
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public EdgeNode getFirstEdge() {
        return firstEdge;
    }
    
    public void setFirstEdge(EdgeNode firstEdge) {
        this.firstEdge = firstEdge;
    }
    
    public VertexNode(String data) {
        this.data = data;
        this.firstEdge = null;
    }
}
