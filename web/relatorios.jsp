<%-- 
    Document   : relatorios
    Created on : 01/09/2021, 03:26:46
    Author     : Notebook
--%>

<%@page import="relatorio.modelo.Relatorio1"%>
<%@page import="relatorio.modelo.Relatorio3"%>
<%@page import="relatorio.modelo.Relatorio2"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="categoria.modelo.CategoriaDAO"%>
<%@page import="categoria.modelo.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>E-commerce PWI</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <%Usuario usuario = (Usuario) session.getAttribute("usuario");%>
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
        <div id='div_et2' class="mx-auto">
            <%if(request.getAttribute("relatorio") == null){%>
            <form method="post"  name="verificarCat" id="remover-prod" action="Relatorio1">
                <input type="submit" id='bt_formet2' value="Total de Compras por Cliente">
            </form>
            <form method="post"  name="verificarCat" id="remover-prod" action="Relatorio2">
                <input type="submit" id='bt_formet2' value="Produtos Indisponíveis em Estoque">
            </form>
            <form method="post"  name="verificarCat" id="remover-prod" action="Relatorio3">
                <input type="submit" id='bt_formet2' value="Total Apurado">
            </form>
            <%}else{
                if(request.getAttribute("relatorio").equals("relatorio1")){%>
                
                <h2 style="text-align: center;">Compras por Cliente</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Id do Cliente</th>
                            <th>Nome do Cliente</th>
                            <th>Compras Feitas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  List<Relatorio1> relatorio = (List<Relatorio1>) request.getAttribute("resultado");
                            Iterator<Relatorio1> iter = relatorio.iterator();
                            Relatorio1 aux = new Relatorio1();
                            while (iter.hasNext()) {
                                aux = iter.next();%>
                        <tr>
                            <td><%= aux.getId()%></td>
                            <td><%= aux.getNome()%></td>
                            <td>R$ <%= aux.getTotalCompras()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                
                <%}%>
                <%if(request.getAttribute("relatorio").equals("relatorio2")){%>
                <h2 style="text-align: center;">Produtos indisponíveis</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Id do produto</th>
                            <th>Nome do produto</th>
                            <th>Preço</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  List<Relatorio2> valores = (List<Relatorio2>) request.getAttribute("resultado");
                            Iterator<Relatorio2> iter = valores.iterator();
                            Relatorio2 aux = new Relatorio2();
                            while (iter.hasNext()) {
                                aux = iter.next();%>
                        <tr>
                            <td><%= aux.getId()%></td>
                            <td><%= aux.getDescricao()%></td>
                            <td>R$ <%= aux.getPreco()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <%}%>
                <%if(request.getAttribute("relatorio").equals("relatorio3")){%>
                
                <h2 style="text-align: center;">Valor Total de Compras</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Número de Compras Realizadas</th>
                            <th>Valor Total em Compras</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  Relatorio3 relatorio = (Relatorio3)request.getAttribute("resultado");%>
                        <tr>
                            <td><%= relatorio.getQtdCompras() %></td>
                            <td><%= relatorio.getTotalCompras() %></td>
                        </tr>
                    </tbody>
                </table>
                <%}%>
            <%}%>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
            <% }%>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
