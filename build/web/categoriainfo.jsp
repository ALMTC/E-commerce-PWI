<%-- 
    Document   : categoriainfo
    Created on : 25/08/2021, 19:42:32
    Author     : Notebook
--%>

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
                <a href="http://placekitten.com/40/32" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                  <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                  <li><a href="Inicio" class="nav-link px-2 text-secondary">Home</a></li>
                  <li><a href="carrinho.jsp" class="nav-link px-2 text-white">Carrinho</a></li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Categoria
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                      <li><a class="dropdown-item" href="#">Categoria 1</a></li>
                      <li><a class="dropdown-item" href="#">Categoria 2</a></li>
                      <li><a class="dropdown-item" href="#">Categoria n</a></li>
                    </ul>
                  </li>
                  <%if(usuario != null && usuario.getTipo() == "Cliente"){%>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle px-2 text-white" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Usuário padrão
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                      <li><a class="dropdown-item" href="#">Finalizar compra</a></li>
                      <li><a class="dropdown-item" href="#">Seus Pedidos</a></li>
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
                                  <form method="post" action="GerirProduto">
                                      <input type="hidden" name="acao" value="verificar">
                                      <input type="submit" value="Verificar Produto"class="dropdown-item" >
                                  </form>
                              </li>
                              <li>
                                  <form method="post" action="GerirProduto">
                                      <input type="hidden" name="acao" value="adicionar">
                                      <input type="submit" value="Adicionar Produto"class="dropdown-item" >
                                  </form>
                              </li>
                              <li>
                                   <form method="post" action="GerirProduto">
                                      <input type="hidden" name="acao" value="alterar">
                                      <input type="submit" value="Alterar Produto"class="dropdown-item" >
                                  </form>
                              </li>
                              <li>
                                   <form method="post" action="GerirProduto">
                                      <input type="hidden" name="acao" value="remover">
                                      <input type="submit" value="Remover Produto"class="dropdown-item" >
                                  </form>
                              </li>
                          </ul>
                      </li>
                      <li>
                          <a class="dropdown-item" href="#">Categorias</a>
                          <ul>
                              <li>
                                  <form method="post" action="GerirCategoria">
                                      <input type="hidden" name="acao" value="verificar">
                                      <input type="submit" value="Verificar Categoria"class="dropdown-item" >
                                  </form>
                              </li>
                              <li>
                                  <form method="post" action="GerirCategoria">
                                      <input type="hidden" name="acao" value="adicionar">
                                      <input type="submit" value="Adicionar Categoria"class="dropdown-item" >
                                  </form>
                              </li>
                              <li>
                                   <form method="post" action="GerirCategoria">
                                      <input type="hidden" name="acao" value="alterar">
                                      <input type="submit" value="Alterar Categoria"class="dropdown-item" >
                                  </form>
                              </li>
                              <li>
                                   <form method="post" action="GerirCategoria">
                                      <input type="hidden" name="acao" value="remover">
                                      <input type="submit" value="Remover Categoria"class="dropdown-item" >
                                  </form>
                              </li>
                          </ul>
                      </li>
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
                    <%List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");%>
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th>Categoria</th>
                                <th>Id</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% Iterator<Categoria> iter = categorias.iterator();%>
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
