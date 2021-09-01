<%-- 
    Document   : categoriainfo
    Created on : 25/08/2021, 19:42:32
    Author     : Notebook
--%>

<%@page import="categoria.modelo.CategoriaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="categoria.modelo.Categoria"%>
<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-commerce PWI</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="style.css"%></style>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <%Usuario usuario = (Usuario) session.getAttribute("usuario");%>
        <%String action=null; %>
        <header class="p-3 bg-dark text-white">
            <div class="container">
              <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                  <li><a href="Inicio" class="nav-link px-2 text-secondary">Home</a></li>
                  <li><a href="GerarPaginaCarrinhoCompra" class="nav-link px-2 text-white">Carrinho</a></li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Categoria
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                      <%
                            List<Categoria> categorias = new ArrayList<>();
                            CategoriaDAO categoriaDAO = new CategoriaDAO();
                            categorias = categoriaDAO.obterCategorias();
                            if (categorias == null || categorias.isEmpty()){
                      %>
                      <li><p class="dropdown-item" href="Inicio">Sem Categorias</p></li>
                      <%
                          }else{
                                Iterator<Categoria> iter = categorias.iterator();
                                Categoria c=new Categoria();
                                while(iter.hasNext()){
                                    c = iter.next();
                      %>
                      <li><a class="dropdown-item" href="Inicio?categoria=<%= c.getId()%>"><%= c.getDescricao()%></a></li>
                      <%}
                        }%>
                    </ul>
                  </li>
                  <%if(usuario != null && usuario.getTipo() == "Cliente"){%>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Usuário padrão
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                      <li><a class="dropdown-item" href="GerarPaginaCarrinhoCompra">Finalizar compra</a></li>
                      <li><a class="dropdown-item" href="VerificarCompras?usuarioId=<%= usuario.getId() %>">Seus Pedidos</a></li>
                      <li><a class="dropdown-item" href="alterarcadastro.jsp">Atualizar dados</a></li>
                      <li><a class="dropdown-item" href="Logout">Sair</a></li>
                    </ul>
                  </li>
                  <%}%>
                  <%if(usuario != null && usuario.getTipo() == "Administrador"){%>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Administrador
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                      <li>
                          <a class="dropdown-item" href="#">Produtos</a>
                          <ul>
                              <li>
                                  <a href="GerirProduto?acao=verificar" class="dropdown-item">Verificar Produto</a>
                              </li>
                              <li>
                                  <a href="GerirProduto?acao=adicionar" class="dropdown-item">Adicionar Produto</a>
                              </li>
                              <li>
                                  <a href="GerirProduto?acao=alterar" class="dropdown-item">Alterar Produto</a>
                              </li>
                              <li>
                                  <a href="GerirProduto?acao=remover" class="dropdown-item">Remover Produto</a>
                              </li>
                          </ul>
                      </li>
                      <li>
                          <a class="dropdown-item" href="#">Categorias</a>
                          <ul>
                              <li>
                                  <a href="GerirCategoria?acao=verificar" class="dropdown-item">Verificar Categoria</a>
                              </li>
                              <li>
                                  <a href="GerirCategoria?acao=adicionar" class="dropdown-item">Adicionar Categoria</a>
                              </li>
                              <li>
                                  <a href="GerirCategoria?acao=alterar" class="dropdown-item">Alterar Categoria</a>
                              </li>
                              <li>
                                  <a href="GerirCategoria?acao=remover" class="dropdown-item">Remover Categoria</a>
                              </li>
                          </ul>
                      </li>
                      <li><a class="dropdown-item" href="relatorios.jsp">Relatórios</a></li>
                      <li><a class="dropdown-item" href="VisualizarTodasCompras">Ver todas as compras</a></li>
                      <li><a class="dropdown-item" href="alterarcadastro.jsp">Atualizar dados</a></li>
                      <li><a class="dropdown-item" href="Logout">Sair</a></li>
                    </ul>
                  </li>
                  <%}%>
                </ul>

                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                  <input type="search" class="form-control form-control-dark" placeholder="Buscar..." aria-label="Search">
                </form>

                <div class="text-end">
                  
                  <%if(usuario == null){%>
                  <a class="btn btn-outline-light me-2" href="login.jsp">Login</a>
                  <a class="btn btn-warning" href="cadastrar.jsp">Cadastrar-se</a>
                  <%}%>
                </div>
              </div>
            </div>
        </header>
                
          <% if (request.getAttribute("acao") != null){
              action = (String)request.getAttribute("acao");
          }%>      
          
          <% if ( action.equals("adicionar")) {%>
          <div id='div_et2' class="mx-auto">
            <form method="post" name="cadastroCat" id="cadastro-prod" action="CadastrarCategoria">
                
                <label for="descricao" class="form-label">Descrição</label>
                <input type="text" class="form-control form_element" id="cadastro-descricao" name="descricao" placeholder="Digite o nome da categoria" required>

                <input type="submit" id='bt_formet2' value="Cadastrar">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
            <% }%>
            </div>
          <% }%>
        
          <% if ( action.equals("remover")) {%>
          <div id='div_et2' class="mx-auto">
            <form method="post"  name="removerCat" id="remover-prod" action="RemoverCategoria">
                
                <label for="id" class="form-label">Id da categoria a ser removido</label>
                <input type="number" class="form-control form_element" id="remover-id" name="id" placeholder="Digite o id da categoria" required>

                <input type="submit" id='bt_formet2' value="Remover">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
            <% }%>
            </div>
          <% }%>
          
          <% if ( action.equals("verificar")) {%>
          <div id='div_et2' class="mx-auto">
            <form method="post"  name="verificarCat" id="remover-prod" action="VerificarCategoria">
                
                <input type="hidden" name="page" value="verificar">
                <input type="submit" id='bt_formet2' value="Verificar todas as categorias">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div><br>
            <% }%>
                <% if (request.getAttribute("categorias") != null) {%>
                    <%List<Categoria> categoriass = (List<Categoria>) request.getAttribute("categorias");%>
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th>Categoria</th>
                                <th>Id</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% Iterator<Categoria> iter = categoriass.iterator();%>
                            <% Categoria aux=new Categoria();%>
                            <%while(iter.hasNext()){ %>
                                <% aux = iter.next();%>
                            <tr>
                                <td><%= aux.getDescricao()%></td>
                                <td><%= aux.getId()%></td>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                <% }%>
            </div>
          <% }%>
          
          <% if ( action.equals("alterar")) {%>
          <div id='div_et2' class="mx-auto">
            <form method="post"  name="alterarCat" id="remover-prod" action="AlterarCategoria">
                
                <label for="id" class="form-label">Id da categoria a ser alterada</label>
                <input type="number" class="form-control form_element" id="verificar-id" name="id" placeholder="Digite o id da categoria" required>
                
                <label for="descricao" class="form-label">Descrição</label>
                <input type="text" class="form-control form_element" id="cadastro-descricao" name="descricao" placeholder="Digite o novo nome da categoria" required>
                
                <input type="submit" id='bt_formet2' value="Alterar">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
            <% }%>
            </div>
          <% }%>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
