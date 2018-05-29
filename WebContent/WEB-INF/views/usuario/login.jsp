<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="login/entrar" var="urlLoginEntrar"/>

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp"/>
	</header>
	
	<section>
	<h1>Faça seu login!</h1>
	
	<c:if test="${not empty erros}">
		<c:forEach items="${erros}" var="erro">
			<p>${erro}</p>
		</c:forEach>
	</c:if>
	
	<form method="post" action="${urlLoginEntrar}">
		<label>
			E-mail <br>
			<input type="email" name="email">
		</label>
		<br><br>
		
		<label>
			Senha <br>
			<input type="password" name="senha">
		</label>
		<br><br>
			
		<button class="btn waves-effect waves-light" type="submit" name="action">Login
			<i class="material-icons right">send</i>
		</button>	
	</form>
	</section>
</body>
</html>