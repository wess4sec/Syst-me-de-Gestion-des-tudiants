package com.example.sge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String code;
    private int coefficient;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    public Module() {}

    public Module(String nom, String code, int coefficient, Filiere filiere) {
        this.nom = nom;
        this.code = code;
        this.coefficient = coefficient;
        this.filiere = filiere;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public int getCoefficient() { return coefficient; }
    public void setCoefficient(int coefficient) { this.coefficient = coefficient; }

    public Filiere getFiliere() { return filiere; }
    public void setFiliere(Filiere filiere) { this.filiere = filiere; }
}
