/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria.modelo;

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
public class CategoriaDAO {
    
    public boolean adicionarCategoria(String descricao) throws ClassNotFoundException, SQLException, Exception{
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (descricao) VALUES (?)");
            preparedStatement.setString(1, descricao);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return sucesso;
        } catch (SQLException ex) {
            return sucesso;
        } 
        return sucesso;
    }
    public boolean alterarCategoria(String descricao, int id) throws ClassNotFoundException, SQLException, Exception{
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria SET descricao = ? WHERE id = ?");
            preparedStatement.setString(1, descricao);
            preparedStatement.setInt(2, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return sucesso;
        } catch (SQLException ex) {
            return sucesso;
        } 
        return sucesso;
    }
    
    public boolean removerCategoria(int id) throws ClassNotFoundException, SQLException, Exception{
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categoria WHERE id = ?");
            preparedStatement.setInt(1, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return sucesso;
        } catch (SQLException ex) {
            return sucesso;
        } 
        return sucesso;
    }
    
    public List<Categoria> obterCategorias(){
        List<Categoria> categoria = new ArrayList<Categoria>();
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao FROM categoria");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categoria c = new Categoria();
                c.setId(resultSet.getInt("id"));
                c.setDescricao(resultSet.getString("descricao"));
                categoria.add(c);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        
        return categoria;
    }
    
}
