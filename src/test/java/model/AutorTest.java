package model;

import br.edu.femass.biblioteca.model.Autor;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class AutorTest {

    @Test
    void testConstrutorCampoVazio(){
        assertThrows(InputMismatchException.class,
        () -> {
            Autor at = new Autor(""," ");
        });
    }

    @Test
    void testToString(){
        Autor au = new Autor("Fulano", "de Tal");
        String esperado = "Fulano de Tal";
        assertEquals(esperado, au.toString());
    }
}
