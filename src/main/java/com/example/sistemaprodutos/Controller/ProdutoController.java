package com.example.sistemaprodutos.Controller;

import com.example.sistemaprodutos.DAO.*;
import com.example.sistemaprodutos.Model.Produto;
import com.example.sistemaprodutos.Model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/produtos")
public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (ProdutoDao dao = new ProdutoDaoClasse()) {
            request.setAttribute("produtos", dao.buscar());
            request.getRequestDispatcher("produtos.jsp").forward(request, response);
        } catch (ErroDao e) {
            response.sendRedirect("index.jsp?mensagem=Erro ao listar produtos");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        Usuario u = (Usuario) session.getAttribute("usuario");

        if (u == null || !u.getTipo().equals("FUNCIONARIO")) {
            response.sendRedirect("produtos?mensagem=Acesso negado");
            return;
        }

        String acao = request.getParameter("acao");
        try (ProdutoDao dao = new ProdutoDaoClasse()) {

            if ("inserir".equals(acao)) {
                Produto p = new Produto(request.getParameter("nome"), Double.parseDouble(request.getParameter("preco")));
                dao.inserir(p);
            }

            if ("editar".equals(acao)) {
                Produto p = new Produto();
                p.setId(Integer.parseInt(request.getParameter("id")));
                p.setNome(request.getParameter("nome"));
                p.setPreco(Double.parseDouble(request.getParameter("preco")));
                dao.editar(p);
                response.sendRedirect("produtos?mensagem=Produto atualizado");
            }

            if ("remover".equals(acao)) {
                dao.deletar(Integer.parseInt(request.getParameter("id")));
            }
            response.sendRedirect("produtos");

        } catch (ErroDao e) {
            response.sendRedirect("produtos?mensagem=Erro no produto");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
