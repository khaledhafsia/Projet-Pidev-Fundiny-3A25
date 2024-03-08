package org.example.Entities;

public class Event {
    private int idEvent;
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    private Integer objectifFinancement;
    private Integer montantCollecte;
    private String statut;
        private String categorie;
    private String organisateur;
        private String localisation;
    private String image;

    public Event() {
    }

    public Event(int idEvent, String nom, String description, String dateDebut, String dateFin, Integer objectifFinancement, Integer montantCollecte, String categorie) {
        this.idEvent = idEvent;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.objectifFinancement = objectifFinancement;
        this.montantCollecte = montantCollecte;
        this.categorie = categorie;
    }

    public Event(int idEvent, String nom, String description, String dateDebut, String dateFin, Integer objectifFinancement, Integer montantCollecte, String statut, String categorie, String organisateur, String localisation, String image) {
        this.idEvent = idEvent;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.objectifFinancement = objectifFinancement;
        this.montantCollecte = montantCollecte;
        this.statut = statut;
        this.categorie = categorie;
        this.organisateur = organisateur;
        this.localisation = localisation;
        this.image = image;
    }

    public Event(String nom, String description, String dateDebut, String dateFin, Integer objectifFinancement, Integer montantCollecte, String categorie) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.objectifFinancement = objectifFinancement;
        this.montantCollecte = montantCollecte;
        this.categorie = categorie;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public Integer getObjectifFinancement() {
        return objectifFinancement;
    }

    public Integer getMontantCollecte() {
        return montantCollecte;
    }

    public String getStatut() {
        return statut;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getImage() {
        return image;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setObjectifFinancement(Integer objectifFinancement) {
        this.objectifFinancement = objectifFinancement;
    }

    public void setMontantCollecte(Integer montantCollecte) {
        this.montantCollecte = montantCollecte;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
