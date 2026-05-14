package com.example.sge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "filieres")
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String niveau;
    private int capacite;

    public Filiere() {}

    public Filiere(String nom, String niveau, int capacite) {
        this.nom = nom;
        this.niveau = niveau;
        this.capacite = capacite;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }
}
