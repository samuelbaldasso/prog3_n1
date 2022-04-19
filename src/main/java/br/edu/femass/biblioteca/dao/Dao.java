package br.edu.femass.biblioteca.dao;

import java.util.List;

public interface Dao<T> {
    public void gravar(T objeto) throws Exception;
    public void salvar();
    public List<T> listar() throws Exception;
    public void excluir(T objeto) throws Exception;
}
