/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrinhocompra.controle;

import carrinhocompra.modelo.CarrinhoCompra;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notebook
 */
public class RemoverCarrinhoCompraServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String produtoId = request.getParameter("produtoId");

        Cookie cookie = CarrinhoCompra.obterCookie(request);
        try {
            String novoValor = CarrinhoCompra.removerItem(Integer.parseInt(produtoId), cookie.getValue());
            cookie.setValue(novoValor);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("GerarPaginaCarrinhoCompra");
        requestDispatcher.forward(request, response);
    }
}
