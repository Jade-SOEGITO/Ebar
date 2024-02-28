package be.helha.ebar.biere;

import java.util.Objects;

public class Biere {
    private String nom;
    private String type;
    private String couleur;
    private String brasserie;

    public Biere(String nom, String type, String couleur, String brasserie) {
        this.nom = nom;
        this.type = type;
        this.couleur = couleur;
        this.brasserie = brasserie;
    }

    public Biere (Biere b) {
        this(b.getNom(), b.getType(), b.getCouleur(), b.getBrasserie());
    }

    public String getNom() {
        return nom;
    }

    public void setNom (String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur (String couleur) {
        this.couleur = couleur;
    }

    public String getBrasserie() {
        return brasserie;
    }

    public void setBrasserie (String brasserie) {
        this.brasserie = brasserie;
    }

    public String toString() {
        return "Nom de la bière : " + this.nom + " | Couleur : " + this.couleur + " | Type : " + this.type + " | Brasserie : " + this.brasserie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biere biere = (Biere) o;
        return Objects.equals(nom, biere.nom) && Objects.equals(type, biere.type) && Objects.equals(couleur, biere.couleur) && Objects.equals(brasserie, biere.brasserie);
    }
}
