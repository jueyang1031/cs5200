<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "edu.neu.cs5200.ds.movie.dao.*, edu.neu.cs5200.ds.movie.entities.*,edu.neu.cs5200.ds.movie.manager.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comments</title>
</head>
<body>
	<h1>Comments</h1>
	<%
	Comment newComment = new Comment();
	newComment.setMid(2);
	newComment.setUid(4);
	newComment.setComment("best");
	newComment.setDate(new java.sql.Date(System.currentTimeMillis()));
	CommentManager commentManager = new CommentManager();
	// create a new comment
	commentManager.createComment(newComment);
	//read all comments and list their firstName
	List<Comment> comments = commentManager.readAllComments();
	for (Comment comment: comments){
		out.println("all: " + comment.getComment());
	}
	comments = commentManager.readAllCommentsForUsername("cather");
	for (Comment comment: comments){
		out.println("byUsername: " + comment.getComment());
	}
	comments = commentManager.readAllCommentsForMovie(2);
	for (Comment comment: comments){
		out.println("byMovieId: " + comment.getComment());
	}
	%>
</body>
</html>