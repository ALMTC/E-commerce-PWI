/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtocategoria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Notebook
 */
public class ProdutoCategoriaDAO {
    public boolean inserir(int produtoId, int categoriaId) throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try{    
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (?, ?)");
            preparedStatement.setInt(1, produtoId);
            preparedStatement.setInt(2, categoriaId);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();;
            return sucesso;
        } catch (ClassNotFoundException ex) {
            return sucesso;
        } catch (SQLException ex) {
            return sucesso;
        }
    }
    
    /**
     * Método utilizado para desvincular uma categoria de um produto
     *
     * @param produtoId
     * @param categoriaId
     * @return
     * @throws SQLException
     */
    public boolean excluir(int produtoId, int categoriaId) throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try{    
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto_categoria WHERE produto_id = ? AND categoria_id = ?");
            preparedStatement.setInt(1, produtoId);
            preparedStatement.setInt(2, categoriaId);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();;
            return sucesso;
        } catch (ClassNotFoundException ex) {
            return sucesso;
        } catch (SQLException ex) {
            return sucesso;
        }
    }
}
