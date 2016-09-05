<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<template:master>
	<h2>Page d'acceuil commun [administrator, Manager]</h2>
	<h4>
		<a href="${contextPath}/login">Pour s'authentifier click ici</a>
	</h4>
	<br>
	<h3>Liste des urls</h3>
	<ul>
		<li>Login : <b>/login</b></li>
		<li>logout : <b>/logout</b></li>
		<li>loginfailed : <b>/loginfailed</b></li>
		<li>Dashboard Administrator : <b>/dashboard</b>
			<ul>
				<li>Crud user : <b>/dashboard/user/manage</b></li>
				<li>Crud location : <b>/dashboard/location/manage</b></li>
			</ul>
		</li>
		<li>Dashboard Gerant : <b>/dashboard-manager</b></li>
            <ul>
			    <li>Crud promotion : <b>/dashboard-manager/promotion/manage</b></li>
		   </ul>
		<li>Web Service REStFull
			<ul>
				<li>rechercher des emplacement (3
					param)[latitude,longitude,query<= non requis] : <b>/locations/search?latitude=33.99&longitude=-6.84</b>
				</li>
				<li>detail d'un emplacement (1 param)[LocationID] : <b>En
						cours</b></li>
			</ul>
		</li>
	</ul>
</template:master>