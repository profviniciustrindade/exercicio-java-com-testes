package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService{

    ProdutoRepositoryImpl produtoRepository = new ProdutoRepositoryImpl();

    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {
        produtoRepository.save(produto);
        return produto;
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {

        return produtoRepository.findById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {
        return produtoRepository.update(produto);
    }

    @Override
    public boolean excluirProduto(int id) {
        return false;
    }
}
