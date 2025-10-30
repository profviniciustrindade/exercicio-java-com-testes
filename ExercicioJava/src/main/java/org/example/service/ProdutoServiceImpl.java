package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService {


    @Override
    public Produto cadastrarProduto(Produto produto) throws SQLException {

        var produtoDAO = new ProdutoRepositoryImpl();

        if(produto.getPreco()<0){
            throw new IllegalArgumentException ("Preço deve ser positivo.");
        }

        produtoDAO.save(produto);

        return produto;
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        var produtoDAO = new ProdutoRepositoryImpl();

        produtos = produtoDAO.findAll();

        return produtos;
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException{
        return null;
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException{

        var produtoDAO = new ProdutoRepositoryImpl();

        int produtoId = produto.getId();

        while(produtoId < id){
            produtoId++;
        }

        if(produtoId == id){
            produtoDAO.update(produto);
        }

        return produto;
    }

    @Override
    public boolean excluirProduto(int id) throws SQLException {

        boolean a;

        var produtoDAO = new ProdutoRepositoryImpl();
        List<Produto> produtos = produtoDAO.findAll();
        List<Integer> ids = new ArrayList<>();

        produtos.forEach(produto -> {
            ids.add(produto.getId());
        });

        if (!ids.contains(id)) {
            a = false;
            System.out.println("Este ID não existe");
        }else{
            produtoDAO.deleteById(id);
            a = true;
        }

        return a;
    }
}
