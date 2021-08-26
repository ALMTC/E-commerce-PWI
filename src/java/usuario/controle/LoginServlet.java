/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import usuario.modelo.Usuario;
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author Notebook
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        /* processamento */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean loginValido = usuarioDAO.efetuarLogin(login, senha);
        /* saída */
        if (loginValido) {
            HttpSession session = request.getSession(true);
            Usuario usuario = usuarioDAO.obter(login);
            session.setAttribute("usuario", usuario);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Inicio");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Login ou senha incorreta");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
