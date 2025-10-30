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
               INSERT INTO produto
               (nome, preco, quantidade, categoria)
               VALUES
               (?,?,?,?);
               """;

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    produto.setId(idGerado);
                }else {
                    throw new SQLException("[FALHA AO OBTER ID]");
                }
            }
        }catch (SQLException erro){
            erro.printStackTrace();
        }
        return produto;
    }

    @Override
    public List<Produto> findAll() throws SQLException{
        List<Produto> produtos = new ArrayList<>();

        String query = """
                SELECT
                id, nome, preco, quantidade, categoria
                FROM
                produto;
                """;

        try(Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                String categoria = rs.getString("categoria");

                Produto produto = new Produto(id, nome, preco, quantidade, categoria);
                produtos.add(produto);
            }
        }

        return produtos;
    }

    @Override
    public Produto findById(int id) throws SQLException{

return null;
    }

    @Override
    public Produto update(Produto produto) throws SQLException{

        String query = """
                UPDATE produto
                SET nome = ?,
                preco = ?,
                quantidade = ?,
                categoria = ?
                """;

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();

        }
        return produto;
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String query = """
                DELETE FROM produto
                WHERE id = ?;
                """;

        try(Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }
}
