package org.example.interfaces;



import org.example.Entities.investissements;

import java.util.List;

public interface IserviceINV {
    investissements getInvestissementById(int t);
    List<investissements> getInvestissementByUserId(int t);
}
