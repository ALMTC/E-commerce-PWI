/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.controle;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Notebook
 */
public class RemoverProdutoServlet extends HttpServlet {

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
        int id = parseInt(request.getParameter("id"));
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            boolean res = produtoDAO.removerProduto(id);
            if(res){
                request.setAttribute("mensagem", "Produto removido com sucesso");
            }else{
                request.setAttribute("mensagem", "Não foi possível remover o produto");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Não foi possível remover o produto");
        }
        request.setAttribute("acao", "remover");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtoinfo.jsp");
        requestDispatcher.forward(request, response);
    }
}
