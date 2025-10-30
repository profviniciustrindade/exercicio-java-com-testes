package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService{
    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {
        var produtoRep = new ProdutoRepositoryImpl();

        if(produto.getPreco() > 0){
            return produtoRep.save(produto);
        } else {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException {
        var produtoRep = new ProdutoRepositoryImpl();
        return produtoRep.findAll();
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        var produtoRep = new ProdutoRepositoryImpl();
        return produtoRep.findById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {
        var produtoRep = new ProdutoRepositoryImpl();
        produto.setId(id);
        return produtoRep.update(produto);
    }

    @Override
    public boolean excluirProduto(int id) throws SQLException {
        var produtoRep = new ProdutoRepositoryImpl();
        List<Produto> produtos = listarProdutos();
        boolean valido = false;
        for(Produto p : produtos){
            if(p.getId() == id){
                produtoRep.deleteById(id);
                valido = true;
                break;
            }
        }
        return (valido) ? true : false;
    }
}
