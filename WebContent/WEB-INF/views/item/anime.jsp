<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/app/anime/alterar" var="urlAnimeAlterar"/>
<c:url value="/app/anime/excluir" var="urlAnimeExcluir"/>

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
	<h1>Lista de animes cadastrados</h1>

	<h3>Edite ou cadastre novos animes</h3>
	<form action="${urlAnimeAlterar}" method="post">
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
				Salvar <i class="material-icons right">send</i>
		</button>	
		<br><br>
	</form>
	<br><br>
	
	<table>
		<thead>
			<tr>
				<td>Nome</td>
				<td>Data de registro</td>
				<td>Deletar</td>
				<td>Editar</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${animes}" var="anime">
				<tr>
					<td>${anime.nome}</td>
					<td>${anime.dataAnime}</td>
					<td>
						<button class="btn waves-effect waves-light" type="button" name="action">
							<a href="${urlAnimeExcluir}?id=${anime.id}">Excluir</a>
						</button>
					</td>
					<td>
						<button class="btn waves-effect waves-light" type="button" name="action">
							<a href="${urlAnimeAlterar}?id=${anime.id}">Alterar</a>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</section>
</body>
</html>