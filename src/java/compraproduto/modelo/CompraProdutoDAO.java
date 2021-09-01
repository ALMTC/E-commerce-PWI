/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compraproduto.modelo;

import carrinhocompra.modelo.CarrinhoCompraItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import produto.modelo.Produto;

/**
 *
 * @author Notebook
 */
public class CompraProdutoDAO {
    public boolean inserir(int compraId, CarrinhoCompraItem item) throws SQLException, Exception {
        boolean sucesso = false;
        
        CompraProduto compraproduto = new CompraProduto();
        compraproduto.setCompraId(compraId);
        compraproduto.setProdutoId(item.getProduto().getId());
        compraproduto.setQuantidade(item.getQuantidade());
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO compra_produto (compra_id, produto_id, quantidade) VALUES (?,?,?)");
            preparedStatement.setInt(1, compraproduto.getCompraId());
            preparedStatement.setInt(2, compraproduto.getProdutoId());
            preparedStatement.setInt(3, compraproduto.getQuantidade());
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
    
    public List<Produto> retornarCompras(int usuarioId) throws SQLException, Exception {
        List<Produto> valores = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("select compra.id, produto.descricao, compra_produto.quantidade, produto.preco from compra_produto,compra, produto where compra.cliente_id = ? and compra_produto.produto_id = produto.id and compra.id = compra_produto.compra_id order by compra.id");
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produto p = new Produto();
                p.setId(resultSet.getInt("id"));
                p.setDescricao(resultSet.getString("descricao"));
                p.setQuantidade(resultSet.getInt("quantidade"));
                p.setPreco(resultSet.getDouble("preco"));
                valores.add(p);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        
        return valores;
        
        
    }
    
}
