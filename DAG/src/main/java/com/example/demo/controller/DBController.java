package com.example.demo.controller;

import com.example.demo.entity.Inner;
import com.example.demo.entity.Outer;
import com.example.demo.service.BuildService;
import com.example.demo.service.GraphService;
import com.example.demo.service.DBService;
import com.example.demo.utils.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//声明用于处理HTTP响应
@RestController
public class DBController {
    //自动实例化Service类
    @Autowired
    private DBService DBService;
    @Autowired
    private GraphService graphService;
    @Autowired
    private BuildService buildService;
    
    //查询全部依赖，直接打印表
    @RequestMapping("/dependencies")
    public List<Inner> getDependencies(){
        return DBService.findAll();
    }
    
    @RequestMapping("/modules")
    public List<Outer> queryByModule(){
        return DBService.queryByModule();
    }
    
    //查询节点映射
    @RequestMapping("/map")
    public Map<String,Integer> getMap(){
        Graph graph=graphService.constructGraph();
        return graph.getMap();
    }
    
    //打印邻接表
    @RequestMapping("/graph")
    public List<String> showGraph(){
        Graph graph=graphService.constructGraph();
        return graph.show();
    }
    
    //依赖分析
    @RequestMapping("/analyze")
    public String analyze(){
        Graph graph=graphService.constructGraph();
        if(graphService.topoAnalyse(graph)){
            return "成功，无循环依赖\n\n";
        } else {
            return "失败，发现循环依赖\n\n";
        }
    }
    
    //生成xml文件
    @RequestMapping("/build")
    public String build(){
        Graph graph=graphService.constructGraph();
        if(buildService.build(graph)){
            return "所有项目的xml文件均已生成完毕";
        }
        else {
            return "生成失败";
        }
    }
    
}
