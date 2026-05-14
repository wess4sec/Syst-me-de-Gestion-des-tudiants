package com.example.sge.controller;

import com.example.sge.model.Note;
import com.example.sge.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<?> ajouter(@RequestBody Note note) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(noteService.ajouterNote(note));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/etudiant/{id}")
    public ResponseEntity<List<Note>> parEtudiant(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.listerParEtudiant(id));
    }

    @GetMapping("/module/{id}")
    public ResponseEntity<List<Note>> parModule(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.listerParModule(id));
    }

    @GetMapping("/moyenne/{etudiantId}")
    public ResponseEntity<Double> moyenne(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(noteService.calculerMoyenne(etudiantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(@PathVariable Long id, @RequestBody Note note) {
        try {
            return ResponseEntity.ok(noteService.modifier(id, note));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            noteService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
