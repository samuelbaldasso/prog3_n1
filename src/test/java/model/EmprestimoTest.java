package model;

import br.edu.femass.biblioteca.model.*;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmprestimoTest {

    @Test
    void testEmprestimo(){
        Livro l = new Livro("Livro_Exemplo", "1º Edição, 2022", GeneroLivro.Suspense);
        l.addCopia(new Copia());
        l.removerCopia();
        l.removerCopia();
        l.removerCopia();
        l.removerCopia();
        l.removerCopia();
        l.removerCopia();
        Professor p = new Professor("Ciclano");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Emprestimo em = new Emprestimo(p,l);
                });
        l.addCopia(new Copia());
        l.getPrimeiraCopiaDisponivel();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Emprestimo em = new Emprestimo(p,l);
                });
    }

    @Test
    void testSetDataDevolução(){
        Professor p = new Professor("Ciclano");
        Livro l = new Livro("Livro_Exemplo", "1º Edição, 2022", GeneroLivro.Terror);
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        Emprestimo em = new Emprestimo(p,l);
        em.setDataDevolução(0);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String esperado = formatter.format(date);
        assertEquals(esperado, em.getDataDevolução());
    }

    @Test
    void testToString(){
        Professor p = new Professor("Ciclano");
        Livro l = new Livro("Livro_Exemplo", "1º Edição, 2022", GeneroLivro.Drama);
        l.addCopia(new Copia());
        l.addCopia(new Copia());
        Emprestimo em = new Emprestimo(p, l);
        em.setDataDevolução(0);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String esperado = "Emprestimo{dataEmprestimo='"+ formatter.format(date)+ "', dataDevolução='"+ formatter.format(date)+"'}";
        assertEquals(esperado, em.toString());
    }
}
