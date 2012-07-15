<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;
					     charset=UTF-8">

	<link href="<c:url value="/css/commons.css" />" rel="stylesheet"
	type="text/css" media="screen" />
	<script src="<c:url value="/js/jquery.js" />" type="text/javascript"></script>
	<script src="<c:url value="/js/commons.js" />" type="text/javascript"></script>
    <title>Veja o que dizem sobre o tema.</title>
  </head>
  <body>
    <div class="conteudo">
      <div class="listagem">
      	<c:if test="${!empty comentarioList}" >
		<h3>Veja o que dizem sobre isso</h3>
		<ul class="comentarios">
			<c:forEach items="${comentarioList}" var="comentario">
	  			<li>
				    <span class="dono">${comentario.dono.email }</span>
				    <p>
						${comentario.conteudo}
			    	</p>
		  		</li>
		  </c:forEach>
		</ul>
		</c:if>
      </div>
    </div>
  </body>
</html>
