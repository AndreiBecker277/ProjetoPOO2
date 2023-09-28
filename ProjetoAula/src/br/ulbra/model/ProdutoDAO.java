/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import br.ulbra.Utils.Utils;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    private GerenciadorConexao gerenciador;

    public ProdutoDAO() {
        this.gerenciador = GerenciadorConexao.getInstancia();
    }

    public boolean adicionarProdutos(Produto pro) {
        String sql = "INSERT INTO tb_produto (nome,categoria,fornecedor,quantidade_estoque,imagemPro)"
                + "VALUES (?,?,?,?,?);";

        try {
            byte[] iconBytes = Utils.iconToBytes(pro.getImagemPro());

            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, pro.getNomeProduto());
            stmt.setString(2, pro.getCategoria());
            stmt.setString(3, pro.getCategoria());
            stmt.setInt(4, pro.getQuantEstoque());
            stmt.setBytes(5, iconBytes);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, ": " + pro.getNomeProduto() + "Inserido com sucesso!");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        return false;
    }

    public List<Produto> readpro() {
        String sql = "SELECT * FROM tb_produto";
        List<Produto> produtos = new ArrayList<>();

        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto pro = new Produto();

                pro.setPk_produto(rs.getInt("pk_produto"));
                pro.setNomeProduto(rs.getString("nome"));
                pro.setCategoria(rs.getString("categoria"));
                pro.setFornecedor(rs.getString("fornecedor"));
                pro.setQuantEstoque(rs.getInt("quantidade_estoque"));

                produtos.add(pro);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);

        }
        return produtos;
    }

    public boolean UpdateProduto(Produto p) {

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tb_produto SET nome = ?,"
                    + "categoria = ?,"
                    + "fornecedor = ?"
                    + " WHERE pk_produto = ?;");

            stmt.setString(1, p.getNomeProduto());
            stmt.setString(2, p.getCategoria());
            stmt.setString(3, p.getFornecedor());
            stmt.setInt(4, p.getPk_produto());
            
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

    public List<Produto> readForDescPro(int tipo, String desc) {
        String sql;
        if (tipo == 1 || tipo == 1) {
            sql = "SELECT * FROM tb_produto where nome Like ?";
        } else {
            sql = "SELECT * FROM tb_produto where categoria Like ?";
        }

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            if (tipo == 0 || tipo == 2) {
                stmt.setString(1, desc + "%");
            } else {
                stmt.setString(1, "%" + desc + "%");
            }
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto pro = new Produto();

                pro.setPk_produto(rs.getInt("pk_produto"));
                pro.setNomeProduto(rs.getString("nome"));
                pro.setCategoria(rs.getString("categoria"));
                pro.setFornecedor(rs.getString("fornecedor"));
                pro.setQuantEstoque(rs.getInt("quantidade_estoque"));
                produtos.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return produtos;

    }

    public Produto readForPkPro(int pk) {
        String sql = "SELECT * FROM tb_produto where pk_produto = ?";
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto pro = new Produto();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pk);

            rs = stmt.executeQuery();

            while (rs.next()) {

                pro.setPk_produto(rs.getInt("pk_produto"));
                pro.setNomeProduto(rs.getString("nome"));
                pro.setCategoria(rs.getString("categoria"));
                pro.setFornecedor(rs.getString("fornecedor"));
                pro.setQuantEstoque(rs.getInt("quantidade_estoque"));

                byte[] bytes = rs.getBytes("imagemPro");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                BufferedImage imagem = ImageIO.read(bis);
                pro.setImagemPro(new ImageIcon(imagem));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return pro;
    }

    public boolean ExcluirProduto(int pk_produto) {

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tb_produto WHERE pk_Produto = ?");

            stmt.setInt(1, pk_produto);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido Com Sucesso");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir" + ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt);
        }
        return false;

    }
      public byte[] iconToBytes(Icon icon) throws IOException {
        BufferedImage image = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutStream);
        return byteArrayOutStream.toByteArray();
    }

}
