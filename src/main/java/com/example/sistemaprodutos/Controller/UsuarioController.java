package com.example.sistemaprodutos.Controller;

import com.example.sistemaprodutos.DAO.*;
import com.example.sistemaprodutos.Model.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jdk.jfr.StackTrace;

import java.io.IOException;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        Usuario logado = (Usuario) session.getAttribute("usuario");
        String acao = request.getParameter("acao");

        try (UsuarioDao dao = new UsuarioDaoClasse()) {

            if ("cadastrar".equals(acao)) {
                if (logado == null || !"FUNCIONARIO".equals(logado.getTipo())) {
                    response.sendRedirect("produtos?mensagem=Acesso negado");
                    return;
                }
                String login = request.getParameter("login");

                if (dao.buscarPorLogin(login) != null) {
                    response.sendRedirect("cadastro_usuario.jsp?mensagem=Login já existe");
                    return;
                }

                Usuario u = new Usuario();
                u.setNome(request.getParameter("nome"));
                u.setLogin(login);
                u.setSenha(request.getParameter("senha"));

                if (logado != null && logado.getTipo().equals("FUNCIONARIO")) {
                    u.setTipo(request.getParameter("tipo"));
                } else {
                    u.setTipo("CLIENTE");
                }

                dao.inserir(u);
                response.sendRedirect("index.jsp?mensagem=Usuário cadastrado");
            }

            if ("editar".equals(acao)) {
                if (logado == null) {
                    response.sendRedirect("index.jsp");
                    return;
                }

                logado.setNome(request.getParameter("nome"));
                logado.setSenha(request.getParameter("senha"));
                dao.editar(logado);
                session.setAttribute("usuario", logado);
                response.sendRedirect("produtos.jsp?mensagem=Dados atualizados");
            }

        } catch (ErroDao e) {
            response.sendRedirect("index.jsp?mensagem=Erro no usuário");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
