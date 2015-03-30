package edu.neu.cs5200.ds.movie.manager;

import java.util.List;

import edu.neu.cs5200.ds.movie.dao.UserDao;
import edu.neu.cs5200.ds.movie.entities.User;

public class UserManager {
	
	private UserDao userDao;
	
	
	public UserManager() {
		super();
		this.userDao = new UserDao();
	}
	
	public void createUser(User newUser){
		userDao.create(newUser);
	}
	
	public List<User> readAllUsers() {
		return userDao.readAllUsers();
	}
	
	public User readUser(String username) {
		return userDao.findByUsername(username);
	}
	
	public void updateUser(String username, User user) {
		userDao.updateUser(username, user);
	}
	
	public void deleteUser(String username) {
		userDao.deleteUser(username);
	}
}
