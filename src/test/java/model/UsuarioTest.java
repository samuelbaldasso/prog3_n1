package model;

import br.edu.femass.biblioteca.model.*;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {

    @Test
    void testConstrutorCampoVazio(){
        assertThrows(InputMismatchException.class,
                () -> {
                    Usuario us = new Usuario(" ",5);
                });
    }

    @Test
    void testAddEmprestimo(){
        Professor p = new Professor("Alan");
        Livro l = new Livro("Livro_Exemplo", "1º Edição, 2022", GeneroLivro.Suspense);
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        assertThrows(IllegalArgumentException.class,
                () -> {
                    p.addEmprestimo(new Emprestimo(p,l));
                    p.addEmprestimo(new Emprestimo(p,l));
                    p.addEmprestimo(new Emprestimo(p,l));
                    p.addEmprestimo(new Emprestimo(p,l));
                    p.addEmprestimo(new Emprestimo(p,l));
                    p.addEmprestimo(new Emprestimo(p,l));
                    p.addEmprestimo(new Emprestimo(p,l));
                    p.addEmprestimo(new Emprestimo(p,l));
                });
    }

    @Test
    void testNovoEmprestimo(){
        Aluno an = new Aluno("Ciclano", 12345);
        Livro l = new Livro("Livro_Exemplo", "1º Edição, 2022", GeneroLivro.Drama);
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        an.addEmprestimo(new Emprestimo(an,l));
    }
}
