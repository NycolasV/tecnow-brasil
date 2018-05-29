<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/cadastro/salvar" var="urlCadastrar" />

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
	<h1>Cadastre-se!</h1>

	<c:if test="${not empty erros}">
		<c:forEach items="${erros}" var="erro">
			<p>${erro}</p>
		</c:forEach>
	</c:if>

	<form method="post" action="${urlCadastrar}">
		<input name="id" value="${usuario.id}" type="hidden">
	
		<label>
			Nome <br>
			<input type="text" value="${usuario.nome}" name="nome">
		</label>
		<br><br>
		
		<label>
			E-mail <br>
			<input type="email" value="${usuario.email}" name="email">
		</label>
		<br><br>
		
		<label>
			Senha <br>
			<input type="password" value="${usuario.senha}" name="senha">
		</label>
		<br><br>
		
		<label>
			Data de nascimento <br>
			<input type="date" value="${usuario.dataNascimento}" name="dataNascimento">
		</label>
		<br><br>
		
		<div class="input-field col s12">
			<select name="sexo">
				<option value="M">Masculino</option>
				<option value="F">Feminino</option>
				<option value="O">Outro</option>
				<option value="P">Prefiro não dizer</option>
			</select>
		</div>
		<br><br>
		
		<button class="btn waves-effect waves-light" type="submit" name="action">Cadastrar
			<i class="material-icons right">send</i>
		</button>	
		
		<button class="btn waves-effect waves-light" type="reset" name="action">Limpar</button>
	</form>
	</section>
</body>
</html>