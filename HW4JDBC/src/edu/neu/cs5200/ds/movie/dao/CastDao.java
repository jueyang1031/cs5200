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

import edu.neu.cs5200.ds.movie.entities.Cast;

public class CastDao {
private DataSource ds;
	
	public CastDao(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HW4Movie");
			System.out.println(ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// create a new cast
	public int createCast(Cast cast) {
		String sql = "INSERT INTO CAST (id,mid,aid,characterName) VALUES (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, cast.getMid());
			statement.setInt(2, cast.getAid());
			statement.setString(3, cast.getCharacterName());
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	// read all casts
	public List<Cast> readAllCasts() {
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "SELECT * FROM CAST";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Cast cast = new Cast();
				cast.setId(rs.getInt("id"));
				cast.setMid(rs.getInt("mid"));
				cast.setAid(rs.getInt("aid"));
				cast.setCharacterName(rs.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return casts;
	}
	
	// read all casts by actorId
	public List<Cast> readAllCastsForActor(int actorId) {
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "SELECT * FROM CAST c, ACTOR a WHERE c.aid=a.id AND a.id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Cast cast = new Cast();
				cast.setId(rs.getInt("id"));
				cast.setMid(rs.getInt("mid"));
				cast.setAid(rs.getInt("aid"));
				cast.setCharacterName(rs.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return casts;
	}
	
	public List<Cast> readAllCastsForMovie(int movieId) {
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "SELECT * FROM CAST c, MOVIE m WHERE c.mid=m.id AND m.id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Cast cast = new Cast();
				cast.setId(rs.getInt("id"));
				cast.setMid(rs.getInt("mid"));
				cast.setAid(rs.getInt("aid"));
				cast.setCharacterName(rs.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return casts;
	}
	
	// read a cast by id
	public Cast readCastForId(int id) {
		Cast cast = new Cast();
		String sql = "SELECT * FROM CAST WHERE id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				cast.setId(rs.getInt("id"));
				cast.setMid(rs.getInt("mid"));
				cast.setAid(rs.getInt("aid"));
				cast.setCharacterName(rs.getString("characterName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cast;
	}
	
	public int updateCast(int castId, Cast newCast) {
		String sql = "UPDATE CAST SET mid=?, aid=?, characterName=? WHERE id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, newCast.getMid());
			statement.setInt(2, newCast.getAid());
			statement.setString(3, newCast.getCharacterName());
			statement.setInt(4, newCast.getId());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteCast(int castId) {
		String sql = "DELETE FROM CAST WHERE id =?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, castId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
