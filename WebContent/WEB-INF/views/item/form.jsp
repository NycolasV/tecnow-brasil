<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/app/anime/salvar" var="urlAnimeSalvar"/>
<c:url value="/app/jogo/salvar" var="urlJogoSalvar"/>
<c:url value="/app/anime" var="urlAnimeLista"/>
<c:url value="/app/jogo" var="urlJogoLista"/>
<c:url value="/login/sair" var="urlSair"/>

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
		<h1>Bem-vindo ${usuario.nome}!</h1>
		
		<div style="text-align: center;">
			<button class="btn waves-effect waves-light" type="button" name="action">
				<a href="${urlSair}">Sair</a>
			</button>
			<br><br>	
		</div>	
			
		<h2>Cadastre quantos jogos e animes desejar:</h2>
		
		<h3>Cadastro de jogos</h3>
		<form action="${urlJogoSalvar}" method="post">
			<input name="id" value="${jogo.id}" type="hidden">
			
			<label>
				Nome do jogo <br>
				<input type="text" name="nome" value="${jogo.nome}">
			</label>
			<br><br>
			
			<label>Categoria do jogo</label>
			<div class="input-field col s12">
				<select name="categoria">
					<option value="T">Tiro</option>
					<option value="R">RPG</option>
					<option value="P">Plataforma</option>
					<option value="E">Esporte</option>
					<option value="H">Hack and Slash</option>
					<option value="O">Outro</option>
				</select>
			</div>
			<br><br>
			
			<c:if test="${not empty erros}">
				<c:forEach items="${erros}" var="erro">
					<p>${erro}</p>
				</c:forEach>
			</c:if>		
			
			<button class="btn waves-effect waves-light" type="submit" name="action">
				Salvar Jogo <i class="material-icons right">send</i>
			</button>	
			<br><br>
		</form>
				
		<button class="btn waves-effect waves-light" type="button" name="action">
			<a href="${urlJogoLista}">Veja a lista completa de jogos aqui!</a>
		</button>
				
		<h3>Cadastro de animes</h3>
		<form action="${urlAnimeSalvar}" method="post">
			<input name="id" value="${anime.id}" type="hidden">
		
			<label>
				Nome do anime <br>
				<input type="text" name="nome" value="${anime.nome}">
			</label>
			<br><br>
			
			<c:if test="${not empty erros}">
				<c:forEach items="${erros}" var="erro">
					<p>${erro}</p>
				</c:forEach>
			</c:if>
			
			<button class="btn waves-effect waves-light" type="submit" name="action">
				Salvar Anime <i class="material-icons right">send</i>
			</button>
			<br><br>
		</form>
	
		<button class="btn waves-effect waves-light" type="button" name="action">
			<a href="${urlAnimeLista}">Veja a lista completa de animes aqui!</a>
		</button>
	</section>
</body>
</html>