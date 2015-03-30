<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "edu.neu.cs5200.ds.movie.dao.*, edu.neu.cs5200.ds.movie.entities.*,edu.neu.cs5200.ds.movie.manager.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actors</title>
</head>
<body>
	<h1>Actors</h1>
	<%
	Actor newActor = new Actor();
	newActor.setFirstName("Ken");
	newActor.setLastName("More");
	newActor.setDateOfBirth(new java.sql.Date(System.currentTimeMillis()));
	ActorManager actorManager = new ActorManager();
	// create a new actor
	//actorManager.createActor(newActor);
	//read all actors and list their firstName
	List<Actor> actors = actorManager.readAllActors();
	for (Actor actor: actors){
		out.println(actor.getFirstName());
	}
	// read a actor by id
	List<Cast> casts = actorManager.readActor(1).getCasts();
	for (Cast cast: casts) {
		out.println("casts: " + cast.getCharacterName() + "\t");
	}
	// update a actor by actorname and display the change
	Actor updateActor = new Actor();
	updateActor.setFirstName("Mike");
	updateActor.setLastName("Green");
	actorManager.updateActor(1, updateActor);
	out.println(actorManager.readActor(1).getFirstName());
	//delete a actor by actorname
	actorManager.deleteActor(2);
	%>
</body>
</html>