/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import produto.modelo.Produto;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Notebook
 */
@WebServlet(name = "MostrarProdutoFotoServlet", urlPatterns = {"/MostrarProdutoFoto"})
public class MostrarProdutoFotoServlet extends HttpServlet {

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
        String foto = null;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.obter(id);
        foto = produto.getFoto();
        
        File arquivoFoto = new File(foto);
        if (!arquivoFoto.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        ServletContext context = this.getServletContext();
        String mimeType = context.getMimeType(foto);
        if (mimeType == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentLength((int) arquivoFoto.length());
        response.setContentType(mimeType);
        FileInputStream in = new FileInputStream(arquivoFoto);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
    }
}
