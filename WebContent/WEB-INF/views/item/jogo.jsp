<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/app/jogo/alterar" var="urlJogoAlterar"/>
<c:url value="/app/jogo/excluir" var="urlJogoExcluir"/>

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp"/>
	</header>
	
	<h1>Lista de jogos cadastrados</h1>
	
	<section>
	<h3>Edite ou cadastre novos jogos</h3>
		<form action="${urlJogoAlterar}" method="post">
		<input name="id" value="${jogo.id}" type="hidden">
		
		<label>
			Nome do jogo <br>
			<input type="text" name="nome" value="${jogo.nome}">
		</label>
		<br><br>
		
		<label>
			Categoria do jogo <br>
			<select name="categoria">
				<option value="T">Tiro</option>
				<option value="R">RPG</option>
				<option value="P">Plataforma</option>
				<option value="E">Esporte</option>
				<option value="H">Hack and Slash</option>
				<option value="O">Outro</option>
			</select>
		</label>
		<br><br>
		
		<button class="btn waves-effect waves-light" type="submit" name="action">
				Salvar Jogo <i class="material-icons right">send</i>
		</button>	
		<br><br>
	</form>
		
	<table>
		<thead>
			<tr>
				<td>Nome</td>
				<td>Categoria</td>
				<td>Data de registro</td>
				<td>Deletar</td>
				<td>Editar</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${jogos}" var="jogo">
				<tr>
					<td>${jogo.nome}</td>
					<td>${jogo.categoria}</td>
					<td>${jogo.dataJogo}</td>
					<td>
						<button class="btn waves-effect waves-light" type="button" name="action">
							<a href="${urlJogoExcluir}?id=${jogo.id}">Excluir</a>
						</button>
					</td>
					<td>
						<button class="btn waves-effect waves-light" type="button" name="action">
							<a href="${urlJogoAlterar}?id=${jogo.id}">Alterar</a>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</section>
</body>
</html>