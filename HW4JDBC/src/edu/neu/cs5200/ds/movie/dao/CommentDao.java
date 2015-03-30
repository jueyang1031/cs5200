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

public class CommentDao {

	private DataSource ds;
	
	public CommentDao(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HW4Movie");
			System.out.println(ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// create a new comment
	public int createComment(Comment comment) {
		String sql = "INSERT INTO COMMENT VALUES (?, ?, ?, ?, null)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, comment.getUid());
			statement.setInt(2, comment.getMid());
			statement.setString(3, comment.getComment());
			statement.setDate(4, comment.getDate());
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	// read all comments
	public List<Comment> readAllComments() {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "SELECT * FROM COMMENT";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setUid(rs.getInt("uid"));
				comment.setMid(rs.getInt("mid"));
				comment.setComment(rs.getString("comment"));
				comment.setDate(rs.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	public List<Comment> readAllCommentsForUsername(String username) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "SELECT * FROM COMMENT c, USER u WHERE c.uid=u.uid AND u.username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setUid(rs.getInt("uid"));
				comment.setMid(rs.getInt("mid"));
				comment.setComment(rs.getString("comment"));
				comment.setDate(rs.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	public List<Comment> readAllCommentsForMovie(int movieId) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "SELECT * FROM COMMENT c, MOVIE m WHERE c.mid=m.id AND m.id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setUid(rs.getInt("uid"));
				comment.setMid(rs.getInt("mid"));
				comment.setComment(rs.getString("comment"));
				comment.setDate(rs.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	// read a comment by id
	public Comment readCommentForId(int id) {
		Comment comment = new Comment();
		String sql = "SELECT * FROM COMMENT WHERE id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				comment.setId(rs.getInt("id"));
				comment.setUid(rs.getInt("uid"));
				comment.setMid(rs.getInt("mid"));
				comment.setComment(rs.getString("comment"));
				comment.setDate(rs.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	public int updateComment(int commentId, String newComment) {
		String sql = "UPDATE COMMENT SET comment=? WHERE id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setInt(2, commentId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteComment(int commentId) {
		String sql = "DELETE FROM COMMENT WHERE id =?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
