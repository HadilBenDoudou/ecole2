package com.ecole.ecole.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Eleve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eleve")
    private Long id_eleve;

    @Column(name = "nom_eleve")
    private String nom_eleve;

    @Column(name = "prenom_eleve")
    private String prenom_eleve;

    @Column(name = "nom_parent")
    private String nom_parent;

    @Column(name = "num_parent")
    private String num_parent;

    @Column(name = "date_naissance")
    private Date date_naissance;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    @JsonBackReference // Prevents serialization of the `Classe` field in the student object
    private Classe classe;

    // Getters et Setters

    public Long getId_eleve() {
        return id_eleve;
    }

    public void setId_eleve(Long id_eleve) {
        this.id_eleve = id_eleve;
    }

    public String getNom_eleve() {
        return nom_eleve;
    }

    public void setNom_eleve(String nom_eleve) {
        this.nom_eleve = nom_eleve;
    }

    public String getPrenom_eleve() {
        return prenom_eleve;
    }

    public void setPrenom_eleve(String prenom_eleve) {
        this.prenom_eleve = prenom_eleve;
    }

    public String getNom_parent() {
        return nom_parent;
    }

    public void setNom_parent(String nom_parent) {
        this.nom_parent = nom_parent;
    }

    public String getNum_parent() {
        return num_parent;
    }

    public void setNum_parent(String num_parent) {
        this.num_parent = num_parent;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
