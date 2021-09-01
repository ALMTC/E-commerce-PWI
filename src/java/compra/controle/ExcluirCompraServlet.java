/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.controle;

import compra.modelo.CompraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notebook
 */
public class ExcluirCompraServlet extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        boolean sucesso = false;
        
        CompraDAO compraDAO = new CompraDAO();
        try {
            sucesso = compraDAO.excluirCompra(id);
        } catch (Exception ex) {
            Logger.getLogger(ExcluirCompraServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(sucesso){
            request.setAttribute("mensagem", "Compra excluída com sucessco");
        }else{
            request.setAttribute("mensagem", "Não foi possível excluir a compra");
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("VisualizarTodasCompras");
        requestDispatcher.forward(request, response);
    }
}
