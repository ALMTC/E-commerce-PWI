package usuario.controle;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
public class CadastrarUsuarioServlet extends HttpServlet {

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
        /* entrada */
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        /* processamento */
        Usuario c = new Usuario();
        c.setNome(nome);
        c.setEndereco(endereco);
        c.setEmail(email);
        c.setLogin(login);
        c.setSenha(senha);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.inserir(c);
            request.setAttribute("mensagem", "Cliente cadastrado com sucesso");
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Não foi possível cadastrar o cliente");
        }
        /* saída */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cadastrar.jsp");
        requestDispatcher.forward(request, response);
    }
}
