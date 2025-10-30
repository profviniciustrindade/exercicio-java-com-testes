package org.example.repository;

import org.example.model.Produto;
import org.example.util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository{

    @Override
    public Produto save(Produto produto) throws SQLException{
        String sql = """
                INSERT INTO produto (nome, preco, quantidade, categoria)
                VALUES (?, ?, ?, ?);
                """;

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

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
        String sql = """
                SELECT id, nome, preco, quantidade, categoria
                FROM produto;
                """;

        List<Produto> produtos = new ArrayList<>();

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                String categoria = rs.getString("categoria");

                var produto = new Produto(id, nome, preco, quantidade, categoria);
                produtos.add(produto);
            }
        }

        return produtos;
    }

    @Override
    public Produto findById(int idDigitado) throws SQLException{
        String sql = """
                SELECT id, nome, preco, quantidade, categoria
                FROM produto
                WHERE id = ?;
                """;
        Produto produto = null;

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idDigitado);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                String categoria = rs.getString("categoria");

                produto = new Produto(id, nome, preco, quantidade, categoria);
            }
        }

        return produto;
    }

    @Override
    public Produto update(Produto produto, int id) throws SQLException{

        String sql = """
                update produto
                SET nome = ?,
                preco = ?,
                quantidade = ?,
                categoria = ?
                WHERE id = ?;
                """;

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.setInt(5, id);

            stmt.executeUpdate();
        }

        return produto;
    }

    @Override
    public boolean deleteById(int id) throws SQLException{
        String sql = """
                DELETE FROM produto
                WHERE id = ?
                """;

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        }

    }
}
