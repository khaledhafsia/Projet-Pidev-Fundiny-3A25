package org.example.interfaces;

import org.example.Entities.taches;

import java.util.List;

public interface IserviceTache {
    List<taches> getTasksByInvestissementID(int invID) ;
}
