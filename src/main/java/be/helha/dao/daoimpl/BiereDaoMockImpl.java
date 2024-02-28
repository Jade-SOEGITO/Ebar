package be.helha.dao.daoimpl;

import be.helha.dao.BiereDao;
import be.helha.ebar.biere.Biere;

import java.util.*;

public class BiereDaoMockImpl implements BiereDao {
    private Map<String, Biere> mapBieres;

    public BiereDaoMockImpl() {
        Comparator<String> comparateur = new ComparateurBieres();
        this.mapBieres = new TreeMap<String, Biere>(comparateur);
    }

    private class ComparateurBieres implements Comparator<String> {
        @Override
        public int compare(String nom1, String nom2) {
            return nom1.compareTo(nom2);
        }
    }

    @Override
    public boolean ajouterBiere(Biere biere) {
        if (biere != null) {
            this.mapBieres.put(biere.getNom(), biere);
            return true;
        }
        return false;
    }

    @Override
    public Biere getBiere(String nom) {
        return this.mapBieres.get(nom);
    }

    @Override
    public List<Biere> listerBieres() {
        List<Biere> listeBieres;
        listeBieres = new ArrayList<Biere>(this.mapBieres.values());
        return listeBieres;
    }

    @Override
    public boolean supprimerBiere(String nom) {
        if (nom != null) {
            this.mapBieres.remove(nom);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifierBiere(Biere biere) {
        if (biere.getNom() != null) {
            if (this.mapBieres.containsKey(biere.getNom())) {
                this.mapBieres.replace(biere.getNom(), biere);
                return true;
            }
        }
        return false;
    }
}
