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

    public boolean adicionarUsuario(String nome, String email, String senha, String data, int ativo) {
        String sql = "INSERT into tb_Usuario (nome_usu,email_usu,senha_usu,dataNasc_usu,ativo_usu)"
                + "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, data);
            stmt.setInt(5, ativo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario: " + nome + "Inserido com sucesso!");
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

    public List<Usuario> readForDesc(String desc) {
        String sql = "SELECT * FROM tb_Usuario where nome_usu Like ?";
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + desc + "%");

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

                usuario.setPkUsuario(rs.getInt("pk_usuario"));
                usuario.setNomeUsu(rs.getString("nome_usu"));
                usuario.setEmailUsu(rs.getString("email_usu"));
                usuario.setSenhaUsu(rs.getString("senha_usu"));
                usuario.setDataNascUsu(rs.getString("dataNasc_usu"));
                usuario.setAtivoUsu(rs.getInt("ativo_usu"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return usuario;

    }

    public boolean UpdateUsuario(Usuario u) {

        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();

        try {
            stmt = con.prepareStatement("UPDATE tb_Usuario SET nome_usu = ?,"
                    + "email_usu = ?,"
                    + "senha_usu = ?,"
                    + "dataNasc_usu = ?,"
                    + "ativo_usu = ?,"
                    + "WHERE pk_usuario = ?");

            stmt.setString(1, u.getNomeUsu());
            stmt.setString(2, u.getEmailUsu());
            stmt.setString(3, u.getSenhaUsu());
            stmt.setString(4, u.getDataNascUsu());
            stmt.setInt(5, u.getAtivoUsu());
            stmt.setInt(6, u.getPkusuario());

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
