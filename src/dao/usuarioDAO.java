package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.usuario;

public class usuarioDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/seu_banco_de_dados";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void inserir(usuario usuario) {
        String sql = "INSERT INTO usuario (codigo, login, senha, sexo) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getCodigo());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getSexo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public usuario buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM usuario WHERE codigo = ?";
        usuario usuario = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new usuario(
                        rs.getInt("codigo"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("sexo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<usuario> listar() {
        String sql = "SELECT * FROM usuario";
        List<usuario> usuarios = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuario usuario = new usuario(
                        rs.getInt("codigo"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("sexo")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void atualizar(usuario usuario) {
        String sql = "UPDATE usuario SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getSexo());
            stmt.setInt(4, usuario.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int codigo) {
        String sql = "DELETE FROM usuario WHERE codigo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
