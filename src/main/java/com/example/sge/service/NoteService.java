package com.example.sge.service;

import com.example.sge.model.Etudiant;
import com.example.sge.model.Note;
import com.example.sge.repository.EtudiantRepository;
import com.example.sge.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Note ajouterNote(Note note) {
        if (note.getValeur() < 0 || note.getValeur() > 20)
            throw new IllegalArgumentException("La note doit être entre 0 et 20.");
        Note saved = noteRepository.save(note);
        mettreAJourMoyenne(note.getEtudiant().getId());
        return saved;
    }

    public List<Note> listerParEtudiant(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId);
    }

    public List<Note> listerParModule(Long moduleId) {
        return noteRepository.findByModuleId(moduleId);
    }

    public Double calculerMoyenne(Long etudiantId) {
        Double moyenne = noteRepository.calculerMoyenne(etudiantId);
        return moyenne != null ? moyenne : 0.0;
    }

    public void mettreAJourMoyenne(Long etudiantId) {
        Double moyenne = calculerMoyenne(etudiantId);
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable : " + etudiantId));
        etudiant.setMoyenne(moyenne);
        etudiantRepository.save(etudiant);
    }

    public Note modifier(Long id, Note noteModifiee) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note introuvable : " + id));
        if (noteModifiee.getValeur() < 0 || noteModifiee.getValeur() > 20)
            throw new IllegalArgumentException("La note doit être entre 0 et 20.");
        note.setValeur(noteModifiee.getValeur());
        Note saved = noteRepository.save(note);
        mettreAJourMoyenne(note.getEtudiant().getId());
        return saved;
    }

    public void supprimer(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note introuvable : " + id));
        Long etudiantId = note.getEtudiant().getId();
        noteRepository.deleteById(id);
        mettreAJourMoyenne(etudiantId);
    }

    public String calculerMention(double moyenne) {
        if (moyenne >= 16) return "Excellent";
        else if (moyenne >= 14) return "Très bien";
        else if (moyenne >= 12) return "Bien";
        else if (moyenne >= 10) return "Passable";
        else return "Ajourné";
    }
}
