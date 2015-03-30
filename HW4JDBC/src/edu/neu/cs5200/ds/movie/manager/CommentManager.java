package edu.neu.cs5200.ds.movie.manager;

import java.util.List;

import edu.neu.cs5200.ds.movie.dao.CommentDao;
import edu.neu.cs5200.ds.movie.entities.Comment;

public class CommentManager {

	private CommentDao commentDao;
	
	public CommentManager() {
		super();
		this.commentDao = new CommentDao();
	}
	
	public void createComment(Comment newComment) {
		commentDao.createComment(newComment);
	}
	
	public List<Comment> readAllComments() {
		return commentDao.readAllComments();
	}
	
	public List<Comment> readAllCommentsForUsername(String username) {
		return commentDao.readAllCommentsForUsername(username);
	}
	
	public List<Comment> readAllCommentsForMovie(int movieId) {
		return commentDao.readAllCommentsForMovie(movieId);
	}
	
	public Comment readCommentForId(int commentId) {
		return commentDao.readCommentForId(commentId);
	}
	
	public void updateComment(int commentId, String newComment) {
		commentDao.updateComment(commentId, newComment);
	}
	
	public void deleteComment(int commentId) {
		commentDao.deleteComment(commentId);
	}
}
