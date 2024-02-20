package tn.esprit.test;

import tn.esprit.models.Reclamation;
import tn.esprit.services.serviceReclamation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Reclamation p1 = new Reclamation(1000, 1, 101, 201, 301, "Open", "Description 1", createDate("2022-02-16"), createDate("2022-02-16"));
        Reclamation p2 = new Reclamation(8945, 2, 102, 202, 302, "In Progress", "Description 2", createDate("2022-02-16"), createDate("2022-02-16"));
        Reclamation p3 = new Reclamation(4565, 3, 103, 203, 303, "Closed", "Description 3", createDate("2022-02-16"), createDate("2022-02-16"));

        serviceReclamation sp = new serviceReclamation();
        sp.add(p1);
        sp.add(p2);
        sp.add(p3);

        System.out.println(sp.getAll());
    }
}