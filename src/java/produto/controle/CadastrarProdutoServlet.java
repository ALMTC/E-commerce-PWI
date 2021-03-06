/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.controle;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Notebook
 */
@WebServlet(name = "CadastrarProdutoServlet", urlPatterns = {"/CadastrarProduto"})
public class CadastrarProdutoServlet extends HttpServlet {

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
        int id = -1;
        String descricao=null;
        Double valor = -1.0;
        int quantidade = -1;
        FileItem foto = null;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            boolean sucesso = false;
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File("C:/Users/Notebook/ecomerce/temp"));

                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> itens = upload.parseRequest(new ServletRequestContext(request));
                Iterator<FileItem> iter = itens.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    
                    if (!item.isFormField() && item.getFieldName().equals("foto")) {
                        foto = item;
                    }
                    
                    if (item.isFormField() && item.getFieldName().equals("quantidade")) {
                        quantidade = Integer.parseInt(item.getString());
                    }
                    
                    if (item.isFormField() && item.getFieldName().equals("valor")) {
                        valor = Double.parseDouble(item.getString());
                    }
                    
                    if (item.isFormField() && item.getFieldName().equals("descricao")) {
                        descricao = item.getString();
                    }
                }
                
                if (descricao != null && valor != -1.0 && quantidade != -1){
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.cadastrarProduto(descricao, quantidade, valor);
                    id = produtoDAO.retornaUltimo();
                    if(foto != null && id != -1){
                        foto.write(new File("C:/Users/Notebook/ecomerce/produtos/" + id + foto.getName().substring(foto.getName().lastIndexOf("."))));
                        produtoDAO.atualizarFoto(id, "C:/Users/Notebook/ecomerce/produtos/" + id + foto.getName().substring(foto.getName().lastIndexOf(".")));
                    }
                    sucesso = true;
                }
                
                if (sucesso) {
                    request.setAttribute("mensagem", "Upload deste produto foi efetuado com sucesso");
                } else {
                    request.setAttribute("mensagem", "N??o foi poss??vel processar o upload deste produto");
                }
            } catch (Exception ex) {
                request.setAttribute("mensagem", "N??o foi poss??vel processar o upload deste produto");
            }
        }
        request.setAttribute("acao", "adicionar");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtoinfo.jsp");
        requestDispatcher.forward(request, response);
    }

}
