package com.example.sistemaprodutos.Controller;

import com.example.sistemaprodutos.DAO.*;
import com.example.sistemaprodutos.Model.Carrinho;
import com.example.sistemaprodutos.Model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/carrinho")
public class CarrinhoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("index.jsp?mensagem=Faça login");
            return;
        }

        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) {
            response.sendRedirect("index.jsp?mensagem=Faça login");
            return;
        }
        if ("FUNCIONARIO".equals(u.getTipo())) {
            response.sendRedirect("produtos?mensagem=Acesso negado");
            return;
        }

        try (CarrinhoDao dao = new CarrinhoDaoClasse()) {
            List<Carrinho> itens = dao.buscar(u.getId());
            double total = 0;
            for (Carrinho c : itens) {
                total += c.getTotal();
            }

            request.setAttribute("itens", itens);
            request.setAttribute("total", total);
            request.getRequestDispatcher("carrinho.jsp").forward(request, response);

        } catch (ErroDao e) {
            response.sendRedirect("produtos?mensagem=Erro no carrinho");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        if (u == null) {
            response.sendRedirect("index.jsp?mensagem=Faça login");
            return;
        }

        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        String acao = request.getParameter("acao");

        try (CarrinhoDao dao = new CarrinhoDaoClasse()) {
            if ("adicionar".equals(acao)) {
                dao.adicionar(u.getId(), idProduto);
            }
            if ("remover".equals(acao)) {
                dao.remover(u.getId(), idProduto);
            }
            response.sendRedirect("carrinho");

        } catch (ErroDao e) {
            response.sendRedirect("produtos?mensagem=Erro no carrinho");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}