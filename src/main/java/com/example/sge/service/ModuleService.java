package com.example.sge.service;

import com.example.sge.model.Module;
import com.example.sge.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> listerTout() { return moduleRepository.findAll(); }

    public Module trouverParId(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module introuvable : " + id));
    }

    public Module ajouter(Module module) { return moduleRepository.save(module); }

    public Module modifier(Long id, Module module) {
        Module existing = trouverParId(id);
        existing.setNom(module.getNom());
        existing.setCode(module.getCode());
        existing.setCoefficient(module.getCoefficient());
        existing.setFiliere(module.getFiliere());
        return moduleRepository.save(existing);
    }

    public void supprimer(Long id) { moduleRepository.deleteById(id); }
}
