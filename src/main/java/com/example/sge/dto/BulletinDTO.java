package com.example.sge.dto;

import com.example.sge.model.Etudiant;
import com.example.sge.model.Note;

import java.util.List;

public class BulletinDTO {

    private Etudiant etudiant;
    private List<Note> notes;
    private double moyenneGenerale;
    private String mention;
    private boolean admis;

    public BulletinDTO() {}

    public BulletinDTO(Etudiant etudiant, List<Note> notes,
                       double moyenneGenerale, String mention, boolean admis) {
        this.etudiant = etudiant;
        this.notes = notes;
        this.moyenneGenerale = moyenneGenerale;
        this.mention = mention;
        this.admis = admis;
    }

    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }

    public double getMoyenneGenerale() { return moyenneGenerale; }
    public void setMoyenneGenerale(double moyenneGenerale) { this.moyenneGenerale = moyenneGenerale; }

    public String getMention() { return mention; }
    public void setMention(String mention) { this.mention = mention; }

    public boolean isAdmis() { return admis; }
    public void setAdmis(boolean admis) { this.admis = admis; }

    public int getTotalCoefficients() {
        if (notes == null) return 0;
        return notes.stream().mapToInt(n -> n.getModule().getCoefficient()).sum();
    }

    public double getTotalSommes() {
        if (notes == null) return 0;
        return notes.stream()
                .mapToDouble(n -> n.getValeur() * n.getModule().getCoefficient())
                .sum();
    }
}
