/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.controle;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.Produto;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Notebook
 */
public class VerificarProdutoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String aux = request.getParameter("page");
        Produto p = null;
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            p = produtoDAO.obter(id);
            if(p != null){
                request.setAttribute("mensagem", "Dados do produto:");
                request.setAttribute("produto", p);
            }else{
                request.setAttribute("mensagem", "Não existe produto com este id");
                request.setAttribute("produto", null);
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Não existe produto com este id");
            request.setAttribute("produto", null);
        }
        if(aux.equals("verificar")){
            request.setAttribute("acao", "verificar");
        }else{
            request.setAttribute("acao", "alterar");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtoinfo.jsp");
        requestDispatcher.forward(request, response);
    }
}
