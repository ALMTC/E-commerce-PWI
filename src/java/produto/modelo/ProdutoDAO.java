/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Notebook
 */
public class ProdutoDAO {
    
    public Produto obter(Integer id) {
        Produto p = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, quantidade, foto FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                p = new Produto();
                p.setId(resultSet.getInt("id"));
                p.setDescricao(resultSet.getString("descricao"));
                p.setPreco(resultSet.getDouble("preco"));
                p.setQuantidade(resultSet.getInt("quantidade"));
                p.setFoto(resultSet.getString("foto"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }
        return p;
    }
    
    public List<Produto> obterProdutosEmEstoque() {
        List<Produto> produtos = new ArrayList<Produto>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, quantidade, foto from produto, produto_categoria where produto.id = produto_categoria.produto_id and produto.quantidade > 0 order by produto_categoria.categoria_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produto p = new Produto();
                p.setId(resultSet.getInt("id"));
                p.setDescricao(resultSet.getString("descricao"));
                p.setPreco(resultSet.getDouble("preco"));
                p.setQuantidade(resultSet.getInt("quantidade"));
                p.setFoto(resultSet.getString("foto"));
                produtos.add(p);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }
        return produtos;
    }
    
    public List<Produto> obterProdutosEmEstoque(int categoria) {
        List<Produto> produtos = new ArrayList<Produto>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, quantidade, foto from produto, produto_categoria where produto.id = produto_categoria.produto_id and produto.quantidade > 0 and produto_categoria.categoria_id = ? order by produto_categoria.categoria_id");
            preparedStatement.setInt(1, categoria);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produto p = new Produto();
                p.setId(resultSet.getInt("id"));
                p.setDescricao(resultSet.getString("descricao"));
                p.setPreco(resultSet.getDouble("preco"));
                p.setQuantidade(resultSet.getInt("quantidade"));
                p.setFoto(resultSet.getString("foto"));
                produtos.add(p);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }
        return produtos;
    }
    
    public void cadastrarProduto(String descricao, int quantidade, double valor) throws Exception{
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (descricao, preco, quantidade) VALUES (?, ?, ?)");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, valor);
            preparedStatement.setInt(3, quantidade);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public void atualizarFoto(int id, String caminhoFoto) throws Exception {
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET foto = ? WHERE id = ?");
            preparedStatement.setString(1, caminhoFoto);
            preparedStatement.setInt(2, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public int retornaUltimo(){
        int max = -1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(id) FROM produto");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                max = resultSet.getInt("max");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return -1;
        } catch (SQLException ex) {
            return -1;
        }
        return max;
    }
    
    public boolean removerProduto(int id) throws Exception{
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        return sucesso;
    }
    
    public boolean alterarProduto(int id, String descricao, int quantidade, double valor) throws Exception{
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto Set descricao = ?, preco = ?, quantidade = ? WHERE id = ?");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, valor);
            preparedStatement.setInt(3, quantidade);
            preparedStatement.setInt(4, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        return sucesso;
    }
}
