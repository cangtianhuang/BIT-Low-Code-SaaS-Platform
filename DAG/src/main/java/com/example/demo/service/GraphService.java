package com.example.demo.service;

import com.example.demo.entity.Inner;
import com.example.demo.utils.EdgeNode;
import com.example.demo.utils.Graph;
import com.example.demo.utils.VertexNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class GraphService {
    //数据库服务
    @Autowired
    private DBService DBService;
    //从数据库抽取数据，构图
    public Graph constructGraph(){
        //初始图
        Graph graph =new Graph();
        //抽取数据
        List<Inner> inners = DBService.findAll();
        //制定Map和节点
        Map<String,Integer> map=new HashMap<String,Integer>();
        int k=0;//节点的序号
        for (int i = 0; i< inners.size(); i++){
            Inner inner = inners.get(i);
            //没见过，则加入map，并插入节点
            if (!map.containsKey(inner.getMain())){
                String key= inner.getMain();
                map.put(key,k);
                graph.insertVex(new VertexNode(key));//节点下标与k一致
                k+=1;
            }
            if(!map.containsKey(inner.getSub())){
                String key= inner.getSub();
                map.put(key,k);
                graph.insertVex(new VertexNode(key));//节点下标与k一致
                k+=1;
            }
        }
        graph.setMap(map);
        //添加边
        for (int i = 0; i< inners.size(); i++){
            Inner inner = inners.get(i);
            int tail=map.get(inner.getMain());//Map逆映射vex下标
            int head=map.get(inner.getSub());
            VertexNode vex=graph.getVexList().get(tail);
            
            EdgeNode e=new EdgeNode(tail,head);
            e.setNext(vex.getFirstEdge());
            vex.setFirstEdge(e);
            
            System.out.println("Insert Edge "+tail+" to "+head);
        }
        
        return graph;
    }
    
    //输入图，拓扑排序分析循环依赖
    public boolean topoAnalyse(Graph graph){
        //健壮
        if(graph==null){
            return false;
        }
        //计算入度
        List<VertexNode> vexList=graph.getVexList();
        int numVexes=graph.getNumVexes();
        int[] inDegree=new int[numVexes];
        for (int i=0;i<numVexes;i++){
            //遍历一行后继
            EdgeNode e=vexList.get(i).getFirstEdge();
            while (e!=null){
                inDegree[e.getHead()]+=1;
                e=e.getNext();
            }
        }
        /*
        for (int i=0;i<inDegree.length;i++){
            System.out.println(""+i+inDegree[i]);
        }*/
        
        //拓扑排序
        int count=0;//指向拓扑输出序列下标，同时计数
        int[] result=new int[numVexes];
        Stack<Integer> work= new Stack<>();
        //初始化（压入0度节点）
        for (int i=0;i<numVexes;i++){
            if(inDegree[i]==0){
                work.push(i);
            }
        }
        //排序
        while(!work.empty()){
            //输出
            int i=work.pop();
            result[count]=i;
            count+=1;
            //除边（同时压入新的0入度节点）
            EdgeNode e=vexList.get(i).getFirstEdge();
            while(e!=null){
                int head=e.getHead();
                inDegree[head]-=1;
                if(inDegree[head]==0){//无入度，则压入work
                    work.push(head);
                }
                e=e.getNext();
            }
        }
        //判断回路
        for (int j : result) {
            System.out.println(j);
        }
        
        return count == numVexes;
    }
    
}
