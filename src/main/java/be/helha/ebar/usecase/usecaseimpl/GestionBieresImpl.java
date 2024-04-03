package be.helha.ebar.usecase.usecaseimpl;

import be.helha.ebar.dao.BiereDao;
import be.helha.ebar.dao.daoimpl.DaoFactory;
import be.helha.ebar.biere.Biere;
import be.helha.ebar.bundle.bundleimpl.BiereDTO;
import be.helha.ebar.usecase.GestionBieres;

import java.util.List;

public class GestionBieresImpl implements GestionBieres {
    private BiereDao biereDao;

    public GestionBieresImpl() {
        this.biereDao = DaoFactory.getInstance().getBiereDao();
    }

    @Override
    public void ajouterBiere(BiereDTO bieredto) {
        Biere biere = (Biere) bieredto.get(BiereDTO.BIERE);
        List<Biere> listeBieres = this.biereDao.listerBieres();

        if (biere.getNom() == null || biere.getType() == null || biere.getCouleur() == null || biere.getBrasserie() == null) {
            bieredto.put(BiereDTO.OPERATION_REUSSIE, false);
        }
        else {
            if (!listeBieres.contains(biere)) {
                this.biereDao.ajouterBiere(biere);
            }
            else {
                bieredto.put(BiereDTO.OPERATION_REUSSIE, false);
            }
        }
    }

    @Override
    public void rechercherBiere(BiereDTO bieredto) {
        if (bieredto.get(BiereDTO.NOM) == null) {
            bieredto.put(BiereDTO.OPERATION_REUSSIE, false);
        }
        else {
            String nom = (String) bieredto.get(BiereDTO.NOM);
            Biere biereObtenue = this.biereDao.getBiere(nom);
            bieredto.put(BiereDTO.BIERE, biereObtenue);
            bieredto.put(BiereDTO.OPERATION_REUSSIE, true);

        }
    }

    @Override
    public void lister(BiereDTO bieredto) {
        List<Biere> listeBieres = this.biereDao.listerBieres();

        bieredto.put(BiereDTO.LISTE, listeBieres);
        bieredto.put(BiereDTO.OPERATION_REUSSIE, true);
    }

    @Override
    public void supprimerBiere(BiereDTO bieredto) {
        String nomBiereSupp = (String) bieredto.get(BiereDTO.NOM);
        Biere biereRecherche = this.biereDao.getBiere(nomBiereSupp);

        if (biereRecherche == null) {
            bieredto.put(BiereDTO.OPERATION_REUSSIE, false);
        }
        else {
            this.biereDao.supprimerBiere(nomBiereSupp);
            bieredto.put(BiereDTO.OPERATION_REUSSIE, true);
        }
    }

    @Override
    public void modifierBiere(BiereDTO bieredto) {
        Biere biereAModif = (Biere) bieredto.get(BiereDTO.BIERE);
        Biere biereRecherche = this.biereDao.getBiere(biereAModif.getNom());

        if (biereRecherche == null) {
            bieredto.put(BiereDTO.OPERATION_REUSSIE, false);
        }
        else {
            this.biereDao.modifierBiere(biereAModif);
            bieredto.put(BiereDTO.OPERATION_REUSSIE, true);
        }
    }
}