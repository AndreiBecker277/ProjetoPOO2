/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.controller;

import br.ulbra.model.Produto;
import br.ulbra.model.ProdutoDAO;
import br.ulbra.model.Usuario;
import br.ulbra.model.UsuarioDAO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author S.Lucas
 */
public class ProdutoController {
      private ProdutoDAO produtoDAO;

    public ProdutoController() {

       produtoDAO = new ProdutoDAO();
    }

   

     public boolean adicionarProdutos(String nome, String categoria,String fornecedor,int quantidade_estoque) {
         return produtoDAO.adicionarProdutos(nome, categoria, fornecedor, quantidade_estoque);
    }

    public List<Usuario> readForDesc(int tipo ,String desc) {
        return usuarioDAO.readForDesc(tipo,desc);
    }

    public Usuario readForPk(int pk) {
        return usuarioDAO.readForPk(pk);
    }
  public boolean UpdateProduto(Produto p) {
      return produtoDAO.UpdateProduto(p);
    }
    public boolean ExcluirUsuario(int pkUsuario) {
        return usuarioDAO.ExcluirUsuario(pkUsuario);
    }
}

}
