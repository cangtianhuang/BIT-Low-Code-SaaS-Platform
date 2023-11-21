package org.lcsp.module6.util;

import org.lcsp.module6.service.ModuleDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lcsp.module6.model.Module;

@Service
public class ModuleConflictResolver {

    private final ModuleDBService moduleDBService;

    @Autowired
    public ModuleConflictResolver(ModuleDBService moduleDBService) {
        this.moduleDBService = moduleDBService;
    }

    public boolean hasConflict(Module module) {
        for (Module otherModule : moduleDBService.getModules()) {
            if (otherModule.getArtifactId().equals(module.getArtifactId()) &&
                    !otherModule.getVersion().equals(module.getVersion())) {
                return true;
            }
        }
        return false;
    }
}