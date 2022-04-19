package br.edu.femass.biblioteca.model;

import java.util.*;
public class Livro {
    private String nome;
    private String edicao;
    private GeneroLivro genero;
    private List<Autor> autores = new ArrayList<Autor>();
    private List<Copia> copias = new ArrayList<Copia>();
    private static Integer count = 0;
    public Livro(String nome, String edicao, GeneroLivro genero) {
        this.nome = nome;
        this.edicao = edicao;
        this.genero = genero;
        this.count = 0;
        addCopia(new Copia());

        if (this.nome.isBlank() || this.edicao.isBlank() || this.genero == null){
            throw new InputMismatchException("É necessário preencher todos os campos");
        }
    }
    public Integer getCount() {
        count = (copias.size()-1);
        return count;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEdicao() {return edicao;}
    public void setEdicao(String edicao) {this.edicao = edicao;}
    public GeneroLivro getGenero() {return genero;}
    public void setGenero(GeneroLivro genero) {this.genero = genero;}
    public void addAutor(Autor autor){this.autores.add(autor);}
    public void removerAutor(Autor autor){this.autores.remove(autor);}
    public List<Autor> getAutores(){return this.autores;}
    public String getAutorNome(){return this.autores.get(0).getNome();}
    public String getAutorSobrenome(){return this.autores.get(0).getSobrenome();}
    public void addCopia(Copia copia){
        if (this.count == 0){
            this.copias.add(copia);
            copia.setFixo(true);
            copia.setDisponivel(false);
            this.count++;
        }else{
            this.copias.add(copia);
            copia.setDisponivel(true);
            this.count++;
        }
    }
    public void removerCopia(){
        if (this.getCount()<= 0) return;
        this.copias.remove(copias.get(copias.size()-1));
        this.count--;
    }
    public List<Copia> getCopias(){return this.copias;}

    public Boolean getPrimeiraCopiaDisponivel(){
        for (int i = 0; i<copias.size(); i++){
            if (copias.get(i).getDisponivel() == true){
                copias.get(i).setDisponivel(false);
                return true;
            }
        } return false;
    }
    @Override
    public String toString() {
        return  "Nome = '" + nome + '\'' +
                ", edição = '" + edicao + '\'' +
                ", gênero = " + genero +
                ", autores = " + autores +
                ", cópias = " + copias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return nome.equals(livro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
