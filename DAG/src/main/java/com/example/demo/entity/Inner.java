package com.example.demo.entity;

//依赖条目类
public class Inner {
    private int id;
    private String main;
    private String sub;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getMain() {
        return main;
    }
    
    public void setMain(String main) {
        this.main = main;
    }
    
    public String getSub() {
        return sub;
    }
    
    public void setSub(String sub) {
        this.sub = sub;
    }
    
    public Inner(int id, String main, String sub) {
        this.id = id;
        this.main = main;
        this.sub = sub;
    }
}
