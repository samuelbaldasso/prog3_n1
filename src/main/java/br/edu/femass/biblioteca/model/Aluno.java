package br.edu.femass.biblioteca.model;

public class Aluno extends Usuario {
    private Integer matricula;

    public Aluno(String nome, Integer matricula) {
        super(nome, 5);
        this.matricula = matricula;
    }

    public Integer getMatricula() {return matricula;}

    public void setMatricula(Integer matricula) {this.matricula = matricula;}
}
