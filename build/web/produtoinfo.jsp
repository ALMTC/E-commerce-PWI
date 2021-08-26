<%-- 
    Document   : produtoinfo
    Created on : 18/08/2021, 18:08:15
    Author     : Notebook
--%>

<%@page import="java.util.Iterator"%>
<%@page import="categoria.modelo.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="categoria.modelo.CategoriaDAO"%>
<%@page import="produto.modelo.Produto"%>
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
            <form method="post"  enctype="multipart/form-data" name="cadastroProd" id="cadastro-prod" action="CadastrarProduto">
                
                <label for="descricao" class="form-label">Descrição</label>
                <input type="text" class="form-control form_element" id="cadastro-descricao" name="descricao" placeholder="Digite o nome do produto" required>
                
                <label for="valor" class="form-label">Valor</label>
                <input type="number" step="0.01" class="form-control form_element" id="cadastro-valor" name="valor" placeholder="Digite o valor do produto" required>
                
                <label for="quantidade" class="form-label">Quantidade</label>
                <input type="number" class="form-control form_element" id="cadastro-quantidade" name="quantidade" placeholder="Digite a quantidade de produtos adicionados" required>

                <label for="foto" class="form-label">Foto</label>
                <input type="file" class="form-control form_element" id="cadastro-foto" name="foto" placeholder="Insira a imagem do produto">

                <input type="submit" id='bt_formet2' value="Cadastrar">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
            <% }%>
            </div>
          <% }%>
          
          <% if ( action.equals("remover")) {%>
          <div id='div_et2' class="mx-auto">
            <form method="post"  name="removerProd" id="remover-prod" action="RemoverProduto">
                
                <label for="id" class="form-label">Id do produto a ser removido</label>
                <input type="number" class="form-control form_element" id="remover-id" name="id" placeholder="Digite o id do produto" required>

                <input type="submit" id='bt_formet2' value="Remover">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
            <% }%>
            </div>
          <% }%>
          
          <% if ( action.equals("verificar")) {%>
          <div id='div_et2' class="mx-auto">
            <form method="post"  name="verificarProd" id="remover-prod" action="VerificarProduto">
                
                <label for="id" class="form-label">Id do produto a ser verificado</label>
                <input type="number" class="form-control form_element" id="verificar-id" name="id" placeholder="Digite o id do produto" required>
                <input type="hidden" name="page" value="verificar">
                <input type="submit" id='bt_formet2' value="Verificar">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
                <% if (request.getAttribute("produto") != null) {%>
                    <%Produto p = (Produto) request.getAttribute("produto");%>
                    <div class="card h-100">
                        <img src="MostrarProdutoFoto?id=<%= p.getId() %>" class="card-img-top" alt="..." />
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getDescricao()%></h5>
                            <p class="card-text">Valor: R$<%= p.getPreco()%></p>
                            <p class="card-text">Quantidade em estoque: <%= p.getQuantidade()%></p>
                        </div>
                    </div>
                <% }%>
            <% }%>
            </div>
          <% }%>
          
          <% if ( action.equals("alterar")) {%>
          <div id='div_et2' class="mx-auto">
            <form method="post"  name="verificarProd" id="remover-prod" action="VerificarProduto">
                
                <label for="id" class="form-label">Id do produto a ser alterado</label>
                <input type="number" class="form-control form_element" id="verificar-id" name="id" placeholder="Digite o id do produto" required>
                <input type="hidden" name="page" value="alterar">
                <input type="submit" id='bt_formet2' value="Verificar">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
                <% if (request.getAttribute("produto") != null) {%>
                    <%Produto p = (Produto) request.getAttribute("produto");%>
                    <form method="post"  enctype="multipart/form-data" name="alterarProd" id="cadastro-prod" action="AlterarProduto">
                        
                        <input type="hidden" name="id" value="<%= p.getId()%>">
                        
                        <label for="descricao" class="form-label">Descrição</label>
                        <input type="text" class="form-control form_element" id="cadastro-descricao" name="descricao" value="<%= p.getDescricao()%>" required>

                        <label for="valor" class="form-label">Valor</label>
                        <input type="number" step="0.01" class="form-control form_element" id="cadastro-valor" name="valor" value="<%= p.getPreco()%>" required>

                        <label for="quantidade" class="form-label">Quantidade</label>
                        <input type="number" class="form-control form_element" id="cadastro-quantidade" name="quantidade" value="<%= p.getQuantidade()%>" required>

                        <label for="foto" class="form-label">Foto</label>
                        <input type="file" class="form-control form_element" id="cadastro-foto" name="foto" placeholder="Insira a imagem do produto">

                        <input type="submit" id='bt_formet2' value="Alterar">
                    </form>
                        <%
                            CategoriaDAO categoriaDAO = new CategoriaDAO();
                            List<Categoria> categorias = new ArrayList<>();
                            categorias = categoriaDAO.obterCategorias();
                            if(categorias != null){
                        %>
                        <hr>
                        <form method="post" name="linkProdCat" id="linkProdCat" action="AdicionarProdutoCategoria">
                            <input type="hidden" name="produtoId" value="<%= p.getId()%>">
                            <label for="categoria" class="form-label">Adicionar categoria</label>
                            <select id="operacao" name="categoriaId" form="linkProdCat" class="form-control form_element" >
                                <option value="0">Não adicionar</option>
                                <%  Iterator<Categoria> iter = categorias.iterator();
                                    Categoria aux=new Categoria();
                                    while(iter.hasNext()){ 
                                        aux = iter.next();%>
                                <option value="<%= aux.getId() %>"><%= aux.getDescricao() %></option>
                                <%}%>
                            </select>
                            <input type="submit" id='bt_formet2' value="Adicionar">
                        </form>
                        <%}%>
                <% }%>
            <% }%>
            </div>
          <% }%>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
