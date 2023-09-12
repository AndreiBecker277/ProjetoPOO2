/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.controller;

import br.ulbra.model.UsuarioDAO;
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
}


