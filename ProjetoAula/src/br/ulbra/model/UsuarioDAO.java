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

public class UsuarioDAO {

    private GerenciadorConexao gerenciador;

    public UsuarioDAO() {
        this.gerenciador = GerenciadorConexao.getInstancia();
    }

    public boolean autenticar(String email, String senha) {
        String sql = "SELECT * from tb_usuario WHERE email_usu = ? and senha_usu = ? and ativo_usu";
        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

    public boolean adicionarUsuario(Usuario u) {
        String sql = "INSERT into tb_Usuario (nome_usu,email_usu,senha_usu,dataNasc_usu,ativo_usu,imagemUsu)"
                + "VALUES (?,?,?,?,?,?)";

        try {
            byte[] iconBytes = Utils.iconToBytes(u.getImagem());
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getDataNasc());
            stmt.setInt(5, u.getAtivo());
            stmt.setBytes(6, iconBytes);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario: " + u.getNome() + "Inserido com sucesso!");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        } catch (IOException e) {
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

                usuario.setPk(rs.getInt("pk_usuario"));
                usuario.setNome(rs.getString("nome_usu"));
                usuario.setEmail(rs.getString("email_usu"));
                usuario.setSenha_usu(rs.getString("senha_usu"));
                usuario.setDataNasc(rs.getString("dataNasc_usu"));
                usuario.setAtivo(rs.getInt("ativo_usu"));

                usuarios.add(usuario);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);

        }
        return usuarios;
    }

    public List<Usuario> readForDesc(int tipo, String desc) {
        String sql;
        if (tipo == 1 || tipo == 1) {
            sql = "SELECT * FROM tb_Usuario where nome_usu Like ?";
        } else {
            sql = "SELECT * FROM tb_Usuario where email_usu Like ?";
        }

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            if (tipo == 0 || tipo == 2) {
                stmt.setString(1, desc + "%");
            } else {
                stmt.setString(1, "%" + desc + "%");
            }
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setPk(rs.getInt("pk_usuario"));
                usuario.setNome(rs.getString("nome_usu"));
                usuario.setEmail(rs.getString("email_usu"));
                usuario.setSenha_usu(rs.getString("senha_usu"));
                usuario.setDataNasc(rs.getString("dataNasc_usu"));
                usuario.setAtivo(rs.getInt("ativo_usu"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return usuarios;

    }

    public Usuario readForPk(int pk) {
        String sql = "SELECT * FROM tb_Usuario where pk_usuario = ?";
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pk);

            rs = stmt.executeQuery();

            while (rs.next()) {

                usuario.setPk(rs.getInt("pk_usuario"));
                usuario.setNome(rs.getString("nome_usu"));
                usuario.setEmail(rs.getString("email_usu"));
                usuario.setSenha_usu(rs.getString("senha_usu"));
                usuario.setDataNasc(rs.getString("dataNasc_usu"));
                usuario.setAtivo(rs.getInt("ativo_usu"));
                
                byte[] bytes = rs.getBytes("imagemUsu");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                BufferedImage imagem = ImageIO.read(bis);
                usuario.setImagem(new ImageIcon(imagem));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException e){
            
          JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        
        finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return usuario;

    }

    public boolean UpdateUsuario(Usuario u) {

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
     

        try {
            
            byte[] iconBytes = Utils.iconToBytes(u.getImagem());
            
            stmt = con.prepareStatement("UPDATE tb_Usuario SET nome_usu = ?,"
                    + "email_usu = ?,"
                    + "senha_usu = ?,"
                    + "dataNasc_usu = ?,"
                    + "ativo_usu = ?,"
                    + "imagemUsu = ? "
                    + "WHERE pk_usuario = ?");

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getDataNasc());
            stmt.setInt(5, u.getAtivo());
            stmt.setBytes(6, iconBytes);
            stmt.setInt(7, u.getPk());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado Com Sucesso");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);

        } catch (IOException e){
            
          JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        
        
        finally {
            GerenciadorConexao.closeConnection(con, stmt);
        }
        return false;

    }

    public boolean ExcluirUsuario(int pkUsuario) {

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tb_Usuario WHERE pk_usuario = ?");

            stmt.setInt(1, pkUsuario);

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
}
