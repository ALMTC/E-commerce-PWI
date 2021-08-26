/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria.controle;

import categoria.modelo.Categoria;
import categoria.modelo.CategoriaDAO;
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
public class VerificarCategoriaServlet extends HttpServlet {

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
        List<Categoria> categorias = new ArrayList<Categoria>();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try{
            categorias = categoriaDAO.obterCategorias();
            if(categorias !=null){
                request.setAttribute("mensagem", "Dados da categorias:");
                request.setAttribute("categorias", categorias);
            }else{
                request.setAttribute("mensagem", "Falha ao retornar categorias");
                request.setAttribute("categorias", null);
            }
        }catch (Exception ex){
            request.setAttribute("mensagem", "Falha ao retornar categorias");
            request.setAttribute("categorias", null);
        }
        request.setAttribute("acao", "verificar");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categoriainfo.jsp");
        requestDispatcher.forward(request, response);
    }
}
