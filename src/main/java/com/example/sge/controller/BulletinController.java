package com.example.sge.controller;

import com.example.sge.dto.BulletinDTO;
import com.example.sge.model.Etudiant;
import com.example.sge.model.Note;
import com.example.sge.repository.EtudiantRepository;
import com.example.sge.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String liste(Model model) {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<BulletinDTO> bulletins = new ArrayList<>();
        for (Etudiant e : etudiants) {
            bulletins.add(construire(e));
        }
        model.addAttribute("bulletins", bulletins);
        return "bulletins/liste";
    }

    @GetMapping("/{etudiantId}")
    public String detail(@PathVariable Long etudiantId, Model model) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
        model.addAttribute("bulletin", construire(etudiant));
        return "bulletins/detail";
    }

    private BulletinDTO construire(Etudiant etudiant) {
        List<Note> notes = noteService.listerParEtudiant(etudiant.getId());
        double moyenne = noteService.calculerMoyenne(etudiant.getId());
        String mention = noteService.calculerMention(moyenne);
        boolean admis = moyenne >= 10;
        return new BulletinDTO(etudiant, notes, moyenne, mention, admis);
    }
}
