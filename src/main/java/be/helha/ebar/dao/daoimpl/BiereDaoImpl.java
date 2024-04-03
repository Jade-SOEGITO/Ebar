package be.helha.ebar.dao.daoimpl;

import be.helha.ebar.dao.BiereDao;
import be.helha.ebar.biere.Biere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiereDaoImpl implements BiereDao {
    public static final String GET = "SELECT * FROM bieres WHERE nom=?";
    public static final String AJOUT = "INSERT INTO bieres (nom, type, couleur, brasserie) VALUES (?, ?, ?, ?)";
    public static final String MAJ = "UPDATE bieres SET type=?, couleur=?, brasserie=? WHERE nom=?";
    public static final String LISTER = "SELECT * FROM bieres";
    public static final String SUPPRIMER = "DELETE FROM bieres WHERE nom=?";

    public DaoFactory daoFactory;

    public BiereDaoImpl (DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private void cloturer (ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws SQLException {
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public boolean ajouterBiere(Biere biere) {
        try {
            Connection connection = this.daoFactory.getConnexion();

            PreparedStatement preparedStatement = connection.prepareStatement(AJOUT);

            preparedStatement.setString(1, biere.getNom());
            preparedStatement.setString(2, biere.getType());
            preparedStatement.setString(3, biere.getCouleur());
            preparedStatement.setString(4, biere.getBrasserie());

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Biere getBiere(String nom) {
        try {
            Biere biereTrouve = new Biere("", "", "", "");

            Connection connection = this.daoFactory.getConnexion();

            PreparedStatement preparedStatement = connection.prepareStatement(GET);

            preparedStatement.setString(1, nom);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                biereTrouve.setNom(resultSet.getString("nom"));
                biereTrouve.setType(resultSet.getString("type"));
                biereTrouve.setCouleur(resultSet.getString("couleur"));
                biereTrouve.setBrasserie(resultSet.getString("brasserie"));
            }

            return biereTrouve;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Biere> listerBieres() {
        try {
            List<Biere> listeBieres = new ArrayList<Biere>();

            Connection connection = this.daoFactory.getConnexion();

            PreparedStatement preparedStatement = connection.prepareStatement(LISTER);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Biere biereligne = new Biere("", "", "", "");

                biereligne.setNom(resultSet.getString("nom"));
                biereligne.setType(resultSet.getString("type"));
                biereligne.setCouleur(resultSet.getString("couleur"));
                biereligne.setBrasserie(resultSet.getString("brasserie"));

                listeBieres.add(biereligne);
            }

            return listeBieres;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean supprimerBiere(String nom) {
        try {
            Connection connection = this.daoFactory.getConnexion();

            PreparedStatement preparedStatement = connection.prepareStatement(SUPPRIMER);

            preparedStatement.setString(1, nom);

            int nbLigneConcerne = preparedStatement.executeUpdate();

            return nbLigneConcerne > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modifierBiere(Biere biere) {
        try {
            Connection connection = this.daoFactory.getConnexion();

            PreparedStatement preparedStatement = connection.prepareStatement(MAJ);

            preparedStatement.setString(1, biere.getType());
            preparedStatement.setString(2, biere.getCouleur());
            preparedStatement.setString(3, biere.getBrasserie());
            preparedStatement.setString(4, biere.getNom());

            int nbLigneConcerne = preparedStatement.executeUpdate();

            return nbLigneConcerne > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
