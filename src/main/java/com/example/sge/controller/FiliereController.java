package com.example.sge.controller;

import com.example.sge.model.Filiere;
import com.example.sge.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @GetMapping
    public List<Filiere> getAll() { return filiereService.listerTout(); }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getById(@PathVariable Long id) {
        return ResponseEntity.ok(filiereService.trouverParId(id));
    }

    @PostMapping
    public ResponseEntity<Filiere> create(@RequestBody Filiere filiere) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filiereService.ajouter(filiere));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filiere> update(@PathVariable Long id, @RequestBody Filiere filiere) {
        return ResponseEntity.ok(filiereService.modifier(id, filiere));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filiereService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
