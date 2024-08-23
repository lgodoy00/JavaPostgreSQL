package modelo.projeto.para.cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/PostgreSQL/Eclipse", "Lucas", "86794649");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, idade) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir o cliente", e);
        }
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, idade = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setInt(3, cliente.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o cliente", e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o cliente", e);
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM clientes";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            List<Cliente> clientes = new ArrayList<>();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setIdade(rs.getInt("idade"));
                clientes.add(cliente);
            }
            
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os clientes", e);
        }
    }
}

