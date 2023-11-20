package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    private ArrayList<VertexNode> vexList;
    private Map<String,Integer> map;//从名字到编号的逆映射
    private int numVexes;
    private int numEdges;
    
    public int getNumVexes() {
        return numVexes;
    }
    
    public void setNumVexes(int numVexes) {
        this.numVexes = numVexes;
    }
    
    public int getNumEdges() {
        return numEdges;
    }
    
    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }
    
    public ArrayList<VertexNode> getVexList() {
        return vexList;
    }
    
    public void setVexList(ArrayList<VertexNode> vexList) {
        this.vexList = vexList;
    }
    
    public Map<String, Integer> getMap() {
        return map;
    }
    
    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
    
    public Graph() {
        this.vexList = new ArrayList<VertexNode>();
        this.numVexes = 0;
        this.numEdges = 0;
    }
    
    //插入节点
    public void insertVex(VertexNode v){
        vexList.add(v);
        numVexes+=1;
        System.out.println("add vex"+numVexes);
    }
    //插入边
    public void insertEdge(EdgeNode e){
        //准备
        int tail=e.getTail();
        int head=e.getHead();
        VertexNode vex=vexList.get(tail);//获取起点
        //插入
        e.setNext(vex.getFirstEdge());//过河
        vex.setFirstEdge(e);//拆桥
        //计数
        numEdges+=1;
        System.out.println("add edge"+numEdges);
    }
    
    //返回一个字符串数组，单元格式为Vex+Edges
    public List<String> show(){
        List<String> result=new ArrayList<>();
        for(int i=0;i<numVexes;i++){
            String line;
            //打印头部
            VertexNode vex=vexList.get(i);
            line=i+":【"+vex.getData()+"】";
            //添加尾部
            EdgeNode e=vex.getFirstEdge();
            while(e != null){
                int target=e.getHead();
                line+=" -> "+vexList.get(target).getData()+"("+target+")";
                e=e.getNext();
            }
            //并入result
            result.add(line);
        }
        return result;
    }
}
