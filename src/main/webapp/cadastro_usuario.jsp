<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.usuario.tipo != 'FUNCIONARIO'}">
    <c:redirect url="produtos"/>
</c:if>

<html>
<head>
    <title>Cadastrar Usuário</title>
</head>
<body>

<h2>Novo Usuário</h2>

<form action="usuario" method="post">
    <input type="hidden" name="acao" value="cadastrar">

    Nome: <input type="text" name="nome"><br><br>
    Login: <input type="text" name="login"><br><br>
    Senha: <input type="password" name="senha"><br><br>

    Tipo:
    <select name="tipo">
        <option value="CLIENTE">Cliente</option>
        <option value="FUNCIONARIO">Funcionário</option>
    </select><br><br>

    <input type="submit" value="Cadastrar">
</form>

<a href="produtos">Voltar</a>

</body>
</html>
