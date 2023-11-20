package com.example.demo.service;

import com.example.demo.entity.Outer;
import com.example.demo.utils.EdgeNode;
import com.example.demo.utils.Graph;
import com.example.demo.utils.VertexNode;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildService {
    //基于数据库服务
    @Autowired
    private DBService dbService;
    
    //配置信息
    private final String root="./multimod";//目标项目根目录
    private final String projectName="multimod";//项目名
    private final String group="com.example";//项目组名
    private final String version="0.0.1-SNAPSHOT";//项目统一版本号
    
    //生成依赖
    //输出到对应目录，可以在数据库里面指定一个输出目录字段
    public boolean build(Graph graph){
        boolean result=true;
        result&=buildFather(graph);
        result&=buildChild(graph);
        
        return result;
    }
    
    //父工程的pom构建函数
    public boolean buildFather(Graph graph){
        Model model = new Model();
        //项目信息
        model.setModelVersion("4.0.0");
        model.setGroupId(group);
        model.setArtifactId(projectName);
        model.setVersion(version);
        model.setName(projectName);
        model.setPackaging("pom");//设置为pom打包方式
        model.addProperty("java.version","17");//统一声明版本
        
        //设置parent
        Parent parent=new Parent();
        parent.setGroupId("org.springframework.boot");
        parent.setArtifactId("spring-boot-starter-parent");
        parent.setVersion("3.1.5");
        parent.setRelativePath("");//置空relative标签，从repo获取父pom
        model.setParent(parent);
        
        //添加子模块信息
        List<VertexNode> vexList=graph.getVexList();
        for (VertexNode vex:vexList){
            model.addModule(vex.getData());//<module>标签
        }
        
        //父模块设置可继承的统一依赖管理
        List<Dependency> dependencies=new ArrayList<>();
        DependencyManagement dependencyManagement=new DependencyManagement();
        
        //外部依赖填充
        List<Outer> outerList=dbService.queryByModule();//外部依赖
        for(Outer outer:outerList){
            //如果项目的外部依赖则填入，此处用projectName筛选
            System.out.println(outer.getModule());
            if (outer.getModule().equals(projectName)){
                Dependency dependency = new Dependency();
                //从outer中提取信息，两项必填
                dependency.setGroupId(outer.getGroupId());
                dependency.setArtifactId(outer.getArtifactId());
                //其余三项，选填
                if (!"".equals(outer.getVersion())){
                    dependency.setVersion(outer.getVersion());
                }
                if(!"".equals(outer.getScope())){
                    dependency.setScope(outer.getScope());
                }
                if(!"".equals(outer.getOptional())){
                    dependency.setOptional(outer.getOptional());
                }
                
                dependencies.add(dependency);
            }
        }
        
        //设置为DependencyManagement
        dependencyManagement.setDependencies(dependencies);
        model.setDependencyManagement(dependencyManagement);
        
        // 将模型写入pom.xml文件
        try {
            MavenXpp3Writer writer = new MavenXpp3Writer();
            writer.write(new FileWriter(new File(root+"/pom.xml")), model);
            System.out.println("pom.xml文件已生成成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        System.out.println("构建父工程:"+projectName+" 成功");
        return true;
    }
    
    //所有子模块的pom构建函数
    public boolean buildChild(Graph graph){
        
        List<VertexNode> vexList=graph.getVexList();//项目内部模块
        List<Outer> outerList=dbService.queryByModule();//外部依赖
        
        //每个模块都制造一个xml
        for (VertexNode vex:vexList){
            // 创建一个Maven项目模型
            Model model = new Model();
            //项目基本信息
            String module=vex.getData();
            model.setModelVersion("4.0.0");
            model.setGroupId(group);
            model.setArtifactId(module);//填入模块名
            model.setVersion(version);
            model.setName(vex.getData());
            
            //设置parent
            Parent parent=new Parent();
            parent.setGroupId(group);
            parent.setArtifactId(projectName);//固定为项目名
            parent.setVersion(version);
            model.setParent(parent);
            
            //依赖填充
            List<Dependency> dependencies=new ArrayList<>();
            //内部依赖填充
            EdgeNode edge=vex.getFirstEdge();
            while (edge!=null){
                Dependency dependency = new Dependency();
                int head=edge.getHead();
                
                //填入被依赖模块信息
                dependency.setGroupId(group);
                dependency.setArtifactId(vexList.get(head).getData());
                dependency.setVersion(version);
                
                dependencies.add(dependency);
                edge=edge.getNext();
            }
            
            //外部依赖填充
            for(Outer outer:outerList){
                //如果是本模块的外部依赖则填入
                if (outer.getModule().equals(module)){
                    Dependency dependency = new Dependency();
                    //从outer中提取信息，两项必填
                    dependency.setGroupId(outer.getGroupId());
                    dependency.setArtifactId(outer.getArtifactId());
                    //其余三项，选填
                    if (!"".equals(outer.getVersion())){
                        dependency.setVersion(outer.getVersion());
                    }
                    if(!"".equals(outer.getScope())){
                        dependency.setScope(outer.getScope());
                    }
                    if(!"".equals(outer.getOptional())){
                        dependency.setOptional(outer.getOptional());
                    }
                    
                    dependencies.add(dependency);
                }
            }
            
            //装载依赖列表
            model.setDependencies(dependencies);
            
            // 将模型写入pom.xml文件
            try {
                MavenXpp3Writer writer = new MavenXpp3Writer();
                writer.write(new FileWriter(new File(root+"/"+module+"/pom.xml")), model);
                System.out.println("pom.xml文件已生成成功！");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            
            System.out.println("构建模块："+module+" 成功");
        }
        
        return true;
    }
}
