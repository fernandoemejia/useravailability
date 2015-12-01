<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Productos List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Productos </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Descripcion</th>
				        <th width="100"></th>
				        <th width="100"></th>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${productos}" var="producto">
					<tr>
						<td>${producto.descripcion}</td>
					
					<td><a href="<c:url value='/edit-producto-${producto.descripcion}' />" class="btn btn-success custom-width">edit</a></td>										
					 
									
						<td><a href="<c:url value='/delete-producto-${producto.descripcion}' />" class="btn btn-danger custom-width">delete</a></td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
	 	<div class="well">
	 		<a href="<c:url value='/newProducto' />">Add New Producto</a>
	 	</div>
   	</div>
</body>
</html>