/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrinhocompra.controle;

import carrinhocompra.modelo.CarrinhoCompra;
import carrinhocompra.modelo.CarrinhoCompraItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class GerarPaginaCarrinhoCompraServlet extends HttpServlet {

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
        try {
            List<CarrinhoCompraItem> carrinhoCompraItens = CarrinhoCompra.obterCarrinhoCompra(cookie.getValue());
            request.setAttribute("carrinhoCompraItens", carrinhoCompraItens);
        } catch (Exception ex) {

        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("carrinho.jsp");
        requestDispatcher.forward(request, response);
    }
}
