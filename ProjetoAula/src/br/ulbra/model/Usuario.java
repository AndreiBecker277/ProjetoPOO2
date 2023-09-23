/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import javax.swing.Icon;

public class Usuario {

    private int pk_usuario;
    private String nome_usu;
    private String email_usu;
    private String dataNasc;
    private int ativo_usu;
    private String senha_usu;
    private Icon imagemUsu;

    public int getPkusuario() {
        return pk_usuario;
    }

    public void setPkUsuario(int pk_usuario) {
        this.pk_usuario = pk_usuario;
    }

    public String getNomeUsu() {
        return nome_usu;
    }

    public void setNomeUsu(String nome_usu) {
        this.nome_usu = nome_usu;
    }

    public String getEmailUsu() {
        return email_usu;
    }

    public void setEmailUsu(String email_usu) {
        this.email_usu = email_usu;
    }

    public String getDataNascUsu() {
        return dataNasc;
    }

    public void setDataNascUsu(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getAtivoUsu() {
        return ativo_usu;
    }

    public void setAtivoUsu(int ativo_usu) {
        this.ativo_usu = ativo_usu;
    }

    public String getSenhaUsu() {
        return senha_usu;
    }

    public void setSenhaUsu(String senha_usu) {
        this.senha_usu = senha_usu;
    }
    
    public String ativoToString(){
    if(this.ativo_usu == 1)
        return "Ativo";
    else
        return "Inativo";
    }

    public Icon getImagemUsu() {
        return imagemUsu;
    }

    public void setImagemUsu(Icon Imagem) {
        this.imagemUsu = Imagem;
    }
    

    @Override
    public String toString() {
        return "Usuario(" + "pk_usuario" + pk_usuario + ", nome_usu" + nome_usu
                + ", email_usu" + email_usu + ", dataNasc_usu" + dataNasc + ", senha_usu" + senha_usu
                + ", ativo_usu" + ativo_usu + ")";
    }

}
