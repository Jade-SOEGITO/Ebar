package be.helha.ebar.tests;

import be.helha.dao.daoimpl.BiereDaoImpl;
import be.helha.dao.daoimpl.DaoFactory;
import be.helha.ebar.biere.Biere;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_BiereDaoImpl {
        public static Biere biereTrouve;
        public static List<Biere> listeBieresAAjouter;
    public static List<Biere> listeBieresTrouvees;
        public static BiereDaoImpl biereDaoImpl;
        public static DaoFactory daoFactory;

        @BeforeAll
        static void initialisation() {
            biereTrouve = new Biere("", "", "", "");
            listeBieresAAjouter = new ArrayList<Biere>();
            listeBieresTrouvees = new ArrayList<Biere>();
            daoFactory = DaoFactory.getInstance();
            biereDaoImpl = new BiereDaoImpl(daoFactory);

            listeBieresAAjouter.add(new Biere("Blanche De Bruxelles", "Blanche", "blanche", "Brasserie Lefébvre"));
            listeBieresAAjouter.add(new Biere("Blanche de Hoegaarden", "Blanche", "blanche", "Brasserie De Kluis"));
            listeBieresAAjouter.add(new Biere("Chimay Bleue", "Trappiste", "brune", "Abbaye de Scourmont"));
            listeBieresAAjouter.add(new Biere("Chimay Rouge", "Trappiste", "brune", "Abbaye de Scourmont"));
            listeBieresAAjouter.add(new Biere("Floreffe Blonde", "Abbaye", "blonde", "Brasserie Lefébvre"));
            listeBieresAAjouter.add(new Biere("Floreffe Triple", "Abbaye", "blonde", "Brasserie Lefébvre"));
        }

        @Test
        @Order(1)
        public void testAjouterDB() {
            boolean operation_reussie = false;

            for (Biere b : listeBieresAAjouter) {
                operation_reussie = biereDaoImpl.ajouterBiere(b);
                assertTrue(operation_reussie);
            }
        }

        @Test
        @Order(2)
        public void testListerDB() {
            listeBieresTrouvees = biereDaoImpl.listerBieres();
            assertNotNull(listeBieresTrouvees);

            for (int index = 0; index < listeBieresTrouvees.size(); index++) {
                assertEquals(listeBieresTrouvees.get(index).getNom(), listeBieresAAjouter.get(index).getNom());
                assertEquals(listeBieresTrouvees.get(index).getType(), listeBieresAAjouter.get(index).getType());
                assertEquals(listeBieresTrouvees.get(index).getCouleur(), listeBieresAAjouter.get(index).getCouleur());
                assertEquals(listeBieresTrouvees.get(index).getBrasserie(), listeBieresAAjouter.get(index).getBrasserie());
            }
        }

        @Test
        @Order(3)
        public void testRechercherBiereDB() {
            biereTrouve = biereDaoImpl.getBiere("Chimay Bleue");
            assertNotNull(biereTrouve);

            if (listeBieresAAjouter.contains(biereTrouve)) {
                int index = listeBieresAAjouter.indexOf(biereTrouve);

                assertEquals(listeBieresAAjouter.get(index).getNom(), biereTrouve.getNom());
                assertEquals(listeBieresAAjouter.get(index).getType(), biereTrouve.getType());
                assertEquals(listeBieresAAjouter.get(index).getCouleur(), biereTrouve.getCouleur());
                assertEquals(listeBieresAAjouter.get(index).getBrasserie(), biereTrouve.getBrasserie());
            }
        }

        @Test
        @Order(4)
        public void testModifierBiereDB() {
            biereTrouve = biereDaoImpl.getBiere("Blanche De Bruxelles");
            assertNotNull(biereTrouve);

            biereTrouve.setType("Fabrique");
            biereTrouve.setCouleur("rouge");
            biereTrouve.setBrasserie("Le Petit Chatelet");

            boolean operation_reussie = biereDaoImpl.modifierBiere(biereTrouve);
            assertTrue(operation_reussie);

            listeBieresTrouvees = biereDaoImpl.listerBieres();

            if (listeBieresTrouvees.contains(biereTrouve)) {
                int index = listeBieresTrouvees.indexOf(biereTrouve);

                assertEquals("Fabrique", listeBieresTrouvees.get(index).getType());
                assertEquals("rouge", listeBieresTrouvees.get(index).getCouleur());
                assertEquals("Le Petit Chatelet", listeBieresTrouvees.get(index).getBrasserie());
            }
        }

        @Test
        @Order(5)
        public void testSupprimerBiereDB() {
            boolean operation_reussie = false;

            listeBieresTrouvees = biereDaoImpl.listerBieres();
            assertNotNull(listeBieresTrouvees);

            for (Biere b : listeBieresTrouvees) {
                operation_reussie = biereDaoImpl.supprimerBiere(b.getNom());
                assertTrue(operation_reussie);
            }
        }
}
