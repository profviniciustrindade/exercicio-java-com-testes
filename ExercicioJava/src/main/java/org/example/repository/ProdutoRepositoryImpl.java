package org.example.repository;

import org.example.model.Produto;
import org.example.util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository{
    @Override
    public Produto save(Produto produto) throws SQLException {
        String query = "INSERT INTO produto (nome, preco, quantidade, categoria) VALUES (?,?,?,?)";

        try(Connection conn = ConexaoBanco.conectar();
            var stmt = conn.prepareStatement(query)){

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();

            int id = PreparedStatement.RETURN_GENERATED_KEYS;

            return new Produto(id, produto.getNome(), produto.getPreco(), produto.getQuantidade(), produto.getCategoria());
        }
    }

    @Override
    public List<Produto> findAll() throws SQLException {
        String query = "SELECT id, nome, preco, quantidade, categoria FROM produto";
        List<Produto> produtos = new ArrayList<>();

        try(Connection conn = ConexaoBanco.conectar();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                produtos.add(new Produto(
                        rs.getInt("Id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("categoria")
                ));
            }

            return produtos;
        }
    }

    @Override
    public Produto findById(int id) throws SQLException {
        String query = "SELECT id, nome, preco, quantidade, categoria FROM produto WHERE id = ?";

        try(Connection conn = ConexaoBanco.conectar();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("categoria")
                );
            }
            return null;
        }
    }

    @Override
    public Produto update(Produto produto, int id) throws SQLException {
        String query = "UPDATE produto SET nome = ?, preco = ?, quantidade = ?, categoria = ? WHERE id = ?";

        try(Connection conn = ConexaoBanco.conectar();
            var stmt = conn.prepareStatement(query)){

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.setInt(5, id);
            stmt.executeUpdate();

            return produto;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String query = "DELETE FROM produto WHERE id = ?";

        try(Connection conn = ConexaoBanco.conectar();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;
        }
    }
}
