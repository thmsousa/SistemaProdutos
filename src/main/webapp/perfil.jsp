<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.usuario}">
  <c:redirect url="index.jsp?mensagem=Faça login"/>
</c:if>

<html>
<head>
  <title>Perfil</title>
</head>
<body>

<h2>Editar Perfil</h2>

<form action="usuario" method="post">

  <!-- ESSENCIAL -->
  <input type="hidden" name="acao" value="editar">

  Nome:
  <input type="text" name="nome" value="${sessionScope.usuario.nome}"><br><br>

  Senha:
  <input type="password" name="senha"><br><br>

  <input type="submit" value="Salvar">
</form>

<a href="produtos">Voltar</a>

</body>
</html>
