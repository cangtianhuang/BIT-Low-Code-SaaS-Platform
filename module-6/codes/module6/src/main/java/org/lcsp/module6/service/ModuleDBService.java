package org.lcsp.module6.service;

import org.lcsp.module6.mapper.ModuleMapper;
import org.lcsp.module6.model.Dependency;
import org.lcsp.module6.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleDBService {

    private final ModuleMapper moduleMapper;

    @Autowired
    public ModuleDBService(ModuleMapper moduleMapper) {
        this.moduleMapper = moduleMapper;
    }

    public List<Module> getModules(){
        return moduleMapper.findAllModules();
    }


    public Module findModuleById(long id) {
        return moduleMapper.findModuleById(id);
    }

    public void addModule(Module module) {
        moduleMapper.insertModule(module);
    }

    public void addDependency(Dependency dependency) {
        moduleMapper.insertDependency(dependency);
    }

    public List<Module> findAllModules() {
        return moduleMapper.findAllModules();
    }

    public void deleteModuleById(long id) {
        moduleMapper.deleteModuleById(id);
    }

    public void deleteDependencyById(long id) {
        moduleMapper.deleteDependencyById(id);
    }
}