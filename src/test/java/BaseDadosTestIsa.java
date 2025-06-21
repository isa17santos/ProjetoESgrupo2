import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BaseDadosTestIsa {
    private BaseDados bd;

    @BeforeEach
    public void setUp() {
        bd = BaseDados.getInstance();
    }

    @Test
    public void testAdicionarFilme() {
        LinkedList<Genero> generosMatrix = new LinkedList<>();
        generosMatrix.add(Genero.ACAO);
        generosMatrix.add(Genero.FICCAO_CIENTIFICA);

        Filme f = new Filme("Matrix", 120, "matrix.png", Idioma.VO, "+16",generosMatrix,"2D", Estado.ATIVO,10.00f);
        bd.adicionarFilme(f);

        assertTrue(bd.getFilmes().contains(f));
    }

    @Test
    public void testRemoverFilme() {
        LinkedList<Genero> generosMatrix = new LinkedList<>();
        generosMatrix.add(Genero.ACAO);
        generosMatrix.add(Genero.FICCAO_CIENTIFICA);

        Filme f = new Filme("Matrix", 120, "matrix.png", Idioma.VO, "+16",generosMatrix,"2D", Estado.ATIVO,10.00f);
        bd.adicionarFilme(f);
        boolean removido = bd.removerFilme(f);

        assertTrue(removido);
        assertFalse(bd.getFilmes().contains(f));
    }

    @Test
    public void testGetFilmeByNome() {
        LinkedList<Genero> generosMatrix = new LinkedList<>();
        generosMatrix.add(Genero.ACAO);
        generosMatrix.add(Genero.FICCAO_CIENTIFICA);

        Filme f = new Filme("Matrix", 120, "matrix.png", Idioma.VO, "+16",generosMatrix,"2D", Estado.ATIVO,10.00f);
        bd.adicionarFilme(f);

        Filme resultado = bd.getFilmeByNome("matrix");

        assertNotNull(resultado);
        assertEquals(f, resultado);
    }

    @Test
    public void testAtualizarSessao() {
        LinkedList<Genero> generosMatrix = new LinkedList<>();
        generosMatrix.add(Genero.ACAO);
        generosMatrix.add(Genero.FICCAO_CIENTIFICA);

        Filme f = new Filme("Matrix", 120, "matrix.png", Idioma.VO, "+16",generosMatrix,"2D", Estado.ATIVO,10.00f);
        bd.adicionarFilme(f);

        Sala sala = (new Sala("Sala teste", 4,10,"30x23m", Acessibilidade.NAO,"Normal",Estado.ATIVO));
        bd.adicionarSala(sala);

        Sessao s = new Sessao(f,sala,Estado.ATIVO,25,6,2025,12,0, 4.50f);
        bd.atualizarSessao(s, 7.5f,Estado.INATIVO);

        assertEquals(7.5f, s.getPrecoBilhete());
        assertEquals(Estado.INATIVO, s.getEstado());
    }

    @Test
    public void testGetTiposSalaDisponiveisParaFilme2D() {
        LinkedList<Genero> generosMatrix = new LinkedList<>();
        generosMatrix.add(Genero.ACAO);
        generosMatrix.add(Genero.FICCAO_CIENTIFICA);

        Filme f = new Filme("Matrix", 120, "matrix.png", Idioma.VO, "+16",generosMatrix,"2D", Estado.ATIVO,10.00f);

        Sala sala = new Sala("Sala teste", 2, 10,"30x23m", Acessibilidade.NAO,"Normal",Estado.ATIVO);

        Sessao s = new Sessao(f, sala, Estado.ATIVO, 1, 4, 2025, 20, 50,5.00f);
        bd.getSessoes().add(s);

        Set<String> tipos = bd.getTiposSalaDisponiveisParaFilme2D(f);
        assertTrue(tipos.contains("Normal"));
    }

    @Test
    public void testExisteSessao() {
        LinkedList<Genero> generosMatrix = new LinkedList<>();
        generosMatrix.add(Genero.ACAO);
        generosMatrix.add(Genero.FICCAO_CIENTIFICA);

        Filme f = new Filme("Matrix", 120, "matrix.png", Idioma.VO, "+16",generosMatrix,"2D", Estado.ATIVO,10.00f);

        Sala sala = new Sala("Sala teste", 2, 10,"30x23m", Acessibilidade.NAO,"Normal",Estado.ATIVO);

        Sessao s = new Sessao(f, sala, Estado.ATIVO, 1, 4, 2025, 20, 50,5.00f);
        bd.getSessoes().add(s);

        boolean existe = bd.existeSessao(f, sala, 1, 4, 2025, 20, 50);
        assertTrue(existe);
    }
}
