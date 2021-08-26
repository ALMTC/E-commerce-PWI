/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria.controle;

import categoria.modelo.CategoriaDAO;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notebook
 */
@WebServlet(name = "RemoverCategoriaServlet", urlPatterns = {"/RemoverCategoria"})
public class RemoverCategoriaServlet extends HttpServlet {

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
        int id = parseInt(request.getParameter("id"));
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            boolean res = categoriaDAO.removerCategoria(id);
            if(res){
                request.setAttribute("mensagem", "Categoria removida com sucesso");
            }else{
                request.setAttribute("mensagem", "Não foi possível remover a categoria");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Não foi possível remover a categoria");
        }
        request.setAttribute("acao", "remover");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categoriainfo.jsp");
        requestDispatcher.forward(request, response);
    }
}
