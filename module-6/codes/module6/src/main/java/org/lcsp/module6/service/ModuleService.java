package org.lcsp.module6.service;

import org.lcsp.module6.model.Module;
import org.lcsp.module6.util.ModuleConflictResolver;
import org.lcsp.module6.util.ModuleDependencyChecker;
import org.lcsp.module6.util.ModuleLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ModuleService {

    private final ModuleLoader moduleLoader;
    private final ModuleDBService moduleDBService;
    private final ModuleDependencyChecker moduleDependencyChecker;
    private final ModuleConflictResolver moduleConflictResolver;

    @Autowired
    public ModuleService(ModuleLoader moduleLoader, ModuleDBService moduleDBService,
                         ModuleDependencyChecker moduleDependencyChecker,
                         ModuleConflictResolver moduleConflictResolver) {
        this.moduleLoader = moduleLoader;
        this.moduleDBService = moduleDBService;
        this.moduleDependencyChecker = moduleDependencyChecker;
        this.moduleConflictResolver = moduleConflictResolver;
    }

    public void importModule(Module module, MultipartFile jarFile) {
        try {
            moduleLoader.load(jarFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Check for conflicts before import
        if (moduleConflictResolver.hasConflict(module)) {
            // Handle conflict or throw an exception
        }
        // Check dependencies before import
        if (!moduleDependencyChecker.areDependenciesSatisfied(module)) {
            // Handle missing dependencies or throw an exception
        }
        moduleDBService.addModule(module);
    }

    public List<Module> listModules() {
        return moduleDBService.findAllModules();
    }

    public void deleteModule(long moduleId) {
        Module module = moduleDBService.findModuleById(moduleId);
        // Check if the module can be deleted without breaking dependencies
        if (moduleDependencyChecker.canModuleBeDeleted(module)) {
            moduleDBService.deleteModuleById(moduleId);
        } else {
            // Handle the situation or throw an exception
        }
    }

    public void updateModule(long moduleId, Module module, MultipartFile jarFile) {
        try {
            moduleLoader.load(jarFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Check for conflicts before update
        if (moduleConflictResolver.hasConflict(module)) {
            // Handle conflict or throw an exception
        }
        // Check dependencies before update
        if (!moduleDependencyChecker.areDependenciesSatisfied(module)) {
            // Handle missing dependencies or throw an exception
        }
        moduleDBService.addModule(module);
    }
}