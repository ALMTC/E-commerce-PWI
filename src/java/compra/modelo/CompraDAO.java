/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.modelo;

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
public class CompraDAO {
    
    public int criarCompra(int clienteId, double total) throws Exception{
        Integer compraId = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO compra (cliente_id, total) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, clienteId);
            preparedStatement.setDouble(2, total);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet != null && resultSet.next()) {
            compraId = resultSet.getInt(1);
        }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        return compraId;
    }
    
    public List<Compra> retornarCompras(){
        
        List<Compra> compras = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM compra");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Compra c = new Compra();
                c.setId(resultSet.getInt("id"));
                c.setClienteId(resultSet.getInt("cliente_id"));
                c.setTotal(resultSet.getDouble("total"));
                compras.add(c);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        
        return compras;
        
    }
    
    public boolean excluirCompra(int id) throws Exception {
                boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM compra WHERE id = ?");
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
}
