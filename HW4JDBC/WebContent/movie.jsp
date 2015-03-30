<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "edu.neu.cs5200.ds.movie.dao.*, edu.neu.cs5200.ds.movie.entities.*,edu.neu.cs5200.ds.movie.manager.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies</title>
</head>
<body>
	<h1>Movies</h1>
	<%
	MovieManager m = new MovieManager();
	List<Movie> movies = m.readAllMovies();
	for (Movie movie: movies){
		out.println(movie.getTitle());
	}
	out.println(m.readMovie(2).getTitle());
	Movie newMovie = new Movie();
	newMovie.setTitle("Terminator");
	newMovie.setPosterImage("Terminator poster");
	newMovie.setReleaseDate(new Date(System.currentTimeMillis()));
	List<Comment> comments = m.readMovie(2).getComments();
	for (Comment comment: comments) {
		out.println("comment: " + comment.getComment() + "\t");
	}
	m.updateMovie(1, newMovie);
	out.println(m.readMovie(3).getTitle());
	m.deleteMovie(1);
	%>
</body>
</html>