<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Login</title>
</head>
<body>

<h2>Login</h2>

<c:if test="${not empty param.mensagem}">
  <p style="color:red">${param.mensagem}</p>
</c:if>

<form action="logar" method="post">
  Login: <input type="text" name="login"><br><br>
  Senha: <input type="password" name="senha"><br><br>
  <input type="submit" value="Entrar">
</form>

</body>
</html>
