<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script <script src="//code.jquery.com/jquery.js"></script>></script>
</head>
<body>
	<h1>Hello</h1>
	<script>
	$(function()
			{
				var site =
				{
					latitude: 22.2,
					longitude: 21.9,
					name: "site update"
				};
				//createSite(site);
				//updateSite(7, site);
				removeSite(7);
			});
			function updateSite(id, site)
			{
				$.ajax(
				{
					url: "api/site/"+id,
					data: JSON.stringify(site),
					type: "put",
					dataType: "json",
					contentType: "application/json",
					success: function(response) {
						console.log(response);
					},
					error: function(response) {
						console.log(response);
					}
				});
			}
			function removeSite(id)
			{
				$.ajax(
				{
					url: "api/site/"+id,
					type: "delete",
					dataType: "json",
					contentType: "application/json",
					success: function(response) {
						console.log(response);
					},
					error: function(response) {
						console.log(response);
					}
				});
			}
			function createSite(site)
			{
				$.ajax(
				{
					url: "api/site",
					data: JSON.stringify(site),
					type: "post",
					dataType: "json",
					contentType: "application/json",
					success: function(response) {
						console.log(response);
					},
					error: function(response) {
						console.log(response);
					}
				});
			}
	</script>
</body>
</html>