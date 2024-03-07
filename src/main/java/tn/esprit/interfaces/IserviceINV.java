package tn.esprit.interfaces;

import tn.esprit.models.investissements;

import java.util.List;

public interface IserviceINV {
    investissements getInvestissementById(int t);
    List<investissements> getInvestissementByUserId(int t);
}
