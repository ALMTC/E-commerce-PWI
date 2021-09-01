/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrinhocompra.controle;

import carrinhocompra.modelo.CarrinhoCompra;
import carrinhocompra.modelo.CarrinhoCompraItem;
import compra.modelo.CompraDAO;
import compraproduto.modelo.CompraProdutoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Notebook
 */
public class FinalizarCompraCarrinhoCompraServlet extends HttpServlet {

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
        boolean sucesso = false;
        int usuarioId = Integer.parseInt(request.getParameter("usuario"));
        double total = Double.parseDouble(request.getParameter("total"));
        int compraId;
        
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals("ufcsmdecommerce.carrinhocompras")) {
                cookie = cookies[i];
                break;
            }
        }
        
        try {
            List<CarrinhoCompraItem> carrinhoCompraItens = CarrinhoCompra.obterCarrinhoCompra(cookie.getValue());
            Iterator<CarrinhoCompraItem> iter = carrinhoCompraItens.iterator();
            
            CompraDAO compraDAO = new CompraDAO();
            compraId = compraDAO.criarCompra(usuarioId, total);
            
            CarrinhoCompraItem item = new CarrinhoCompraItem();
            while(iter.hasNext()){
                item = iter.next();
                
                CompraProdutoDAO compraprodutoDAO = new CompraProdutoDAO();
                sucesso = compraprodutoDAO.inserir(compraId, item);
                
                ProdutoDAO produtoDAO = new ProdutoDAO();
                sucesso = produtoDAO.alterarProduto(item.getProduto().getId(), item.getProduto().getDescricao(), item.getProduto().getQuantidade()-item.getQuantidade(), item.getProduto().getPreco());
            }
            if (sucesso){
                cookie.setValue("");
            }
                
            
            request.setAttribute("mensagem", "Compra realizada com sucesso");
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Não foi possível realizar a compra");
        }
        
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("carrinho.jsp");
        requestDispatcher.forward(request, response);
    }
}
