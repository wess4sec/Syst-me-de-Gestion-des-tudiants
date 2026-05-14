package com.example.sge.controller;

import com.example.sge.model.Etudiant;
import com.example.sge.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> getAll() { return etudiantService.listerTout(); }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.trouverParId(id));
    }

    @PostMapping
    public ResponseEntity<Etudiant> create(@RequestBody Etudiant etudiant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(etudiantService.ajouter(etudiant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> update(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        return ResponseEntity.ok(etudiantService.modifier(id, etudiant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etudiantService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
