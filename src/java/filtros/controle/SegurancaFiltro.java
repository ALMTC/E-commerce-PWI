/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros.controle;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import usuario.modelo.Usuario;

/**
 *
 * @author Notebook
 */
public class SegurancaFiltro implements Filter {
    
    private static final String[] RECURSOS_PUBLICOS = {
        "/E-commerce/",
        "/E-commerce/cadastrar.jsp",
        "/E-commerce/carrinho.jsp",
        "/E-commerce/index.jsp",
        "/E-commerce/login.jsp",
        "/E-commerce/script.js",
        "/E-commerce/style.css",
        "/E-commerce/AdicionarProdutoCarrinhoCompra",
        "/E-commerce/RemoverCarrinhoCompra",
        "/E-commerce/SubtrairProdutoCarrinhoCompra",
        "/E-commerce/GerarPaginaCarrinhoCompra",
        "/E-commerce/Inicio",
        "/E-commerce/MostrarProdutoFoto",
        "/E-commerce/CadastrarUsuario",
        "/E-commerce/Login",
        "/E-commerce/Logout"
    };
    
    private static final String[] RECURSOS_ADMIN = {
        "/E-commerce/alterarcadastro.jsp",
        "/E-commerce/categoriainfo.jsp",
        "/E-commerce/produtoinfo.jsp",
        "/E-commerce/relatorios.jsp",
        "/E-commerce/totaascompras.jsp",
        "/E-commerce/AlterarCategoria",
        "/E-commerce/CadastrarCategoria",
        "/E-commerce/GerirCategoria",
        "/E-commerce/RemoverCategoria",
        "/E-commerce/VerificarCategoria",
        "/E-commerce/ExcluirCompra",
        "/E-commerce/VisualizarTodasCompras",
        "/E-commerce/AlterarProduto",
        "/E-commerce/CadastrarProduto",
        "/E-commerce/GerirProduto",
        "/E-commerce/RemoverProduto",
        "/E-commerce/VerificarProduto",
        "/E-commerce/AdicionarProdutoCategoria",
        "/E-commerce/RemoverProdutoCategoria",
        "/E-commerce/Relatorio1",
        "/E-commerce/Relatorio2",
        "/E-commerce/Relatorio3",
        "/E-commerce/AlterarCadastro",
        "/E-commerce/DeletarUsuario"
    };
    
    private static final String[] RECURSOS_CLIENTE = {
        "/E-commerce/alterarcadastro.jsp",
        "/E-commerce/FinalizarCompraCarrinhoCompra",
        "/E-commerce/mostrarcompras.jsp",
        "/E-commerce/VerificarCompras",
        "/E-commerce/AlterarCadastro",
        "/E-commerce/DeletarUsuario"
    };
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        if (Arrays.stream(RECURSOS_PUBLICOS).anyMatch(requestURI::equals)) {
            chain.doFilter(request, response);
        } else {
            HttpSession sessao = req.getSession();
            if (sessao == null || sessao.getAttribute("usuario") == null) {
                req.setAttribute("mensagem", "Você não tem uma sessão válida de usuário");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                if (sessao.getAttribute("usuario") instanceof Usuario) {
                    Usuario user = (Usuario) sessao.getAttribute("usuario");
                    if (Arrays.stream(RECURSOS_ADMIN).anyMatch(requestURI::equals) && user.getTipo() == "Administrador") {
                        chain.doFilter(request, response);
                    }
                    else if (Arrays.stream(RECURSOS_CLIENTE).anyMatch(requestURI::equals) && user.getTipo() == "Cliente") {
                        chain.doFilter(request, response);
                    }else{
                        req.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                        requestDispatcher.forward(req, resp);
                    }
                } else {
                    req.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                    requestDispatcher.forward(req, resp);
                }
            }
        }
    }
}
