package org.lcsp.module6.model;

import java.util.Date;
import java.util.List;

public class Module {
    private long id;
    private String groupId; // The groupId of the module in Maven
    private String artifactId; // The artifactId of the module in Maven
    private String version; // The version of the module in Maven
    private Date modifiedTime; // The time when the module was last modified
    private String modifier; // The person who last modified the module
    private List<Dependency> dependencies; // List of dependencies (IDs of other modules)
    private String description; // Short description of the module
    private String detailedDescription; // Detailed description of the module
    private List<ModuleInterface> interfaces; // List of interfaces provided by the module
    private List<DataStructure> dataStructures; // List of data structures used by the module
    private String usageExample; // Example of how to use the module
    private String notes; // Any additional notes about the module

    // getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public List<ModuleInterface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<ModuleInterface> interfaces) {
        this.interfaces = interfaces;
    }

    public List<DataStructure> getDataStructures() {
        return dataStructures;
    }

    public void setDataStructures(List<DataStructure> dataStructures) {
        this.dataStructures = dataStructures;
    }

    public String getUsageExample() {
        return usageExample;
    }

    public void setUsageExample(String usageExample) {
        this.usageExample = usageExample;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

