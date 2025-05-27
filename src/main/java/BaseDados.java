import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BaseDados {
    private static BaseDados instance = null;

    private List<Filme> filmes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();
    private List<Sessao> sessoes = new ArrayList<>();

    private BaseDados() {
        // ------------ IDIOMA FILMES --------------
        LinkedList<Idioma> idiomas = new LinkedList<>();
        idiomas.add(Idioma.VP);
        idiomas.add(Idioma.VO);
        // ------------ IDIOMA FILMES --------------

        // -------- FILME VAIANA 2 -----------
        //---- generos -----
        LinkedList<Genero> generosVaiana2 = new LinkedList<>();
        generosVaiana2.add(Genero.ACAO);
        generosVaiana2.add(Genero.ANIMACAO);
        //---- generos -----

        //----- tipo ------
        LinkedList<String> tiposVaiana2 = new LinkedList<>();
        tiposVaiana2.add("5D");
        tiposVaiana2.add("2D");

        //----- tipo ------
        // -------- FILME VAIANA 2 -----------


        // -------- FILME PADDINGTON NA AMAZONIA -----------
        //---- generos -----
        LinkedList<Genero> generosPaddington = new LinkedList<>();
        generosPaddington.add(Genero.ANIMACAO);
        generosPaddington.add(Genero.COMEDIA);
        //---- generos -----

        //----- tipo ------
        LinkedList<String> tiposPaddington = new LinkedList<>();
        tiposPaddington.add("2D");
        //----- tipo ------
        // -------- FILME PADDINGTON NA AMAZONIA -----------


        // -------- FILME AQUAMAN 2 -----------
        //---- generos -----
        LinkedList<Genero> generosAquaman = new LinkedList<>();
        generosAquaman.add(Genero.ACAO);
        generosAquaman.add(Genero.FICAO_CIENTIFICA);
        //---- generos -----
        // -------- FILME AQUAMAN 2 -----------


        // -------- FILME MINECRAFT -----------
        //---- generos -----
        LinkedList<Genero> generosMinecraft = new LinkedList<>();
        generosMinecraft.add(Genero.ACAO);
        generosMinecraft.add(Genero.COMEDIA);
        //---- generos -----
        // -------- FILME MINECRAFT -----------


        // -------- FILME A SEMENTE DO MAL -----------
        //---- generos -----
        LinkedList<Genero> generosASementeDoMal = new LinkedList<>();
        generosASementeDoMal.add(Genero.TERROR);
        //---- generos -----

        //----- tipo ------
        LinkedList<String> tiposASementeDoMal = new LinkedList<>();
        tiposASementeDoMal.add("2D");
        tiposASementeDoMal.add("3D");
        //----- tipo ------
        // -------- FILME A SEMENTE DO MAL -----------


        // -------- FILME CONCLAVE -----------
        //---- generos -----
        LinkedList<Genero> generosConclave = new LinkedList<>();
        generosConclave.add(Genero.ACAO);
        //---- generos -----
        // -------- FILME CONCLAVE -----------


        // -------- FILME BRANCA DE NEVE -----------
        //---- generos -----
        LinkedList<Genero> generosBrancaNeve = new LinkedList<>();
        generosBrancaNeve.add(Genero.ROMANCE);
        //---- generos -----
        // -------- FILME BRANCA DE NEVE -----------


        // -------- FILME DIVERTIDAMENTE -----------
        //---- generos -----
        LinkedList<Genero> generosDivertidamente = new LinkedList<>();
        generosDivertidamente.add(Genero.ANIMACAO);
        //---- generos -----
        // -------- FILME DIVERTIDAMENTE -----------

        // -------- FILME SONIC -----------
        //---- generos -----
        LinkedList<Genero> generosSonic = new LinkedList<>();
        generosSonic.add(Genero.COMEDIA);
        //---- generos -----
        // -------- FILME SONIC -----------

        // criar filmes
        filmes.add(new Filme("Vaiana 2", 100,"812326.jpg", idiomas, "6+", generosVaiana2, tiposVaiana2, Estado.ATIVO, 30000.00f));
        filmes.add(new Filme("Paddington na Amazónia", 106,"images.jpg", idiomas, "6+", generosPaddington, tiposPaddington, Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("Aquaman 2 - O reino perdido", 124,"AQUAMAN_3_3.jpg", idiomas, "16+", generosAquaman, tiposVaiana2, Estado.ATIVO, 40000.00f));
        filmes.add(new Filme("Um filme Minecraft", 100,"PT_MNCRFT_INSTA_TSR2_1936x1936_INTL.jpg", idiomas, "6+", generosMinecraft, tiposPaddington, Estado.ATIVO, 40000.00f));
        filmes.add(new Filme("A semente do mal", 90,"terror.jpg", idiomas, "16+", generosASementeDoMal, tiposASementeDoMal, Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("Conclave", 120,"810654.jpg", idiomas, "12+", generosConclave, tiposPaddington, Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("Branca de Neve", 110,"image_4eb1eebd.jpeg", idiomas, "6+", generosBrancaNeve, tiposPaddington, Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("Joker: Loucura a dois", 140,"naom_660d5818c0da3.jpg", idiomas, "16+", generosConclave, tiposPaddington, Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("Divertidamente 2", 96,"807351.jpg", idiomas, "6+", generosDivertidamente, tiposPaddington, Estado.ATIVO, 35000.00f));
        filmes.add(new Filme("Sonic 2", 115,"Sonic_the_Hedgehog_2_Poster.jpg", idiomas, "6+", generosSonic, tiposPaddington, Estado.ATIVO, 25000.00f));
        filmes.add(new Filme("A Familia Addams", 87,"725902.jpg", idiomas, "6+", generosPaddington, tiposPaddington, Estado.ATIVO, 20000.00f));
        filmes.add(new Filme("Gru Maldisposto 4", 94,"519182_pt.jpg", idiomas, "6+", generosPaddington, tiposPaddington, Estado.ATIVO, 30000.00f));


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
        salas.add(new Sala("Sala 5", 4,10,"30x23m", Acessibilidade.NAO,"5D",Estado.ATIVO));


        //criar sessoes
        //------- VAIANA 2 ----------
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,25,6,2025,12,0)); // filme Vaiana 2, Sala 5, dia 25-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,25,6,2025,15,30)); // filme Vaiana 2, Sala 5, dia 25-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,25,6,2025,18,50)); // filme Vaiana 2, Sala 5, dia 25-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,25,6,2025,21,30)); // filme Vaiana 2, Sala 5, dia 25-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,26,6,2025,12,0)); // filme Vaiana 2, Sala 5, dia 26-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,26,6,2025,15,30)); // filme Vaiana 2, Sala 5, dia 26-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,26,6,2025,18,50)); // filme Vaiana 2, Sala 5, dia 26-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,26,6,2025,21,30)); // filme Vaiana 2, Sala 5, dia 26-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,27,6,2025,12,0)); // filme Vaiana 2, Sala 5, dia 27-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,27,6,2025,15,30)); // filme Vaiana 2, Sala 5, dia 27-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,27,6,2025,18,50)); // filme Vaiana 2, Sala 5, dia 27-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(0),salas.get(3),Estado.ATIVO,27,6,2025,21,30)); // filme Vaiana 2, Sala 5, dia 27-6-2025, hora 21h30
        //------- VAIANA 2 ----------


        //------- PADDINGTON ----------
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,22,6,2025,12,0)); // filme Paddington, Sala 1, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,22,6,2025,15,30)); // filme Paddington, Sala 1, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,22,6,2025,18,50)); // filme Paddington, Sala 1, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,22,6,2025,21,30)); // filme Paddington, Sala 1, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,23,6,2025,12,0)); // filme Paddington, Sala 1, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,23,6,2025,15,30)); // filme Paddington, Sala 1, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,23,6,2025,18,50)); // filme Paddington, Sala 1, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,23,6,2025,21,30)); // filme Paddington, Sala 1, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,24,6,2025,12,0)); // filme Paddington, Sala 1, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,24,6,2025,15,30)); // filme Paddington, Sala 1, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,24,6,2025,18,50)); // filme Paddington, Sala 1, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(1),salas.get(0),Estado.ATIVO,24,6,2025,21,30)); // filme Paddington, Sala 1, dia 24-6-2025, hora 21h30
        //------- PADDINGTON ----------

        //------- AQUAMAN 2 ----------
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,22,6,2025,12,0)); // filme Aquaman 2, Sala 5, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,22,6,2025,15,30)); // filme Aquaman 2, Sala 5, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,22,6,2025,18,50)); // filme Aquaman 2, Sala 5, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,22,6,2025,21,30)); // filme Aquaman 2, Sala 5, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,23,6,2025,12,0)); // filme Aquaman 2, Sala 5, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,23,6,2025,15,30)); // filme Aquaman 2, Sala 5, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,23,6,2025,18,50)); // filme Aquaman 2, Sala 5, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,23,6,2025,21,30)); // filme Aquaman 2, Sala 5, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,24,6,2025,12,0)); // filme Aquaman 2, Sala 5, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,24,6,2025,15,30)); // filme Aquaman 2, Sala 5, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,24,6,2025,18,50)); // filme Aquaman 2, Sala 5, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(2),salas.get(3),Estado.ATIVO,24,6,2025,21,30)); // filme Aquaman 2, Sala 5, dia 24-6-2025, hora 21h30
        //------- AQUAMAN 2 ----------

        //------- Minecraft ----------
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,22,6,2025,12,0)); // filme Minecraft, Sala 2, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,22,6,2025,15,30)); // filme Minecraft, Sala 2, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,22,6,2025,18,50)); // filme Minecraft, Sala 2, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,22,6,2025,21,30)); // filme Minecraft, Sala 2, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,23,6,2025,12,0)); // filme Minecraft, Sala 2, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,23,6,2025,15,30)); // filme Minecraft, Sala 2, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,23,6,2025,18,50)); // filme Minecraft, Sala 2, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,23,6,2025,21,30)); // filme Minecraft, Sala 2, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,24,6,2025,12,0)); // filme Minecraft, Sala 2, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,24,6,2025,15,30)); // filme Minecraft, Sala 2, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,24,6,2025,18,50)); // filme Minecraft, Sala 2, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(3),salas.get(1),Estado.ATIVO,24,6,2025,21,30)); // filme Minecraft, Sala 2, dia 24-6-2025, hora 21h30
        //------- Minecraft ----------

        //------- A SEMENTE DO MAL ----------
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,22,6,2025,12,0)); // filme A Semente do mal, Sala 3, dia 22-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,22,6,2025,15,30)); // filme A Semente do mal, Sala 3, dia 22-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,22,6,2025,18,50)); // filme A Semente do mal, Sala 3, dia 22-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,22,6,2025,21,30)); // filme A Semente do mal, Sala 3, dia 22-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,23,6,2025,12,0)); // filme A Semente do mal, Sala 3, dia 23-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,23,6,2025,15,30)); // filme A Semente do mal, Sala 3, dia 23-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,23,6,2025,18,50)); // filme A Semente do mal, Sala 3, dia 23-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,23,6,2025,21,30)); // filme A Semente do mal, Sala 3, dia 23-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,24,6,2025,12,0)); // filme A Semente do mal, Sala 3, dia 24-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,24,6,2025,15,30)); // filme A Semente do mal, Sala 3, dia 24-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,24,6,2025,18,50)); // filme A Semente do mal, Sala 3, dia 24-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(4),salas.get(2),Estado.ATIVO,24,6,2025,21,30)); // filme A Semente do mal, Sala 3, dia 24-6-2025, hora 21h30
        //------- A SEMENTE DO MAL ----------

        //------- CONCLAVE ----------
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,25,6,2025,12,0)); // filme Conclave, Sala 1, dia 25-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,25,6,2025,15,30)); // filme Conclave, Sala 1, dia 25-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,25,6,2025,18,50)); // filme Conclave, Sala 1, dia 25-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,25,6,2025,21,30)); // filme Conclave, Sala 1, dia 25-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,26,6,2025,12,0)); // filme Conclave, Sala 1, dia 26-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,26,6,2025,15,30)); // filme Conclave, Sala 1, dia 26-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,26,6,2025,18,50)); // filme Conclave, Sala 1, dia 26-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,26,6,2025,21,30)); // filme Conclave, Sala 1, dia 26-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,27,6,2025,12,0)); // filme Conclave, Sala 1, dia 27-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,27,6,2025,15,30)); // filme Conclave, Sala 1, dia 27-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,27,6,2025,18,50)); // filme Conclave, Sala 1, dia 27-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(5),salas.get(0),Estado.ATIVO,27,6,2025,21,30)); // filme Conclave, Sala 1, dia 27-6-2025, hora 21h30
        //------- CONCLAVE ----------

        //------- BRANCA DE NEVE ----------
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,25,6,2025,12,0)); // filme Branca de Neve, Sala 3, dia 25-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,25,6,2025,15,30)); // filme Branca de Neve, Sala 3, dia 25-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,25,6,2025,18,50)); // filme Branca de Neve, Sala 3, dia 25-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,25,6,2025,21,30)); // filme Branca de Neve, Sala 3, dia 25-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,26,6,2025,12,0)); // filme Branca de Neve, Sala 3, dia 26-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,26,6,2025,15,30)); // filme Branca de Neve, Sala 3, dia 26-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,26,6,2025,18,50)); // filme Branca de Neve, Sala 3, dia 26-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,26,6,2025,21,30)); // filme Branca de Neve, Sala 3, dia 26-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,27,6,2025,12,0)); // filme Branca de Neve, Sala 3, dia 27-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,27,6,2025,15,30)); // filme Branca de Neve, Sala 3, dia 27-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,27,6,2025,18,50)); // filme Branca de Neve, Sala 3, dia 27-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(6),salas.get(2),Estado.ATIVO,27,6,2025,21,30)); // filme Branca de Neve, Sala 3, dia 27-6-2025, hora 21h30
        //------- BRANCA DE NEVE ----------

        //------- JOKER ----------
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,25,6,2025,12,0)); // filme Joker: Loucura a dois, Sala 2, dia 25-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,25,6,2025,15,30)); // filme Joker: Loucura a dois, Sala 2, dia 25-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,25,6,2025,18,50)); // filme Joker: Loucura a dois, Sala 2, dia 25-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,25,6,2025,21,30)); // filme Joker: Loucura a dois, Sala 2, dia 25-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,26,6,2025,12,0)); // filme Joker: Loucura a dois, Sala 2, dia 26-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,26,6,2025,15,30)); // filme Joker: Loucura a dois, Sala 2, dia 26-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,26,6,2025,18,50)); // filme Joker: Loucura a dois, Sala 2, dia 26-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,26,6,2025,21,30)); // filme Joker: Loucura a dois, Sala 2, dia 26-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,27,6,2025,12,0)); // filme Joker: Loucura a dois, Sala 2, dia 27-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,27,6,2025,15,30)); // filme Joker: Loucura a dois, Sala 2, dia 27-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,27,6,2025,18,50)); // filme Joker: Loucura a dois, Sala 2, dia 27-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(7),salas.get(1),Estado.ATIVO,27,6,2025,21,30)); // filme Joker: Loucura a dois, Sala 2, dia 27-6-2025, hora 21h30
        //------- JOKER ----------

        //------- DIVERTIDAMENTE 2 ----------
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,28,6,2025,12,0)); // filme Divertidamente 2, Sala 2, dia 28-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,28,6,2025,15,30)); // filme Divertidamente 2, Sala 2, dia 28-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,28,6,2025,18,50)); // filme Divertidamente 2, Sala 2, dia 28-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,28,6,2025,21,30)); // filme Divertidamente 2, Sala 2, dia 28-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,29,6,2025,12,0)); // filme Divertidamente 2, Sala 2, dia 29-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,29,6,2025,15,30)); // filme Divertidamente 2, Sala 2, dia 29-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,29,6,2025,18,50)); // filme Divertidamente 2, Sala 2, dia 29-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,29,6,2025,21,30)); // filme Divertidamente 2, Sala 2, dia 29-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,30,6,2025,12,0)); // filme Divertidamente 2, Sala 2, dia 30-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,30,6,2025,15,30)); // filme Divertidamente 2, Sala 2, dia 30-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,30,6,2025,18,50)); // filme Divertidamente 2, Sala 2, dia 30-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(8),salas.get(1),Estado.ATIVO,30,6,2025,21,30)); // filme Divertidamente 2, Sala 2, dia 30-6-2025, hora 21h30
        //------- DIVERTIDAMENTE 2 ----------


        //------- SONIC 2 ----------
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,28,6,2025,12,0)); // filme Sonic 2, Sala 1, dia 28-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,28,6,2025,15,30)); // filme Sonic 2, Sala 1, dia 28-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,28,6,2025,18,50)); // filme Sonic 2, Sala 1, dia 28-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,28,6,2025,21,30)); // filme Sonic 2, Sala 1, dia 28-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,29,6,2025,12,0)); // filme Sonic 2, Sala 1, dia 29-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,29,6,2025,15,30)); // filme Sonic 2, Sala 1, dia 29-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,29,6,2025,18,50)); // filme Sonic 2, Sala 1, dia 29-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,29,6,2025,21,30)); // filme Sonic 2, Sala 1, dia 29-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,30,6,2025,12,0)); // filme Sonic 2, Sala 1, dia 30-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,30,6,2025,15,30)); // filme Sonic 2, Sala 1, dia 30-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,30,6,2025,18,50)); // filme Sonic 2, Sala 1, dia 30-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(9),salas.get(0),Estado.ATIVO,30,6,2025,21,30)); // filme Sonic 2, Sala 1, dia 30-6-2025, hora 21h30
        //------- SONIC 2 ----------

        //------- A FAMILIA ADDAMS ----------
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,28,6,2025,12,0)); // filme A Familia Addams, Sala 3, dia 28-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,28,6,2025,15,30)); // filme A Familia Addams, Sala 3, dia 28-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,28,6,2025,18,50)); // filme A Familia Addams, Sala 3, dia 28-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,28,6,2025,21,30)); // filme A Familia Addams, Sala 3, dia 28-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,29,6,2025,12,0)); // filme A Familia Addams, Sala 3, dia 29-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,29,6,2025,15,30)); // filme A Familia Addams, Sala 3, dia 29-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,29,6,2025,18,50)); // filme A Familia Addams, Sala 3, dia 29-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,29,6,2025,21,30)); // filme A Familia Addams, Sala 3, dia 29-6-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,30,6,2025,12,0)); // filme A Familia Addams, Sala 3, dia 30-6-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,30,6,2025,15,30)); // filme A Familia Addams, Sala 3, dia 30-6-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,30,6,2025,18,50)); // filme A Familia Addams, Sala 3, dia 30-6-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(10),salas.get(2),Estado.ATIVO,30,6,2025,21,30)); // filme A Familia Addams, Sala 3, dia 30-6-2025, hora 21h30
        //------- A FAMILIA ADDAMS ----------

        //------- GRU MALDISPOSTO 4 ----------
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,1,7,2025,12,0)); // filme Gru Maldisposto 4, Sala 1, dia 1-7-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,1,7,2025,15,30)); // filme Gru Maldisposto 4, Sala 1, dia 1-7-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,1,7,2025,18,50)); // filme Gru Maldisposto 4, Sala 1, dia 1-7-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,1,7,2025,21,30)); // filme Gru Maldisposto 4, Sala 1, dia 1-7-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,2,7,2025,12,0)); // filme Gru Maldisposto 4, Sala 1, dia 2-7-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,2,7,2025,15,30)); // filme Gru Maldisposto 4, Sala 1, dia 2-7-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,2,7,2025,18,50)); // filme Gru Maldisposto 4, Sala 1, dia 2-7-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,2,7,2025,21,30)); // filme Gru Maldisposto 4, Sala 1, dia 2-7-2025, hora 21h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,3,7,2025,12,0)); // filme Gru Maldisposto 4, Sala 1, dia 3-7-2025, hora 12h00
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,3,7,2025,15,30)); // filme Gru Maldisposto 4, Sala 1, dia 3-7-2025, hora 15h30
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,3,7,2025,18,50)); // filme Gru Maldisposto 4, Sala 1, dia 3-7-2025, hora 18h50
        sessoes.add(new Sessao(filmes.get(11),salas.get(0),Estado.ATIVO,3,7,2025,21,30)); // filme Gru Maldisposto 4, Sala 1, dia 3-7-2025, hora 21h30
        //------- GRU MALDISPOSTO 4 ----------

    }

    public static BaseDados getInstance() {
        if (instance == null) {
            instance = new BaseDados();
            carregarDados();
        }
        return instance;
    }

    private static void carregarDados() {}

    private static void gravarDados() {}

    public List<Filme> getFilmes() {
        return filmes;
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
}
