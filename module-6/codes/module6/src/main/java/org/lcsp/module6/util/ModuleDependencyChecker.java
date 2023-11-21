package org.lcsp.module6.util;

import org.lcsp.module6.model.Module;
import org.lcsp.module6.service.ModuleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleDependencyChecker {

    private final ModuleDBService moduleDBService;

    @Autowired
    public ModuleDependencyChecker(ModuleDBService moduleDBService) {
        this.moduleDBService = moduleDBService;
    }

    public boolean areDependenciesSatisfied(Module module) {
        // For each dependency of the module, check if it exists in the database.
        // If all dependencies exist, return true. Otherwise, return false.
        //for (Dependency dependency : moduleDBService.) {
        //    if (!moduleDBService.moduleExists(dependency)) {
        //        return false;
        //    }
        //}
        return true;
    }

    public boolean canModuleBeDeleted(Module module) {
        // Check if any other modules in the database depend on the specified module.
        // If not, return true. Otherwise, return false.
        return true;
    }
}