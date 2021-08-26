/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtocategoria.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produtocategoria.modelo.ProdutoCategoriaDAO;

/**
 *
 * @author Notebook
 */
public class AdicionarProdutoCategoriaServlet extends HttpServlet {

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
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
        ProdutoCategoriaDAO produtoCategoriaDAO = new ProdutoCategoriaDAO();
        try {
            produtoCategoriaDAO.inserir(produtoId, categoriaId);
            request.setAttribute("mensagem", "Categoria vinculada ao produto");
        } catch (SQLException ex) {
            request.setAttribute("mensagem", ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdicionarProdutoCategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("GerirProduto?acao=alterar");
        dispatcher.forward(request, response);
    }
}
