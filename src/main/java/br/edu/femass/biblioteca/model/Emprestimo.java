package br.edu.femass.biblioteca.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Emprestimo {
    private static Integer proximoCodigo = 1;
    private Integer codigo;
    private String dataEmprestimo;
    private String dataDevolução;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Livro> livros = new ArrayList<Livro>();
    private Date data = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String strDate = formatter.format(data);

    public Emprestimo(Usuario usuario, Livro livro) {
        codigo = proximoCodigo;
        proximoCodigo++;
        this.dataEmprestimo = strDate;
        this.setDataDevolução(usuario.getPrazoDevolucao());

        if (livro.getCount()<= 1){
            throw new IllegalArgumentException("Não há cópia disponível para empréstimo");
        }else{
            if (livro.getPrimeiraCopiaDisponivel() == true){
                addLivros(livro);
            }else{
                throw new IllegalArgumentException("Não há cópia disponível para empréstimo");
            }
        }
    }

    public Integer getCodigo() {return codigo;}

    public String getDataEmprestimo() {return dataEmprestimo;}

    public void setDataEmprestimo(String dataEmprestimo) {this.dataEmprestimo = dataEmprestimo;}
    public String getDataDevolução() {return dataDevolução;}
    public void setDataDevolução(Integer prazoDevolucao) {
        Calendar cal = Calendar.getInstance();
        try{
            cal.setTime(formatter.parse(strDate));
        }catch(ParseException e){
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_MONTH, prazoDevolucao);
        this.dataDevolução = formatter.format(cal.getTime());
    }

    public List<Usuario> getUsuarios(){return usuarios;}

    public void addUsuario(Usuario usuario){this.usuarios.add(usuario);}

    public List<Livro> getLivros() {return livros;}
    public void addLivros(Livro livro) {this.livros.add(livro);}

    @Override
    public String toString() {
        return "Empréstimo na data '" + dataEmprestimo + '\'' +
                " e a data de devolução é: '" + dataDevolução + '\'';
    }
}
