package tn.esprit.interfaces;

import tn.esprit.models.taches;

import java.util.ArrayList;
import java.util.List;

public interface IserviceTache {
    List<taches> getTasksByInvestissementID(int invID) ;
}
