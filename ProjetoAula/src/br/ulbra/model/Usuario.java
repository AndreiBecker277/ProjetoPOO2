/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import javax.swing.Icon;

public class Usuario extends Pessoa {

    
 
    private String dataNasc;    
    private String senha;

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha_usu(String senha_usu) {
        this.senha = senha_usu;
    }

    @Override
    public String toString() {
        return "Usuario{" + "dataNasc=" + getDataNasc() + ", senha=" + getSenha() + ", nome" + getNome() +", email" + getEmail() +", ativo" + getAtivo()
                 +", imagem" + getImagem() + ", pk" + getPk()+'}';
    }
   

 

    

   

}
