<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Escreva seu comentario!</title>
</head>
<body>
	<h1>O que vc pensa sobre ${tema.titulo} ?</h1>
	<form action="comentario/comentar" method="post">
		<fieldset>
			<p>
				<label for="email">Seu Email: </label>
				<input type="text" id="email" name="usuario.email"/>
			</p>
			<p>
				<label for="comentario">Faça seu comentario!</label><br/>
				<textarea id="comentario" name="comentario.conteudo"></textarea>
			</p>
			<input type="hidden" name="tema.id" value="${tema.id}"/>
			<button>Comentar</button>
		</fieldset>
	</form>
</body>
</html>