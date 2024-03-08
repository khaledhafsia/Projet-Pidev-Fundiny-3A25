package org.example.Entities;

public class article {

    private int id ;
    private String description, image;

    public article() {
    }

    public article(int id, String description, String image) {
        this.id = id;
        this.description = description;
        this.image = image;
    }

//    public article(String description, String image) {
//        this.description = description;
//        this.image= image;
//    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "article{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
