/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.controle;

import compra.modelo.Compra;
import compra.modelo.CompraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notebook
 */
public class VisualizarTodasComprasServlet extends HttpServlet {

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
        
        List<Compra> compras = new ArrayList<>();
        CompraDAO compraDAO = new CompraDAO();
        
        compras = compraDAO.retornarCompras();
        
        request.setAttribute("compras", compras);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("todaascompras.jsp");
        requestDispatcher.forward(request, response);
        
        
    }
}
