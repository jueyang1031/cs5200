package edu.neu.cs5200.ds.movie.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.ds.movie.entities.Actor;
import edu.neu.cs5200.ds.movie.entities.Cast;

public class ActorDao {

	private DataSource ds;
	
	public ActorDao(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HW4Movie");
			System.out.println(ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// create a actor
	public Actor create(Actor actor) {
		String sql = "INSERT INTO ACTOR VALUES (null, ?, ?, ?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}
	
	// read all actors
	public List<Actor> readAllActors() {
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "SELECT * FROM ACTOR";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("firstName"));
				actor.setLastName(rs.getString("lastName"));
				actor.setDateOfBirth(rs.getDate("dateOfBirth"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}
	
	//
	public Actor findById(int id) {
		Actor actor = new Actor();
		String sql = "SELECT * FROM ACTOR WHERE id = ?";
		Connection connection;
		try {
			connection = ds.getConnection();		
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("firstName"));
				actor.setLastName(rs.getString("lastName"));
				actor.setDateOfBirth(rs.getDate("dateOfBirth"));
			}
			String castsSql = "SELECT * FROM CAST WHERE aid=?";
			statement = connection.prepareStatement(castsSql);
			statement.setInt(1, actor.getId());
			rs = statement.executeQuery();
			List<Cast> casts = new ArrayList<Cast>();
			while (rs.next()) {
				Cast cast = new Cast();
				cast.setId(rs.getInt("id"));
				cast.setMid(rs.getInt("mid"));
				cast.setAid(rs.getInt("aid"));
				cast.setCharacterName(rs.getString("characterName"));
				casts.add(cast);
			}
			actor.setCasts(casts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}
	
	public Actor updateActor(int id, Actor actor) {
		String sql = "UPDATE ACTOR SET firstName=?, lastName=?, dateOfBirth=? WHERE id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.setInt(4,id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}
	
	// delete actor by id
	public int deleteActor(int id) {
		String sql = "DELETE FROM ACTOR WHERE id =?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
