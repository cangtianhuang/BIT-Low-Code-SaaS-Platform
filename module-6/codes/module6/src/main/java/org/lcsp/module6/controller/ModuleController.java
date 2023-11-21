package org.lcsp.module6.controller;

import org.lcsp.module6.model.Module;
import org.lcsp.module6.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public void importModule(@RequestPart("module") Module module, @RequestParam("file") MultipartFile jarFile) {
        moduleService.importModule(module, jarFile);
    }

    @GetMapping("")
    public List<Module> listModules() {
        return moduleService.listModules();
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable long id) {
        moduleService.deleteModule(id);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public void updateModule(@PathVariable long id, @RequestPart("module") Module module, @RequestParam("file") MultipartFile jarFile) {
        moduleService.updateModule(id, module, jarFile);
    }
}