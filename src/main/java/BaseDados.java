import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class BaseDados implements Serializable {
    //------------ GUARDAR DADOS EM FICHEIROS ----------------
    private static final long serialVersionUID = 1L;
    private static BaseDados instance = null;
    private static final String FICHEIRO_DADOS = "basedados.dat";
    //------------ GUARDAR DADOS EM FICHEIROS ----------------

    private List<Filme> filmes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();
    private List<Sessao> sessoes = new ArrayList<>();
    private List<ObjetoCarrinho> carrinho = new ArrayList<>();

    private BaseDados() {
        //---- generos VAIANA 2 -----
        LinkedList<Genero> generosVaiana2 = new LinkedList<>();
        generosVaiana2.add(Genero.ACAO);
        generosVaiana2.add(Genero.ANIMACAO);
        //---- generos VAIANA 2 -----

        //---- generos PADDINGTON NA AMAZONIA -----
        LinkedList<Genero> generosPaddington = new LinkedList<>();
        generosPaddington.add(Genero.ANIMACAO);
        generosPaddington.add(Genero.COMEDIA);
        //---- generos PADDINGTON NA AMAZONIA -----

        //---- generos AQUAMAN 2 -----
        LinkedList<Genero> generosAquaman = new LinkedList<>();
        generosAquaman.add(Genero.ACAO);
        generosAquaman.add(Genero.FICCAO_CIENTIFICA);
        //---- generos AQUAMAN 2 -----

        //---- generos MINECRAFT -----
        LinkedList<Genero> generosMinecraft = new LinkedList<>();
        generosMinecraft.add(Genero.ACAO);
        generosMinecraft.add(Genero.COMEDIA);
        //---- generos MINECRAFT -----

        //---- generos A SEMENTE DO MAL -----
        LinkedList<Genero> generosASementeDoMal = new LinkedList<>();
        generosASementeDoMal.add(Genero.TERROR);
        //---- generos A SEMENTE DO MAL -----

        //---- generos CONCLAVE -----
        LinkedList<Genero> generosConclave = new LinkedList<>();
        generosConclave.add(Genero.ACAO);
        //---- generos CONCLAVE -----

        //---- generos BRANCA DE NEVE -----
        LinkedList<Genero> generosBrancaNeve = new LinkedList<>();
        generosBrancaNeve.add(Genero.ROMANCE);
        //---- generos BRANCA DE NEVE -----

        //---- generos DIVERTIDAMENTE 2-----
        LinkedList<Genero> generosDivertidamente = new LinkedList<>();
        generosDivertidamente.add(Genero.ANIMACAO);
        //---- generos DIVERTIDAMENTE 2-----

        //---- generos SONIC -----
        LinkedList<Genero> generosSonic = new LinkedList<>();
        generosSonic.add(Genero.COMEDIA);
        //---- generos SONIC -----


        // -------------------------------------- criar filmes ----------------------------------
        // ------ FILME VAIANA 2 ------
        filmes.add(new Filme("Vaiana 2", 100,"vaianaDois.jpg", Idioma.VP, "6+", generosVaiana2, "2D", Estado.ATIVO, 30000.00f));
        filmes.add(new Filme("Vaiana 2", 100,"vaianaDois.jpg", Idioma.VP, "6+", generosVaiana2, "5D", Estado.ATIVO, 30000.00f));
        filmes.add(new Filme("Vaiana 2", 100,"vaianaDois.jpg", Idioma.VO, "6+", generosVaiana2, "2D", Estado.ATIVO, 30000.00f));
        filmes.add(new Filme("Vaiana 2", 100,"vaianaDois.jpg", Idioma.VO, "6+", generosVaiana2, "5D", Estado.ATIVO, 30000.00f));
        // ------ FILME VAIANA 2 ------

        // ----- FILME PADDINGTON NA AMAZONIA ------
        filmes.add(new Filme("Paddington na Amazónia", 106,"paddingtonNaAmazonia.jpg", Idioma.VP, "6+", generosPaddington, "2D", Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("Paddington na Amazónia", 106,"paddingtonNaAmazonia.jpg", Idioma.VO, "6+", generosPaddington, "2D", Estado.ATIVO, 25000.00f));
        // ----- FILME PADDINGTON NA AMAZONIA ------

        // -------- FILME AQUAMAN 2 -----------
        filmes.add(new Filme("Aquaman 2 - O reino perdido", 124,"aquamanEOReinoPerdido.jpg", Idioma.VP, "16+", generosAquaman, "2D", Estado.ATIVO, 40000.00f));
        filmes.add(new Filme("Aquaman 2 - O reino perdido", 124,"aquamanEOReinoPerdido.jpg", Idioma.VP, "16+", generosAquaman, "5D", Estado.ATIVO, 40000.00f));
        filmes.add(new Filme("Aquaman 2 - O reino perdido", 124,"aquamanEOReinoPerdido.jpg", Idioma.VO, "16+", generosAquaman, "2D", Estado.ATIVO, 40000.00f));
        filmes.add(new Filme("Aquaman 2 - O reino perdido", 124,"aquamanEOReinoPerdido.jpg", Idioma.VO, "16+", generosAquaman, "5D", Estado.ATIVO, 40000.00f));
        // -------- FILME AQUAMAN 2 -----------

        // -------- FILME MINECRAFT -----------
        filmes.add(new Filme("Um filme Minecraft", 100,"umFilmeMinecraft.jpg", Idioma.VP, "6+", generosMinecraft, "2D", Estado.ATIVO, 40000.00f));
        filmes.add(new Filme("Um filme Minecraft", 100,"umFilmeMinecraft.jpg", Idioma.VO, "6+", generosMinecraft, "2D", Estado.ATIVO, 40000.00f));
        // -------- FILME MINECRAFT -----------

        // ----- FILME A SEMENTE DO MAL -------
        filmes.add(new Filme("A semente do mal", 90,"ASementeDoMal.jpg", Idioma.VP, "16+", generosASementeDoMal, "2D", Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("A semente do mal", 90,"ASementeDoMal.jpg", Idioma.VP, "16+", generosASementeDoMal, "3D", Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("A semente do mal", 90,"ASementeDoMal.jpg", Idioma.VO, "16+", generosASementeDoMal, "2D", Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("A semente do mal", 90,"ASementeDoMal.jpg", Idioma.VO, "16+", generosASementeDoMal, "3D", Estado.ATIVO, 20000.00f));
        // ----- FILME A SEMENTE DO MAL -------

        // -------- FILME CONCLAVE -----------
        filmes.add(new Filme("Conclave", 120,"conclave.jpg", Idioma.VP, "12+", generosConclave, "2D", Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("Conclave", 120,"conclave.jpg", Idioma.VO, "12+", generosConclave, "2D", Estado.ATIVO, 20000.00f));
        // -------- FILME CONCLAVE -----------

        // ------ FILME BRANCA DE NEVE -------
        filmes.add(new Filme("Branca de Neve", 110,"brancaDeNeve.jpeg", Idioma.VP, "6+", generosBrancaNeve, "2D", Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("Branca de Neve", 110,"brancaDeNeve.jpeg", Idioma.VO, "6+", generosBrancaNeve, "2D", Estado.ATIVO, 25000.00f));
        // ------ FILME BRANCA DE NEVE -------

        // ------ FILME JOKER -------
        filmes.add(new Filme("Joker: Loucura a dois", 140,"jokerLoucuraADois.jpg", Idioma.VP, "16+", generosConclave, "2D", Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("Joker: Loucura a dois", 140,"jokerLoucuraADois.jpg", Idioma.VO, "16+", generosConclave, "2D", Estado.ATIVO, 25000.00f));
        // ------ FILME JOKER -------

        // ---- FILME DIVERTIDAMENTE 2 ----
        filmes.add(new Filme("Divertidamente 2", 96,"divertidamenteDois.jpg", Idioma.VP, "6+", generosDivertidamente, "2D", Estado.ATIVO, 35000.00f));
        filmes.add(new Filme("Divertidamente 2", 96,"divertidamenteDois.jpg", Idioma.VO, "6+", generosDivertidamente, "2D", Estado.ATIVO, 35000.00f));
        // ---- FILME DIVERTIDAMENTE 2 ----

        // -------- FILME SONIC -----------
        filmes.add(new Filme("Sonic 2", 115,"sonicDois.jpg", Idioma.VP, "6+", generosSonic, "2D", Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("Sonic 2", 115,"sonicDois.jpg", Idioma.VO, "6+", generosSonic, "2D", Estado.ATIVO, 25000.00f));
        // -------- FILME SONIC -----------

        // -------- FILME FAMILIA ADDAMS -----------
        filmes.add(new Filme("A Familia Addams", 87,"familiaAddams.jpg", Idioma.VP, "6+", generosPaddington, "2D", Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("A Familia Addams", 87,"familiaAddams.jpg", Idioma.VO, "6+", generosPaddington, "2D", Estado.ATIVO, 20000.00f));
        // -------- FILME FAMILIA ADDAMS -----------

        // -------- FILME GRU MALDISPOSTO 4 -----------
        filmes.add(new Filme("Gru Maldisposto 4", 94,"gruMaldispostoQuatro.jpg", Idioma.VP, "6+", generosPaddington, "2D", Estado.ATIVO, 30000.00f));
        filmes.add(new Filme("Gru Maldisposto 4", 94,"gruMaldispostoQuatro.jpg", Idioma.VO, "6+", generosPaddington, "2D", Estado.ATIVO, 30000.00f));
        // -------- FILME GRU MALDISPOSTO 4 -----------
        // -------------------------------------- criar filmes ----------------------------------

        //criar produtos
        produtos.add(new Produto(1,"cocaCola.png","Coca Cola",TipoProduto.BEBIDA,Estado.ATIVO,20,0.50f,1.50f));
        produtos.add(new Produto(2,"sumolAnanas.png","Sumol Ananás",TipoProduto.BEBIDA,Estado.ATIVO,50,0.50f,1.50f));
        produtos.add(new Produto(3,"icedTeaLimão.png","Iced tea Limão",TipoProduto.BEBIDA,Estado.ATIVO,50,0.50f,1.50f));
        produtos.add(new Produto(4,"agua.png","Água",TipoProduto.BEBIDA,Estado.ATIVO,40,0.20f,0.90f));
        produtos.add(new Produto(5,"baldePequeno.png","Balde pequeno",TipoProduto.APERITIVO,Estado.ATIVO,40,0.20f,3.90f));
        produtos.add(new Produto(6,"baldeMedio.png","Balde medio",TipoProduto.APERITIVO,Estado.ATIVO,40,0.30f,5.50f));
        produtos.add(new Produto(7,"baldeGrande.png","Balde grande",TipoProduto.APERITIVO,Estado.ATIVO,40,0.40f,7.00f));
        produtos.add(new Produto(8,"nachos.png","Nachos",TipoProduto.APERITIVO,Estado.ATIVO,20,1.00f,4.00f));
        produtos.add(new Produto(9,"packPequeno.png","Pack pequeno",TipoProduto.PACK,Estado.ATIVO,20,6.20f,9.00f));
        produtos.add(new Produto(10,"packMedio.png","Pack medio",TipoProduto.PACK,Estado.ATIVO,20,6.30f,11.00f));
        produtos.add(new Produto(11,"packGrande.png","Pack grande",TipoProduto.PACK,Estado.ATIVO,20,6.40f,13.00f));
        produtos.add(new Produto(12,"packNachos.png","Pack nachos",TipoProduto.PACK,Estado.ATIVO,20,7.00f,9.00f));


        //criar salas
        salas.add(new Sala("Sala 1", 4,10,"10x5m", Acessibilidade.SIM,"Normal",Estado.ATIVO));
        salas.add(new Sala("Sala 2", 4,10,"14x6m", Acessibilidade.NAO,"VIP",Estado.ATIVO)); 
        salas.add(new Sala("Sala 3", 4,10,"22x16m", Acessibilidade.NAO,"Normal",Estado.ATIVO)); 
        salas.add(new Sala("Sala 4", 4,10,"10x5m", Acessibilidade.SIM,"Normal",Estado.ATIVO)); 
        salas.add(new Sala("Sala 5", 4,10,"30x23m", Acessibilidade.NAO,"5D",Estado.ATIVO)); 
        salas.add(new Sala("Sala 6", 4,10,"10x5m", Acessibilidade.SIM,"Normal",Estado.ATIVO)); 
        salas.add(new Sala("Sala 7", 4,10,"10x5m", Acessibilidade.SIM,"Normal",Estado.ATIVO)); 
        salas.add(new Sala("Sala 8", 4,10,"30x23m", Acessibilidade.NAO,"5D",Estado.ATIVO));


        // ------------------------------------ criar sessoes -------------------------------------------
        //------- VAIANA 2 ----------
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,25,6,2025,12,0, 4.50f)); // filme Vaiana 2 VP 2D, Sala 4, dia 25-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(1),salas.get(7),Estado.ATIVO,25,6,2025,12,0, 4.50f)); // filme Vaiana 2 VP 5D, Sala 8, dia 25-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,25,6,2025,15,30, 4.50f)); // filme Vaiana 2 VO 2D, Sala 4, dia 25-6-2025, hora 15h30

        sessoes.add(new Sessao(filmes.get(3),salas.get(7),Estado.ATIVO,25,6,2025,15,30, 4.50f)); // filme Vaiana 2 VO 5D, Sala 8, dia 25-6-2025, hora 15h30
        //------- VAIANA 2 ----------
        
        //------- PADDINGTON ----------
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,12,0, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,15,30, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,21,30, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,12,0, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,15,30, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,18,50, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,21,30, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,12,0, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,15,30, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,18,50, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,21,30, 4.50f)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 21h30

        sessoes.add(new Sessao(filmes.get(5),salas.get(3),Estado.ATIVO,22,6,2025,12,0, 4.50f)); // filme Paddington VO 2D, Sala 4, dia 22-6-2025, hora 12h00
        //------- PADDINGTON ----------

        //------- AQUAMAN 2 ----------
        sessoes.add(new Sessao(filmes.get(6),salas.get(5),Estado.ATIVO,22,6,2025,12,0, 4.50f)); // filme Aquaman 2 VP 2D, Sala 6, dia 22-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(7),salas.get(7),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme Aquaman 2 VP 5D, Sala 8, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(8),salas.get(5),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme Aquaman 2 VO 2D, Sala 6, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,12,0, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,15,30, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,21,30, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,12,0, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,15,30, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,18,50, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,21,30, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,12,0, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,15,30, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,18,50, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,21,30, 4.50f)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 21h30
        //------- AQUAMAN 2 ----------

        //------- Minecraft ----------
        sessoes.add(new Sessao(filmes.get(10),salas.get(3),Estado.ATIVO,22,6,2025,21,30, 4.50f)); // filme Minecraft VP 2D, Sala 4, dia 22-6-2025, hora 21h30

        sessoes.add(new Sessao(filmes.get(11),salas.get(3),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme Minecraft VO 2D, Sala 4, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,12,0, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,15,30, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,21,30, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,12,0, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,15,30, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,18,50, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,21,30, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,12,0, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,15,30, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,18,50, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,21,30, 4.50f)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 21h30
        //------- Minecraft ----------

        //------- A SEMENTE DO MAL ----------
        sessoes.add(new Sessao(filmes.get(12),salas.get(6),Estado.ATIVO,22,6,2025,12,0, 4.50f)); // filme A Semente do mal VP 2D, Sala 7, dia 22-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(13),salas.get(6),Estado.ATIVO,22,6,2025,15,30, 4.50f)); // filme A Semente do mal VP 3D, Sala 7, dia 22-6-2025, hora 15h30

        sessoes.add(new Sessao(filmes.get(14),salas.get(6),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme A Semente do mal VO 2D, Sala 7, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,12,0, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,15,30, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,18,50, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,21,30, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,12,0, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,15,30, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,18,50, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,21,30, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,12,0, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,15,30, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,18,50, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,21,30, 4.50f)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 21h30
        //------- A SEMENTE DO MAL ----------

        //------- CONCLAVE ----------
        sessoes.add(new Sessao(filmes.get(17),salas.get(0),Estado.ATIVO,25,6,2025,12,0, 4.50f)); // filme Conclave VO 2D, Sala 1, dia 25-6-2025, hora 12h00
        //------- CONCLAVE ----------

        //------- BRANCA DE NEVE ----------
        sessoes.add(new Sessao(filmes.get(18),salas.get(2),Estado.ATIVO,25,6,2025,12,0, 4.50f)); // filme Branca de Neve VP 2D, Sala 3, dia 25-6-2025, hora 12h00
        //------- BRANCA DE NEVE ----------

        //------- JOKER ----------
        sessoes.add(new Sessao(filmes.get(21),salas.get(1),Estado.ATIVO,25,6,2025,12,0, 4.50f)); // filme Joker: Loucura a dois VO 2D, Sala 2, dia 25-6-2025, hora 12h00
        //------- JOKER ----------

        //------- DIVERTIDAMENTE 2 ----------
        sessoes.add(new Sessao(filmes.get(22),salas.get(3),Estado.ATIVO,28,6,2025,12,0, 4.50f)); // filme Divertidamente 2 VP 2D, Sala 4, dia 28-6-2025, hora 12h00
        //------- DIVERTIDAMENTE 2 ----------


        //------- SONIC 2 ----------
        sessoes.add(new Sessao(filmes.get(25),salas.get(5),Estado.ATIVO,28,6,2025,12,0, 4.50f)); // filme Sonic 2 VO 2D, Sala 6, dia 28-6-2025, hora 12h00
        //------- SONIC 2 ----------

        //------- A FAMILIA ADDAMS ----------
        sessoes.add(new Sessao(filmes.get(26),salas.get(2),Estado.ATIVO,28,6,2025,12,0, 4.50f)); // filme A Familia Addams VP 2D, Sala 3, dia 28-6-2025, hora 12h00
        //------- A FAMILIA ADDAMS ----------

        //------- GRU MALDISPOSTO 4 ----------
        sessoes.add(new Sessao(filmes.get(28),salas.get(0),Estado.ATIVO,1,7,2025,12,0, 4.50f)); // filme Gru Maldisposto 4 VP 2D, Sala 1, dia 1-7-2025, hora 12h00
        //------- GRU MALDISPOSTO 4 ----------
        // ------------------------------------ criar sessoes -------------------------------------------

        // Dados dos filmes
        for(Sessao sessao : sessoes) {
            sessao.getFilme().setComSessao(true);
        }

    }

    public static BaseDados getInstance() {
        if (instance == null) {
            instance = carregarDados();
            if (instance == null) {
                instance = new BaseDados();
            }
        }
        return instance;
    }

    public static BaseDados carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DADOS))) {
            return (BaseDados) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ficheiro não encontrado ou erro ao carregar: " + e.getMessage());
            return null;
        }
    }

    public void gravarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO_DADOS))) {
            oos.writeObject(this);
            System.out.println("Dados guardados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao guardar os dados: " + e.getMessage());
        }
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public Filme getFilmeByNome(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        return null;
    }


    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void adicionarFilme(Filme f) {
        filmes.add(f);
    }

    public boolean removerFilme(Filme filme) {
        return filmes.removeIf(f ->
                f.getNome().equalsIgnoreCase(filme.getNome()) &&
                        f.getDuracao() == filme.getDuracao() &&
                        f.getFoto().equals(filme.getFoto()) &&
                        f.getIdiomas().equals(filme.getIdiomas()) &&
                        f.getIdade().equalsIgnoreCase(filme.getIdade()) &&
                        f.getGeneros().equals(filme.getGeneros()) &&
                        f.getTipos().equals(filme.getTipos()) &&
                        f.getEstado().equals(filme.getEstado()) &&
                        Float.compare(f.getPrecoCompra(), filme.getPrecoCompra()) == 0
        );
    }

    public Set<String> getTiposSalaDisponiveisParaFilme2D(Filme filmeSelecionado) {
        Set<String> tiposSala = new HashSet<>();

        for (Sessao sessao : sessoes) {
            Filme f = sessao.getFilme();
            Sala s = sessao.getSala();

            // Verifica se é o mesmo filme e se é 2D
            if (f.getNome().equalsIgnoreCase(filmeSelecionado.getNome()) && f.getIdiomas().equals(filmeSelecionado.getIdiomas()) && f.getTipos().equalsIgnoreCase("2D")) {
                tiposSala.add(s.getTipo()); // Ex: "Normal", "VIP"
            }
        }

        return tiposSala;
    }

    public boolean existeSessao(Filme filme, Sala sala, int dia, int mes, int ano, int hora, int minuto) {
        for (Sessao s : sessoes) {
            if (s.getFilme().equals(filme) &&
                    s.getSala().equals(sala) &&
                    s.getDia() == dia &&
                    s.getMes() == mes &&
                    s.getAno() == ano &&
                    s.getHora() == hora &&
                    s.getMinuto() == minuto) {
                return true;
            }
        }
        return false;
    }

    public void atualizarSessao(Sessao sessao, float precoBilhete, Estado estado) {
        sessao.setPrecoBilhete(precoBilhete);
        sessao.setEstado(estado);
    }


    // ------------------- ESTATÍSTICAS -------------------

    // Fetches ticket sales per day (example: count of Sessao per date)
    public Map<String, Integer> vendasPorDia() {        // Apenas os ultimos 30 dias
        Map<String, Integer> vendasPorDia = new LinkedHashMap<>();
        LocalDate hoje = LocalDate.now();
        for (Sessao s : sessoes) {
            LocalDate dataSessao = LocalDate.of(s.getAno(), s.getMes(), s.getDia());
            if (!dataSessao.isBefore(hoje.minusDays(30))) {
                String dataFormatada = dataSessao.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                vendasPorDia.put(dataFormatada, vendasPorDia.getOrDefault(dataFormatada, 0) + s.getBilhetesVendidos());
            }
        }
        return vendasPorDia;
    }

    // Fetches stock for each product
    public Object[][] tabelaStock() {
        List<Produto> produtos = getProdutos();
        Object[][] data = new Object[produtos.size()][2];
        for (int i = 0; i < produtos.size(); i++) {
            data[i][0] = produtos.get(i).getNome();
            data[i][1] = produtos.get(i).getStock();
        }
        return data;
    }

    public Map<String, Integer> vendasPorMes() {        // últimos 12 meses
        Map<String, Integer> vendasPorMes = new LinkedHashMap<>();
        LocalDate hoje = LocalDate.now();
        for (Sessao s : sessoes) {
            LocalDate dataSessao = LocalDate.of(s.getAno(), s.getMes(), s.getDia());
            if (!dataSessao.isBefore(hoje.minusMonths(12))) {
                String mesAno = dataSessao.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                vendasPorMes.put(mesAno, vendasPorMes.getOrDefault(mesAno, 0) + s.getBilhetesVendidos());
            }
        }
        return vendasPorMes;
    }

    public Map<String, Integer> vendasPorAno() {       // últimos 5 anos
        Map<String, Integer> vendasPorAno = new LinkedHashMap<>();
        LocalDate hoje = LocalDate.now();
        for (Sessao s : sessoes) {
            LocalDate dataSessao = LocalDate.of(s.getAno(), s.getMes(), s.getDia());
            if (!dataSessao.isBefore(hoje.minusYears(5))) {
                String ano = String.valueOf(s.getAno());
                vendasPorAno.put(ano, vendasPorAno.getOrDefault(ano, 0) + s.getBilhetesVendidos());
            }
        }
        return vendasPorAno;
    }


    public Map<String, Integer> vendasPorSessao() {     // Gera um mapa com o número de bilhetes vendidos por sessão
        Map<String, Integer> vendasPorSessao = new LinkedHashMap<>();
        for (Sessao s : sessoes) {
            String sessaoInfo = String.format("%s - %02d/%02d/%04d %02d:%02d", s.getFilme().getNome(), s.getDia(), s.getMes(), s.getAno(), s.getHora(), s.getMinuto());
            vendasPorSessao.put(sessaoInfo, vendasPorSessao.getOrDefault(sessaoInfo, 0) + s.getBilhetesVendidos());
        }
        return vendasPorSessao;
    }

    public Map<String, Integer> vendasPorFilme() {      // Gera um mapa com o número de bilhetes vendidos por filme
        Map<String, Integer> vendasPorFilme = new LinkedHashMap<>();
        for (Sessao s : sessoes) {
            String filmeNome = s.getFilme().getNome();
            vendasPorFilme.put(filmeNome, vendasPorFilme.getOrDefault(filmeNome, 0) + s.getBilhetesVendidos());
        }
        return vendasPorFilme;
    }

    public Map<String, Integer> produtosMaisVendidos() {    // Gera um mapa com os 10 produtos mais vendidos por ordem decrescente
        return null; // Implementar lógica para calcular os produtos mais vendidos
    }

    public Map<String, Double> taxaOcupacaoPorSessao() {
        Map<String, Double> taxaOcupacao = new LinkedHashMap<>();
        for (Sessao s : sessoes) {
            String sessaoInfo = String.format("%s - %02d/%02d/%04d %02d:%02d", s.getFilme().getNome(), s.getDia(), s.getMes(), s.getAno(), s.getHora(), s.getMinuto());
            double taxa = (double) s.getBilhetesVendidos() / s.getSala().getLotacao() * 100;
            taxaOcupacao.put(sessaoInfo, taxa);
        }
        return taxaOcupacao;
    }

    public Map<String, Double> taxaOcupacaoPorSala() {      // Gera um mapa com a taxa de ocupação por sala
        Map<String, Double> taxaOcupacaoPorSala = new LinkedHashMap<>();
        for (Sessao s : sessoes) {
            String salaNome = s.getSala().getDesignacao();
            double taxa = (double) s.getBilhetesVendidos() / s.getSala().getLotacao() * 100;
            taxaOcupacaoPorSala.put(salaNome, taxaOcupacaoPorSala.getOrDefault(salaNome, 0.0) + taxa);
        }
        return taxaOcupacaoPorSala;
    }

    public Map<String, Double> lucros() {        // Gera um mapa com os lucros por sessão
        return null; // Implementar lógica para calcular os lucros por sessão
    }

    public Map<String, Integer> generosMaisVistos() {       // Gera um mapa com os géneros mais vistos e o número de bilhetes vendidos por ordem decrescente
        Map<String, Integer> generosMaisVistos = new LinkedHashMap<>();
        for (Sessao s : sessoes) {
            LinkedList<Genero> genero = s.getFilme().getGeneros();
            for (Genero g : genero) {
                String generoNome = g.toString(); // Assuming Genero is an enum or has a valid toString implementation
                generosMaisVistos.put(generoNome, generosMaisVistos.getOrDefault(generoNome, 0) + s.getBilhetesVendidos());
            }
        }

        // Sort the map by values (number of tickets sold) in descending order
        return generosMaisVistos.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<String, Integer> top5FilmesMaisVistos() {        // Gera um mapa com os 5 filmes mais vistos e o número de bilhetes vendidos por ordem decrescente
        Map<String, Integer> top5Filmes = vendasPorFilme();
        return top5Filmes.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Object[][] tabelaVendasPorSessao() {     // Tabela de vendas por sessão ordenada por bilhetes vendidos decrescentes
        List<Sessao> sessoes = getSessoes();
        Object[][] data = new Object[sessoes.size()][3];
        AtomicInteger index = new AtomicInteger(0);

        sessoes.stream()
                .sorted(Comparator.comparingInt(Sessao::getBilhetesVendidos).reversed())
                .forEach(s -> {
                    data[index.get()][0] = s.getFilme().getNome();
                    data[index.get()][1] = String.format("%02d/%02d/%04d %02d:%02d", s.getDia(), s.getMes(), s.getAno(), s.getHora(), s.getMinuto());
                    data[index.get()][2] = s.getBilhetesVendidos();
                    index.getAndIncrement();
                });

        return data;
    }

    public Object[][] tabelaVendasPorFilme() {      // Tabela de vendas por filme ordenada por vendas decrescentes
        Map<String, Integer> vendasPorFilme = vendasPorFilme();
        Object[][] data = new Object[vendasPorFilme.size()][2];
        AtomicInteger index = new AtomicInteger(0);

        // Sort the map by values (number of tickets sold) in descending order
        vendasPorFilme.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    data[index.get()][0] = entry.getKey();
                    data[index.get()][1] = entry.getValue();
                    index.getAndIncrement();
                });

        return data;
    }

    public Object[][] tabelaTaxaOcupacaoPorSessao() {       // Tabela de taxa de ocupação por sessão ordenada por taxa decrescente
        Map<String, Double> taxaOcupacao = taxaOcupacaoPorSessao();
        Object[][] data = new Object[taxaOcupacao.size()][2];
        AtomicInteger index = new AtomicInteger(0);

        // Sort the map by values (taxa de ocupação) in descending order
        taxaOcupacao.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> {
                    data[index.get()][0] = entry.getKey();
                    data[index.get()][1] = entry.getValue();
                    index.getAndIncrement();
                });

        return data;
    }

    public Object[][] tabelaLucros() {      // Tabela de lucros por sessão ordenada por lucros decrescentes
        Map<String, Double> lucros = lucros();
        Object[][] data = new Object[lucros.size()][2];
        AtomicInteger index = new AtomicInteger(0);

        // Sort the map by values (lucro) in descending order
        lucros.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> {
                    data[index.get()][0] = entry.getKey();
                    data[index.get()][1] = entry.getValue();
                    index.getAndIncrement();
                });

        return data;
    }


    // ------------------- ESTATÍSTICAS -------------------

    // ------------------- PRODUTO -------------------
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Produto> getProdutosPorTipo(TipoProduto tipo) {
        List<Produto> produtosTipo = new ArrayList<>();

        for(Produto produto : this.getProdutos()){
            if(produto.getTipoProduto() == tipo){
                produtosTipo.add(produto);
            }
        }

        return produtosTipo;
    }

    public Produto getProdutosPorNome(String nome) {
        Produto produtoSelecionado = null;
        for(Produto produto : this.getProdutos()){
            if(produto.getNome().equals(nome)){
                produtoSelecionado = produto;
                break;
            }
        }

        return produtoSelecionado;
    }

    public Integer getLastIdProduto(){
        Produto produto = produtos.get(produtos.size() - 1);
        return produto.getIdProduto();
    }

    public Produto getProdutobyID(int id){
        Produto produtoSelecionado = null;

        for(Produto produto : this.getProdutos()){
            if(produto.getIdProduto() == id){
                produtoSelecionado = produto;
                break;
            }
        }

        return produtoSelecionado;
    }


    // --------------------- Salas ---------------------

    public Sala getSalaByNome(String nome) {
        for (Sala sala : salas) {
            if (sala.getDesignacao().equalsIgnoreCase(nome)) {
                return sala;
            }
        }
        return null;
    }

    public void editarSala(Sala salaAEditar, Sala salaEditada) {
        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).equals(salaAEditar)) {
                salas.set(i, salaEditada);
                return;
            }
        }
    }

    public void adicionarSala(Sala sala) {
        salas.add(sala);
    }

    // ------------------- CARRINHO -------------------
    //adiciona objetos do tipo Produto, bilhete (?)
    public void adicionarAoCarrinho(ObjetoCarrinho objeto){

        boolean flagObjetoExiste = false;

        //só verifica produtos, falta colocar bilhetes
        if(objeto.getObjeto() instanceof Produto){

            //produto a adicionar, quando clica no botão adicionar
            Produto produto = (Produto) objeto.getObjeto();
            for (ObjetoCarrinho carrinho : this.carrinho){
                if(carrinho.getObjeto() instanceof Produto){
                    Produto produtoCarrinho = (Produto) carrinho.getObjeto();

                    //verifica se o produto do carrinho é o mesmo produto que está na lista
                    if (produtoCarrinho.getIdProduto() == produto.getIdProduto()){

                        //remove do carrinho caso a quantidade seja 0 ou inferior
                        //fazer validação no frontedn
                        if(objeto.getQuantidade() == 0 || objeto.getQuantidade() < 0 ){
                            this.carrinho.remove(carrinho);
                            return;
                        }

                        //alterar a quantidade
                        carrinho.setQuantidade(objeto.getQuantidade());
                        return;
                    }
                }
            }
        }

        // BILHETE
        else if (objeto.getObjeto() instanceof Bilhete) {
            Bilhete novoBilhete = (Bilhete) objeto.getObjeto();

            // Garante que não há bilhete duplicado com mesmo lugar + sessão
            for (ObjetoCarrinho carrinho : this.carrinho) {
                if (carrinho.getObjeto() instanceof Bilhete) {
                    Bilhete bilheteCarrinho = (Bilhete) carrinho.getObjeto();

                    if (bilheteCarrinho.getSessao().equals(novoBilhete.getSessao())
                            && bilheteCarrinho.getLugar().equals(novoBilhete.getLugar())) {
                        return; // já existe, não adiciona de novo
                    }
                }
            }
        }

        carrinho.add(objeto);

    }

    public void removerDoCarrinho(ObjetoCarrinho objetoParaRemover) {
        Iterator<ObjetoCarrinho> iterator = carrinho.iterator();

        while (iterator.hasNext()) {
            ObjetoCarrinho obj = iterator.next();

            // Verifica se os objetos são do mesmo tipo
            if (obj.getObjeto() instanceof Produto && objetoParaRemover.getObjeto() instanceof Produto) {
                Produto p1 = (Produto) obj.getObjeto();
                Produto p2 = (Produto) objetoParaRemover.getObjeto();
                if (p1.getIdProduto() == p2.getIdProduto()) {
                    iterator.remove();
                    return;
                }
            } else if (obj.getObjeto() instanceof Bilhete && objetoParaRemover.getObjeto() instanceof Bilhete) {
                Bilhete b1 = (Bilhete) obj.getObjeto();
                Bilhete b2 = (Bilhete) objetoParaRemover.getObjeto();

                boolean mesmaSessao = b1.getSessao().equals(b2.getSessao());
                boolean mesmoLugar = b1.getLugar().equals(b2.getLugar());

                if (mesmaSessao && mesmoLugar) {
                    iterator.remove();
                    return;
                }
            }
        }
    }



    public List<ObjetoCarrinho> getElementosCarrinho(){
        return this.carrinho;
    }

    public float getTotalCarrinho(){
        float total = 0;

        for(ObjetoCarrinho objeto : this.carrinho){
            if(objeto.getObjeto() instanceof Produto){
                total += ((Produto) objeto.getObjeto()).getPrecoVendaUnidade() * objeto.getQuantidade() * (1 - objeto.getDesconto());
            } else if (objeto.getObjeto() instanceof Bilhete) {
                Bilhete bilhete = (Bilhete) objeto.getObjeto();
                total += bilhete.getSessao().getPrecoBilhete(); // Cada bilhete é uma unidade
            }
        }

        return total;
    }

    public boolean pagamentoCarrinho(){
        boolean stockBaixo = false;

        for(ObjetoCarrinho objeto : this.carrinho){
            if(objeto.getObjeto() instanceof Produto){
                //vai buscar o stock produto
                int stock = ((Produto) objeto.getObjeto()).getStock();
                //define um novo = stock atual - quantidade comprada
                ((Produto) objeto.getObjeto()).setStock(stock - objeto.getQuantidade());

                //se o stock do produto for menor que 5
                if(((Produto) objeto.getObjeto()).getStock() < 5){
                    stockBaixo = true;
                }
            }
            if (objeto.getObjeto() instanceof Bilhete) {
                Bilhete bilhete = (Bilhete) objeto.getObjeto();

                // Marcar como vendido
                bilhete.setVendido(true);

                // Ocupa lugar definitivamente na sessão
                Sessao sessao = bilhete.getSessao();

                // Extrair fila e lugar a partir da string "F3-L7"
                String[] partes = bilhete.getLugar().split("-");
                int fila = Integer.parseInt(partes[0].substring(1)) - 1; // índice zero-based
                int lugar = Integer.parseInt(partes[1].substring(1)) - 1;

                int lugaresPorFila = sessao.getSala().getNumLugaresFila();
                int index = fila * lugaresPorFila + lugar;

                sessao.ocuparLugar(index); // aqui grava na base de dados real
            }
        }

        //elimina todos produtos do carrinho
        this.carrinho = new ArrayList<>();

        this.gravarDados();

        return stockBaixo;
    }




}
