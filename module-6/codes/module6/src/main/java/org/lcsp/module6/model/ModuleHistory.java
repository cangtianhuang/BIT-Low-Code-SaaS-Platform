package org.lcsp.module6.model;

import java.util.Date;
import java.util.List;

public class ModuleHistory {
    private int id;
    private String groupId; // The groupId of the module in Maven
    private String artifactId; // The artifactId of the module in Maven
    private Date createdTime; // The time when the module was created
    private String creator; // The person who created the module
    private List<Module> versions; // All versions of the module

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Module> getVersions() {
        return versions;
    }

    public void setVersions(List<Module> versions) {
        this.versions = versions;
    }
}