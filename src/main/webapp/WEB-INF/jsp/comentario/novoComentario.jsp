<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/commons.css" />" rel="stylesheet"
	type="text/css" media="screen" />
<script src="<c:url value="/js/jquery.js" />" type="text/javascript"></script>
	<script src="<c:url value="/js/commons.js" />" type="text/javascript"></script>
<title>Escreva seu comentario!</title>
</head>
<body>
	<div class="conteudo">
	<form method="get" action="<c:url value="comentar" />" class="painel_comentario">
		<fieldset>
			<legend>O que você pensa sobre ${tema.titulo} ?</legend>
			<p>
				<label for="email">Seu Email: </label>
				<input type="text" id="email" name="usuario.email" class="formulario"/>
			</p>
			<p>
				<label for="comentario">Faça seu comentario!</label><br/>
				<textarea id="comentario" name="comentario.conteudo" class="formulario"></textarea>
			</p>
			<input type="button" value="comentar" class="go go_comentario" />
		</fieldset>
		
		<c:if test="${!empty errors}" >
			<div class="erro">
				<c:forEach items="${errors}" var="erro">
					${erro.message}<br>
				</c:forEach>
			</div>
		</c:if>
	</form>
	</div>
</body>
</html>