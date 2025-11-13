package org.example.repository;

import org.example.model.Produto;
import org.example.util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository{

    @Override
    public Produto save(Produto produto) throws SQLException {
        String query = """
                INSERT INTO produto (nome, preco, quantidade, categoria) VALUES (?,?,?,?)
                """;

        int id = 0;
        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            int linhasafetadas = stmt.executeUpdate();

            if (linhasafetadas > 0){
                try (ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        id = rs.getInt(1);
                        produto.setId(id);
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public List<Produto> findAll() throws SQLException {
        String query = """
                SELECT nome, preco, quantidade, categoria
                FROM produto
                """;
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCategoria(rs.getString("categoria"));
                produtos.add(produto);
            }
        }

        return produtos;
    }

    @Override
    public Produto findById(int id) throws SQLException {
        String query = """
                SELECT nome
                FROM produto
                WHERE id = ?
                """;

        Produto produto = null;
        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                produto = new Produto();
                produto.setNome(rs.getString("nome"));
            }
        }
        return produto;
    }

    @Override
    public Produto update(Produto produto, int idOriginal) throws SQLException {
        String query = """
                UPDATE produto
                SET nome = ?,
                preco = ?,
                quantidade = ?,
                categoria = ?
                WHERE id = ?;
                """;

        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
       stmt.setString(1, produto.getNome());
       stmt.setDouble(2, produto.getPreco());
       stmt.setInt(3, produto.getQuantidade());
       stmt.setString(4, produto.getCategoria());
       stmt.setInt(5, idOriginal);
       stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public void deleteById(int id) throws SQLException {
    String query = """
            DELETE FROM produto
            WHERE id = ?
            """;
    try (Connection conn = ConexaoBanco.conectar();
    PreparedStatement stmt = conn.prepareStatement(query)){
       stmt.setInt(1, id);
       stmt.executeUpdate();

    }catch (SQLException e){
        e.printStackTrace();
    }

    }
}