package be.helha.ebar.dao;

import be.helha.ebar.user.User;

public interface UserDao extends Dao{
	User getUser(String email, String password);
}
