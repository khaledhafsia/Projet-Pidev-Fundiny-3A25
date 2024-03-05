package tn.esprit.services;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StatistiqueCollaboration {

    private ServiceProjet serviceProjet;
    private ServiceCollaboration serviceCollaboration;

    public StatistiqueCollaboration() {
        this.serviceProjet = new ServiceProjet();
        this.serviceCollaboration = new ServiceCollaboration();
    }

    public void genererStatistiques() {
        // Récupérer les données sur les collaborations
        ArrayList<Collaboration> collaborations = serviceCollaboration.getAll();

        // Récupérer les données sur les projets
        ArrayList<Projet> projets = serviceProjet.getAll();

        // Initialiser un compteur de collaborations par projet
        HashMap<Integer, Integer> collaborationsParProjet = new HashMap<>();

        // Compter le nombre de collaborations par projet
        for (Collaboration collaboration : collaborations) {
            int idProjet = collaboration.getId();
            collaborationsParProjet.put(idProjet, collaborationsParProjet.getOrDefault(idProjet, 0) + 1);
        }

        // Calculer le nombre total de collaborations
        int totalCollaborations = collaborations.size();

        // Créer un ensemble de données pour le diagramme circulaire
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Projet projet : projets) {
            int idProjet = projet.getId();
            int nbCollaborations = collaborationsParProjet.getOrDefault(idProjet, 0);
            double pourcentage = (double) nbCollaborations / totalCollaborations * 100;
            dataset.setValue("Projet ID " + idProjet, pourcentage);
        }

        // Créer le diagramme circulaire
        JFreeChart chart = ChartFactory.createPieChart("Statistiques des collaborations par projet", dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);

        // Afficher le diagramme circulaire dans une fenêtre Swing
        JFrame frame = new JFrame("Diagramme circulaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    public HashMap<Integer, Double> generateData() {
        // Récupérer les données sur les collaborations
        ArrayList<Collaboration> collaborations = serviceCollaboration.getAll();

        // Récupérer les données sur les projets
        ArrayList<Projet> projets = serviceProjet.getAll();

        // Initialiser un compteur de collaborations par projet
        HashMap<Integer, Integer> collaborationsParProjet = new HashMap<>();

        // Compter le nombre de collaborations par projet
        for (Collaboration collaboration : collaborations) {
            int idProjet = collaboration.getId();
            collaborationsParProjet.put(idProjet, collaborationsParProjet.getOrDefault(idProjet, 0) + 1);
        }

        // Calculer le nombre total de collaborations
        int totalCollaborations = collaborations.size();

        // Créer un ensemble de données pour le diagramme circulaire
        HashMap<Integer, Double> data = new HashMap<>();
        for (Projet projet : projets) {
            int idProjet = projet.getId();
            int nbCollaborations = collaborationsParProjet.getOrDefault(idProjet, 0);
            double pourcentage = (double) nbCollaborations / totalCollaborations * 100;
            data.put(idProjet, pourcentage);
        }

        return data;
    }

    public static void main(String[] args) {
        StatistiqueCollaboration statistiqueCollaboration = new StatistiqueCollaboration();
        statistiqueCollaboration.genererStatistiques();
    }
}

