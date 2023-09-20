/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.controller;

import br.ulbra.model.Usuario;
import br.ulbra.model.UsuarioDAO;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {

        usuarioDAO = new UsuarioDAO();
    }

    public boolean autenticar(String email, String senha) {
        if (usuarioDAO.autenticar(email, senha)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario ou Senha Incorreta!");
            return false;
        }

    }

    public boolean adicionarUsuario(String nome, String email, String senha, String DataNasc, int ativo) {
        return usuarioDAO.adicionarUsuario(nome, email, senha, DataNasc, ativo);
    }

    public List<Usuario> readForDesc(int tipo ,String desc) {
        return usuarioDAO.readForDesc(tipo,desc);
    }

    public Usuario readForPk(int pk) {
        return usuarioDAO.readForPk(pk);
    }
    public boolean UpdateUsuario(Usuario u) {
        return usuarioDAO.UpdateUsuario(u);
    }
    public boolean ExcluirUsuario(int pkUsuario) {
        return usuarioDAO.ExcluirUsuario(pkUsuario);
    }
}
