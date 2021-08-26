<%-- 
    Document   : cadastrar
    Created on : 28/07/2021, 18:02:21
    Author     : Notebook
--%>

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
        <div id='div_et2' class="mx-auto">
            <form method="post" name="cadastro" id="login" action="CadastrarUsuario">
                
                <label for="nome" class="form-label">Nome</label>
                <input type="text" class="form-control form_element" id="cadastro-nome" name="nome" placeholder="Digite seu nome" required>
                
                <label for="email" class="form-label">Email</label>
                <input type="text" class="form-control form_element" id="cadastro-email" name="email" placeholder="Digite seu email" required>
                
                <label for="endereco" class="form-label">endereço</label>
                <input type="text" class="form-control form_element" id="cadastro-endereco" name="endereco" placeholder="Digite seu endereço" required>

                <label for="login" class="form-label">Login</label>
                <input type="text" class="form-control form_element" id="cadastro-login" name="login" placeholder="Digite seu login" required>
                
                <label for="senha" class="form-label">Senha</label>
                <input type="password" class="form-control form_element" id="cadastro-senha" name="senha" placeholder="Digite sua senha" required>

                <input type="submit" id='bt_formet2' value="Cadastrar">
            </form>
            <% if (request.getAttribute("mensagem") != null) {%>
            <div id="resposta"><%= request.getAttribute("mensagem")%></div>
            <% }%>
        </div>
        
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
