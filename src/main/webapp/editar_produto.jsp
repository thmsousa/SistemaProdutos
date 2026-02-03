<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Editar Produto</title>
</head>
<body>

<h2>Editar Produto</h2>

<form action="produtos" method="post">
    <input type="hidden" name="acao" value="editar">
    <input type="hidden" name="id" value="${param.id}">

    Nome:
    <input type="text" name="nome" value="${param.nome}"><br>

    Preço:
    <input type="text" name="preco" value="${param.preco}"><br>

    <input type="submit" value="Salvar">
</form>

<a href="produtos">Voltar</a>

</body>
</html>
