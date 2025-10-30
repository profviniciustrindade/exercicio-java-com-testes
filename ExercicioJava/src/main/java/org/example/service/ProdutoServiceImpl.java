package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepositoryimpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService {
    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {
        var repository = new ProdutoRepositoryimpl();
        if (produto.getPreco() > 0) {
            return repository.save(produto);
        } else {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException {
        var repository = new ProdutoRepositoryimpl();
        List<Produto> produtos = repository.findAll();
        return produtos;

    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        var repository = new ProdutoRepositoryimpl();

    return repository.findById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {
        var repository = new ProdutoRepositoryimpl();
        produto.setId(id);

    return  repository.update(produto);

    }

    @Override
    public boolean excluirProduto(int id) throws SQLException {
        var repository = new ProdutoRepositoryimpl();

        List<Produto> produtos = repository.findAll();

        boolean existe = false;
        for(Produto p : produtos){
            if(p.getId() == id){
                repository.deleteById(id);
                existe = true;
            }

        }
         return (existe) ? true : false;
    }
}
