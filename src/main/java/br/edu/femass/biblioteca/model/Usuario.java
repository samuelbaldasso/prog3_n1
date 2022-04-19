package br.edu.femass.biblioteca.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String nome;
    private Integer prazoDevolucao;
    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    private Integer count = 0;

    public Usuario(String nome, Integer prazoDevolucao) {
        this.nome = nome;
        this.prazoDevolucao = prazoDevolucao;
        if (this.nome.isBlank() ||this.prazoDevolucao == null){
            throw new InputMismatchException("É necessário preencher todos os campos");
        }
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
    public Integer getPrazoDevolucao() {return prazoDevolucao;}

    public void setPrazoDevolucao(Integer prazoDevolucao) {this.prazoDevolucao = prazoDevolucao;}
    public List<Emprestimo> getEmprestimos() {return emprestimos;}
    public void addEmprestimo(Emprestimo emprestimo) {
        if (count <= 5) {
            this.emprestimos.add(emprestimo);
            emprestimo.setDataDevolução(prazoDevolucao);
            this.count++;
        } else {
            throw new IllegalArgumentException("Este usuário atingiu o limite de empréstimos");
        }
    }

    public void removeEmprestimos(Emprestimo emprestimo){
        this.emprestimos.remove(emprestimo);
        this.count--;
    }

    @Override
    public String toString() {return this.nome;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return nome.equals(usuario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
