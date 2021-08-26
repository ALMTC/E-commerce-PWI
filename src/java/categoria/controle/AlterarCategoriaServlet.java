/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria.controle;

import categoria.modelo.CategoriaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notebook
 */
public class AlterarCategoriaServlet extends HttpServlet {

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
        String descricao=request.getParameter("descricao");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean sucesso=false;
        
        try{
            sucesso=categoriaDAO.alterarCategoria(descricao, id);
            if(sucesso){
                request.setAttribute("mensagem", "Alteração desta categoria foi efetuado com sucesso");
            }else{
                request.setAttribute("mensagem", "Não foi possível alterar a categoria");
            }
        }catch (Exception ex){
            request.setAttribute("mensagem", "Não foi possível alterar a categoria");
        }
        request.setAttribute("acao", "alterar");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categoriainfo.jsp");
        requestDispatcher.forward(request, response);
    }
}
