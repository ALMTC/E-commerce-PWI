/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usuario.modelo.Usuario;
import usuario.modelo.UsuarioDAO;


/**
 *
 * @author Notebook
 */
public class AlterarCadastroServlet extends HttpServlet {

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
        boolean sucesso = false;
        int id=-1;
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipo");
        
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEndereco(endereco);
        u.setEmail(email);
        u.setLogin(login);
        u.setSenha(senha);
        u.setId(id);
        u.setTipo(tipo);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            sucesso = usuarioDAO.alterarUsuario(u);
            if(sucesso){
                request.setAttribute("mensagem", "Cliente alterado com sucesso");
            }else{
                request.setAttribute("mensagem", "Não foi possível alterar o cliente");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Não foi possível alterar o cliente");
        }
    }
}
