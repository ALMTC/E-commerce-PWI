/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import relatorio.modelo.Relatorio1;
import relatorio.modelo.RelatorioDAO;

/**
 *
 * @author Notebook
 */
public class Relatorio1Servlet extends HttpServlet {

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
        
        List<Relatorio1> resultado = null;
        boolean sucesso = false;
        
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        resultado = relatorioDAO.obterTotalComprasPorCliente();
        sucesso = (resultado != null);
        
        if (sucesso) {
            request.setAttribute("resultado", resultado);
        } else {
            request.setAttribute("mensagem", "Problemas ao gerar o relatório");
            
        }
        request.setAttribute("relatorio", "relatorio1");
        RequestDispatcher dispatcher = request.getRequestDispatcher("relatorios.jsp");
        dispatcher.forward(request, response);
        
    }
}
