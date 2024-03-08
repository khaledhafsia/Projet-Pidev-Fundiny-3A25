package org.example.Entities;


public class Poste {


    // private String continueCommentaireField2;
    public enum categorie {
        AUTRE,
        SANTE,
        SELF_CARE
    }

    private int id;
    private String titre;
    private String discription;
    private String image;
    private categorie categorie;
    private int likes = 0;
    private int dislikes = 0;

    public Poste() {
        this.likes = 0;
        this.dislikes = 0;
    }

    public Poste(int id, String titre, String discription, String image, categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.discription = discription;
        this.image = image;
        this.categorie = categorie;
        this.likes = 0; // Set default likes to 0
        this.dislikes = 0; // Set default dislikes to 0
    }

    public Poste(int id, String titre, String discription, String image) {
        this.id = id;
        this.titre = titre;
        this.discription = discription;
        this.image = image;
    }

    public Poste(String titre, String discription, String image, categorie categorie) {
        this.titre = titre;
        this.discription = discription;
        this.image = image;
        this.categorie = categorie;
        this.likes = 0; // Set default likes to 0
        this.dislikes = 0; // Set default dislikes to 0
    }

    public Poste(String titre, String discription, String image) {
        this.titre = titre;
        this.discription = discription;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(categorie categorie) {
        this.categorie = categorie;
    }

    public void setLikes(int image) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setDislikes(int image) {
        this.dislikes = dislikes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setId(int aInt) {
        this.id = aInt;
    }

}