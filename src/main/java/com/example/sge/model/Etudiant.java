package com.example.sge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String groupe;
    private double moyenne;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

    public Etudiant() {}

    public Etudiant(String nom, String prenom, String email, String groupe, Filiere filiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.groupe = groupe;
        this.filiere = filiere;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGroupe() { return groupe; }
    public void setGroupe(String groupe) { this.groupe = groupe; }

    public double getMoyenne() { return moyenne; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }

    public Filiere getFiliere() { return filiere; }
    public void setFiliere(Filiere filiere) { this.filiere = filiere; }

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }
}
