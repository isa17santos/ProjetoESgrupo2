import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaTestBruno {
    @Test
    void testCriarSala() {
        Sala sala = new Sala("Sala 1", 10, 20, "30x23m", Acessibilidade.NAO, "Normal", Estado.ATIVO);
        assertEquals("Sala 1", sala.getDesignacao());
        assertEquals(10, sala.getNumFilas());
        assertEquals(20, sala.getNumLugaresFila());
        assertEquals("30x23m", sala.getEcra());
        assertEquals(Acessibilidade.NAO, sala.getAcessibilidade());
        assertEquals("Normal", sala.getTipo());
        assertEquals(Estado.ATIVO, sala.getEstado());
    }
}