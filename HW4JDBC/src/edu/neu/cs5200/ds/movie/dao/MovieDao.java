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

import edu.neu.cs5200.ds.movie.entities.Cast;
import edu.neu.cs5200.ds.movie.entities.Comment;
import edu.neu.cs5200.ds.movie.entities.Movie;

public class MovieDao {

	private DataSource ds;
	
	public MovieDao(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HW4Movie");
			System.out.println(ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// create a movie
	public Movie create(Movie movie) {
		String sql = "INSERT INTO MOVIE VALUES (null, ?, ?, ?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, new Date(movie.getReleaseDate().getTime()));
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	// read all movies
	public List<Movie> readAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM MOVIE";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Movie m = new Movie();
				m.setId(rs.getInt("id"));
				m.setTitle(rs.getString("title"));
				m.setPosterImage(rs.getString("posterImage"));
				m.setReleaseDate(rs.getDate("releaseDate"));
				movies.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	// read a movie by id and its all comments
	public Movie findById(int id) {
		Movie movie = new Movie();
		String sql = "SELECT * FROM MOVIE WHERE id = ?";
		Connection connection;
		try {
			connection = ds.getConnection();		
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setPosterImage(rs.getString("posterImage"));
				movie.setReleaseDate(rs.getDate("releaseDate"));
			}
			String commentsSql = "SELECT * FROM COMMENT WHERE mid=?";
			statement = connection.prepareStatement(commentsSql);
			statement.setInt(1, movie.getId());
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
			movie.setComments(comments);
			String castsSql = "SELECT * FROM CAST WHERE mid=?";
			statement = connection.prepareStatement(castsSql);
			statement.setInt(1, movie.getId());
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
			movie.setCasts(casts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	public Movie updateMovie(int id, Movie movie) {
		String sql = "UPDATE MOVIE SET title=?, posterImage=?, releaseDate=? WHERE id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, new Date(movie.getReleaseDate().getTime()));
			statement.setInt(4,id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	public int deleteMovie(int id) {
		String sql = "DELETE FROM MOVIE WHERE id =?";
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
