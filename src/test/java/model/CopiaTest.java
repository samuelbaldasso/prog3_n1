package model;

import br.edu.femass.biblioteca.model.Copia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CopiaTest {

    @Test
    void testToString(){
        Copia cp = new Copia();
        String esperado = "Copia{fixo=" + cp.getFixo() + ", disponivel=" + cp.getDisponivel() + "}";
        assertEquals(esperado, cp.toString());
    }
}
