<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "edu.neu.cs5200.ds.movie.dao.*, edu.neu.cs5200.ds.movie.entities.*,edu.neu.cs5200.ds.movie.manager.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
	<h1>Users</h1>
	<%
	User newUser = new User();
	newUser.setFirstName("Jerry");
	newUser.setLastName("Green");
	newUser.setPassword("11234");
	newUser.setEmail("JGreen@hotmail.com");
	newUser.setUsername("JG");
	newUser.setDateOfBirth(new java.sql.Date(System.currentTimeMillis()));
	UserManager userManager = new UserManager();
	// create a new user
	//userManager.createUser(newUser);
	//read all users and list their firstName
	List<User> users = userManager.readAllUsers();
	for (User user: users){
		out.println(user.getEmail());
	}
	// read user by username
	List<Comment> comments = userManager.readUser("cather").getComments();
	for (Comment comment: comments) {
		out.println("comment: " + comment.getComment() + "\t");
	}
	
	// update a user by username
	User updateUser = new User();
	updateUser.setFirstName("Jerry");
	updateUser.setLastName("Green");
	updateUser.setPassword("11234");
	updateUser.setEmail("JerryGreen@hotmail.com");
	updateUser.setUsername("JG");
	userManager.updateUser("JG", newUser);
	out.println(userManager.readUser("JG").getEmail());
	//delete a user by username
	userManager.deleteUser("ham");
	%>
</body>
</html>