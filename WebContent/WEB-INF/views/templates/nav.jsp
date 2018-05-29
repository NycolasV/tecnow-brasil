<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/" var="urlIndex"/>
<c:url value="/cadastro" var="urlCadastro"/>
<c:url value="/login" var="urlLogin"/>
<c:url value="/login/sair" var="urlLoginSair"/>
<c:url value="/app" var="urlForm"/>
<c:url value="/app/anime" var="urlAnime"/>
<c:url value="/app/jogo" var="urlJogo"/>

<nav style="background-color: red;">
	<div class="nav-wrapper" id="cabecalho">
		<a href="${urlIndex}" class="brand-logo" 
			style="color: blue; font-weight: bold;">TecNow
		</a>
		
		<ul id="nav-mobile" class="right hide-on-med-and-down">
	        <li><a href="${urlIndex}">Página inicial</a></li>
    	    
			<c:if test="${not empty usuario.id}">
				<li><a href="${urlForm}">Bem-vindo ${usuario.nome}!</a><li>
				<li><a href="${urlAnime}">Lista de animes</a></li>
				<li><a href="${urlJogo}">Lista de jogos</a></li>
				<li><a href="${urlCadastro}">Editar Usuário</a></li>
			</c:if>
			<c:if test="${empty usuario.id}">
				<li><a href="${urlLogin}">Login</a></li>
				<li><a href="${urlCadastro}">Cadastro</a></li>
			</c:if>
		</ul>
	</div>
</nav>