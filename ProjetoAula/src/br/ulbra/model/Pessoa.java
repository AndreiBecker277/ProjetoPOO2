/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import javax.swing.Icon;

/**
 *
 * @author S.Lucas
 */
public abstract class Pessoa {

    private int pk;
    private String nome;
    private String email;
    private int ativo;
    private Icon imagem;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public Icon getImagem() {
        return imagem;
    }

    public void setImagem(Icon imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "pk=" + pk + ", nome=" + nome + ", email=" + email + ", ativo=" + ativo + ", imagem=" + imagem + '}';
    }

    public String ativoToString() {
        if (this.ativo == 1) {
            return "Ativo";
        } else {
            return "inativo";
        }
    }
}
