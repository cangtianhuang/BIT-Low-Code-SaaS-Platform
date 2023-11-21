package org.lcsp.module6.model;

public class Dependency {
    private long id;
    private long moduleId;
    private long dependentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public long getDependentId() {
        return dependentId;
    }

    public void setDependentId(long dependentId) {
        this.dependentId = dependentId;
    }
}
