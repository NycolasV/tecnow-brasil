<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/assets/imagens" var="imagensRoot" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="templates/head.jsp"/>
</head>
<body>
	<header>
		<c:import url="templates/nav.jsp"/>
	</header>
	
	<section>
		<h1>Opa! ERROR 404 - Página não encontrada</h1>
		
		<div id="img-centro">
			<img src="${imagensRoot}/gatinhoTriste.jpg" id="img-central"/>
		</div>
	</section>
</body>
</html>