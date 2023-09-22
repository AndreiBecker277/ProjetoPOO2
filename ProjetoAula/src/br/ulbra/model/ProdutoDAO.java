/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    private GerenciadorConexao gerenciador;

    public ProdutoDAO() {
        this.gerenciador = GerenciadorConexao.getInstancia();
    }

    public boolean adicionarProdutos(String nome, String categoria, String fornecedor, int quantidade_estoque) {
        String sql = "INSERT INTO tb_produto (nome,categoria,fornecedor,quantidade_estoque)"
                + "VALUES (?,?,?,?,);";

        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, categoria);
            stmt.setString(3, fornecedor);
            stmt.setInt(4, quantidade_estoque);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, ": " + nome + "Inserido com sucesso!");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        return false;
    }

    public List<Usuario> read() {
        String sql = "SELECT * FROM tb_usuario";
        List<Usuario> usuarios = new ArrayList<>();

        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setPkUsuario(rs.getInt("pk_usuario"));
                usuario.setNomeUsu(rs.getString("nome_usu"));
                usuario.setEmailUsu(rs.getString("email_usu"));
                usuario.setSenhaUsu(rs.getString("senha_usu"));
                usuario.setDataNascUsu(rs.getString("dataNasc_usu"));
                usuario.setAtivoUsu(rs.getInt("ativo_usu"));

                usuarios.add(usuario);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);

        }
        return usuarios;
    }

    public boolean UpdateProduto(Produto p) {

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto pro = new Produto();

        try {
            stmt = con.prepareStatement("UPDATE tb_produto SET nome = ?,"
                    + "categoria = ?,"
                    + "fornecedor ?"
                    + "WHERE pk_produto");

            stmt.setString(1, p.getNomeProduto());
            stmt.setString(2, p.getCategoria());
            stmt.setString(3, p.getFornecedor());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado Com Sucesso");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt);
        }
        return false;

    }

}
