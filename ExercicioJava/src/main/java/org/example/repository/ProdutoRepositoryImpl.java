package org.example.repository;

import org.example.model.Produto;
import org.example.util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository{
    @Override
    public Produto save(Produto produto) throws SQLException {
        return null;
    }

    @Override
    public List<Produto> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public Produto findById(int id) throws SQLException {
        String sql = "SELECT id, nome, preco FROM Produto WHERE id = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Produto(rs.getInt("id"),rs.getString("nome"),rs.getDouble("preco"));

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

            )
    }

    @Override
    public Produto update(Produto produto) throws SQLException {
        return null;
    }

    @Override
    public void deleteById(int id) throws SQLException {

    }
}
