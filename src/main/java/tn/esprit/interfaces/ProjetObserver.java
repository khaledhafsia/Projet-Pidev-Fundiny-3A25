package tn.esprit.interfaces;

import tn.esprit.models.Projet;

public interface ProjetObserver {
    void onProjetAdded(Projet projet);
}
