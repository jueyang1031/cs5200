<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "edu.neu.cs5200.ds.movie.dao.*, edu.neu.cs5200.ds.movie.entities.*,edu.neu.cs5200.ds.movie.manager.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Casts</title>
</head>
<body>
	<h1>Casts</h1>
	<%
	Cast newCast = new Cast();
	newCast.setMid(2);
	newCast.setAid(3);
	newCast.setCharacterName("engineer");
	CastManager castManager = new CastManager();
	// create a new cast
	castManager.createCast(newCast);
	//read all casts and list their firstName
	List<Cast> casts = castManager.readAllCast();
	for (Cast cast: casts){
		out.println("all: " + cast.getCharacterName());
	}
	casts = castManager.readAllCastForActor(1);
	for (Cast cast: casts){
		out.println("byActorId: " + cast.getCharacterName());
	}
	casts = castManager.readAllCastForMovie(2);
	for (Cast cast: casts){
		out.println("byMovieId: " + cast.getCharacterName());
	}
	%>
</body>
</html>