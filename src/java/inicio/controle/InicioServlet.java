/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.Produto;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Notebook
 */
public class InicioServlet extends HttpServlet {

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
        /* processamento */
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtosDisponiveis = produtoDAO.obterProdutosEmEstoque();
        request.setAttribute("produtosDisponiveis", produtosDisponiveis);
        
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals("ufcsmdecommerce.carrinhocompras")) {
                cookie = cookies[i];
                break;
            }
        }
        if (cookie == null) {
            cookie = new Cookie("ufcsmdecommerce.carrinhocompras", "");
        }
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);

        /* saída */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
}
