import java.util.Date;

public class SessaoController {
    public static boolean atualizarSessao(String nome, String filme, String sala, Date dataHora) {
        // Aqui você atualizaria os dados na sua lista/banco de dados/etc.
        System.out.println("Sessão atualizada:");
        System.out.println("Nome: " + nome);
        System.out.println("Filme: " + filme);
        System.out.println("Sala: " + sala);
        System.out.println("Data e Hora: " + dataHora);
        return true;
    }
}
