
package br.ulbra.model;

import javax.swing.Icon;

public class Produto {
    private int pk_produto;
    private String nomeProduto;
    private String categoria;
    private String fornecedor;
    private int quantEstoque;
    private Icon imagemPro;

    public Icon getImagemPro() {
        return imagemPro;
    }

    public void setImagemPro(Icon imagemPro) {
        this.imagemPro = imagemPro;
    }    
    
    public int getPk_produto() {
        return pk_produto;
    }

    public void setPk_produto(int pk_produto) {
        this.pk_produto = pk_produto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    @Override
    public String toString() {
        return "Produto{" + "pk_produto=" + pk_produto + ", nomeProduto=" + nomeProduto + ", categoria=" + categoria + ", fornecedor=" + fornecedor + ", quantEstoque=" + quantEstoque + '}';
    }
    
    
}
