<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Manager Home</title>
<style type="text/css">
table {
	width: 80%
}

th, td {
	padding: 10px;
	
}
.contai{
	justify-content: center;
	display: flex;
	margin-top: 40px;
}
.link{
	width: 30px;
	display: block;
	padding: 15px;
	background-color: pink;
	float: left;
	margin: 0px 20px;
	text-decoration: none;
}
.link:hover{
opacity: 0.6}
</style>
</head>
<body>
	<div align="center">
		<h1>Contact List</h1>
		<h3>
			<a href='<c:url value="/newContact"></c:url>'>New Contact</a>
		</h3>
		<table border="1">
			<th>No</th>
			<th>Name</th>
			<th>Email</th>
			<th>Address</th>
			<th>Telephone</th>
			<th colspan='2'></th>

			<c:forEach var="contact" items="${listContact}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${contact.name}</td>
					<td>${contact.email}</td>
					<td>${contact.address}</td>
					<td>${contact.telephone}</td>
					<td><a
						href='<c:url value="/editContact?id=${contact.id}"></c:url>'>Edit</a></td>
					<td><a
						href='<c:url value="/deleteContact?id=${contact.id}"></c:url>'>Delete</a>
					</td>

				</tr>
			</c:forEach>
		</table>
		<div class='contai'>
		<c:if test="${page > 1}">
			<a class='link' href="<c:url value="/viewemp/${page-1}"></c:url>">${page-1}</a>
		</c:if>

		<a class='link' href="<c:url value="/viewemp/${page}"></c:url>">${page}</a>
		
		<c:if test="${page * num < total}">
			<a class='link' href="<c:url value="/viewemp/${page+1}"></c:url>">${page+1}</a>
		</c:if>
		</div>
		
		
		
<!-- 		hahaa -->
	</div>
</body>
</html>