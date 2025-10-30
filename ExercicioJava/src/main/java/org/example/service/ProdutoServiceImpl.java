package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService{

    ProdutoRepositoryImpl repository = new ProdutoRepositoryImpl();

    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {

        if(produto == null){
            throw new NullPointerException();
        }

        if(produto.getPreco() < 0){
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }

        return repository.save(produto);
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException {

        return repository.findAll();
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {

        return repository.findById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {

        Produto produtoBuscado = buscarPorId(id);

        if(produtoBuscado == null){
            throw new NullPointerException("Produto não encontrado");
        }

        return repository.update(produto, id);
    }

    @Override
    public boolean excluirProduto(int id) throws SQLException {

        Produto produtoBuscado = buscarPorId(id);

        if(produtoBuscado == null){
            return false;
        }

        return repository.deleteById(id);
    }
}
