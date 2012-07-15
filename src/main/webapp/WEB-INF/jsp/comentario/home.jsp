<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="/css/commons.css" />" rel="stylesheet" type="text/css" media="screen" />
	<script src="<c:url value="/js/jquery.js" />" type="text/javascript"></script>
	<script src="<c:url value="/js/commons.js" />" type="text/javascript"></script>
	<title>Seja bem vindo ao comente aqui!</title>
</head>
<body>
	<div class="conteudo">
		<form action="tema/escolher" method="get" accept-charset="UTF-8" class="painel">
			<fieldset>
				<legend>Sobre o que deseja comentar?</legend>

				<input id="tema" type="text"
					name="tema.titulo" /> <br />
				<input type="button" value="go!" class="go"/>
			</fieldset>
			
			<c:if test="${!empty errors}">
				<div class="erro">
					<c:forEach var="erro" items="${errors}">
	   					${erro.message}
	   				</c:forEach>
	   			</div>
	   		</c:if>
		</form>
	</div>
</body>
</html>