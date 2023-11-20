package com.example.demo.entity;

public class Outer {
    private int id;
    private String module;//模块名
    private String groupId;//被依赖项信息
    private String artifactId;
    private String version;
    private String scope;
    private String optional;
    
    public Outer(int id, String module, String groupId, String artifactId, String version, String scope, String optional) {
        this.id = id;
        this.module = module;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.scope = scope;
        this.optional = optional;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getModule() {
        return module;
    }
    
    public void setModule(String module) {
        this.module = module;
    }
    
    public String getGroupId() {
        return groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public String getArtifactId() {
        return artifactId;
    }
    
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getScope() {
        return scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    public String getOptional() {
        return optional;
    }
    
    public void setOptional(String optional) {
        this.optional = optional;
    }
}
