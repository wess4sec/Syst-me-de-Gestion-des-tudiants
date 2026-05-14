package com.example.sge.service;

import com.example.sge.model.Filiere;
import com.example.sge.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    public List<Filiere> listerTout() { return filiereRepository.findAll(); }

    public Filiere trouverParId(Long id) {
        return filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable : " + id));
    }

    public Filiere ajouter(Filiere filiere) { return filiereRepository.save(filiere); }

    public Filiere modifier(Long id, Filiere filiere) {
        Filiere existing = trouverParId(id);
        existing.setNom(filiere.getNom());
        existing.setNiveau(filiere.getNiveau());
        existing.setCapacite(filiere.getCapacite());
        return filiereRepository.save(existing);
    }

    public void supprimer(Long id) { filiereRepository.deleteById(id); }
}
