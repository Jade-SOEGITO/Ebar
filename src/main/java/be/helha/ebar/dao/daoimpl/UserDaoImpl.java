package be.helha.ebar.dao.daoimpl;

import be.helha.ebar.biere.Biere;
import be.helha.ebar.dao.UserDao;
import be.helha.ebar.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    public static final String GET = "SELECT * FROM users WHERE email=? and password = crypt(?, password)";
    public DaoFactory daoFactory;

    @Override
    public User getUser(String email, String password) {
        try {
            User userTrouve = new User();

            Connection connection = this.daoFactory.getConnexion();

            PreparedStatement preparedStatement = connection.prepareStatement(GET);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userTrouve.setEmail(resultSet.getString("email"));
                userTrouve.setNom(resultSet.getString("nom"));
                userTrouve.setPassword(resultSet.getString("password"));
            }

            return userTrouve;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
