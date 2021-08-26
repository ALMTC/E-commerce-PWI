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
import usuario.modelo.UsuarioDAO;

/**
 *
 * @author Notebook
 */
public class DeletarUsuarioServlet extends HttpServlet {

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
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{  
            int id = Integer.parseInt(request.getParameter("id"));
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.removerUsuario(id);
        }catch (Exception ex) {
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Logout");
        requestDispatcher.forward(request, response);
        
    }
}
