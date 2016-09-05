<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Accès Denied</title>
</head>
<body>
	<h3>Ooops !</h3>
	<h2>${message}</h2>

	<security:authorize access="hasRole('ROLE_MANAGER')">
		<a href="<c:url value="/dashboard-manager" />">Retour</a>
	</security:authorize>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="<c:url value="/dashboard" />">Retour</a>

	</security:authorize>
</body>
</html>