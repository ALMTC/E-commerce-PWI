/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrinhocompra.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import produto.modelo.Produto;
import produto.modelo.ProdutoDAO;

/**
 *
 * @author Notebook
 */
public class CarrinhoCompra {
    
    public static final String SEPARADOR_PRODUTO = "&";
    public static final String SEPARADOR_CAMPO = ":";
    
    public static final Cookie obterCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals("ufcsmdecommerce.carrinhocompras")) {
                cookie = cookies[i];
                break;
            }
        }
        return cookie;
    }
    
    public static final List<CarrinhoCompraItem> obterCarrinhoCompra(String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = new ArrayList<>();
        if (valor == null || valor.trim().length() == 0 || !valor.contains(SEPARADOR_CAMPO)) {
            return carrinhoCompraItens;
        }
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (valor.contains(SEPARADOR_PRODUTO)) {
            String[] produtos = valor.split(SEPARADOR_PRODUTO);
            for (int i = 0; produtos != null && i < produtos.length; i++) {
                String[] item = produtos[i].split(SEPARADOR_CAMPO);
                CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
                Produto produto = produtoDAO.obter(Integer.parseInt(item[0]));
                carrinhoCompraItem.setProduto(produto);
                carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));
                carrinhoCompraItens.add(carrinhoCompraItem);
            }
        } else {
            String[] item = valor.split(SEPARADOR_CAMPO);
            CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
            Produto produto = produtoDAO.obter(Integer.parseInt(item[0]));
            carrinhoCompraItem.setProduto(produto);
            carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));
            carrinhoCompraItens.add(carrinhoCompraItem);
        }
        return carrinhoCompraItens;
    }
    
    public static final String adicionarItem(int produtoId, int quantidade, String valor) throws Exception {
        List<CarrinhoCompraItem> carrinhoCompraItens = obterCarrinhoCompra(valor);
        if (carrinhoCompraItens.isEmpty()) {
            return produtoId + SEPARADOR_CAMPO + quantidade;
        }
        boolean adicionou = false;
        boolean emEstoque = true;
        String resultado = "";
        for (CarrinhoCompraItem carrinhoCompraItem : carrinhoCompraItens) {
            if (carrinhoCompraItem.getProduto().getId() == produtoId) {
                if(carrinhoCompraItem.getProduto().getQuantidade() <= carrinhoCompraItem.getQuantidade()){
                    emEstoque = false;
                }else{
                    carrinhoCompraItem.setQuantidade(carrinhoCompraItem.getQuantidade() + quantidade);
                    adicionou = true;
                }
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_PRODUTO;
            }
            resultado += carrinhoCompraItem.getProduto().getId() + SEPARADOR_CAMPO + carrinhoCompraItem.getQuantidade();
        }
        if (!adicionou && emEstoque) {
            resultado += SEPARADOR_PRODUTO + produtoId + SEPARADOR_CAMPO + quantidade;
        }
        return resultado;
    }
    
    public static final String subtrairItem(int produtoId, int quantidade, String valor) throws Exception {
        List<CarrinhoCompraItem> carrinhoCompraItens = obterCarrinhoCompra(valor);
        if (carrinhoCompraItens.isEmpty()) {
            return valor;
        }
        boolean adicionou = false;
        boolean menor = false;
        String resultado = "";
        for (CarrinhoCompraItem carrinhoCompraItem : carrinhoCompraItens) {
            if (carrinhoCompraItem.getProduto().getId() == produtoId) {
                if(carrinhoCompraItem.getQuantidade() > 1){
                    carrinhoCompraItem.setQuantidade(carrinhoCompraItem.getQuantidade() - quantidade);
                    adicionou = true;
                }else{
                    menor = true;
                }
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_PRODUTO;
            }
            resultado += carrinhoCompraItem.getProduto().getId() + SEPARADOR_CAMPO + carrinhoCompraItem.getQuantidade();
        }
        if (!adicionou && !menor) {
            resultado += SEPARADOR_PRODUTO + produtoId + SEPARADOR_CAMPO + quantidade;
        }
        return resultado;
    }
    
    public static final String removerItem(int produtoId, String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = obterCarrinhoCompra(valor);
        if (carrinhoCompraItens.isEmpty()) {
            return "";
        }
        String resultado = "";
        for (CarrinhoCompraItem carrinhoCompraItem : carrinhoCompraItens) {
            if (carrinhoCompraItem.getProduto().getId() == produtoId) {
                continue;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_PRODUTO;
            }
            resultado += carrinhoCompraItem.getProduto().getId() + SEPARADOR_CAMPO + carrinhoCompraItem.getQuantidade();
        }
        return resultado;
    }
    
}
