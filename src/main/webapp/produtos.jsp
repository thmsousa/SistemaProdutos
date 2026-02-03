<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Produtos</title>
</head>
<body>

<h2>Produtos da Loja</h2>

<c:if test="${not empty param.mensagem}">
    <p style="color:green">${param.mensagem}</p>
</c:if>

<c:if test="${not empty sessionScope.usuario}">
    Olá, ${sessionScope.usuario.nome}
    | <a href="perfil.jsp">Perfil</a>

    <c:if test="${sessionScope.usuario.tipo == 'CLIENTE'}">
        | <a href="carrinho">Carrinho</a>
    </c:if>

    | <a href="sair">Sair</a>
</c:if>

<c:if test="${sessionScope.usuario.tipo == 'FUNCIONARIO'}">
    | <a href="cadastro_usuario.jsp">Cadastrar Usuário</a>
</c:if>

<hr>

<table border="1">
    <tr>
        <th>Nome</th>
        <th>Preço</th>
        <th>Ação</th>
    </tr>

    <c:forEach var="p" items="${produtos}">
        <tr>
            <td>${p.nome}</td>
            <td>${p.preco}</td>
            <td>

                <c:if test="${sessionScope.usuario.tipo == 'CLIENTE'}">
                    <form action="carrinho" method="post">
                        <input type="hidden" name="acao" value="adicionar">
                        <input type="hidden" name="idProduto" value="${p.id}">
                        <input type="submit" value="Adicionar ao carrinho">
                    </form>
                </c:if>

                <c:if test="${sessionScope.usuario.tipo == 'FUNCIONARIO'}">
                    <a href="editar_produto.jsp?id=${p.id}&nome=${p.nome}&preco=${p.preco}">Editar</a>
                    |
                    <form action="produtos" method="post" style="display:inline">
                        <input type="hidden" name="acao" value="remover">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="submit" value="Remover">
                    </form>
                </c:if>

            </td>
        </tr>
    </c:forEach>
</table>

<hr>

<c:if test="${sessionScope.usuario.tipo == 'FUNCIONARIO'}">
    <h3>Cadastrar Produto</h3>

    <form action="produtos" method="post">
        <input type="hidden" name="acao" value="inserir">
        Nome: <input type="text" name="nome"><br>
        Preço: <input type="text" name="preco"><br>
        <input type="submit" value="Cadastrar">
    </form>
</c:if>

</body>
</html>
