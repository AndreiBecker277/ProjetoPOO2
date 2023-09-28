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

   

     public boolean adicionarProdutos(Produto pro) {
         return produtoDAO.adicionarProdutos(pro);
    }

     public List<Produto> readForDescPro(int tipo ,String desc) {
        return produtoDAO.readForDescPro(tipo,desc);
    }

    public Produto readForPkPro(int pk) {
        return produtoDAO.readForPkPro(pk);
    }
    
  public boolean UpdateProduto(Produto p) {
      return produtoDAO.UpdateProduto(p);
    }
  
    public boolean ExcluirProduto(int pkUsuario) {
        return produtoDAO.ExcluirProduto(pkUsuario);
    }
}


