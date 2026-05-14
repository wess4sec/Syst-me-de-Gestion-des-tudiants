package com.example.sge.service;

import com.example.sge.model.Etudiant;
import com.example.sge.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> listerTout() { return etudiantRepository.findAll(); }

    public Etudiant trouverParId(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable : " + id));
    }

    public Etudiant ajouter(Etudiant etudiant) { return etudiantRepository.save(etudiant); }

    public Etudiant modifier(Long id, Etudiant etudiant) {
        Etudiant existing = trouverParId(id);
        existing.setNom(etudiant.getNom());
        existing.setPrenom(etudiant.getPrenom());
        existing.setEmail(etudiant.getEmail());
        existing.setGroupe(etudiant.getGroupe());
        existing.setFiliere(etudiant.getFiliere());
        return etudiantRepository.save(existing);
    }

    public void supprimer(Long id) { etudiantRepository.deleteById(id); }
}
