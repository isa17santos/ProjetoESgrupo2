import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

public class BaseDados implements Serializable{
    //------------ GUARDAR DADOS EM FICHEIROS ----------------
    private static final long serialVersionUID = 1L;
    private static BaseDados instance = null;
    private static final String FICHEIRO_DADOS = "basedados.dat";
    //------------ GUARDAR DADOS EM FICHEIROS ----------------

    private List<Filme> filmes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();
    private List<Sessao> sessoes = new ArrayList<>();

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
        produtos.add(new Produto("cocaCola.png","Coca Cola",TipoProduto.BEBIDA,Estado.ATIVO,20,0.50f,1.50f));
        produtos.add(new Produto("sumolAnanas.png","Sumol Ananás",TipoProduto.BEBIDA,Estado.ATIVO,50,0.50f,1.50f));
        produtos.add(new Produto("icedTeaLimão.png","Iced tea Limão",TipoProduto.BEBIDA,Estado.ATIVO,50,0.50f,1.50f));
        produtos.add(new Produto("agua.png","Água",TipoProduto.BEBIDA,Estado.ATIVO,40,0.20f,0.90f));
        produtos.add(new Produto("baldePequeno.png","Balde pequeno",TipoProduto.APERITIVO,Estado.ATIVO,40,0.20f,3.90f));
        produtos.add(new Produto("baldeMedio.png","Balde medio",TipoProduto.APERITIVO,Estado.ATIVO,40,0.30f,5.50f));
        produtos.add(new Produto("baldeGrande.png","Balde grande",TipoProduto.APERITIVO,Estado.ATIVO,40,0.40f,7.00f));
        produtos.add(new Produto("nachos.png","Nachos",TipoProduto.APERITIVO,Estado.ATIVO,20,1.00f,4.00f));
        produtos.add(new Produto("packPequeno.png","Pack pequeno",TipoProduto.PACK,Estado.ATIVO,20,6.20f,9.00f));
        produtos.add(new Produto("packMedio.png","Pack medio",TipoProduto.PACK,Estado.ATIVO,20,6.30f,11.00f));
        produtos.add(new Produto("packGrande.png","Pack grande",TipoProduto.PACK,Estado.ATIVO,20,6.40f,13.00f));
        produtos.add(new Produto("packNachos.png","Pack nachos",TipoProduto.PACK,Estado.ATIVO,20,7.00f,9.00f));


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
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,25,6,2025,12,0)); // filme Vaiana 2 VP 2D, Sala 4, dia 25-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(1),salas.get(7),Estado.ATIVO,25,6,2025,12,0)); // filme Vaiana 2 VP 5D, Sala 8, dia 25-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,25,6,2025,15,30)); // filme Vaiana 2 VO 2D, Sala 4, dia 25-6-2025, hora 15h30

        sessoes.add(new Sessao(filmes.get(3),salas.get(7),Estado.ATIVO,25,6,2025,15,30)); // filme Vaiana 2 VO 5D, Sala 8, dia 25-6-2025, hora 15h30
        //------- VAIANA 2 ----------
        
        //------- PADDINGTON ----------
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,12,0)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,15,30)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,18,50)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,22,6,2025,21,30)); // filme Paddington VP 2D, Sala 1, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,12,0)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,15,30)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,18,50)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,23,6,2025,21,30)); // filme Paddington VP 2D, Sala 1, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,12,0)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,15,30)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,18,50)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(0),Estado.ATIVO,24,6,2025,21,30)); // filme Paddington VP 2D, Sala 1, dia 24-6-2025, hora 21h30

        sessoes.add(new Sessao(filmes.get(5),salas.get(3),Estado.ATIVO,22,6,2025,12,0)); // filme Paddington VO 2D, Sala 4, dia 22-6-2025, hora 12h00
        //------- PADDINGTON ----------

        //------- AQUAMAN 2 ----------
        sessoes.add(new Sessao(filmes.get(6),salas.get(5),Estado.ATIVO,22,6,2025,12,0)); // filme Aquaman 2 VP 2D, Sala 6, dia 22-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(7),salas.get(7),Estado.ATIVO,22,6,2025,18,50)); // filme Aquaman 2 VP 5D, Sala 8, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(8),salas.get(5),Estado.ATIVO,22,6,2025,18,50)); // filme Aquaman 2 VO 2D, Sala 6, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,12,0)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,15,30)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,18,50)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,22,6,2025,21,30)); // filme Aquaman 2 VO 5D, Sala 5, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,12,0)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,15,30)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,18,50)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,23,6,2025,21,30)); // filme Aquaman 2 VO 5D, Sala 5, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,12,0)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,15,30)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,18,50)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(4),Estado.ATIVO,24,6,2025,21,30)); // filme Aquaman 2 VO 5D, Sala 5, dia 24-6-2025, hora 21h30
        //------- AQUAMAN 2 ----------

        //------- Minecraft ----------
        sessoes.add(new Sessao(filmes.get(10),salas.get(3),Estado.ATIVO,22,6,2025,21,30)); // filme Minecraft VP 2D, Sala 4, dia 22-6-2025, hora 21h30

        sessoes.add(new Sessao(filmes.get(11),salas.get(3),Estado.ATIVO,22,6,2025,18,50)); // filme Minecraft VO 2D, Sala 4, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,12,0)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,15,30)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,18,50)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,22,6,2025,21,30)); // filme Minecraft VO 2D, Sala 2, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,12,0)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,15,30)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,18,50)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,23,6,2025,21,30)); // filme Minecraft VO 2D, Sala 2, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,12,0)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,15,30)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,18,50)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(1),Estado.ATIVO,24,6,2025,21,30)); // filme Minecraft VO 2D, Sala 2, dia 24-6-2025, hora 21h30
        //------- Minecraft ----------

        //------- A SEMENTE DO MAL ----------
        sessoes.add(new Sessao(filmes.get(12),salas.get(6),Estado.ATIVO,22,6,2025,12,0)); // filme A Semente do mal VP 2D, Sala 7, dia 22-6-2025, hora 12h00

        sessoes.add(new Sessao(filmes.get(13),salas.get(6),Estado.ATIVO,22,6,2025,15,30)); // filme A Semente do mal VP 3D, Sala 7, dia 22-6-2025, hora 15h30

        sessoes.add(new Sessao(filmes.get(14),salas.get(6),Estado.ATIVO,22,6,2025,18,50)); // filme A Semente do mal VO 2D, Sala 7, dia 22-6-2025, hora 18h50

        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,12,0)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,15,30)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,18,50)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,22,6,2025,21,30)); // filme A Semente do mal VO 3D, Sala 3, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,12,0)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,15,30)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,18,50)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,23,6,2025,21,30)); // filme A Semente do mal VO 3D, Sala 3, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,12,0)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,15,30)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,18,50)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(15),salas.get(2),Estado.ATIVO,24,6,2025,21,30)); // filme A Semente do mal VO 3D, Sala 3, dia 24-6-2025, hora 21h30
        //------- A SEMENTE DO MAL ----------

        //------- CONCLAVE ----------
        sessoes.add(new Sessao(filmes.get(17),salas.get(0),Estado.ATIVO,25,6,2025,12,0)); // filme Conclave VO 2D, Sala 1, dia 25-6-2025, hora 12h00
        //------- CONCLAVE ----------

        //------- BRANCA DE NEVE ----------
        sessoes.add(new Sessao(filmes.get(18),salas.get(2),Estado.ATIVO,25,6,2025,12,0)); // filme Branca de Neve VP 2D, Sala 3, dia 25-6-2025, hora 12h00
        //------- BRANCA DE NEVE ----------

        //------- JOKER ----------
        sessoes.add(new Sessao(filmes.get(21),salas.get(1),Estado.ATIVO,25,6,2025,12,0)); // filme Joker: Loucura a dois VO 2D, Sala 2, dia 25-6-2025, hora 12h00
        //------- JOKER ----------

        //------- DIVERTIDAMENTE 2 ----------
        sessoes.add(new Sessao(filmes.get(22),salas.get(3),Estado.ATIVO,28,6,2025,12,0)); // filme Divertidamente 2 VP 2D, Sala 4, dia 28-6-2025, hora 12h00
        //------- DIVERTIDAMENTE 2 ----------


        //------- SONIC 2 ----------
        sessoes.add(new Sessao(filmes.get(25),salas.get(5),Estado.ATIVO,28,6,2025,12,0)); // filme Sonic 2 VO 2D, Sala 6, dia 28-6-2025, hora 12h00
        //------- SONIC 2 ----------

        //------- A FAMILIA ADDAMS ----------
        sessoes.add(new Sessao(filmes.get(26),salas.get(2),Estado.ATIVO,28,6,2025,12,0)); // filme A Familia Addams VP 2D, Sala 3, dia 28-6-2025, hora 12h00
        //------- A FAMILIA ADDAMS ----------

        //------- GRU MALDISPOSTO 4 ----------
        sessoes.add(new Sessao(filmes.get(28),salas.get(0),Estado.ATIVO,1,7,2025,12,0)); // filme Gru Maldisposto 4 VP 2D, Sala 1, dia 1-7-2025, hora 12h00
        //------- GRU MALDISPOSTO 4 ----------

        // ------------------------------------ criar sessoes -------------------------------------------
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

    public List<Produto> getProdutos() {
        return produtos;
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

}
