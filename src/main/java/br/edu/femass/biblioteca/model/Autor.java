package br.edu.femass.biblioteca.model;

import java.util.InputMismatchException;
import java.util.Objects;

public class Autor {
    private String nome;
    private String sobrenome;
    public Autor(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        if (this.nome.isBlank() ||this.sobrenome.isBlank()){
            throw new InputMismatchException("É necessário preencher todos os campos");
        }
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getSobrenome() {return sobrenome;}

    public void setSobrenome(String sobrenome) {this.sobrenome = sobrenome;}

    @Override
    public String toString() {return this.nome + " " +this.sobrenome;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return nome.equals(autor.nome) && sobrenome.equals(autor.sobrenome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome);
    }
}
