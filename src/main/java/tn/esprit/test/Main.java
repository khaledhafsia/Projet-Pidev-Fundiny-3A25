package tn.esprit.test;

import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceCollaboration;
import tn.esprit.services.ServiceProjet;
import tn.esprit.utils.MyDataBase;

public class Main {
    public static void main(String[] args) {
       // Projet pr1 = new Projet(49,"fle7a","oussema","2/Fevrier/24", 75000);
        //Projet pr2 = new Projet(50,"mou9awlat","zouhaier","2/Fevrier/24", 50000);
        //Projet pr3 = new Projet(51,"magasin","fadi","2/Fevrier/24", 50000);
       // Collaboration cl1= new Collaboration(14,"aymen","Publicité","02-03-24",1);

        ServiceProjet sp = new ServiceProjet();
        ServiceCollaboration sc = new ServiceCollaboration();
       /* sp.add(pr1);
        sp.add(pr2);
        sp.add(pr3);
        sc.add(cl1);*/
        //Projet pr1Updated = new Projet(1, "fle7a", "oussema", "4/Fevrier/24", 75000);

        //sp.update(pr1Updated);
       // sp.delete(pr2);
        //sc.delete(cl1);

        System.out.println(sp.getAll());

        System.out.println(sc.getAll());
    }
}