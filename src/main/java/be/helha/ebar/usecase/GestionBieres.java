package be.helha.ebar.usecase;

import be.helha.ebar.bundle.bundleimpl.BiereDTO;

public interface GestionBieres {
    void ajouterBiere (BiereDTO bieredto);
    void rechercherBiere (BiereDTO bieredto);
    void lister (BiereDTO bieredto);
    void supprimerBiere (BiereDTO bieredto);
    void modifierBiere (BiereDTO bieredto);
}
