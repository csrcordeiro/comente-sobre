<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="/css/commons.css" />" rel="stylesheet" type="text/css" media="scree" /> 
	<title>Seja bem vindo ao comente aqui!</title>
</head>
<body>
	<form action="tema/escolher" method="get" accept-charset="UTF-8">
	   <fieldset>
	      <legend>Sobre o que quer comentar?</legend>

	      <label for="tema">Tema: </label>
		  <input id="tema" type="text" name="tema.titulo" />
		  
		  <br/>
		  <button type="submit">go!</button>
	   </fieldset>
	</form>
</body>
</html>