package com.example.sge.controller;

import com.example.sge.model.Module;
import com.example.sge.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public List<Module> getAll() { return moduleService.listerTout(); }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.trouverParId(id));
    }

    @PostMapping
    public ResponseEntity<Module> create(@RequestBody Module module) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.ajouter(module));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> update(@PathVariable Long id, @RequestBody Module module) {
        return ResponseEntity.ok(moduleService.modifier(id, module));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moduleService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
