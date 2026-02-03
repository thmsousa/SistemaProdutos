<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Carrinho</title>
</head>
<body>

<h2>Meu Carrinho</h2>

<a href="produtos">Voltar</a>

<table border="1">
  <tr>
    <th>Produto</th>
    <th>Preço</th>
    <th>Quantidade</th>
    <th>Total</th>
    <th>Ação</th>
  </tr>

  <c:set var="totalGeral" value="0"/>

  <c:forEach var="item" items="${itens}">
    <tr>
      <td>${item.nomeProduto}</td>
      <td>${item.preco}</td>
      <td>${item.quantidade}</td>
      <td>${item.total}</td>
      <td>
        <form action="carrinho" method="post">
          <input type="hidden" name="acao" value="remover">
          <input type="hidden" name="idProduto" value="${item.idProduto}">
          <input type="submit" value="Remover">
        </form>
      </td>
    </tr>

    <c:set var="totalGeral" value="${totalGeral + item.total}"/>
  </c:forEach>
</table>

<h3>Total Geral: R$ ${totalGeral}</h3>

</body>
</html>
