package be.helha.ebar.dao;

import be.helha.ebar.biere.Biere;

import java.sql.SQLException;
import java.util.List;

public interface BiereDao {
    boolean ajouterBiere (Biere biere);
    Biere getBiere (String nom);
    List<Biere> listerBieres();
    boolean supprimerBiere (String nom);
    boolean modifierBiere (Biere biere);

}
