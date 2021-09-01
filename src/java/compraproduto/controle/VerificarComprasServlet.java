/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compraproduto.controle;

import compraproduto.modelo.CompraProdutoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.Produto;

/**
 *
 * @author Notebook
 */
public class VerificarComprasServlet extends HttpServlet {

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
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        List<Produto> valores = new ArrayList<>();
        
        CompraProdutoDAO compraprodutoDAO = new CompraProdutoDAO();
        try {
            valores = compraprodutoDAO.retornarCompras(usuarioId);
        } catch (Exception ex) {
            Logger.getLogger(VerificarComprasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("valores", valores);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("mostrarcompras.jsp");
        requestDispatcher.forward(request, response);
    }
}
