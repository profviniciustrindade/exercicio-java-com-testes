package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepository;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService{

    ProdutoRepository repository = new ProdutoRepositoryImpl();

    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {
        if(produto.getPreco() < 0){
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        return repository.save(produto);
    }

    @Override
    public List<Produto> listarProdutos()throws SQLException {

        return repository.findAll();
    }

    @Override
    public Produto buscarPorId(int id)throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id)throws SQLException {
        Produto produtoOld = repository.findById(id);
        if(produtoOld == null){
            throw new IllegalArgumentException();
        }
        produto.setId(id);

        produto = repository.update(produto);

        if(produto == null){
            throw new RuntimeException("Erro de execução!");
        }
        return repository.update(produto);
    }

    @Override
    public boolean excluirProduto(int id)throws SQLException {
        Produto produto = buscarPorId(id);
        if(produto != null){
            return repository.deleteById(produto.getId());
        }
        return false;
    }
}
