package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService{

    ProdutoRepositoryImpl produtoRepository = new ProdutoRepositoryImpl();

    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {
        if(produto.getPreco() < 0){
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
        return produtoRepository.findById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {
        return produtoRepository.update(produto, id);
    }

    @Override
    public boolean excluirProduto(int id) throws SQLException {
        Produto produto = buscarPorId(id);
        if(produto != null) {
            return produtoRepository.deleteById(produto.getId());
        }
        return false;
    }
}
