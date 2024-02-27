    package org.example.Entities;

    public class User {

        public User(int id, String nom, String prenom, String telephone, String adresse, String email, String password, User.role role) {

        }



        public enum role {
            ADMIN,
            Owner,
            Funder
        }

        protected int id;
        protected String nom;
        //  protected StringProperty nom;
        protected String prenom;
        protected String email;
        protected String password;
        protected role role ;





         /*public User(String nom) {
            this.nom = new SimpleStringProperty(nom);
        }

          public StringProperty nomProperty() {
            return nom;
        }*/
         public User(int id) {
             this.id = id;
         }

        public User(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }
        public User(int id, String nom, String prenom,String email, String password) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.password = password;


        }

        public User(String nom, String prenom, String email, String password) {
            this.nom = nom;
            // this.nom.set(nom);
            this.prenom = prenom;
            this.email = email;
            this.password = password;
        }

        public User(int id, String nom, String prenom, String email, String password, User.role role) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        public User(int id, String nom, String prenom,String email) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
        }


        public User(String nom, String prenom, String email, String hashpwd, User.role role) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.password = hashpwd;
            this.role = role;
        }


        public int getId() {

             return id;
        }

        public void setId(int id) {

             this.id = id;
        }

        public String getNom() {
            //return nom.get();
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
            //  this.nom.set(nom);
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public role getRole() {
            return role;
        }

        public void setRole(role role) {
            this.role = role;
        }



    }
