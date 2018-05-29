<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/assets/imagens" var="imagensRoot" />
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Eventos TecNow Brasil</title>

<link rel="icon" type="image/ico" sizes="16x16" href="${imagensRoot}/tecnow-icone.ico">

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<link rel="stylesheet" href="${cssRoot}/ui.css" />

<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.js"></script>

<script>

$(document).ready(function(){
	$("select").material_select();
})
</script>
