package model;

import br.edu.femass.biblioteca.model.Copia;
import br.edu.femass.biblioteca.model.GeneroLivro;
import br.edu.femass.biblioteca.model.Livro;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LivroTest {

    @Test
    void testConstrutorCampoVazio(){
        assertThrows(InputMismatchException.class,
                () -> {
                    Livro lv = new Livro(""," ", GeneroLivro.Suspense);
                });
    }

    @Test
    void testAddCopia(){
        Livro e = new Livro("Livro_Exemplo1", "1º Edição, 2022", GeneroLivro.Terror);
        String esperado = "Copia{fixo=true, disponivel=false}";
        assertEquals(esperado, e.getCopias().get(0).toString());
        e.addCopia(new Copia());
        String esperado2 = "Copia{fixo=false, disponivel=true}";
        assertEquals(esperado2, e.getCopias().get(1).toString());
    }

    @Test
    void testGetPrimeiraCopiaDisponivel(){
        Livro e = new Livro("Livro_Exemplo2", "1º Edição, 2022", GeneroLivro.Drama);
        String esperado = "false";
        assertEquals(esperado, e.getPrimeiraCopiaDisponivel().toString());
        e.addCopia(new Copia());
        String esperado2 = "true";
        assertEquals(esperado2, e.getPrimeiraCopiaDisponivel().toString());
        String esperado3 = "false";
        assertEquals(esperado3, e.getPrimeiraCopiaDisponivel().toString());
    }

    @Test
    void testToString(){
        Livro lv = new Livro("Livro_Exemplo3", "1º Edição, 2022", GeneroLivro.Ficcao);
        String esperado = "Nome='Livro_Exemplo3', edicao='1º Edição, 2022', genero=Academico, autores=[], copias=[Copia{fixo=true, disponivel=false}]}";
        assertEquals(esperado, lv.toString());
    }
}
