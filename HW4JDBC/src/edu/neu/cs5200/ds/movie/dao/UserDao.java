package edu.neu.cs5200.ds.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.ds.movie.entities.Comment;
import edu.neu.cs5200.ds.movie.entities.User;

public class UserDao {
	DataSource ds;
	
	public UserDao(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HW4Movie");
			System.out.println(ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// create a user
	public User create(User user) {
		String sql = "INSERT INTO USER VALUES (null, ?, ?, ?, ?, ?, ?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setDate(5, user.getDateOfBirth());
			statement.setString(6, user.getUsername());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	// read all users
	public List<User> readAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM USER";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	// read user by its username
	public User findByUsername(String username) {
		User user = new User();
		String sql = "SELECT * FROM USER WHERE username = ?";
		Connection connection;
		try {
			connection = ds.getConnection();		
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
			}
			String commentsSql = "SELECT * FROM COMMENT WHERE uid=?";
			statement = connection.prepareStatement(commentsSql);
			statement.setInt(1, user.getUid());
			rs = statement.executeQuery();
			List<Comment> comments = new ArrayList<Comment>();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setUid(rs.getInt("uid"));
				comment.setMid(rs.getInt("mid"));
				comment.setComment(rs.getString("comment"));
				comment.setDate(rs.getDate("date"));
				comments.add(comment);
			}
			user.setComments(comments);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	// update user by username
	public User updateUser(String username, User user) {
		String sql = "UPDATE USER SET username=?, password=?, firstName=?, lastName=?, email=?, dateOfBirth=? WHERE username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, user.getDateOfBirth());
			statement.setString(7, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	// delete a use by userName
	public int deleteUser(String username) {
		String sql = "DELETE FROM USER WHERE username =?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
