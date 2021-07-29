/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.modelo;

import static java.awt.SystemColor.window;
import static java.lang.System.console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Notebook
 */
public class UsuarioDAO {

    /**
     *
     * @param c
     * @throws Exception
     */
    public void inserir(Usuario c) throws Exception {
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliente (nome, email, login, senha, endereco) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, c.getNome());
            preparedStatement.setString(2, c.getEmail());
            preparedStatement.setString(3, c.getLogin());
            preparedStatement.setString(4, c.getSenha());
            preparedStatement.setString(5, c.getEndereco());
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public Usuario obter(String login) {
        Usuario c = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha FROM cliente WHERE login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                c = new Usuario();
                c.setId(resultSet.getInt("id"));
                c.setNome(resultSet.getString("nome"));
                c.setEndereco(resultSet.getString("endereco"));
                c.setEmail(resultSet.getString("email"));
                c.setLogin(resultSet.getString("login"));
                c.setSenha(resultSet.getString("senha"));
                c.setTipo("Cliente");
            }
            
            preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha FROM administrador WHERE login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                c = new Usuario();
                c.setId(resultSet.getInt("id"));
                c.setNome(resultSet.getString("nome"));
                c.setEndereco(resultSet.getString("endereco"));
                c.setEmail(resultSet.getString("email"));
                c.setLogin(resultSet.getString("login"));
                c.setSenha(resultSet.getString("senha"));
                c.setTipo("Administrador");
            }
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }
        return c;
    }
    
    public boolean efetuarLogin(String login, String senha) {
        boolean sucesso = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha FROM cliente WHERE login = ? AND senha = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sucesso = true;
            }
            
            preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha FROM administrador WHERE login = ? AND senha = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sucesso = true;
            }
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }
        return sucesso;
    }
}
