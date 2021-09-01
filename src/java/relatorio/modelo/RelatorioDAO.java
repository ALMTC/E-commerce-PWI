/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio.modelo;

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
public class RelatorioDAO {
    
    public List<Relatorio2> obterProdutosIndisponiveis() {
        List<Relatorio2> resultado = new ArrayList<Relatorio2>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement ps = c.prepareStatement("SELECT p.id, p.descricao, p.preco FROM produto AS p WHERE p.quantidade = 0 ORDER BY p.descricao ASC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Relatorio2 r = new Relatorio2();
                r.setId(rs.getInt("id"));
                r.setDescricao(rs.getString("descricao"));
                r.setPreco(rs.getDouble("preco"));
                resultado.add(r);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            resultado = null;
        }
        return resultado;
    }
    
    public Relatorio3 obterTotalCompras(){
        Relatorio3 resultado = new Relatorio3();
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement ps = c.prepareStatement("select sum(total),count(compra.id) from compra");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setQtdCompras(rs.getInt("count"));
                resultado.setTotalCompras(rs.getDouble("sum"));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            resultado = null;
        }
        
        return resultado;
    }
    
    public List<Relatorio1> obterTotalComprasPorCliente() {
        List<Relatorio1> resultado = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "Ash.01475369");
            PreparedStatement ps = c.prepareStatement("SELECT c2.id, c2.nome, COUNT(c1.id) AS total_compras FROM compra AS c1, cliente AS c2 WHERE c1.cliente_id = c2.id GROUP BY c2.id, c2.nome ORDER BY total_compras DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Relatorio1 r = new Relatorio1();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setTotalCompras(rs.getInt("total_compras"));
                resultado.add(r);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            resultado = null;
        }
        return resultado;
    }
    
}
