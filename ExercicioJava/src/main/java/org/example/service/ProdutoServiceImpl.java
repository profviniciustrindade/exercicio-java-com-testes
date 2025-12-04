package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepository;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService {

    ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();

    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {

        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }

        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        return null;
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {

        produto.setId(id);
        produtoRepository.update(produto);

        return produto;
    }

    @Override
    public boolean excluirProduto(int id) throws SQLException {

        Produto produtoo = produtoRepository.findById(id);

        if (produtoo == null) {
            return false;
        }

        produtoRepository.deleteById(id);
        return true;
    }
}
